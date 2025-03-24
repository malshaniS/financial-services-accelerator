/**
 * Copyright (c) 2021-2023, WSO2 LLC. (https://www.wso2.com). All Rights Reserved.
 *
 * This software is the property of WSO2 LLC. and its suppliers, if any.
 * Dissemination of any information or reproduction of any material contained
 * herein in any form is strictly forbidden, unless permitted by WSO2 expressly.
 * You may not alter or remove any copyright or other notice from copies of this content.
 */


package org.wso2.financial.services.accelerator.test.consent.management.PaymentsTests

import org.testng.Assert
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.constant.PaymentRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil

class FilePaymentsInitiationRequestPayloadValidationTests extends FSConnectorTest {

    def initPath = ConnectorTestConstants.CONSENT_PATH_FILE_PAYMENTS

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701013_File payment initiation request with no file type in the payload"() {

        def initPayloadWithNoFileType = PaymentRequestPayloads.filePaymentInitPayloadWithNoFileType

        //initiation
        def consentResponse = paymentInitiationRequest(initPayloadWithNoFileType,
                initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_FIELD_MISSING)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Object has missing required properties ([\"FileType\"])"))
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701015_File payment initiation request with no file hash in the payload"() {

        def initPayloadWithNoFileHash = PaymentRequestPayloads.filePaymentInitPayloadWithNoFileHash

        //initiation
        def consentResponse = paymentInitiationRequest(initPayloadWithNoFileHash,
                initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_FIELD_MISSING)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("Object has missing required properties ([\"FileHash\"])"))
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701014_File payment initiation request with an invalid file type in the payload"() {

        def initPayloadWithInvalidFileType = PaymentRequestPayloads.filePaymentInitPayloadWithInvalidFileType

        //initiation
        def consentResponse = paymentInitiationRequest(initPayloadWithInvalidFileType,
                initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_FIELD_INVALID)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("File Type of the request does not match with any allowed values"))
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0701015 Payment Initiation request with an invalid execution date"() {

        def initPayloadWithInvalidExecutionDate = PaymentRequestPayloads
                .initiationPayloadFilePaymentWithInvalidExecutionDate

        //initiation
        def consentResponse = paymentInitiationRequest(initPayloadWithInvalidExecutionDate,
                initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_FIELD_INVALID_DATE)
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "US-312_File payment initiation request with an invalid file hash in the payload"() {

        def initPayloadWithInvalidFileType = PaymentRequestPayloads.initiationPayloadFilePaymentWithInvalidFileHash

        //initiation
        def consentResponse = paymentInitiationRequest(initPayloadWithInvalidFileType,
                initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_FIELD_INVALID)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("is too long (length: 50, maximum allowed: 44)"))
    }

    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "US-313_File payment initiation request with an invalid file reference in the payload"() {

        def initPayloadWithInvalidFileReference = PaymentRequestPayloads.initiationPayloadFilePaymentWithInvalidFileReference

        //initiation
        def consentResponse = paymentInitiationRequest(initPayloadWithInvalidFileReference,
                initPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertEquals(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_CODE),
                ConnectorTestConstants.OBIE_ERROR_FIELD_INVALID)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_MESSAGE)
                .contains("is too long (length: 50, maximum allowed: 40)"))
    }
}
