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

package org.wso2.financial.services.accelerator.test.consent.management.EndToEndTest

import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.AccountsRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.constant.CofRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.utility.ConsentMgtTestUtils
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil
import org.wso2.openbanking.test.framework.utility.OBTestUtil

/**
 * End to End Consent Management Flow Tests.
 */
class EndToEndAcountsManagementFlowTest extends FSConnectorTest {

    public List<ConnectorTestConstants.ApiScope> consentScopes = [
            ConnectorTestConstants.ApiScope.ACCOUNTS
    ]

    @BeforeClass
    void setUp() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload
    }

    @Test
    void "Verify Create Application Access Token"() {

//        List<ConnectorTestConstants.ApiScope> scopeList = ConsentMgtTestUtils.getApiScopesForConsentType(map.get("consentType"))
        Response response = getApplicationAccessTokenResponse(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                configuration.getAppInfoClientID(), consentScopes)

        def accessToken = TestUtil.parseResponseBody(response, "access_token")
        def scopes = TestUtil.parseResponseBody(response, "scope")
        log.info("Got app access token $accessToken")


        Assert.assertNotNull(accessToken)
        Assert.assertEquals(scopes, consentScopes.get(0).scopeString)
    }

    @Test
    void "Verify Create the consent with valid inputs"() {

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
                "https://api.alphabank.com/open-banking/v3.1/aisp/account-access-consents" + "/" + consentId)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.META_TOTAL_PAGES))
    }

    @Test (dependsOnMethods = "Verify Create the consent with valid inputs")
    void "Verify Retrieving for a Created Consent"() {

        doConsentRetrieval(consentId)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertEquals(OBTestUtil.parseResponseBody(consentResponse, "Data.Status"), "AwaitingAuthorisation")
    }

    @Test (dependsOnMethods = "Verify Retrieving for a Created Consent")
    void "Generate authorization code when valid request object is present in the authorization request"() {

        //Authorise Consent
        doConsentAuthorisation(configuration.getAppInfoClientID(), true, consentScopes)

        Assert.assertNotNull(code)
        Assert.assertNotNull(OBTestUtil.getIdTokenFromUrl(automation.currentUrl.get()))
    }

    @Test (dependsOnMethods = "Generate authorization code when valid request object is present in the authorization request")
    void "Verify Create User Access Token"() {

//        List<ConnectorTestConstants.ApiScope> scopeList = ConsentMgtTestUtils.getApiScopesForConsentType(map.get("consentType"))
        Response response = getUserAccessTokenResponse(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                configuration.getAppInfoClientID(), code, consentScopes)

        def accessToken = TestUtil.parseResponseBody(response, "access_token")
        def idToken = TestUtil.parseResponseBody(response, "id_token")
        def scopes = TestUtil.parseResponseBody(response, "scope")
        log.info("Got app access token $accessToken")

        Assert.assertNotNull(accessToken)
        Assert.assertNotNull(idToken)
        Assert.assertTrue(scopes.contains(consentScopes.get(0).scopeString))
    }

    @Test (dependsOnMethods = "Verify Create User Access Token")
    void "Verify Retrieving for a Created Consent After authorizing"() {

        doConsentRetrieval(consentId)
        Assert.assertNotNull(consentId)
        Assert.assertEquals(consentResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertEquals(OBTestUtil.parseResponseBody(consentResponse, "Data.Status"), "Authorised")
    }

    @Test (dependsOnMethods = "Verify Retrieving for a Created Consent After authorizing")
    void "Validate Retrieval on valid account for requestUri"() {

        def accessToken = GenerateBasicHeader()
        def selectedAccount = "1234"
        def requestUri
        def validationPayload

        validationPayload = AccountsRequestPayloads.buildValidationAccountsPayload(accessToken, userId, consentId)

        doAccountValidation(validationPayload)

        Assert.assertEquals(accountValidationResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertEquals(Boolean.parseBoolean(OBTestUtil.parseResponseBody(accountValidationResponse, ConnectorTestConstants.IS_VALID)),
                true)
        Assert.assertNotNull(OBTestUtil.parseResponseBody(accountValidationResponse, "consentInformation"))
    }

    @Test (dependsOnMethods = "Validate Retrieval on valid account for requestUri")
    void "Revoke a Created Consent"() {

        doConsentRevocation(consentId)

        Assert.assertEquals(consentRevocationResponse.getStatusCode(), ConnectorTestConstants.STATUS_CODE_204)
    }

}
