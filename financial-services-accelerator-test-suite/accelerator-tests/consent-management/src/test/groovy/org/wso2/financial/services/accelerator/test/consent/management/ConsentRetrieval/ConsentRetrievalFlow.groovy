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

package org.wso2.financial.services.accelerator.test.consent.management.ConsentRetrieval

import io.restassured.http.ContentType
import org.wso2.financial.services.accelerator.test.framework.utility.FSRestAsRequestBuilder
import org.wso2.openbanking.test.framework.utility.OBTestUtil
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.constant.AccountsRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.utility.ConsentMgtTestUtils
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil

import java.nio.charset.Charset

/**
 * Consent Retrieval  class
 */
class ConsentRetrievalFlow extends FSConnectorTest {

    //Consent scopes as a list of Strings
    private List<String> consentScopesString = [
            ConnectorTestConstants.ApiScope.ACCOUNTS.getScopeString(),
    ]

    @BeforeClass(alwaysRun = true)
    void init(){
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload
    }

    @Test
    void "OB-1958_Verify Retrieval of a Created Consent"() {

        doDefaultInitiation()
        doConsentRetrieval(consentId)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
    }

    @Test
    void "OB-1959_Verify Retrieval of a Created Consent without consent ID"() {

        doDefaultInitiation()
        doConsentRetrievalWithoutConsentID()
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "OB-1960_Verify Retrieval of a Created Consent without Authorization header"() {

        doDefaultInitiation()
        doConsentRetrievalWithoutAuthorizationHeader(consentId)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_401)
    }

