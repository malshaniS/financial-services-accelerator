/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
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

package org.wso2.financial.services.accelerator.test.consent.management.ConsentInitiationTest

import org.wso2.openbanking.test.framework.request_builder.SignedObject
import io.restassured.RestAssured
import io.restassured.config.EncoderConfig
import io.restassured.http.ContentType
import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.constant.AccountsRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.utility.FSRestAsRequestBuilder
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil

/**
 * Consent Initiation Test class
 */
class ConsentInitiationTest extends FSConnectorTest {

    @BeforeClass
    void init() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload
    }

    @Test
    void "OB-1945_Verify Creation of a consent with valid inputs"() {

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse,
                ConnectorTestConstants.DATA_CONSENT_ID).toString())
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse,
                ConnectorTestConstants.DATA_STATUS).toString(), ConnectorTestConstants.AWAITING_AUTHORISATION)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse,
                ConnectorTestConstants.DATA_STATUS_UPDATE_DATE_TIME))
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse,
                ConnectorTestConstants.DATA_CREATION_DATE_TIME_VALUE))
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_PERMISSIONS_VALUE))
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse,
                ConnectorTestConstants.DATA_EXPIRATION_DATE_TIME_VALUE))
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse,
                ConnectorTestConstants.DATA_TRANSACTION_FROM_DATE_TIME_VALUE))
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse,
                ConnectorTestConstants.DATA_TRANSACTION_TO_DATE_TIME_VALUE))
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.RISK))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.SELF_LINK),
                configuration.getISServerUrl() + ConnectorTestConstants.ACCOUNT_CONSENT_PATH + "/" + consentId)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.META_TOTAL_PAGES))
    }

    @Test
    void "OB-1952_Verify Creation of a consent with incorrect request payload"() {

        doDefaultInitiationWithIncorrectPayload()
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "OB-1955_Verify Creation of a consent without ReadAccountsDetail permission"() {

        consentResponse = doDefaultInitiation(AccountsRequestPayloads.initiationPayloadWithoutReadAccountsDetail)
        consentId = TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID).toString()
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Authorize consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        def host = configuration.getISServerUrl()
        SignedObject signedObject = new SignedObject()
        signedObject.setSigningAlgorithm(configuration.getCommonSigningAlgorithm())

        //Consent Validate Request
        consentValidateResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ConnectorTestConstants.CONTENT_TYPE_JWT)
                .body(signedObject.getSignedRequest(AccountsRequestPayloads.buildValidationAccountsPayload(GenerateBasicHeader(),
                        userId, consentId)))
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, consentRequestBuilder.GenerateBasicHeader())
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .accept(ConnectorTestConstants.CONTENT_TYPE_JSON)
                .config(RestAssured.config()
                        .sslConfig(RestAssured.config().getSSLConfig().sslSocketFactory(
                                TestUtil.getSslSocketFactory()))
                        .encoderConfig(new EncoderConfig().encodeContentTypeAs(
                                ConnectorTestConstants.CONTENT_TYPE_JWT, ContentType.TEXT)
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .baseUri(configuration.getISServerUrl())
                .post(ConnectorTestConstants.ACCOUNT_VALIDATE_PATH)

        Assert.assertEquals(consentValidateResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertTrue(consentValidateResponse.jsonPath().get("isValid").toString()
                .contains(ConnectorTestConstants.IS_VALID_FALSE))
        Assert.assertTrue(consentValidateResponse.jsonPath().get("errorMessage").toString()
                .contains(ConnectorTestConstants.PERMISSION_MISMATCH_ERROR))
    }

    @Test
    void "OB-1956_Verify Creation of a consent without ReadTransactionsDetail permission"() {

        consentResponse = doDefaultInitiation(AccountsRequestPayloads.initiationPayloadWithoutReadTransactionsDetail)
        consentId = TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID).toString()
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Authorize consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        def host = configuration.getISServerUrl()
        SignedObject signedObject = new SignedObject()
        signedObject.setSigningAlgorithm(configuration.getCommonSigningAlgorithm())

        //Consent Validate Request
        consentValidateResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ConnectorTestConstants.CONTENT_TYPE_JWT)
                .body(signedObject.getSignedRequest(AccountsRequestPayloads.buildValidationTransactionPayload(userId, consentId, host)))
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, consentRequestBuilder.GenerateBasicHeader())
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .accept(ConnectorTestConstants.CONTENT_TYPE_JSON)
                .config(RestAssured.config()
                        .sslConfig(RestAssured.config().getSSLConfig().sslSocketFactory(
                                TestUtil.getSslSocketFactory()))
                        .encoderConfig(new EncoderConfig().encodeContentTypeAs(
                                ConnectorTestConstants.CONTENT_TYPE_JWT, ContentType.TEXT)
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .baseUri(configuration.getISServerUrl())
                .post(ConnectorTestConstants.ACCOUNT_VALIDATE_PATH)

        Assert.assertEquals(consentValidateResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertTrue(consentValidateResponse.jsonPath().get("isValid").toString()
                .contains(ConnectorTestConstants.IS_VALID_FALSE))
        Assert.assertTrue(consentValidateResponse.jsonPath().get("errorMessage").toString()
                .contains(ConnectorTestConstants.PERMISSION_MISMATCH_ERROR))
    }

    @Test
    void "OB-1957_Verify Creation of a consent without ReadBalances permission"() {

        consentResponse = doDefaultInitiation(AccountsRequestPayloads.initiationPayloadWithoutReadBalances)
        consentId = TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID).toString()
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Authorize consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        def host = configuration.getISServerUrl()
        SignedObject signedObject = new SignedObject()
        signedObject.setSigningAlgorithm(configuration.getCommonSigningAlgorithm())

        //Consent Validate Request
        consentValidateResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ConnectorTestConstants.CONTENT_TYPE_JWT)
                .body(signedObject.getSignedRequest(AccountsRequestPayloads.buildValidationBalancePayload(userId, consentId, host)))
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, consentRequestBuilder.GenerateBasicHeader())
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .accept(ConnectorTestConstants.CONTENT_TYPE_JSON)
                .config(RestAssured.config()
                        .sslConfig(RestAssured.config().getSSLConfig().sslSocketFactory(
                                TestUtil.getSslSocketFactory()))
                        .encoderConfig(new EncoderConfig().encodeContentTypeAs(
                                ConnectorTestConstants.CONTENT_TYPE_JWT, ContentType.TEXT)
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .baseUri(configuration.getISServerUrl())
                .post(ConnectorTestConstants.ACCOUNT_VALIDATE_PATH)

        Assert.assertEquals(consentValidateResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertTrue(consentValidateResponse.jsonPath().get("isValid").toString()
                .contains(ConnectorTestConstants.IS_VALID_FALSE))
        Assert.assertTrue(consentValidateResponse.jsonPath().get("errorMessage").toString()
                .contains(ConnectorTestConstants.PERMISSION_MISMATCH_ERROR))
    }

    @Test
    void "Verify Creation of a consent without client ID"() {

        consentResponse = buildKeyManagerRequest("")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .post(consentPath)

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION),
                "Client ID missing in the request.")
    }

    @Test
    void "Verify Create Consent Without the Payload Body"() {

        consentResponse = buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body("")
                .baseUri(configuration.getISServerUrl())
                .post(consentPath)

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION),
                "Payload is not in the correct format")
    }
}
