/**
 * Copyright (c) 2025, WSO2 LLC. (https://www.wso2.com).
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

package org.wso2.financial.services.accelerator.test.consent.management.PaymentsTests

import io.restassured.http.ContentType
import io.restassured.response.Response
import junit.framework.TestSuite
import org.testng.Assert
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.utility.FSRestAsRequestBuilder
import org.wso2.financial.services.accelerator.test.framework.utility.PaymentDataProviders
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil

import java.nio.charset.Charset
import java.time.Instant

/**
 * Payments Initiation Request Header Validation Tests.
 */
class InitiationRequestHeaderValidationTests extends FSConnectorTest{

    public List<ConnectorTestConstants.ApiScope> consentScopes = [
            ConnectorTestConstants.ApiScope.PAYMENTS
    ]

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401076_Validate_Payments Initiation_x-idempotency-key_Header_Exceed_max_length"(Map<String, String> maps) {

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, ConnectorTestConstants.X_IDEMPOTENCY_KEY_MAX_LENGTH)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE)
                .contains(ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID))
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("String \"x-idempotency-key_1234567890-x-idempotency-key_1234567890-x-idempotency-key" +
                        "_1234567890\" is too long (length: 86, maximum allowed: 40)"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401079_Validate_Payments_Initiation_With_Invalid_Authorisation_Header"(Map<String, String> maps){

        //initiation
        def authHeader = getBasicAuthHeader(configurationService.getUserPSUName(),
                configurationService.getUserPSUPWD())

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, authHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .baseUri(configuration.getISServerUrl())
                .body(initiationPayload)
                .post(consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE)
                .contains(ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID))
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Invalid Credentials. Make sure you have provided the correct security credentials"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401080_Validate_Payments_Initiation_Without_Authorisation_Header"(Map<String, String> maps){

        //initiation
        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .baseUri(configuration.getISServerUrl())
                .body(initiationPayload)
                .post(consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE)
                .contains(ConnectorTestConstants.OBIE_ERROR_HEADER_MISSING))
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Invalid Credentials. Make sure your API invocation call has a header"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401081_Validate_Payments_Initiation_Without_x_jws_signature"(Map<String, String> maps) {

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_SIGNATURE_MISSING)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains(ConnectorTestConstants.X_JWS_SIGNATURE_MISSING))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401084_Validate_Payments_Initiation_With_Invalid_Content-type"(Map<String, String> maps) {

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.XML)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .baseUri(configuration.getISServerUrl())
                .body(initiationPayload)
                .post(consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_415)
        Assert.assertEquals(ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID,
                TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE))
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Request Content-Type header does not match any allowed types"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InitiationForCheckIdempotency", dataProviderClass = PaymentDataProviders.class)
    void "TC0401077_Validate_Payments Initiation_Request_with_duplicate_x-idempotency-key"(Map<String, String> maps) {

        String idempotencyKey = TestUtil.idempotency

        //initiation request 1
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //initiation request 2
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get("payload_changed")))
                .body(maps.get("payload_changed"))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE),
                "Payloads are not similar. Hence this is not a valid idempotent request")
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401099_Validate_Payments_Initiation_Request_with_same_payload_same_x-idempotency-key"(Map<String, String> maps) {

        String idempotencyKey = TestUtil.idempotency

        //initiation request 1
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //initiation request 2
        def consentResponse2 = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)

        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID),
                TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.DATA_STATUS))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401090_Validate_Payments_Initiation_Request_with_an_access_token_of_authorization_code_type"(Map<String, String> maps) {

        paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD), maps.get(ConnectorTestConstants.PATH))
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        //initiation
        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "Bearer ${userAccessToken}")
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .baseUri(configuration.getISServerUrl())
                .body(initiationPayload)
                .post(consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        Assert.assertEquals(ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID,
                TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE))
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Incorrect Access Token Type provided"))
    }

