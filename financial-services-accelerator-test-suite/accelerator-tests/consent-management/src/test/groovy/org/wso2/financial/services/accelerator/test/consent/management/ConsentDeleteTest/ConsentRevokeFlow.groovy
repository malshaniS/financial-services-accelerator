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

package org.wso2.financial.services.accelerator.test.consent.management.ConsentDeleteTest

import io.restassured.http.ContentType
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.constant.AccountsRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.request_builder.ConsentRequestBuilder
import org.wso2.financial.services.accelerator.test.framework.utility.AccountsDataProviders
import org.wso2.financial.services.accelerator.test.framework.utility.ConsentMgtTestUtils
import org.wso2.financial.services.accelerator.test.framework.utility.FSRestAsRequestBuilder
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil

import java.nio.charset.Charset

/**
 * Consent Revocation Flow.
 */
class ConsentRevokeFlow extends FSConnectorTest {

    ConsentRequestBuilder consentRequestBuilder = new ConsentRequestBuilder()

    //Consent scopes as a list of Strings
    private List<String> consentScopesString = [
            ConnectorTestConstants.ApiScope.ACCOUNTS.getScopeString(),
    ]

    @BeforeClass(alwaysRun = true)
    void init() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload
    }

    @Test
    void "OB-1722_Verify Consent Revoke for valid consent"() {

        //Consent Initiation
        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

//        //Authorize consent
//        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
//        Assert.assertNotNull(code)
//        Assert.assertNotNull(TestUtil.getIdTokenFromUrl(automation.currentUrl.get()))

        //Consent Revocation
        doConsentRevocation(consentId)
        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_204)
    }

    @Test
    void "OB-1723_Verify Consent Revoke for valid consent without Authorization Header"() {

        //Consent Initiation
        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Authorize consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
        Assert.assertNotNull(code)
        Assert.assertNotNull(TestUtil.getIdTokenFromUrl(automation.currentUrl.get()))

        //Consent Revocation
        doConsentRevocationWithoutAuthorizationHeader()
        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_401)
    }

    @Test
    void "TC0203004_Delete Consent With Authorization Code Type Access Token"() {

        List<ConnectorTestConstants.ApiScope> scopeList = ConsentMgtTestUtils.getApiScopesForConsentType(ConnectorTestConstants.ACCOUNTS_TYPE)

        doDefaultInitiation()
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
        userAccessToken = getUserAccessToken(code, scopeList)

        consentRevocationResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, userAccessToken)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/${consentId}")

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        def errorMessage = TestUtil.parseResponseBody(consentResponse,ConnectorTestConstants.ERROR_DESCRIPTION)
        Assert.assertEquals(errorMessage, "AuthenticationHandler not found.")
    }

    @Test
    void "OB-1724_Verify Consent Revoke for valid consent with Incorrect Content Type Header"() {

        //Consent Initiation
        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Authorize consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
        Assert.assertNotNull(code)
        Assert.assertNotNull(TestUtil.getIdTokenFromUrl(automation.currentUrl.get()))

        //Consent Revocation
        doConsentRevocationWithIncorrectContentTypeHeader()
        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_415)

    }

    @Test
    void "OB-1725_Verify Consent Revoke for valid consent with Incorrect Consent ID"() {

        //Consent Initiation
        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Authorize consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
        Assert.assertNotNull(code)

        //Consent Revocation
        String incorrectConsentID = "2171e7f0-641c-4f4e-9a9d-cfbbdd02b85b99"
        doConsentRevocation(incorrectConsentID)
        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "OB-1726_Verify Consent Revoke for valid consent with Incorrect Consent Path"() {

        //Consent Initiation
        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Authorize consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
        Assert.assertNotNull(code)

        //Consent Revocation
        doConsentRevocationWithIncorrectConsentPath()
        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_404)

    }

    @Test
    void "Verify Consent Revoke without client ID"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        Response response = consentRequestBuilder.buildKeyManagerRequest("")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/${consentId}")

        Assert.assertEquals(response.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Revoke with invalid client ID"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        Response response = consentRequestBuilder.buildKeyManagerRequest("tyionwbbvqhhwvh")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/${consentId}")

        Assert.assertEquals(response.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Revoke without consent ID"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        Response response = consentRequestBuilder.buildKeyManagerRequest("")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/")

        Assert.assertEquals(response.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_INVALID_FORMAT)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE).contains(
                "Method not allowed for given API resource"))
    }

    @Test
    void "Verify Consent Revoke with invalid consent ID"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        Response response = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/12345678")

        Assert.assertEquals(response.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Revoke invalid consent type"() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        Response response = consentRequestBuilder.buildKeyManagerRequest(configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .baseUri(configuration.getISServerUrl())
                .delete(ConnectorTestConstants.COF_CONSENT_PATH + "/${consentId}")

        Assert.assertEquals(response.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test
    void "Verify Consent Revoke for already revoked consent"() {

        //Consent Initiation
        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Consent Authorisation
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
        Assert.assertNotNull(code)

        //Consent Revocation
        doConsentRevocation(consentId)
        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_204)

        //Revoke Same Consent Again
        doConsentRevocation(consentId)
        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentRevocationResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_RESOURCE_INVALID_CONSENT_STATUS)
        Assert.assertTrue(TestUtil.parseResponseBody(consentRevocationResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Invalid Status value"))
    }

    @Test
    void "TC0203021_Delete_Accounts_Initiation_With_Headers with Capital Case"() {

        //Consent Initiation
        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        //Authorize consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)
        Assert.assertNotNull(code)
        Assert.assertNotNull(TestUtil.getIdTokenFromUrl(automation.currentUrl.get()))

        def basicHeader = getBasicAuthHeader(configurationService.getUserKeyManagerAdminName(),
                configurationService.getUserKeyManagerAdminPWD())

        //Consent Revocation
        consentRevocationResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID_CAPS, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID_CAPS, UUID.randomUUID().toString())
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/${consentId}")

        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_204)
    }

    @Test
    void "TC0203008_Delete Consent With Empty Consent Id"() {

        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload
        def consentid = ""

        doDefaultInitiation()
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_201)

        Response response = consentRequestBuilder.buildKeyManagerRequest("")
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${GenerateBasicHeader()}")
                .body(initiationPayload)
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/" + consentid)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_405)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_INVALID_FORMAT)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE).contains(
                "Method not allowed for given API resource"))
    }

    @Test
    void "TC0203020_Delete Already Rejected Consent"() {

        //Reject a Consent
        doDefaultInitiation()
        consentDenial(configuration.getAppInfoClientID(), true, consentScopes)

        //Delete the previously Rejected consent
        def rejectedConsentResponse = doConsentRevocation(consentId)

        Assert.assertEquals(rejectedConsentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)

        Assert.assertEquals(TestUtil.parseResponseBody(rejectedConsentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_RESOURCE_INVALID_CONSENT_STATUS)
        Assert.assertTrue(TestUtil.parseResponseBody(rejectedConsentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Invalid Status value"))
    }

    @Test
    void "US-317_Delete an account consent in AwaitingAuthorisation State"() {

        //Revoke a Consent
        doDefaultInitiation()
        consentResponse = doConsentRevocation(consentId)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_204)

    }

    @Test
    void "US-318_Delete an account consent in Authorised State"() {

        //Revoke a Consent
        doDefaultInitiation()
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        consentResponse = doConsentRevocation(consentId)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_204)
    }

    @Test (dataProvider = "InvalidXfapiInteractionId", dataProviderClass = AccountsDataProviders.class)
    void "TC0203014_Delete Consent With defined x-fapi-interaction-id header"(xfapiInteractionIds, assertion) {

        doDefaultInitiation()
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        def response = assertion.get(0) ? true : false
        for (String xfapiInteractionId : xfapiInteractionIds) {

            consentResponse = consentRevocationResponse = FSRestAsRequestBuilder.buildRequest()
                    .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                    .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, xfapiInteractionId)
                    .queryParam("consentID", consentId)
                    .baseUri(configuration.getISServerUrl())
                    .delete(consentPath + "/${consentId}")

            Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_204)

            if (xfapiInteractionId != "") {

                Assert.assertEquals(consentResponse.getHeader(ConnectorTestConstants.X_FAPI_INTERACTION_ID).toString()
                        .equalsIgnoreCase(xfapiInteractionId), response)
            }
            else {
                Assert.assertEquals(consentResponse.getHeader(ConnectorTestConstants.X_FAPI_INTERACTION_ID)!=null,response)
            }
        }
    }

    @Test (dataProvider = "InvalidAuthorizationHeader", dataProviderClass = AccountsDataProviders.class)
    void "TC0203016_Delete Consent With Invalid Authorization Header"(authorizationHeaders, assertion) {

        doDefaultInitiation()
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        def response = assertion[0] ? ConnectorTestConstants.STATUS_CODE_401 : ConnectorTestConstants.STATUS_CODE_204

        for (String authorizationHeader : authorizationHeaders) {

            consentResponse = consentRevocationResponse = FSRestAsRequestBuilder.buildRequest()
                    .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                    .header(ConnectorTestConstants.AUTHORIZATION_HEADER, "${authorizationHeader}")
                    .queryParam("consentID", consentId)
                    .baseUri(configuration.getISServerUrl())
                    .delete(consentPath + "/${consentId}")

            Assert.assertEquals(consentResponse.statusCode(), response)
        }
    }

    @Test
    void "TC0203022_Delete_Accounts_Initiation_With_x-fapi-financial_Header"() {

        doDefaultInitiation()
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = consentRevocationResponse = FSRestAsRequestBuilder.buildRequest()
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .queryParam("consentID", consentId)
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/${consentId}")

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_204)
    }

    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0203023_Delete_Accounts_Initiation_With_x-fapi-customer-last-logged-time_Header"() {

        doDefaultInitiation()
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = consentRevocationResponse = FSRestAsRequestBuilder.buildRequest()
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME,
                        ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME_VALUE)
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .queryParam("consentID", consentId)
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/${consentId}")

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_204)
    }


    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0203024_Delete_Accounts_Initiation_With_x-fapi-auth-date_Header"() {

        doDefaultInitiation()
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        consentResponse = consentRevocationResponse = FSRestAsRequestBuilder.buildRequest()
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE,
                        ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .queryParam("consentID", consentId)
                .baseUri(configuration.getISServerUrl())
                .delete(consentPath + "/${consentId}")

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_204)
    }
}
