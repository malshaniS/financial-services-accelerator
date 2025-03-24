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
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.AccountsRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.utility.ConsentMgtTestUtils
import org.wso2.financial.services.accelerator.test.framework.utility.FSRestAsRequestBuilder
import org.wso2.financial.services.accelerator.test.framework.utility.PaymentDataProviders
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil
import org.wso2.openbanking.test.framework.utility.OBTestUtil

import java.nio.charset.Charset

/**
 * Consent Retrieval  class
 */
class PaymentConsentRetrievalFlow extends FSConnectorTest {

    //Consent scopes as a list of Strings
    private List<String> consentScopesString = [
            ConnectorTestConstants.ApiScope.PAYMENTS.getScopeString(),
    ]

    @Test(dataProvider = "paymentSubmissionTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401100_Payment Consent Retrieval With x-fapi-financial-id"(Map<String, String> map) {

        //do the initiation and get the code
        path = map.get("initPath")
        payload = getRequiredInitiationPayload(map.get("payloadType"))

        doDefaultInitiation()

        doConsentRetrieval(consentId)

        def consentStatus = TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS)
        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
    }

    @Test(dataProvider = "paymentSubmissionTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401098_Payment Consent Retrieval With x-fapi-customer-last-logged-time"(Map<String, String> map) {

        //do the initiation and get the code
        path = map.get("initPath")
        payload = getRequiredInitiationPayload(map.get("payloadType"))

        doDefaultInitiation()

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def retrievalResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME, ConnectorTestConstants
                        .X_FAPI_CUSTOMER_LAST_LOGGED_TIME_VALUE)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        def consentStatus = TestUtil.parseResponseBody(retrievalResponse, ConnectorTestConstants.DATA_STATUS)
        Assert.assertEquals(retrievalResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertEquals(consentStatus, ConnectorTestConstants.AWAITING_AUTHORISATION)
    }

    @Test(dataProvider = "paymentSubmissionTypes", dataProviderClass = PaymentDataProviders.class)
    void "TC0401099_Payment Consent Retrieval With x-fapi-auth-date"(Map<String, String> map) {

        //do the initiation and get the code
        path = map.get("initPath")
        payload = getRequiredInitiationPayload(map.get("payloadType"))

        doDefaultInitiation()

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def retrievalResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants
                        .X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        def consentStatus = TestUtil.parseResponseBody(retrievalResponse, ConnectorTestConstants.DATA_STATUS)
        Assert.assertEquals(retrievalResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertEquals(consentStatus, ConnectorTestConstants.AWAITING_AUTHORISATION)
    }

    @Test(dataProvider = "paymentSubmissionTypes", dataProviderClass = PaymentDataProviders.class)
    void "Payment Consent Retrieval With x-idempotency-key"(Map<String, String> map) {

        //do the initiation and get the code
        path = map.get("initPath")
        payload = getRequiredInitiationPayload(map.get("payloadType"))

        doDefaultInitiation()

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def retrievalResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .baseUri(configuration.getISServerUrl())
                .get(consentPath + "/${consentId}")

        def consentStatus = TestUtil.parseResponseBody(retrievalResponse, ConnectorTestConstants.DATA_STATUS)
        Assert.assertEquals(retrievalResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertEquals(consentStatus, ConnectorTestConstants.AWAITING_AUTHORISATION)
    }

    @Test(dataProvider = "paymentSubmissionTypes", dataProviderClass = PaymentDataProviders.class)
    void "Payment funds confirmation request"(Map<String, String> map) {

        //do the initiation and get the code
        path = map.get("initPath")
        payload = getRequiredInitiationPayload(map.get("payloadType"))
        fundsConfirmPath = map.get("fundsConfirmPath")

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        doDefaultInitiation()

        def paymentsFundsConfirmResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .baseUri(configuration.getISServerUrl())
                .get(fundsConfirmPath + "/${consentId}/funds-confirmation")

        Assert.assertEquals(paymentsFundsConfirmResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
    }
}
