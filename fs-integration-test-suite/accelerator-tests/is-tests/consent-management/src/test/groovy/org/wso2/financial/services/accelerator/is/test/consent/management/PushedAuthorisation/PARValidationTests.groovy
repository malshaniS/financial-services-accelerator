package org.wso2.financial.services.accelerator.is.test.consent.management.PushedAuthorisation

import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.AccountsRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil

class PARValidationTests extends FSConnectorTest{

    String basicHeader

    @BeforeClass
    void init() {
        consentPath = ConnectorTestConstants.ACCOUNT_CONSENT_PATH
        initiationPayload = AccountsRequestPayloads.initiationPayload
        basicHeader = getBasicAuthHeader(configuration.getUserKeyManagerAdminName(),
                configuration.getUserKeyManagerAdminPWD())

    }

    @Test (groups = "SmokeTest")
    void "TC0205001_Data Recipients Initiate authorisation request using PAR"() {

        def clientId = configuration.getAppInfoClientID()

        //Consent Initiation
        doDefaultInitiation()

        //Send PAR request
        def response = doPushAuthorisationRequest(consentScopes, consentId,
                clientId, configuration.getCommonSigningAlgorithm())
        def requestUri = TestUtil.parseResponseBody(response, ConnectorTestConstants.REQUEST_URI)

        Assert.assertEquals(response.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertNotNull(requestUri)
        Assert.assertNotNull(TestUtil.parseResponseBody(response, ConnectorTestConstants.RESPONSE_EXPIRES_IN))

        //Authorise the consent
        doConsentAuthorisationViaRequestUri(requestUri.toURI(), clientId)
        Assert.assertNotNull(code)
    }
}