//TODO: Commented out till issue get fixed Tracked
// via https://github.com/wso2-enterprise/ob-compliance-toolkit-uk/issues/32
//    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
//            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401114_Validate_Payments_Initiation_With_Headers with Capital Case"(Map<String, String> paymentTypesMap) {

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def paymentMap = [:]

        //initiation
        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY_CAPS, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .accept(ConnectorTestConstants.CONTENT_TYPE_JSON)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE_CAPS, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        paymentTypesMap.get(ConnectorTestConstants.PAYLOAD)))
                .header(ConnectorTestConstants.CHARSET, ConnectorTestConstants.CHARSET_TYPE)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID_CAPS, ConnectorTestConstants.INTERACTION_ID)
                .header(ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME_CAPS,
                        ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME_VALUE)
                .body(paymentTypesMap.get(ConnectorTestConstants.PAYLOAD))
                .post(paymentTypesMap.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }


    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401083_Validate_Payments_Initiation_with_x-fapi-financial-id"(Map<String, String> paymentTypesMap) {

        def paymentMap = [:]

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        paymentTypesMap.get(ConnectorTestConstants.PAYLOAD)))
                .body(paymentTypesMap.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(paymentTypesMap.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401093_Validate_Payments_Initiation_With_x-fapi-customer-last-logged-time"(Map<String,
            String> paymentTypesMap) {

        def paymentMap = [:]

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME, ConnectorTestConstants
                        .X_FAPI_CUSTOMER_LAST_LOGGED_TIME_VALUE)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        paymentTypesMap.get(ConnectorTestConstants.PAYLOAD)))
                .body(paymentTypesMap.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(paymentTypesMap.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }


    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401097_Validate_Payments_Initiation_With_x-fapi-auth-date"(Map<String, String> paymentTypesMap) {

        def paymentMap = [:]

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        paymentTypesMap.get(ConnectorTestConstants.PAYLOAD)))
                .body(paymentTypesMap.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(paymentTypesMap.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401115_Validate_Payments_Initiation_Request_with_same_payload_different_x-idempotency-key"(Map<String, String> maps) {

        String idempotencyKey = TestUtil.getIdempotency()


        //initiation request 1
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)

        String idempotencyKey2 = TestUtil.getIdempotency()

        //initiation request 2
        def consentResponse2 = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey2)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertNotEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID),
                TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.DATA_STATUS))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "Validate_Payments Initiation request without x-idempotency-key"(Map<String, String> maps) {

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE)
                .contains(ConnectorTestConstants.OBIE_ERROR_HEADER_MISSING))
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_MESSAGE).contains(
                "Schema validation failed in the Request: Header parameter 'x-idempotency-key' is required on path"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "US-229_Payment Initiation with x-jws-signature header having unsupported alg"(Map<String, String> maps) {

        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(
                        TestUtil.getRequestHeader(ConnectorTestConstants.RS256),
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_SIGNATURE_MALFORMED)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("The RS256 algorithm is not supported"))
    }

    // TODO: Uncomment after fixing: https://github.com/wso2-enterprise/financial-open-banking/issues/8481
    // @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
    //        dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "US-230_Payment Initiation with x-jws-signature header having an invalid kid"(Map<String, String> maps) {

        String jwsHeader = TestUtil.getRequestHeader(configuration.commonSigningAlgorithm, "1234")

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(jwsHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(
                        TestUtil.getRequestHeader(ConnectorTestConstants.RS256),
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_SIGNATURE_INVALID_CLAIM)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("kid does not resolve to a valid signing certificate"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "US-231_Payment Initiation with x-jws-signature header having invalid iss"(Map<String, String> maps) {

        String jwsHeader = TestUtil.getRequestHeader(configuration.commonSigningAlgorithm,
                configuration.getAppKeyStoreSigningKid(), "CN=0123456789HQQrZAAX")

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(jwsHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_SIGNATURE_INVALID_CLAIM)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Error due to iss claim validation failed"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "US-235_Payment Initiation with x-jws-signature header having invalid optional claims typ"(Map<String, String> maps) {

        String jwsHeader = TestUtil.getRequestHeader(configuration.commonSigningAlgorithm,
                configuration.getAppKeyStoreSigningKid(), TestUtil.getApplicationCertificateSubjectDn(),
                ConnectorTestConstants.JWS_TAN, Instant.now().getEpochSecond().minus(2).toString(), "JSON")

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(jwsHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_SIGNATURE_INVALID_CLAIM)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Error occurred due to invalid type"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "US-236_Payment Initiation with x-jws-signature header having invalid optional claims cty"(Map<String, String> maps) {

        String jwsHeader = TestUtil.getRequestHeader(configuration.commonSigningAlgorithm,
                configuration.getAppKeyStoreSigningKid(), TestUtil.getApplicationCertificateSubjectDn(),
                ConnectorTestConstants.JWS_TAN, Instant.now().getEpochSecond().minus(2).toString(),
                ConnectorTestConstants.TYP_JOSE, "application/jwt")

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(jwsHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_SIGNATURE_INVALID_CLAIM)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Error occurred due to invalid cty claim"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "US-287_Payment Initiation with x-jws-signature header having future date for iat"(Map<String, String> maps) {

        String jwsHeader = TestUtil.getRequestHeader(configuration.commonSigningAlgorithm,
                configuration.getAppKeyStoreSigningKid(), TestUtil.getApplicationCertificateSubjectDn(),
                ConnectorTestConstants.JWS_TAN, Instant.now().getEpochSecond().plus(2).toString())

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(jwsHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_SIGNATURE_INVALID_CLAIM)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("iat claim cannot be a future date"))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentDataProviders.class)
    void "US-289_Payment Initiation with x-jws-signature header having present date and time for iat"(Map<String, String> maps) {

        String jwsHeader = TestUtil.getRequestHeader(configuration.commonSigningAlgorithm,
                configuration.getAppKeyStoreSigningKid(), TestUtil.getApplicationCertificateSubjectDn(),
                ConnectorTestConstants.JWS_TAN, Instant.now().getEpochSecond().toString())

        //initiation
        consentResponse = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE, TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(jwsHeader,
                        maps.get(ConnectorTestConstants.PAYLOAD)))
                .body(maps.get(ConnectorTestConstants.PAYLOAD))
                .baseUri(configuration.getISServerUrl())
                .post(maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(),ConnectorTestConstants.STATUS_CODE_201)
    }
}
