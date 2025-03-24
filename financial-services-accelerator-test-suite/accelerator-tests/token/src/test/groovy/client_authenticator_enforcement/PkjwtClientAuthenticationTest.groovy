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

package client_authenticator_enforcement

import org.testng.annotations.BeforeClass
import org.wso2.openbanking.test.framework.utility.OBTestUtil
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.configuration.ConfigurationService
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants

/**
 * PKJWT Client AUthentication Validation Test.
 */
class PkjwtClientAuthenticationTest extends FSConnectorTest {

    String clientId
    ConnectorTestConstants.ApiScope scope = ConnectorTestConstants.ApiScope.ACCOUNTS
    private ConfigurationService configuration = new ConfigurationService()

    @BeforeClass
    void setup() {
        configuration.setTppNumber(0)
    }

    @Test (priority = 0)
    void "Validate token request with deleted client_id for iss and sub in client_assertion"() {


        clientId = configuration.getAppInfoClientID()
        //Token Request for the deleted client
        Response tokenResponse = getApplicationAccessTokenResponse(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                "deleted_client_id", [scope])

        Assert.assertEquals(tokenResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        Assert.assertEquals(OBTestUtil.parseResponseBody(tokenResponse, ConnectorTestConstants.ERROR),
                ConnectorTestConstants.INVALID_CLIENT)
        Assert.assertEquals(OBTestUtil.parseResponseBody(tokenResponse, ConnectorTestConstants.ERROR_DESCRIPTION),
                ("A valid OAuth client could not be found for client_id: deleted_client_id"))
    }

    @Test
    void "Validate token request with expired client assertion"() {

        long expireTime = (long) (System.currentTimeSeconds()).minus(600000000)

        clientId = configuration.getAppInfoClientID()
        Response tokenResponse = getApplicationAccessTokenResponseWithCustomExp(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                clientId, [scope], expireTime)

        Assert.assertEquals(tokenResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(OBTestUtil.parseResponseBody(tokenResponse, ConnectorTestConstants.ERROR),
                "invalid_request")
        Assert.assertTrue(OBTestUtil.parseResponseBody(tokenResponse, ConnectorTestConstants.ERROR_DESCRIPTION).
                contains("JWT Token is expired."))
    }

    @Test
    void "Validate token request for pkjwt client without client_assertion"() {

        clientId = configuration.getAppInfoClientID()
        Response tokenResponse = getApplicationAccessTokenResponseWithoutAssertion(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                clientId, [scope])

        Assert.assertEquals(tokenResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        Assert.assertEquals(OBTestUtil.parseResponseBody(tokenResponse, ConnectorTestConstants.ERROR),
                ConnectorTestConstants.INVALID_CLIENT)
        Assert.assertEquals(OBTestUtil.parseResponseBody(tokenResponse, ConnectorTestConstants.ERROR_DESCRIPTION),
                "Unsupported client authentication mechanism")
    }

    @Test
    void "Validate token request for pkjwt client without client_id"() {

        Response tokenResponse = getApplicationAccessTokenResponseWithoutClientId(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                [scope])

        def accessToken = OBTestUtil.parseResponseBody(tokenResponse, "access_token")

        //TODO:
//        Assert.assertEquals(tokenResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
//        Assert.assertNotNull(accessToken)
//        Assert.assertNotNull(OBTestUtil.parseResponseBody(tokenResponse, "expires_in"))
//        Assert.assertNotNull(OBTestUtil.parseResponseBody(tokenResponse, "scope"))
//        Assert.assertEquals(OBTestUtil.parseResponseBody(tokenResponse, "token_type"), ConnectorTestConstants.BEARER)
    }

    @Test(priority = 1)
    void "pkjwt client authentication with both x-wso2-mutual-auth-cert header and client_assertion"() {

        clientId = configuration.getAppInfoClientID()
        Response tokenResponse = getApplicationAccessTokenResponse(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                clientId, [scope])

        def accessToken = OBTestUtil.parseResponseBody(tokenResponse, "access_token")

        Assert.assertEquals(tokenResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertNotNull(accessToken)
        Assert.assertNotNull(OBTestUtil.parseResponseBody(tokenResponse, "expires_in"))
        Assert.assertNotNull(OBTestUtil.parseResponseBody(tokenResponse, "scope"))
        Assert.assertEquals(OBTestUtil.parseResponseBody(tokenResponse, "token_type"), ConnectorTestConstants.BEARER)
    }

    @Test(priority = 1)
    void "pkjwt client authentication with x-wso2-mutual-auth-cert header, client id and client_assertion and client assertion type"() {

        clientId = configuration.getAppInfoClientID()
        Response tokenResponse = getApplicationAccessTokenResponse(ConnectorTestConstants.PKJWT_AUTH_METHOD,
                clientId, [scope])

        def accessToken = OBTestUtil.parseResponseBody(tokenResponse, "access_token")

        Assert.assertEquals(tokenResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_200)
        Assert.assertNotNull(accessToken)
        Assert.assertNotNull(OBTestUtil.parseResponseBody(tokenResponse, "expires_in"))
        Assert.assertNotNull(OBTestUtil.parseResponseBody(tokenResponse, "scope"))
        Assert.assertEquals(OBTestUtil.parseResponseBody(tokenResponse, "token_type"), ConnectorTestConstants.BEARER)
    }
}
