/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.com). All Rights Reserved.
 *
 * This software is the property of WSO2 Inc. and its suppliers, if any.
 * Dissemination of any information or reproduction of any material contained
 * herein is strictly forbidden, unless permitted by WSO2 in accordance with
 * the WSO2 Software License available at https://wso2.com/licenses/eula/3.1. For specific
 * language governing the permissions and limitations under this license,
 * please see the license as well as any agreement you’ve entered into with
 * WSO2 governing the purchase of this software and any associated services.
 *
 */

package org.wso2.financial.services.accelerator.test.consent.management.PaymentsTests

import io.restassured.http.ContentType
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.constant.PaymentRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.utility.FSRestAsRequestBuilder
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil

import java.nio.charset.Charset

/**
 * File Payments Initiation Request Header Validation Tests.
 */
class FilePaymentsInitiationRequestHeaderValidationTests extends FSConnectorTest {

    def initPath = ConnectorTestConstants.CONSENT_PATH_FILE_PAYMENTS

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701002_File payment initiation request with invalid access token"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        def authHeader = getBasicAuthHeader(configurationService.getUserPSUName(),
                configurationService.getUserPSUPWD())

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, authHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Invalid Credentials. Make sure you have provided the correct security credentials"))
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701007_File payment initiation request without authorization header"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        def authHeader = getBasicAuthHeader(configurationService.getUserPSUName(),
                configurationService.getUserPSUPWD())

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_401)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_HEADER_MISSING)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Make sure your API invocation call has a header"))
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701011_File payment initiation request without content type header"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        //initiation
        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_415)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Request Content-Type header '[text/plain; charset=ISO-8859-1]' " +
                        "does not match any allowed types. "))
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8","3.1.11"])
    void "TC0701010_File payment initiation request with wrong content type"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        //initiation
        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.XML)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_415)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Request Content-Type header '[application/xml; charset=ISO-8859-1]' does not" +
                        " match any allowed types. Must be one of: [application/json; charset=utf-8]."))
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8","3.1.11"])
    void "TC0701012_File payment initiation request without x-jws-signature header"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        //initiation
        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)

        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_SIGNATURE_MISSING)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains(ConnectorTestConstants.X_JWS_SIGNATURE_MISSING))
    }

    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701018_File payment initiation request with x-fapi-financial-id"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        //initiation
        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.XML)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701016_File payment initiation request with x-fapi-customer-last-logged-time"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME,
                        ConnectorTestConstants.X_FAPI_CUSTOMER_LAST_LOGGED_TIME_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701017_File payment initiation request with x-fapi-auth-date"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    @Test(groups = ["3.1.5", "3.1.6","3.1.11"])
    void "TC0701018_File payment initiation request without x-fapi-financial-id"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                ConnectorTestConstants.AWAITING_UPLOAD)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, "Data.Initiation.FileType"))
    }

    @Test(groups = ["3.1.5", "3.1.6","3.1.11"])
    void "TC0701019_File payment initiation request with an invalid x-fapi-financial-id"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, "abc")
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, TestUtil.idempotency)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                ConnectorTestConstants.AWAITING_UPLOAD)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, "Data.Initiation.FileType"))
    }

    @Test(groups = ["3.1.5", "3.1.6","3.1.11"])
    void "TC0701020_File payment initiation request with same payload same x-idempotency-key"() {

        String idempotencyKey = TestUtil.idempotency
        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        //initiation
        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                ConnectorTestConstants.AWAITING_UPLOAD)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, "Data.Initiation.FileType"))

        Response consentResponse2 = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse2.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID),
                TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.DATA_STATUS))

    }

    @Test(groups = ["3.1.5", "3.1.6","3.1.11"])
    void "TC0701021_File payment initiation request with same payload different x-idempotency-key"() {

        String idempotencyKey = TestUtil.getIdempotency()
        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                ConnectorTestConstants.AWAITING_UPLOAD)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, "Data.Initiation.FileType"))

        String idempotencyKey2 = TestUtil.getIdempotency()

        Response consentResponse2 = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey2)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse2.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertNotEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID),
                TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.DATA_STATUS))

    }

    @Test(groups = ["3.1.5", "3.1.6","3.1.11"])
    void "TC0701022_File payment initiation request without x-idempotency-key"() {

        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_HEADER_MISSING)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Schema validation failed in the Request: Header parameter 'x-idempotency-key' is required on path"))

    }

    @Test(groups = ["3.1.5", "3.1.6","3.1.11"])
    void "TC0701021_File payment initiation request with different payload same x-idempotency-key"() {

        String idempotencyKey = TestUtil.idempotency
        def initPayload = PaymentRequestPayloads.initiationPayloadFilePayment

        def authToken = "${configurationService.getUserKeyManagerAdminName()}:" +
                "${configurationService.getUserKeyManagerAdminPWD()}"
        def basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

        def consentResponse = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        initPayload))
                .body(initPayload)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_CONSENT_ID))
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.DATA_STATUS),
                ConnectorTestConstants.AWAITING_UPLOAD)
        Assert.assertNotNull(TestUtil.parseResponseBody(consentResponse, "Data.Initiation.FileType"))

        def payload2 = PaymentRequestPayloads.initiationPayloadFilePaymentWithDebtorAccount

        Response consentResponse2 = FSRestAsRequestBuilder.buildRequest()
                .contentType(ContentType.JSON)
                .header(ConnectorTestConstants.X_FAPI_AUTH_DATE, ConnectorTestConstants.X_FAPI_AUTH_DATE_VALUE)
                .header(ConnectorTestConstants.X_FAPI_FINANCIAL_ID, ConnectorTestConstants.X_FAPI_FINANCIAL_ID_VALUE)
                .header(ConnectorTestConstants.X_WSO2_CLIENT_ID_KEY, configuration.getAppInfoClientID())
                .header(ConnectorTestConstants.AUTHORIZATION_HEADER, basicHeader)
                .header(ConnectorTestConstants.X_FAPI_INTERACTION_ID, UUID.randomUUID().toString())
                .header(ConnectorTestConstants.X_IDEMPOTENCY_KEY, idempotencyKey)
                .header(ConnectorTestConstants.X_JWS_SIGNATURE,TestUtil.generateXjwsSignature(TestUtil.requestHeader,
                        payload2))
                .body(payload2)
                .baseUri(configuration.getISServerUrl())
                .post(initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_HEADER_INVALID)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse2, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Payloads are not similar. Hence this is not a valid idempotent request"))

    }
}