    @Test
    void "TC0202012_Get Accounts Initiation With Invalid Authorization Header"() {

        configuration.setPsuNumber(1)

        def basicHeader = getBasicAuthHeader(configuration.getUserPSUName(),
                configuration.getUserPSUPWD())

        doDefaultInitiation()
        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, "${configuration.getAppInfoClientID()}")
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_401)
    }

    @Test
    void "OB-1961_Verify Retrieval of a Created Consent with Incorrect request path"() {

        doDefaultInitiation()
        doConsentRetrievalWithIncorrectRequestPath(consentId)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_404)
    }

    @Test
    void "OB-1962_Verify Retrieval of a Created Consent with Authorization Code Type Access"() {

        doDefaultInitiation()
        Assert.assertNotNull(consentId)

        //Authorize consent and generate user access token
        List<ConnectorTestConstants.ApiScope> scopeList = ConsentMgtTestUtils
                .getApiScopesForConsentType(ConnectorTestConstants.ACCOUNTS_TYPE)
        Response response = getUserAccessTokenResponse(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                configuration.getAppInfoClientID(), code, scopeList)

        def accessToken = TestUtil.parseResponseBody(response, "access_token")

        consentResponse = consentRequestBuilder.buildBasicRequest(accessToken)
                .baseUri(configuration.getISServerUrl())
                .get(incorrectConsentPath + "/${consentId}")

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_401)
    }

    @Test
    void "OB-1963_Verify Retrieval of a Created Consent with incorrect Consent ID"() {

        String incorrectConsentID = 'c1b6c5c9-1ec9-4ccf-8f68-e18df87777bfaaa'

        doDefaultInitiation()
        doConsentRetrieval(incorrectConsentID)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "OB-1964_Verify Retrieval of a Created Consent with different search params"() {

        doDefaultInitiation()
        doConsentRetrievalWithDifferentSearchParams(consentId)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_401)
    }

    @Test
    void "Verify Retrieval of a consent with valid inputs"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        doConsentRetrieval(consentId as String)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
        String status = OBTestUtil.parseResponseBody(consentResponse, "Data.Status")
        Assert.assertEquals(status, "AwaitingAuthorisation")
    }

    @Test
    void "Verify Retrieval of a consent After Authorisation"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        doConsentAuthorisation(configuration.getAppInfoClientID(), true, [ConnectorTestConstants.ApiScope.ACCOUNTS])

        doConsentRetrieval(consentId as String)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
        String status = OBTestUtil.parseResponseBody(consentResponse, "Data.Status")
        Assert.assertEquals(status, "Authorised")
    }

    @Test
    void "Verify Consent Retrieval without client ID"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        consentResponse = buildKeyManagerRequest("")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Retrieval invalid client ID"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        consentResponse = buildKeyManagerRequest("tyionwbbvqhhwvh")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Retrieval without consent ID"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        consentResponse = buildKeyManagerRequest("")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/")

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Retrieval with invalid consent ID"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        consentResponse = buildKeyManagerRequest("")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/12345678")

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Retrieval invalid consent type"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        consentResponse = buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .get(ConnectorTestConstants.COF_CONSENT_PATH + "/${consentId}")

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Retrieval without Authorisation header"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        consentResponse = buildKeyManagerRequestWithoutAuthorizationHeader(configuration.getAppInfoClientID())
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_401)
    }

    @Test
    void "Verify Consent Retrieval with incorrect Access Token type"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        doConsentRetrievalWithIncorrectAccessTokenType()

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_401)
    }

    @Test
    void "Verify Consent Retrieval with incorrect request path"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        doConsentRetrievalWithIncorrectRequestPath(consentId as String)

        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_401)
    }

    @Test
    void "TC0202018_Get Accounts Initiation With Headers with Capital Case"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        def basicHeader = getBasicAuthHeader(configurationService.getUserKeyManagerAdminName(),
                configurationService.getUserKeyManagerAdminPWD())

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID_CAPS, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID_CAPS, UUID.randomUUID().toString())
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
        String status = OBTestUtil.parseResponseBody(consentResponse, "Data.Status")
        Assert.assertEquals(status, "AwaitingAuthorisation")
    }

    @Test
    void "TC0202004_Get Accounts Initiation With Authorization Code Type Access Token"() {

        if (userAccessToken == null) {
            doDefaultInitiation()
            doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
            userAccessToken = getUserAccessToken(code, consentScopes)
        }

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, userAccessToken)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        this.consentId = TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID).toString()

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        def errorMessage = TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
        Assert.assertEquals(errorMessage, "Incorrect Access Token Type provided")
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID)
    }

    @Test
    void "TC0202010_Get Accounts Initiation With TPP Defined x-fapi-interaction-id Header"() {

        doDefaultInitiation()
        def xfapiInteractionId = TestUtil.generateUUID()

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, xfapiInteractionId)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .get(consentPath + "/" + consentId)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertEquals(consentResponse.getHeader(ConnectorTestConstants.X_FAPI_INTERACTION_ID), xfapiInteractionId)
    }

    @Test
    void "TC0202013_Get Accounts Initiation Without Specifying Accept Header"() {

        doDefaultInitiation()
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .get(consentPath + "/" + consentId)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
    }

    //TODO: Uncomment after fixing: https://github.com/wso2-enterprise/financial-open-banking/issues/7965
//    @Test
    void "TC0202014_Get Accounts Initiation With Invalid Accept Header"() {

        doDefaultInitiation()

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .accept(ContentType.XML)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .get(consentPath + "/" + consentId)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_406)
        def errorMessage = TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
        Assert.assertTrue(errorMessage.contains("Request Accept header 'application' is not a valid media type"))
        def errorCode = TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE)
        Assert.assertEquals(errorCode, ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID)
    }

    @Test
    void "TC0202015_Get Accounts Initiation With Empty Consent Id"() {

        doDefaultInitiation()

        def consentid = ""

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .get(consentPath + "/" + consentid)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_405)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_INVALID_FORMAT)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE).contains(
                "Method not allowed for given API resource"))
    }

    @Test
    void "TC0202016_Get Accounts Initiation Without Consent Id Parameter"() {

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .get(consentPath + "/")

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_405)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_INVALID_FORMAT)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE).contains(
                "Method not allowed for given API resource"))
    }

    @Test
    void "TC0202020_Get Accounts Initiation With x-fapi-customer-last_logged_time Header"() {
        doDefaultInitiation()

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME,
                        ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .get(consentPath + "/" + consentId)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
    }

    @Test
    void "TC0202021_Get Accounts Initiation With x-fapi-auth-date Header"() {
        doDefaultInitiation()

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE,
                        ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .get(consentPath + "/" + consentId)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
    }
}
