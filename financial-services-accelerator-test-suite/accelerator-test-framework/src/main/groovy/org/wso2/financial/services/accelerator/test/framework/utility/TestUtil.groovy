/**
 * Copyright (c) 2023, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.financial.services.accelerator.test.framework.utility

import com.fasterxml.uuid.Generators
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.util.StandardCharset
import org.jose4j.jws.JsonWebSignature
import org.jose4j.lang.JoseException
import org.wso2.bfsi.test.framework.CommonTest
import org.wso2.bfsi.test.framework.exception.TestFrameworkException
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.openbanking.test.framework.utility.OBTestUtil
import org.wso2.openbanking.test.framework.utility.SSLSocketFactoryCreator
import org.apache.http.conn.ssl.SSLSocketFactory
import org.wso2.financial.services.accelerator.test.framework.configuration.ConfigurationService

import java.nio.charset.Charset
import java.security.Key
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.UnrecoverableEntryException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.security.interfaces.RSAPrivateKey
import java.text.ParseException
import java.time.Instant

/**
 * Accelerator layer class to contain utilities.
 */
class TestUtil extends OBTestUtil{

    static ConfigurationService configurationService = new ConfigurationService()

    /**
     * Get Basic Auth Header.
     * @param client
     * @param password
     * @return encoded basic auth header
     */
    static String getBasicAuthHeader(String client, String password) {

        def authToken = "${client}:${password}"
        return Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))
    }

    /**
     * Generate random UUID.
     *
     * @return generated random UUID
     */
    static String generateUUID(){
        UUID uuid = Generators.timeBasedGenerator().generate()
        return uuid.toString()
    }

    /**
     * Get the error description when user denies a Consent
     * @param url
     */
    static String getErrorDescriptionFromUrlWhenDenied(String url) {
        try {
            return url.split("error_description=")[1].split("&")[0]
        } catch (Exception e) {
            log.error("User+denied+the+consent", e)
        }
        return null
    }

    /**
     * Get Public Key from Transport Keystore.
     *
     * @return public key
     * @throws org.wso2.bfsi.test.framework.exception.TestFrameworkException exception
     */
    public static String getPublicKeyFromTransportKeyStore() throws TestFrameworkException {
        ConfigurationService configurationService = new ConfigurationService();

        try (InputStream inputStream = new FileInputStream(
                configurationService.getAppTransportKeyStoreLocation())) {
            KeyStore keyStore = KeyStore.getInstance("JKS");

            String keystorePassword = configurationService.getAppTransportKeyStorePWD();
            keyStore.load(inputStream, keystorePassword.toCharArray());

            String keystoreAlias = configurationService.getTransportKeystoreAlias();
            // Get certificate of public key
            Certificate cert = keyStore.getCertificate(keystoreAlias);

            // Get public key
            return Base64.getEncoder().encodeToString(cert.getEncoded());

        } catch (IOException e) {
            throw new TestFrameworkException("Failed to load Keystore file from the location", e);
        } catch (CertificateException e) {
            throw new TestFrameworkException("Failed to load Certificate from the keystore", e);
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            throw new TestFrameworkException("Error occurred while retrieving values from KeyStore ", e);
        }
    }

    /**
     * Extract Error Description From URL
     *
     * @param url
     * @return Error Description
     */
    static String getOAuthErrorCodeFromUrl(String url) {
        return url.split("oauth2_error.do?")[1].split("&")[0].replaceAll("\\+", " ");
    }

    /**
     * Extract Error Message From URL
     *
     * @param url
     * @return Error Description
     */
    static String getOAuthErrorMsgFromUrl(String url) {

        return url.split("oauth2_error.do?")[1].split("&")[1].replaceAll("\\+", " ");
    }

    /**
     * Get request with request context.
     * @param keystoreLocation keystore file path.
     * @param keystorePassword keystore password.
     * @return sslSocketFactory.
     */
    public static SSLSocketFactory getSslSocketFactory(String keystoreLocation, String keystorePassword) {

        if (configurationService.appTransportMLTSEnable) {
            try {
                SSLSocketFactoryCreator sslSocketFactoryCreator = new SSLSocketFactoryCreator();
                sslSocketFactory = sslSocketFactoryCreator.creatWithCustomKeystore(keystoreLocation, keystorePassword, 0);

                // Skip hostname verification.
                sslSocketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory
                        .ALLOW_ALL_HOSTNAME_VERIFIER);
            } catch (TestFrameworkException e) {
                log.error("Unable to create the SSL socket factory", e);
            }
        }
        return sslSocketFactory;
    }

    /**
     * Extract Authorisation code from redirect URL of hybriod flow response.
     *
     * @param codeUrl redirection url.
     * @return authorisation code.
     */
    static String getHybridCodeFromUrl(String codeUrl) {
        return codeUrl.split("#")[1].split("&")[1].split("code")[1].substring(1)
    }

    /**
     * Method for reading the payment file content
     *
     * @param file - the payment file
     * @return the content of the payment file with formatting
     */
    static String getFileContent(File file) {

        if (file == null) return

        String s = ""

        try {
            FileReader reader = new FileReader(file)
            BufferedReader br = new BufferedReader(reader)

            String line
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("<?xml")) {
                    //Added this condition to avoid hash mismatching during file payment file upload step
                    if (line != ("</Document>")) {
                        s = s + line + "\n"
                    } else {
                        s = s + line
                    }
                }
            }
            return s.stripIndent()
        } catch (IOException e) {
            throw new IOException("Error getting file content from file.", e)
        }
    }

    /**
     * Get a file from the resources folder
     * @param fileName file name with preceding folder names
     * @return file
     */
    static File getFileFromResources(String fileName) {

        URL resource = new File(ConnectorTestConstants.FILE_RESOURCE_PATH + fileName).toURI().toURL()
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!")
        } else {
            return new File(resource.getFile())
        }
    }

    static int getIdempotency() {
        Random random = new Random()
        int min = 1
        int max = 1000000
        def idempotency = random.nextInt(max-min) + min

        return idempotency
    }

