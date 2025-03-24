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
import org.wso2.financial.services.accelerator.test.framework.constant.AccountsRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.request_builder.ConsentRequestBuilder
import org.wso2.financial.services.accelerator.test.framework.utility.AccountsDataProviders
import org.wso2.financial.services.accelerator.test.framework.utility.ConsentMgtTestUtils
import org.wso2.financial.services.accelerator.test.framework.utility.FSRestAsRequestBuilder
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil

import java.nio.charset.Charset

/**
 * Consent Revocation Flow.
 */
class CofConsentRevokeFlow extends FSConnectorTest {

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
}