//    /**
//     * Generate X-JWS Signature.
//     *
//     * @param header      Headers from the request
//     * @param requestBody Request payload
//     * @return x-jws-signature
//     */
//    public static String generateXjwsSignature(String header, String requestBody) {
//
//        char[] keyStorePassword = configurationService.getApplicationKeystorePassword().toCharArray();
//        String keyStoreName = AppConfigReader.getApplicationKeystoreAlias();
//
//        try {
//
//            KeyStore keyStore = TestUtil.getApplicationKeyStore();
//            Key key = keyStore.getKey(keyStoreName, keyStorePassword);
//
//            if (key instanceof RSAPrivateKey) {
//
//                JWSHeader jwsHeader = JWSHeader.parse(header);
//                Object b64ValueObject = jwsHeader.getCustomParam(B64_CLAIM_KEY);
//                boolean b64Value = b64ValueObject != null ? ((Boolean) b64ValueObject) : true;
//
//                // Create a new JsonWebSignature
//                JsonWebSignature jws = new JsonWebSignature();
//
//                // Set the payload, or signed content, on the JWS object
//                jws.setPayload(requestBody);
//
//                // Set the signature algorithm on the JWS that will integrity protect the payload
//                jws.setAlgorithmHeaderValue(String.valueOf(jwsHeader.getAlgorithm()));
//
//                if (jwsHeader.getContentType() != null) {
//                    jws.setContentTypeHeaderValue(jwsHeader.getContentType());
//                }
//
//                if (jwsHeader.getType() != null) {
//                    jws.setHeader("typ", String.valueOf(jwsHeader.getType()));
//                }
//
//                // Setting headers
//                jws.setKeyIdHeaderValue(jwsHeader.getKeyID());
//                jws.setCriticalHeaderNames(jwsHeader.getCriticalParams().toArray(new String[0]));
//
//                if (b64ValueObject != null) {
//                    jws.getHeaders().setObjectHeaderValue(B64_CLAIM_KEY, b64Value);
//                }
//
//                for (Map.Entry<String, Object> entry : jwsHeader.getCustomParams().entrySet()) {
//                    jws.getHeaders().setObjectHeaderValue(entry.getKey(), entry.getValue());
//                }
//
//                // Set the signing key on the JWS
//                jws.setKey(key);
//
//                // Sign the JWS and produce the detached JWS representation, which
//                // is a string consisting of two dots ('.') separated base64url-encoded
//                // parts in the form Header..Signature
//                return jws.getDetachedContentCompactSerialization();
//            }
//
//        } catch (ParseException | KeyStoreException e) {
//            log.error("Error occurred while reading the KeyStore file", e);
//        } catch (NoSuchAlgorithmException | JoseException e) {
//            log.error("Error occurred while signing", e);
//        } catch (UnrecoverableEntryException e) {
//            log.error("Error occurred while retrieving the cert key");
//        } catch (TestFrameworkException e) {
//            log.error("Error occurred while reading the certificate thumb print", e);
//        }
//
//        return " ";
//    }

    /**
     * Returns the subject DN for the application certificate.
     *
     * @return subject DN
     */
    public static String getApplicationCertificateSubjectDn() {

        X509Certificate certificate = (X509Certificate) getCertificateFromKeyStore();

        if (certificate != null && certificate.getSubjectDN() != null) {
            return certificate.getSubjectDN().getName();
        }

        return null;
    }

    static String getRequestHeader(String alg = configurationService.commonSigningAlgorithm,
                                   String kid = configurationService.getAppKeyStoreSigningKid(),
                                   String iss = TestUtil.getApplicationCertificateSubjectDn(),
                                   String tan = "openbanking.org.uk",
                                   String iat = Instant.now().getEpochSecond().minus(2),
                                   String typ = "JOSE",
                                   String cty = "application/json") {

        def algorithm = alg
        def clientKeyId = kid
        def clientSubjectDN = iss
        def trustAnchorDns = tan
        def issuedAt = iat
        def type = typ
        def contentType = cty

        def REQUEST_HEADER

        REQUEST_HEADER = "{\n" +
                    "\"alg\": \"${algorithm}\",\n" +
                    "\"kid\": \"${clientKeyId}\",\n" +
                    "\"typ\": \"${type}\",\n" +
                    "\"cty\": \"${contentType}\",\n" +
                    "\"http://openbanking.org.uk/iat\": ${issuedAt},\n" +
                    "\"http://openbanking.org.uk/iss\": \"${clientSubjectDN}\", \n" +
                    "\"http://openbanking.org.uk/tan\": \"${trustAnchorDns}\",\n" +
                    "\"crit\": [ \"http://openbanking.org.uk/iat\",\n" +
                    "\"http://openbanking.org.uk/iss\", \"http://openbanking.org.uk/tan\"] \n" +
                    "}"

        return REQUEST_HEADER

    }

    /**
     * Method for generating the file hash of the payment file
     *
     * @param file - the payment file
     * @return the SHA-256 hash of base64 encoded payment file
     */
    static generateFileHash(String file) {
        String fileHash = new String(Base64.getEncoder().encode(MessageDigest.getInstance("SHA-256")
                .digest(getFileContent(getFileFromResources(file)).getBytes(StandardCharset.UTF_8))),"UTF-8")
        return fileHash
    }
}
