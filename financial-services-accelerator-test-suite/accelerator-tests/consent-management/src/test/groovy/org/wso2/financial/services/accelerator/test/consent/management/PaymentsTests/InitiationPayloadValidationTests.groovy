/**
 * Copyright (c) 2025, WSO2 LLC. (https://www.wso2.com).
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

package org.wso2.financial.services.accelerator.test.consent.management.PaymentsTests


import org.testng.Assert
import org.testng.annotations.Test
import org.wso2.financial.services.accelerator.test.framework.FSConnectorTest
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.constant.PaymentRequestPayloads
import org.wso2.financial.services.accelerator.test.framework.utility.PaymentDataProviders
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil
/**
 * Payments Initiation Payload Validation Tests.
 */
class InitiationPayloadValidationTests extends FSConnectorTest{

    @Test(dataProvider = "paymentValidateWithoutEndToEndIdentificationForPayment", dataProviderClass = PaymentDataProviders.class)
    void "TC0401002_Validate_Payments_initiation_Without_EndToEnd_identification"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM), 
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties"))
    }

    @Test(dataProvider = "paymentValidateWithoutEndToEndIdentificationForSchedulePayments", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501007_Validate_Payments_initiation_Without_EndToEnd_identification"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * Test the EndToEndIdentification filed for null.
     * Flow : domestic payment and international payments flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateWithNullEndToEndIdentification", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401003_Validate_Payments_initiation_with_a_Null_EndToEnd_Identification"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * Test the EndToEnd identification filed for max length.
     * Flow :all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentValidateEndToEndIdentificationMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401004_Validate_Payments_initiation_EndToEnd_Identification_Parameter_For_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"12345-12345-12345-12345-12345-12345-12345-12345\" is too long\" +\n" +
                        "                        \" (length: 47, maximum allowed: 35)"))
    }

    /*
     * Test the initiation payload without the instruction identification.
     * Flow : domestic payment flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateWithoutInstructionIdentification", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401005_Validate_Payments_initiation_Without_Instruction_identification"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"InstructionIdentification\"])"))
    }

    /*
     * Test the instructed identification field for null values.
     * Flow :all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateInstructionIdentificationForNull", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401006_Validate_Payments_initiation_Instruction_Identification_For_Max_Length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * Test the instructed identification field for max length.
     * Flow :domestic payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateInstructionIdentificationMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401007_Validate_Payments_initiation_Instruction_Identification_For_Max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * This test validates the initiation payload for InstructedAmount.
     * Flow :All domestic /international payment flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateWithoutInstructedAmountPayload", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401008_Validate_Payments_initiation_validate_InstructedAmount_payload"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"InstructedAmount\"])"))
    }

    /*
     * Test the instructedAmount.amount field for invalid data type.
     * Flow : domestic payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateWithoutInstructedAmount_Amount", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401009_Validate_Payments_initiation_flows_without_the_InstructedAmount_Amount"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Amount\"])"))
    }

    /*
     * Test the instructed amount for invalid values.
     * Flow :all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "invalidPaymentInitiationConsentTypes", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401010_Validate_Payments_Initiation_With_Invalid_InstructedAmount_Amount"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"abc\""))
    }

    /*
     * Test the instructed amount for invalid values.
     * Flow :all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "invalidStringPaymentInitiationConsentTypes", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401112_Validate_Payments_Initiation_With_Invalid_String_InstructedAmount_Amount"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instructed Amount specified should be grater than zero"))
    }

    /*
     * Test the instructed amount for null values.
     * Flow :all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "nullInstructedAmount", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401011_Validate_Payments_Initiation_For_Null_Value_In_InstructedAmount_Amount"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"\""))
    }

    /*
    * Test the instructed amount without the mandatory currency parameter.
    * Flow :all domestic and international flows.
    * Expected status code is 400.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "withoutInstructedAmountCurrency", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401012_Validate_Payments_Initiation_For_Null_Value_In_InstructedAmount_Currency"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Currency\"])"))
    }

    /*
     * Test the instructed amount for against the MaximumInstructedAmount of open-banking.xml.
     * Flow :all domestic payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateInstructedAmountMaxLimit", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401013_Validate_Payments_Initiation_For_InstructedAmount_MaxLimit"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"1112121212122232200.00\""))
    }

    /*
     * Test the instructed amount currency parameter for invalid data type.
     * Flow :all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Unsupported.Currency.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateInstructedAmountCurrency", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401014_Validate_Payments_Initiation_For_Invalid_Currency"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"abc\""))
    }

    /*
     * Test the instructed amount currency parameter for null value.
     * Flow :all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Unsupported.Currency.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "NullInstructedAmountCurrency", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401015_Validate_Payments_Initiation_For_Null_Currency"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^[A-Z]{3,3}\$\" does not match input string \"\""))
    }

    /*
     * Test the LocalInstrument parameter for null value.
     * Flow : for domestic payments/domestic schedule payment/international payment/international schedule payment.
     * Expected status code is 400.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateLocalInstrument", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401016_Validate_Payments_initiation_for_LocalInstrument_Parameter"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * Test the DebtorAccount SchemeName.
     * Flow :domestic payment and domestic schedule payment.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentTypesWithoutDebtorAccount_SchemeName", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401017_Validate_Payments_Initiation_Without_Debtor_Account_SchemeName"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Mandatory parameter Debtor Account Scheme Name cannot be empty"))
    }

    /*
     * Test the debtor account schema type.
     * Flow :all domestic and international flows.
     * Expected status code is 400.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountSchemeNameType", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401018_Validate_Payments_Initiation_with_Debtor_Account_SchemeName_IBAN"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Debtor Account Scheme Name does not match with the Scheme Names defined in the specification"))
    }

    /*
     * Test the scheme Name parameter for invalid input.
     * Flow :all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Unsupported.Scheme.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "invalidSchemeName", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401019_Validate_Payments_initiation_with_Invalid_SchemeName"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Debtor Account Scheme Name does not match with the Scheme Names defined in the specification"))
    }

    /*
     *  Test the scheme Name parameter for maxLength.
     *  Flow :Domestic payments flow.
     *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    //TODO uncomment after https://github.com/wso2-enterprise/ob-compliance-toolkit-uk/issues/32
//    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
//            dataProvider = "validateDebtorAccountSchemeNameMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401020_Validate_Payments_initiation_SchemeName_MaxLength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Debtor Account Scheme Name length does not match with the length defined in the specification"))
    }

    /*
     * Test the initiation request without debtor account identification.
     * Flow :Domestic payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountWithoutIdentification", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401021_Validate_Payments_initiation_without_Identification"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Identification\"])"))
    }

    /*
     *  Test the debtor account identification for null values.
     *  flow :All domestic and international flows.
     *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountIdentification", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401022_Validate_Payments_Initiation_Flow_For_Null_Identification_Parameter"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     *  Test the debtor account length for 14 characters.
     *  flow :All domestic and international flows.
     *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Unsupported.AccountIdentifier.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountIdentificationLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401023_Validate_Payments_Initiation_DebtorAccount_Identification_length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Identification validation for SortCodeNumber Scheme failed."))
    }

    /*
     *  Test the debtor account identification parameter for for max length.
     *  flow :domestic payments flow.
     *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Unsupported.AccountIdentifier.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountIdentificationMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401024_Validate_Payments_Initiation_DebtorAccount_Identification_max_length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("(length: 419, maximum allowed: 256"))
    }

    /*
     *  Test the debtor account name parameter for for max length.
     *  Flow :All domestic  and international payments flow.
     *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountNameMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401025_Validate_Payments_Initiation_DebtorAccount_Name_max_length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * Test the debtor account name parameter for for max length.
     * Flow :All domestic  and international payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountNameForNullValues", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801086_Validate_Payments_Initiation_DebtorAccount_Name_forNull"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * Test the debtor account Secondary Identification parameter for max length.
     * Flow :All Domestic  and International payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountSecondaryIdentificationMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401026_Validate_Payments_Initiation_DebtorAccount_Secondary_identification_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" is too long\" +\n" +
                        "                        \" (length: 65, maximum allowed: 34)"))
    }

    /*
     * Test the debtor account Secondary Identification parameter for Null Values.
     * Flow :domestic payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateDebtorAccountSecondaryIdentificationForNull",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801089_Validate_Payments_Initiation_DebtorAccount_SecondaryIdentification_ForNull"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount Payload is missing.
     * Flow : All domestic and international flows.
     *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentWithoutCreditorAccount", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401027_Validate_Payments_Initiation_Request_Without_CreditorAccount_payload"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"CreditorAccount\"])"))
    }

    /*
    * The test for asserting the response code 400 if the CreditorAccount Scheme Name Payload is missing.
    * Flow : All domestic and international flows.
    *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],

            dataProvider = "paymentWithoutCreditorAccountSchemeName", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401028_Validate_Payments_Initiation_Request_Without_CreditorAccount_SchemeName"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"SchemeName\"])"))
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount Scheme Name Payload has a null value.
     * Flow : All domestic and international flows.
     *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorAccountNullSchemeName", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401029_Validate_Payments_Initiation_Request_With_Null_CreditorAccount_SchemeName"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Mandatory parameter Creditor Account Scheme Name does not exists"))
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount Scheme Name Payload exceeds the max length.
     * Flow : All domestic and international flows.
     *  Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    //TODO Uncomment after fixing https://github.com/wso2-enterprise/ob-compliance-toolkit-uk/issues/32
//    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
//            dataProvider = "validateCreditorAccountSchemeNameMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501044_Validate_Payments_Initiation_Request_With_CreditorAccount_SchemeName_Exceed_MaxLength"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Invalid scheme name"))
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount identification has less than 14 characters.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Unsupported.AccountIdentifier.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorAccountIdentificationLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401030_Validate_Payments_Initiation_Request_with_CreditorAccount_Identification_length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Identification validation for SortCodeNumber Scheme failed."))
    }

    /*
    * The test for asserting the response code 400 without the CreditorAccount identification parameter.
    * Flow : All domestic and international flows.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorAccountWithoutIdentification", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001084_Validate_Payments_Initiation_Request_without_CreditorAccount_Identification_parameter"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Identification\"])"))
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount identification has a null value.
     * Flow : Domestic and international payments.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],

            dataProvider = "validateCreditorAccountIdentificationForNull",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401031_Validate_Payments_Initiation_Request_With_CreditorAccount_Identification_For_Null"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount identification has more than the max length.
     * Flow : Domestic and international payments.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Unsupported.AccountIdentifier.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorAccountIdentificationMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501048_Validate_Payments_Initiation_Request_With_CreditorAccount_Identification_Exceeding_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("(length: 419, maximum allowed: 256"))
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount Name parameter has null values.
     * Flow :  All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorAccountNullName", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401032_Validate_Payments_Initiation_Request_With_CreditorAccount_Having_Null_Value_For_Name"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount name parameter exceeding max length.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorAccountNameMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401033_Validate_Payments_Initiation_Request_with_CreditorAccount_Name_Exceeding_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * The test for asserting the response code 400 if the CreditorAccount secondary identification parameter exceeding
     * max length.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAccountSecondaryIdentificationMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401034_Validate_Payments_Initiation_Request_With_CreditorAccount_SecondaryIdentification_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" \" +\n" +
                        "                        \"is too long (length: 65, maximum allowed: 34)"))
    }

    /*
    * The test for asserting the response code 400 if the CreditorAccount secondary identification parameter as null.
    * Flow : All domestic and international flows.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAccountSecondaryIdentificationMinLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC1001091_Validate_Payments_Initiation_Request_CreditorAccount_SecondaryIdentification_For_Min_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the Creditor department parameter exceeding max length.
     * Flow : Domestic payments and Domestic schedule payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorDepartmentForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401035_Validate_Payments_Request_with_Creditor_PostalAddress_Department_Parameter_For_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"department test value 121212121 2department test value 1 department test value 1\"\" +\n" +
                        "                        \" is too long (length: 80, maximum allowed: 70)"))
    }

    /*
     * The test for asserting the response code 400 if the Creditor department parameter less than the min length.
     * Flow : Domestic payments and Domestic schedule payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorDepartmentForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501055_Validate_Payments_Request_with_Creditor_PostalAddress_Department_Parameter_For_minlength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the Creditor Sub department parameter exceeding max length.
     * Flow : Domestic payments and domestic schedule payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorSubDepartmentForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401036_Validate_Payments_request_For_Creditor_PostalAddress_SubDepartment_Exceeding_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"department test value 121212121 2department test value 1 department test value 1\" \" +\n" +
                        "                        \"is too long (length: 80, maximum allowed: 70)"))
    }

    /*
    * The test for asserting the response code 400 if the Creditor Sub department parameter is less than the min length
    * max length.
    * Flow : Domestic payments and domestic schedule payments flow.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorSubDepartmentForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501058_Validate_Payments_request_For_Creditor_PostalAddress_SubDepartment_Min_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the Creditor PostalAddress BuildingNumber parameter exceeding
     * max length.
     * Flow : Domestic payments and Domestic Schedule Payments.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorBuildingNumberForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401037_Validate_Payments_request_For_Creditor_PostalAddress_BuildingNumber_Exceeding_Max_Length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"10234567891212435445\" is too long (length: 20, maximum allowed: 16)"))
    }

    /*
     * The test for asserting the response code 400 if the Creditor PostalAddress BuildingNumber parameter less than
     * minimum length.
     * Flow : Domestic payments and Domestic Schedule Payments.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorBuildingNumberForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501063_Validate_Payments_request_For_Creditor_PostalAddress_BuildingNumber_less_than_MinLength"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the PostCode parameter is less than the min length.
     * Flow : Domestic payments and Domestic schedule payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostCodeForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501066_Validate_Payments_request_For_Creditor_PostalAddress_PostCode_Less_than_Min_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the PostCode parameter exceeding the max length.
     * Flow : Domestic payments and Domestic schedule payments flow.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostCodeForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401038_Validate_Payments_request_For_Creditor_PostalAddress_PostCode_Exceeding_Max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"102121212-102121212-102121212\" is too long (length: 29, maximum allowed: 16)"))
    }

    /*
     * The test for asserting the response code 400 if the TownName parameter exceeding max length.
     * Flow : Domestic payments and Domestic schedule payments.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorTownNameForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401039_Validate_Payments_request_For_Creditor_PostalAddress_TownName_Exceeding_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"London-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" " +
                        "is too long (length: 72, maximum allowed: 35)"))
    }

    /*
     * The test for asserting the response code 400 if the TownName parameter less than the min length.
     * Flow : Domestic payments and Domestic schedule payments.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorTownNameForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501069_Validate_Payments_request_For_Creditor_PostalAddress_TownName_LessThan_MinLength"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the CountrySubDivision parameter exceeding max length.
     * Flow : Domestic payments and Domestic Schedule payments.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorCountrySubDivisionForMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401040_Validate_Payments_request_For_Creditor_PostalAddress_CountrySubDivision_Exceeding_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"London-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" " +
                        "is too long (length: 72, maximum allowed: 35)"))
    }

    /*
    * The test for asserting the response code 400 if the CountrySubDivision parameter for less than min length.
    * Flow : Domestic payments and Domestic Schedule payments.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorCountrySubDivisionForMinLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0501074_Validate_Payments_request_For_Creditor_PostalAddress_CountrySubDivision_Less_than_Min_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * The test for asserting the response code 400 if the AdderssType parameter exceeding max length.
    * Flow : Domestic payments and Domestic Schedule Payments.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorAddressTypeForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401041_Validate_Payments_request_For_Creditor_PostalAddress_AddressType_Exceeding_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"Correspondence-1212121212-1212121212-1212121212-1212121212-1212121212-" +
                        "1212121212\") not found in enum (possible values: [\"Business\",\"Correspondence\",\"" +
                        "DeliveryTo\",\"MailTo\",\"POBox\",\"Postal\",\"Residential\",\"Statement\"])"))
    }

    /*
    * The test for asserting the response code 400 if the AddressType parameter exceeding max number of items on the list.
    * Flow : Domestic payments and Domestic Schedule Payments.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorAddressTypeListOfItems", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401042_Validate_Payments_Request_For_Creditor_PostalAddress_AddressType_Exceeding_Max_Items_On_List_Of_Items"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"[Business, Correspondence, DeliveryTo, MailTo, POBox, Postal, Residential," +
                        " Statement, Residential]\") not found in enum (possible values: [\"Business\",\"" +
                        "Correspondence\",\"DeliveryTo\",\"MailTo\",\"POBox\",\"Postal\",\"Residential\",\"" +
                        "Statement\"])"))
    }

    /*
     * The test for asserting the response code 400 if the Remittance Information parameter exceeding Max length.
     * Flow : Domestic payments,Domestic Schedule payments,International payments and International Schedule payment flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateRemittanceInformationUnstructuredForMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401043_Validate_Payments_Initiation_Request_with_Remittance_Information_Unstructured_Exceeding_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"Internal ops code 5120101-1212121212-1212121212-1212121212-1212121212-" +
                        "1212121212-212121212-1212121212-1212121212-1212121212-Internal ops code 5120101" +
                        "-1212121212-1212121212-1212121212-1212121212-121212212-212121212-1212121212-" +
                        "1212121212-1212121212\" is too long (length: 246, maximum allowed: 140)"))
    }

    /*
    * The test for asserting the response code 400 if the Remittance Information parameter exceeding Min length.
    * Flow : Domestic payments,Domestic Schedule payments,International payments and International Schedule payment flows.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RemittanceInformationUnstructuredForMinLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0501081_Validate_Payments_Initiation_Request_with_Remittance_Information_Unstructured_For_Min_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the Remittance Information reference parameter exceeding max length.
     * Flow : Domestic payments,Domestic Schedule payments,International payments and International Schedule payment flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RemittanceInformationReferenceMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401044_Validate_Payments_Initiation_Request_with_Remittance_Information_Reference_Exceeding_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"FRESCO-101-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" " +
                        "is too long (length: 76, maximum allowed: 35)"))
    }

    /*
     * The test for asserting the response code 400 if the Remittance Information  reference parameter less than the Min
     * length.
     * Flow : Domestic payments,Domestic Schedule payments,International payments and International Schedule payment flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RemittanceInformationReferenceForMinLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0501084_Validate_Payments_Initiation_Request_with_Remittance_Information_Reference_for_min_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the Risk payload is missing.
     * Flow : Domestic payments and international payments.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateInitiationWithoutRiskPayload", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401045_Validate_Payments_Initiation_Request_Without_Risk_Payload"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Risk\"])"))
    }

    /*
     * The test for asserting the response code 400 if the MerchantCategoryCode is less than 3 characters.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateRiskPayloadParameters", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401046_Validate_Payments_Initiation_Request_With_Invalid_Merchant_Category_Code"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 3)"))
    }

    /*
     * The test for asserting the response code 400 if the MerchantCategoryCode is more than  the max length.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateMerchantCategoryCodeLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401047_Validate_Payments_Initiation_Request_with_Merchant_Category_Code_exceed_max_length"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"1234567890\" is too long (length: 10, maximum allowed: 4)"))
    }

    /*
     * The test for asserting the response code 400 if the MerchantCategoryCode is null for PaymentContxCodeEcommerceGoods.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateMerchantCategoryCodeForPaymentContexCodeEcommerceGoods",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0501089_Validate_Merchant_Category_Code_for_Payment_Context_Code"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 3)"))
    }

    /*
     * The test for asserting the response code 400 if the MerchantCategoryCode is null for PaymentContxCode is
     * EcommerceServices.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateMerchantCategoryCodeForPaymentContexCodeEcommerceServices",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401048_Validate_Merchant_Category_Code_for_Payment_Context_Code_EcommerceServices"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 3)"))
    }

    /*
     * The test for asserting the response code 400 if the delivery address not passed when payment context code is
     * max number of items on the list.
     * Flow : For domestic and international payment flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validatePaymentContextCodeEcomerceGoodsWithoutDeliveryAddress",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401049_Validate_DeliveryAddress_for_Payment_Context_Code_EcommerceGoods"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("EcommerceGoods context is set, but delivery address is not set"))
    }

    /*
     * The test for asserting the response code 400 if the address line has a value that exceed max length.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskAddressLineForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401050_Validate_Risk_AddressLine_Max_Length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"CME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-CME412-ACME412-" +
                        "ACME412-ACME412-ACME412-ACME412-ACME412-ACME412\" is too long" +
                        " (length: 125, maximum allowed: 70)"))
    }

    /*
     * The test for asserting the response code 400 if the address line is less than the min length.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressAddressLineForMinLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401051_Validate_Risk_DeliveryAddress_AddressLine_Min_Length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 201 if the initiation payload does not have the address line parameter.
     * Flow : Domestic payments.
     * Expected status code is 201 Created.
    */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0401052_Validate_Risk_DeliveryAddress_without_AddressLine_parameter"(){

        //initiation
        def initiationPayload = PaymentRequestPayloads.domesticPaymentWithoutRiskDeliveryAddressAddressLine
        def consentPath = ConnectorTestConstants.PAYMENT_CONSENT_PATH

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * The test for asserting the response code 400 if the street name has more than the allowed max length.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressStreetNameForMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401053_Validate_Risk_DeliveryAddress_StreetName_for_MaxLength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"delivery address street name test value 121212121 2department test value 1 " +
                        "department test value 1\" is too long (length: 98, maximum allowed: 70)"))
    }

    /*
     * The test for asserting the response code 400 if the Risk DeliveryAddress street name has a value that is less
     * than the min length.
     * Flow : All domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressStreetNameForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401054_Validate_Risk_DeliveryAddress_StreetName_for_MinLength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the street name parameter is not passed.
     * Flow : For  domestic payment and international payment flows.
     * Expected status code is 201 Created.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateRiskDeliveryAddressWithoutStreetName", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401055_Validate_Risk_DeliveryAddress_without_StreetName_for_201_response_code"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * The test for asserting the response code 400 if the Delivery Address Building Number has a value that is more
     * than the max length.
     * Flows: For all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressBuildingNumberForMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401056_Validate_Risk_DeliveryAddress_BuildingNumber_for_MaxLength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"10234567891212435445\" is too long (length: 20, maximum allowed: 16)"))
    }

    /*
     * The test for asserting the response code 400 if the Delivery Address Building Number has a value that is less than
     * the min length.
     * Flows: For all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressBuildingNumberForMinLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401057_Validate_Risk_DeliveryAddress_BuildingNumber_for_MinLength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 201 if the Delivery Address Building Number is not passed with the payload.
     * Flow : All Domestic and International payments flows.
     * Expected status code is 201 Created.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateRiskDeliveryAddressWithoutBuildingNumberParameter",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401058_Validate_Risk_DeliveryAddress_without_BuildingNumber_parameter_and_Assert_For_201_Response_Code"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * The test for asserting the response code 400 if the Delivery Address PostCode exceeds max length.
     * Flow : For all Domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressPostCodeForMaxLength", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401059_Validate_Risk_DeliveryAddress_PostCode_for_MaxLength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"102121212-102121212-102121212\" is too long (length: 29, maximum allowed: 16)"))
    }

    /*
     * The test for asserting the response code 400 if the Delivery Address PostCode parameter is less than the min length.
     * Flow : All Domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressPostCodeForMinLength", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401060_Validate_Risk_DeliveryAddress_PostCode_for_MinLength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 201 if the Delivery Address is passed without the PostCode parameter.
     * Flow : Domestic payments.
     * Expected status code is 201 Created.
     */
    @Test (groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0401061_Validate_Risk_DeliveryAddress_Without_PostCode_and_assert_For_201_Response_Code"() {

        def initiationPayload = PaymentRequestPayloads.domesticPaymentWithoutDeliveryAddressPostCode
        def consentPath = ConnectorTestConstants.PAYMENT_CONSENT_PATH

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }


    /*
     * The test for asserting the response code 400 if the Delivery Address TownName is less than the min length.
     * Flows: For all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressTownNameForMinLength", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401062_Validate_Risk_DeliveryAddress_TownName_for_Min_Length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 400 if the Delivery Address TownName is more than the max length.
     * Flows: For all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressTownNameForMaxLength", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401063_Validate_Risk_DeliveryAddress_TownName_for_Max_Length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"London-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" is too" +
                        " long (length: 72, maximum allowed: 35)"))
    }

    /*
     * The test for asserting the response code 400 if the Delivery Address CountrySubDivision is more than the max
     * length.
     * Flows: For all domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressCountrySubDivisionForMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401064_Validate_Risk_DeliveryAddress_CountrySubDivision_for_Max_Length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"London-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" is too " +
                        "long (length: 72, maximum allowed: 35)"))
    }

    /*
    * The test for asserting the response code 400 if the Delivery Address Country does not meet the  regex pattern.
    * Flows: For all domestic and international flows.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateRiskDeliveryAddressCountry",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC1001455_Validate_Risk_DeliveryAddress_Country_for_RegEx_Pattern"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
    * The test for asserting the response code 400 if the Delivery Address CountrySubDivision is less than the min length.
    * Flows: For all domestic and international flows.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressCountrySubDivisionForMinLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401065_Validate_Risk_DeliveryAddress_CountrySubDivision_for_Min_Length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * The test for asserting the response code 201 if the DeliveryAddress does not have the CountrySubDivision parameter.
     * Flow : Domestic payments.
     * Expected status code is 201 Created.
     */
    @Test (groups = [ "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0401066_Validate_Risk_DeliveryAddress_without_CountrySubDivision_and_assert_for_201_Response_Code"() {

        //initiation
        def initiationPayload = PaymentRequestPayloads.domesticPaymentWithoutRiskDeliveryAddressCountrySubDivisionParameter
        def consentPath = ConnectorTestConstants.PAYMENT_CONSENT_PATH

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * The test for asserting the response code 400 if the DeliveryAddress does not have the Country parameter.
     * Flow : All Domestic and International payment flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressWithoutCountryParameter", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401067_Validate_Risk_DeliveryAddress_without_Country_parameter_and_assert_for_400_Response_Code"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Country\"])"))
    }

    /*
     * The test for asserting the response code 400 if the DeliveryAddress Country parameter is less than min length.
     * Flow : For all Domestic and international flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "RiskDeliveryAddressCountryForMinLength", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401068_Validate_Risk_DeliveryAddress_with_Country_parameter_LessThan_MinLength"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * The test for asserting the response code 400 if the Authorisation.AuthorisationType is not passed with the payload.
     * Flow :All international and domestic payment flows.
     * Expected status code is 400.
     *
     * */
    @Test(dataProvider = "validateAuthorisationPayloadForAuthorisationType",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401069_Validate Payment Initiation without Authorisation_AuthorisationType"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"AuthorisationType\"])"))
    }


    /*
    * The test for asserting the response code 400 if the Authorisation.AuthorisationType having an unknown value.
    * Flow : All international and domestic payment flows.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateAuthorisationPayloadForAuthorisationTypeValue",
            dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401070_Validate_Authorisation_AuthorisationType_For_Unknown_Value"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"abc\") not found in enum (possible values: [\"Any\",\"Single\"])"))
    }

    /*
    * The test for asserting the response code 400 if the Authorisation.AuthorisationType having a null value.
    * Flow : All international and domestic payment flows.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateNullAuthorisationTypeValue", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401071_Validate Payment Initiation with null Authorisation_AuthorisationType"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"\") not found in enum (possible values: [\"Any\",\"Single\"])"))
    }

    /*
     * The test for asserting the response code 400 if the Authorisation.CompletionDateTime for unknown value.
     * Flow : All international and domestic payment flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.InvalidDate.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateForInvalidCompletionDateTimeValue", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401072_Validate_Authorisation_CompletionDateTime_For_Invalid_Value"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"2019-05-01T12\" is invalid against requested date format(s) [yyyy-MM-dd'T'HH:mm:ssZ," +
                        " yyyy-MM-dd'T'HH:mm:ss.[0-9]{1,12}Z]"))
    }

    /*
    * The test for asserting the response code 400 if the Authorisation.CompletionDateTime for null value.
    * Flow : All domestic and international payloads.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.InvalidDate.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateForNullCompletionDateTimeValue", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0901120_Validate_Authorisation_CompletionDateTime_For_Null_Value"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is invalid against requested date format(s) [yyyy-MM-dd'T'HH:mm:ssZ, " +
                        "yyyy-MM-dd'T'HH:mm:ss.[0-9]{1,12}Z]"))
    }

    /*
     * The test for asserting the response code 201 if the Authorisation.CompletionDateTime is not passed.
     * Flow : Domestic payments and international payment flows.
     * Expected status code is 201 Created.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "WithoutCompletionDateTimeParameter", dataProviderClass = PaymentRequestPayloads.class)

    void "TC0401073_Validate_Authorisation_CompletionDateTime_Parameter"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * The test for asserting the response code 400 if the initiation payload has a schema violation.
     * Flow : All Domestic and International flows.
     * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.InvalidFormat.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentPayloadWithUnrelatedValues", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401074_Validate_Initiation_Payload_for_Unrelated_Values"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
    * The test for asserting the response code 400 if the initiation payload has a wrong payload mapping.
    * Flow : Domestic payments.
    * Expected status code is 400 with OBIE ErrorCode of UK.OBIE.Field.InvalidFormat.
    */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0401075_Validate_Initiation_Payload_With_Wrong_Payload_Mapping"() {

        //initiation
        def initiationPayload = PaymentRequestPayloads.domesticPaymentWithWrongPayloadMapping
        def consentPath = ConnectorTestConstants.PAYMENT_CONSENT_PATH

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object instance has properties which are not allowed by the schema: [\"Authorisation\"]"))
    }

    /*
     * Test the initiation flow with empty payload.
     * This test case is to check the happy path for all domestic and international payments.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Missing.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0401083_Validate_Payments_Initiation_Request_With_Empty_Payload"() {

        //initiation
        def initiationPayload = PaymentRequestPayloads.paymentInitiationConsentTypesWithEmptyPayload
        def consentPath = ConnectorTestConstants.PAYMENT_CONSENT_PATH

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Data\",\"Risk\"])"))
    }

    /*
     * Test the initiation flow with empty string as the  payload.
     * This test case is to check the happy path for all domestic and international payments.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Resource.InvalidFormat.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypesWithEmptyStringPayload", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401096_Payments_Initiation"(Map<String, String> maps) {

        def paymentMap = [:]

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
    * This test case is to check the domestic schedule permission for invalid permission.
    * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Missing.
    * Flows: Domestic Schedule Payment/ International Schedule Payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "SchedulePaymentsWithoutPermission", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501002_Validate_Schedule_Payments_Initiation_without_Permission"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Permission\"])"))
    }

    /*
     * This test case is to check the domestic schedule permission for invalid permission.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Missing.
     * Flows: Domestic Schedule Payment/ International Schedule Payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "PaymentWithInvalidPermission", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501003_Validate_Schedule_Payments_Initiation_with_Invalid_Permission"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"Create123\") not found in enum (possible values: [\"Create\"])"))
    }

    /*
     * This test case is to check the domestic schedule permission for invalid permission.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     * Flows: Domestic Schedule Payment/ International Schedule Payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "SchedulePaymentWithNullValueForPermission", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501004_Validate_Schedule_Payments_Initiation_With_Null_Value_For_Permission"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"\") not found in enum (possible values: [\"Create\"])"))
    }

    /*
     * This test case is to check the domestic standing order permission for invalid permission.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithInvalidPermission", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601009_Validate_Standing_Order_Payloads_Invalid_Permission"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * This test case is to check the domestic standing order without permission parameter.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601010_Validate_SDomestic_Standing_Order_Initiation_without_the_Permission_parameter"() {

        //initiation
        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrdersWithoutPermission
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Permission\"])"))
    }

    /*
     * This test case is to check the domestic standing order permission field for null value.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601011_Validate_Domestic_Standing_Order_Initiation_with_null_value_for_Permission_parameter"(){

        //initiation
        def paymentMap = ["Permission" : ""]

        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap)
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"\") not found in enum (possible values: [\"Create\"])"))
    }

    /*
     * This test case is to check the domestic standing order without the  frequency parameter.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithoutFrequencyParameter", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601013_Validate_Domestic_Standing_Order_Initiation_without_the_Frequency_parameter"(Map<String, String> maps) {

        //initiation
        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Frequency\"])"))
    }

    /*
     * This test case is to check the domestic standing order with null value for the  frequency parameter.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderPayloadWithNullValueForFrequency", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601014_Validate_Standing_Order_Initiation_with_null_value_for_the_Frequency_parameter"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains(" ECMA 262 regex \"^(EvryDay)\$|^(EvryWorkgDay)\$|^(IntrvlDay:((0[2-9])|([1-2]" +
                        "[0-9])|3[0-1]))\$|^(IntrvlWkDay:0[1-9]:0[1-7])\$|^(WkInMnthDay:0[1-5]:0[1-7])\$|^(IntrvlMnthDay:" +
                        "(0[1-6]|12|24):(-0[1-5]|0[1-9]|[12][0-9]|3[01]))\$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))\$\"" +
                        " does not match input string"))
    }

    /*
    * This test case is to check the standing order payloads for Frequency EvryDay.
    * Payloads: Domestic and International Standing Order.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderPayloadValueForFrequencyAsEvryDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001023_Validate_Standing_Order_Initiation_with_Frequency_parameter_AsEvryDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
    * This test case is to check the domestic standing order with Invalid Frequency Pattern For EvryDay.
    * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidFrequencyPatternForEvryDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001024_Validate_Standing_Order_Initiation_With_InvalidFrequencyPatternForEvryDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains(" ECMA 262 regex \"^(EvryDay)\$|^(EvryWorkgDay)\$|^(IntrvlDay:((0[2-9])|([1-2]" +
                        "[0-9])|3[0-1]))\$|^(IntrvlWkDay:0[1-9]:0[1-7])\$|^(WkInMnthDay:0[1-5]:0[1-7])\$|^(IntrvlMnthDay:" +
                        "(0[1-6]|12|24):(-0[1-5]|0[1-9]|[12][0-9]|3[01]))\$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))\$\"" +
                        " does not match input string"))
    }

    /*
     * This test case is to check the standing order payloads for Frequency EvryWorkgDay.
     * Payloads: Domestic and International Standing Order.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderPayloadValueForFrequencyAsEvryWorkgDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001025_Validate_Standing_Order_Initiation_with_Frequency_parameter_As_EvryWorkgDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This test case is to check the domestic standing order with Invalid Frequency Pattern For EvryWorkgDay.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidFrequencyPatternForEvryWorkgDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001026_Validate_Standing_Order_Initiation_With_InvalidFrequencyPatternFor_EvryWorkgDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^(EvryDay)\$|^(EvryWorkgDay)\$|^(IntrvlDay:((0[2-9])|([1-2][0-9])|3[0-1]))\$|" +
                        "^(IntrvlWkDay:0[1-9]:0[1-7])\$|^(WkInMnthDay:0[1-5]:0[1-7])\$|^(IntrvlMnthDay:(0[1-6]|12|24):" +
                        "(-0[1-5]|0[1-9]|[12][0-9]|3[01]))\$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))\$\" " +
                        "does not match input string \"EvryWorkgDay123\""))
    }

    /*
     * This test case is to check the standing order payloads for Frequency IntrvlDay:15.
     * Payloads: Domestic and International Standing Order.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderPayloadValueForFrequencyAsIntrvlWkDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001027_Validate_Standing_Order_Initiation_with_Frequency_parameter_As_IntrvlDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
    * This test case is to check the domestic standing order with Invalid Frequency Pattern For IntrvlWkDay.
    * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidFrequencyPatternForIntrvlWkDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001028_Validate_Standing_Order_Initiation_With_InvalidFrequencyPatternFor_IntrvlWkDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^(EvryDay)\$|^(EvryWorkgDay)\$|^(IntrvlDay:((0[2-9])|([1-2][0-9])|3[0-1]))\$|" +
                        "^(IntrvlWkDay:0[1-9]:0[1-7])\$|^(WkInMnthDay:0[1-5]:0[1-7])\$|^(IntrvlMnthDay:(0[1-6]|12|24):" +
                        "(-0[1-5]|0[1-9]|[12][0-9]|3[01]))\$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))\$\" " +
                        "does not match input string \"IntrvlWkDay\""))
    }

    /*
    * This test case is to check the standing order payloads for Frequency IntrvlMnthDay.
    * Payloads: Domestic and International Standing Order.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderPayloadValueForFrequencyAsIntrvlMnthDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001029_Validate_Standing_Order_Initiation_with_Frequency_parameter_As_IntrvlMnthDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This test case is to check the domestic standing order with Invalid Frequency Pattern For IntrvlMnthDay.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidFrequencyPatternForIntrvlMnthDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001030_Validate_Standing_Order_Initiation_With_InvalidFrequencyPatternFor_IntrvlMnthDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^(EvryDay)\$|^(EvryWorkgDay)\$|^(IntrvlDay:((0[2-9])|([1-2][0-9])|3[0-1]))\$|" +
                        "^(IntrvlWkDay:0[1-9]:0[1-7])\$|^(WkInMnthDay:0[1-5]:0[1-7])\$|^(IntrvlMnthDay:(0[1-6]|12|24):" +
                        "(-0[1-5]|0[1-9]|[12][0-9]|3[01]))\$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))\$\" " +
                        "does not match input string \"IntrvlMnthDay\""))
    }

    /*
    * This test case is to check the domestic standing order with Invalid Frequency Pattern For IntrvlMnthDay.
    * Expected response code is 201.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderPayloadValueForFrequencyAsWkInMnthDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001031_Validate_Standing_Order_Initiation_With_InvalidFrequencyPatternFor_WkInMnthDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This test case is to check the domestic standing order with Invalid Frequency Pattern For WkInMnthDay.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidFrequencyPatternForIWkInMnthDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001032_Validate_Standing_Order_Initiation_With_InvalidFrequencyPatternFor_WkInMnthDay"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^(EvryDay)\$|^(EvryWorkgDay)\$|^(IntrvlDay:((0[2-9])|([1-2][0-9])|3[0-1]))\$|" +
                        "^(IntrvlWkDay:0[1-9]:0[1-7])\$|^(WkInMnthDay:0[1-5]:0[1-7])\$|^(IntrvlMnthDay:(0[1-6]|12|24):" +
                        "(-0[1-5]|0[1-9]|[12][0-9]|3[01]))\$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))\$\" " +
                        "does not match input string \"WkInMnthDay123\""))
    }

    /*
     * This test case is to check the domestic standing order with Invalid Frequency Pattern For QtrDay.
     * Expected response code is 201.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderPayloadValueForFrequencyAsQtrDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001033_Validate_Standing_Order_Initiation_With_InvalidFrequencyPatternFor_QtrDay"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
    * This test case is to check the domestic standing order with Invalid Frequency Pattern For QtrDay.
    * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidFrequencyPatternForQtrDay", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001034_Validate_Standing_Order_Initiation_With_InvalidFrequencyPatternFor_QtrDay"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^(EvryDay)\$|^(EvryWorkgDay)\$|^(IntrvlDay:((0[2-9])|([1-2][0-9])|3[0-1]))\$|" +
                        "^(IntrvlWkDay:0[1-9]:0[1-7])\$|^(WkInMnthDay:0[1-5]:0[1-7])\$|^(IntrvlMnthDay:(0[1-6]|12|24):" +
                        "(-0[1-5]|0[1-9]|[12][0-9]|3[01]))\$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))\$\" " +
                        "does not match input string \"QtrDay123\""))

    }

    /*
     * This test case is to check the domestic standing order with Invalid Frequency Pattern For QtrDay.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateFrequencyMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001037_Validate_Standing_Order_Initiation_With_Frequency_Exceeding_Max_Length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * This test case is to check the domestic standing order with invalid value for the frequency.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Unsupported.Frequency.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithInvalidValueForFrequency", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601015_Validate_Standing_Order_Initiation_with_invalid_frequency_parameter"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^(EvryDay)\$|^(EvryWorkgDay)\$|^(IntrvlDay:((0[2-9])|([1-2][0-9])|3[0-1]))\$|" +
                        "^(IntrvlWkDay:0[1-9]:0[1-7])\$|^(WkInMnthDay:0[1-5]:0[1-7])\$|^(IntrvlMnthDay:(0[1-6]|12|24):" +
                        "(-0[1-5]|0[1-9]|[12][0-9]|3[01]))\$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))\$\" " +
                        "does not match input string \"EvryWorkgDay123\""))
    }

    /*
     * This test case is to check the domestic standing order with null value for reference.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithNullReference", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601017_Validate_Standing_Order_Initiation_with_Null_Value_For_Reference"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This test case is to check the domestic standing order with reference exceeding max length.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ReferenceExceedingMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601018_Validate_Standing_Order_Initiation_with_Reference_exceeding_max_length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains(" is too long (length: 76, maximum allowed: 35)"))
    }

    /*
    * This test case is to check the  standing order without initiation reference payload.
    * Payloads :Domestic/International Standing Order payloads.
    * Expected response code is 201.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithoutReference", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601019_Validate_Standing_Order_Initiation_without_References_and_Assert_201_Created"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This test case is to check the domestic standing order with a null value for NumberOfPayments parameter.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateNullValueForNumberOfPayments", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601020_Validate_Standing_Order_Initiation_with_a_Null_Value_For_NumberOfPayments"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This test case is to check the domestic standing order with a null value for NumberOfPayments parameter.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "NumberOfPaymentsExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601021_Validate_Standing_Order_Initiation_with_NumberOfPayments_exceed_max_length"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains(" String \"101-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" " +
                        "is too long (length: 69, maximum allowed: 35)"))
    }

    /*
     * This test case is to check the domestic standing order without initiation NumberOfPayments.
     * Expected response code is 201.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithoutNumberOfPayments", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601022_Validate_Standing_Order_Initiation_without_NumberOfPayments_and_Assert_201_Created"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This test case is to check the domestic standing order with an invalid FirstPaymentDateTime.
     * Expected response code is 400 and OBIE error code of UK.OBIE.Field.InvalidDate.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithInvalidFirstPaymentDateTime", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601023_Validate_Standing_Order_Initiation_with_Invalid_FirstPaymentDateTime"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"2019-11-02\" is invalid against requested date format(s) [yyyy-MM-dd'T'HH:mm:ssZ, " +
                        "yyyy-MM-dd'T'HH:mm:ss.[0-9]{1,12}Z]"))
    }

    /*
    * This test case is to check the domestic standing order without the FirstPaymentDateTime.
    * Expected response code is 400 and OBIE error code of UK.OBIE.Field.Missing.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithoutFirstPaymentDateTime", dataProviderClass =PaymentRequestPayloads.class)
    void "TC0601024_Validate_Standing_Order_Initiation_without_FirstPaymentDateTime"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"FirstPaymentDateTime\"])"))
    }

    /*
     * This test case is to check the domestic standing order with a null value for  the FirstPaymentDateTime.
     * Expected response code is 400 and OBIE error code of UK.OBIE.Field.InvalidDate.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "NullFirstPaymentDateTime", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601025_Validate_Standing_Order_Initiation_with_null_FirstPaymentDateTime"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is invalid against requested date format(s) [yyyy-MM-dd'T'HH:mm:ssZ, yyyy-MM-dd'" +
                        "T'HH:mm:ss.[0-9]{1,12}Z]"))
    }

    /*
     * This test case is to check the domestic standing order with a null value for  the FirstPaymentDateTime.
     * Expected response code is 400 and OBIE error code of UK.OBIE.Field.InvalidDate.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC1001046_Validate_Standing_Order_Initiation_with_Old_FirstPaymentDateTime"() {

        //initiation
        def paymentMap = ["FirstPaymentDateTime" : "2010-11-25T10:38:53.141Z"]
        def initiationPayload = PaymentRequestPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap)
        def consentPath = ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("First Payment Date Time value cannot be set in the past"))
    }

    /*
     * This test case is to check the domestic standing order with a null value for  the RecurringPaymentDateTime.
     * Expected response code is 400 and OBIE error code of UK.OBIE.Field.InvalidDate.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601026_Validate_Standing_Order_Initiation_with_invalid_RecurringPaymentDateTime"() {

        //initiation
        def paymentMap = ["RecurringPaymentDateTime": "2019-02-01"]

        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap)
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"2019-02-01\" is invalid against requested date format(s) " +
                        "[yyyy-MM-dd'T'HH:mm:ssZ, yyyy-MM-dd'T'HH:mm:ss.[0-9]{1,12}Z]"))
    }

    /*
    * This test case is to check the domestic standing order without  the RecurringPaymentDateTime.
    * Payloads :Domestic Standing Order payload.
    * Expected response code is 201 Created.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithoutRecurringPaymentDateTime", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601027_Validate_Standing_Order_Initiation_Payloads_without_the_RecurringPaymentDateTime"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
    * This test case is to check the domestic standing order with invalid value for the FinalPaymentDateTime.
    * Expected response code is 400 with an OBIE error code of UK.OBIE.Field.InvalidDate.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderWithInvalidFinalPaymentDateTime", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601028_Validate_Standing_Order_Initiation_with_Invalid_FinalPaymentDateTime"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains(" String \"2011-01-01\" is invalid against requested date format(s) [yyyy-MM-dd'T'HH:mm:ssZ," +
                        " yyyy-MM-dd'T'HH:mm:ss.[0-9]{1,12}Z]"))
    }

    /*
     * This test case is to check the  FirstPaymentDateTime is before the FinalPaymentDateTime.
     * Expected response code is 400 with an OBIE Error code of UK.OBIE.Field.InvalidDate.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "StandingOrderPaymentDateTimeValidation", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601029_Validate_Standing_Order_Validate_FirstPaymentDateTime_Is_Before_FinalPaymentDateTime"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Final Payment Date Time value cannot be before the first payment date time"))
    }

    /*
     * This test case is to check the  standing order without the  FinalPaymentDateTime parameter on the initiation
     * payload.
     * Flows: Domestic Standing Order/International Standing Order.
     * Expected response code is 201 Created.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "WithoutFinalPaymentDateTime", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0601030_Validate_Standing_Order_Initiation_without_FinalPaymentDateTime"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This test case is to check the domestic standing order with invalid value for the FirstPaymentAmount.
     * Expected response code is 400 and OBIE Error Code of UK.OBIE.Field.Missing.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601031_Validate_Domestic_Standing_Order_Initiation_with_Invalid_FirstPaymentAmount"(){

        //initiation
        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrdersWithoutFirstPaymentAmount
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"FirstPaymentAmount\"])"))
    }

    /*
     * This test case is to check the domestic standing order without Amount parameter of FirstPaymentAmount payload.
     * Expected response code of 400 with an OBIE error code of UK.OBIE.Field.Missing.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601032_Validate_Domestic_Standing_Order_Initiation_without_FirstPaymentAmount_Amount"() {

        //initiation
        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrdersWithoutFirstPaymentAmount_Amount
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Amount\"])"))
    }

    /*
     * This test case is to check the domestic standing order with invalid value for the FirstPaymentAmountPayload.
     * Expected response code of 400.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601033_Validate_Domestic_Standing_Order_Initiation_with_Invalid_FirstPaymentAmount_Amount"() {

        //initiation
        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrderPayment
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"abc\""))
    }

    /*
     * This test case is to check the domestic standing order with invalid value for the FirstPaymentAmountPayload.
     * Expected response code of 400.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601034_Validate_Domestic_Standing_Order_Initiation_with_FirstPaymentAmount_Exceeding_Max_Limit"() {

        //initiation
        def paymentMap = ["FirstPaymentAmount": ConnectorTestConstants.FIRST_PAYMENT_AMOUNT]

        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap)
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
    * This test case is to check the domestic standing order with invalid value for the FirstPaymentAmountPayload.
    * Expected response code of 400 with an OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601035_Validate_Domestic_Standing_Order_Initiation_with_null_FirstPaymentAmount"() {

        //initiation
        def paymentMap = ["FirstPaymentAmount" : PaymentsConstants.NULL]

        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap)
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"\""))
    }

    /*
     * This test case is to check the domestic standing order with invalid value for the FirstPaymentAmountPayload.
     * Expected response code of 400 with an OBIE Error Code of UK.OBIE.Field.Missing.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601036_Validate_Domestic_Standing_Order_Initiation_without_FirstPaymentAmount_Currency_Parameter"() {

        //initiation
        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrdersWithoutFirstPaymentAmount_Currency
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Currency\"])"))
    }

    /*
     * This test case is to check the domestic standing order with invalid value for the FirstPaymentAmount Currency.
     * Expected response code of 400 with an OBIE Error Code of UK.OBIE.Unsupported.Currency.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0601037_Validate_Domestic_Standing_Order_Initiation_with_Invalid_FirstPaymentAmount_Currency_Parameter"() {

        def paymentMap = ["FirstPaymentCurrency" : "abc"]

        def initiationPayload = PaymentRequestPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap)
        def consentPath = ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS
        //initiation

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"abc\""))
    }

    /*
     * This test case is to check the international payments with invalid value for CreditorAgent AddressType Parameter.
     * Expected response code of 400 with an OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentAddressTypeForInvalidValues", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801121_Validate_International_Payment_Initiation_with_Invalid_CreditorAgent_AddressType_Parameter"(Map<String,
            String> maps) {

        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"Business123\") not found in enum (possible values: [\"Business\"," +
                        "\"Correspondence\",\"DeliveryTo\",\"MailTo\",\"POBox\",\"Postal\",\"Residential\"," +
                        "\"Statement\"])"))
    }

    /*
     * This test case is to check the international payments with invalid value for CreditorAgent AddressType Parameter.
     * Expected response code of 400 with an OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(dataProvider = "CreditorAgentSchemeNameForNullValues", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901157_Validate_International_Payment_Initiation_with_empty_CreditorAgent_SchemeName_Parameter"(Map<String,
            String> maps) {

        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Invalid Creditor Agent scheme name"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent SchemeName exceeding Maxlength.
     * Expected response code of 400 with an OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentSchemeNameForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901158_Validate_International_Payment_Initiation_with_CreditorAgent_SchemeName_Parameter_Exceed_MaxLength" (Map<String,
            String> maps) {

        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * This test case is to check the international payments without CreditorAgent SchemeName.
     * Expected response code of 400 with an OBIE Error Code of UK.OBIE.Field.Expected.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentWithoutSchemeName", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801173_Validate_International_Payment_Initiation_without_CreditorAgent_SchemeName"(Map<String, String> maps) {

        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Must specify either Scheme Name and Identification or Name and Postal Address for " +
                        "creditor agent"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent SchemeName exceeding Maxlength.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentIdentificationForNull", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901160_Validate_International_Payment_Initiation_with_CreditorAgent_Identification_Parameter_Null_Value"(Map<String,
            String> maps) {

        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent SchemeName exceeding Maxlength.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentIdentificationForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901161_Validate_International_Payment_Initiation_with_CreditorAgent_Identification_Parameter_Exceeding_MaxLength"(Map<String,
            String> maps) {

        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("(length: 419, maximum allowed: 35)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent Name for null values.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentNameForNullValues", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901162_Validate_International_Payment_Initiation_with_CreditorAgent_Name_Parameter_For_Null_Values"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent Name for null values.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentNameExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901163_Validate_International_Payment_Initiation_with_CreditorAgent_Name_Parameter_Exceed_MaxLength"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains(" too long (length: 249, maximum allowed: 140)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent Name for null values.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentAddressType", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901165_Validate_International_Payment_Initiation_with_CreditorAgent_AddressType_Parameter_Is_Invalid"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"Business123\") not found in enum (possible values: [\"Business\"," +
                        "\"Correspondence\",\"DeliveryTo\",\"MailTo\",\"POBox\",\"Postal\",\"Residential\"" +
                        ",\"Statement\"])"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent Department Parameter less than the min
     * length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "NullValueForCreditorAgentDepartment", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801122_Validate_International_Payment_Initiation_with_CreditorAgent_Department_Parameter_less_than_MinLength"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent Department Parameter exceed max length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentDepartmentExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801123_Validate_International_Payment_with_CreditorAgent_Department_Parameter_more_than_MaxLength"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"department test value 121212121 2department test value 1 department test value 1\" " +
                        "is too long (length: 80, maximum allowed: 70)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent SubDepartment Parameter
     * less than the min length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentSubDepartmentMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801127_Validate_International_Payment_CreditorAgent_SubDepartment_Parameter_less_than_Min_Length" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This test case is to check the international payments  with CreditorAgent SubDepartment Parameter less than the
     * min length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentSubDepartmentExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801128_Validate_International_Payment_CreditorAgent_SubDepartment_Parameter_Exceed_Max_Length" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"department test value 121212121 2department test value 1 department test value 1\"" +
                        " is too long (length: 80, maximum allowed: 70)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent StreetName Parameter less than the min
    * length.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentStreetNameMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801129_Validate_International_Payment_CreditorAgent_StreetName_Parameter_For_MinLength" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent StreetName Parameter is more than the
     * max length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentStreetNameExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801130_Validate_International_Payment_CreditorAgent_StreetName_Parameter_Exceed_Max_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"delivery address street name test value 121212121 2department test value 1 department " +
                        "test value 1\" is too long (length: 98, maximum allowed: 70)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent Building Number Parameter less than the
    * min length.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentBuildingNumberForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801132_Validate_International_Payment_CreditorAgent_BuildingNumber_Parameter_For_Min_Length" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent Building Parameter is more than the max
    * length.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentBuildingNumberExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801133_Validate_International_Payment_CreditorAgent_BuildingNumber_Parameter_is_More_Than_Max_Length" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"10234567891212435445\" is too long (length: 20, maximum allowed: 16)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent PostCode is less than the min length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentPostCodeForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801135_Validate_International_Payment_CreditorAgent_PostCode_Parameter_is_Less_Than_Min_Length" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent PostCode Parameter is exceed the max length.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentPostCodeExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801136_Validate_International_Payment_CreditorAgent_PostCode_Parameter_Exceed_Max_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"102121212-102121212-102121212\" is too long (length: 29, maximum allowed: 16)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent Town name parameter for min length.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentTownNameForMinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801138_Validate_International_Payment_CreditorAgent_TownName_Parameter_For_Min_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent TownName Paramete is exceed the max length.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentTownNameExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801139_Validate_International_Payment_CreditorAgent_TownName_Parameter_Exceed_Max_Length" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"London-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" is too" +
                        " long (length: 72, maximum allowed: 35)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent Country Parameter for invalid regex.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentCountryCode", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801141_Validate_International_Payment_CreditorAgent_County_For_Invalid_Regex" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"TestCountry\""))
    }

    /*
     * This test case is to check the international payments with CreditorAgent Country Parameter for minimum length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentCountryCodeMinLengthValidation", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901153_Validate_International_Payment_CreditorAgent_County_For_MinLength" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^[A-Z]{2,2}\$\" does not match input string \"\""))
    }

    /*
    * This test case is to check the international payments with CreditorAgent AddressLine Parameter for minimum length.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    * Flows:International payments/international schedule payments.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentAddressLineMinLengthValidation", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901189_Validate_International_Payment_CreditorAgent_AddressLine_For_MinLength" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent AddressLine Parameter for max length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     * Flows:International payments/international schedule payments.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentAddressLineMaxLengthValidation", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001422_Validate_International_Payment_CreditorAgent_AddressLine_For_MaxLength" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"CME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-CME412-ACME412-ACME" +
                        "412-ACME412-ACME412-ACME412-ACME412-ACME412\" is too long (length: 125, maximum allowed: 70)"))
    }

    /*
     * This test case is to check the international payments with CreditorAgent CountrySubDivision for min length.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentCountrySubDivision", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801143_Validate_International_Payment_CreditorAgent_CountySub_Division_Min_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This test case is to check the international payments with CreditorAgent CountrySubDivision for max length.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CreditorAgentCountrySubDivisionExceedMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801144_Validate_International_Payment_CreditorAgent_CountySub_Division_Exceed_Max_Length" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"London-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" is too " +
                        "long (length: 72, maximum allowed: 35)"))
    }

    /*
    * This test case is to check the international payments and  validate the Risk Merchant CategoryCode.
    * Flows: domestic  payment flows.
    * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
    */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0801151_Validate_International_Payment_MerchantCategoryCode_When_PaymentContextCode_EcommerceGoods" () {

        //initiation
        def paymentMap = ["PaymentContextCode": "EcommerceGoods", "MerchantCategoryCode": ""]
        def initiationPayload = PaymentRequestPayloads.initiationPayloadInternationalPayment(paymentMap)
        def consentPath = ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS

        //initiation
        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 3)"))
    }

    /*
     * This test case is to check the international payments and  validate the Risk Merchant CategoryCode.
     * Flows all international payment flows.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "internationalPaymentValidateMerchantCustomerIdentification",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801152_Validate_International_Payment_MerchantCustomerIdentification_When_PaymentContextCode_EcomerceGoods" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This test case is to check the  Risk Merchant Customer Identification parameter for MaxLength.
    * Expected response code of 201.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidatePayloadWithoutMerchantCustomerIdentification",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001440_Validate_Initiation_Request_Without_MerchantCustomerIdentification" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This test case is to check the  Risk Merchant Customer Identification parameter for MaxLength.
     * Flows all domestic and international payment flows.
     * Expected response code of 400 and OBIE Error Code UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateMerchantCustomerIdentificationExceedMaxLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501097_Validate_MerchantCustomerIdentification_Exceed_MaxLength" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains(" String \"12121212211212000000000012121212121212121212112121212121212121212121212121\" " +
                        "is too long (length: 74, maximum allowed: 70)"))
    }

    /*
     * This testcase validates the availability of InstructionPriority for International Schedule Payment.
     * Expected response code of 201 Created.
     */
    @Test (groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"])
    void "TC0901026_Validate_International_Schedule_Payment_Validate_InstructionPriority" () {

        //initiation
        def paymentMap = [:]
        def initiationPayload = PaymentRequestPayloads.internationalSchedulePaymentWithMandatoryParameters(paymentMap)
        def consentPath = ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE

        def consentResponse = paymentInitiationRequest(initiationPayload, consentPath)

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
    * This testcase validates the LocalInstrument for  null values.
    * Flows: Domestic payment/Domestic schedule payment/International Payment/International Schedule payment.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "PaymentValidateLocalInstrumentForNull", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901024_Validate_LocalInstrument_for_Null_Values" (Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("The given local instrument value is not supported"))
    }

    /*
     * This testcase validates the LocalInstrument for International Schedule Payment for exceeding max length.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateLocalInstrumentForExceedingMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901025_Validate_International_Schedule_Payment_Validate_LocalInstrument_for_Exceeding_Max_Length" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("The given local instrument value is not supported"))
    }

    /*
     * This testcase validates the InstructionPriority for International Schedule Payment for null values.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateInstructionPriorityForNull", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901027_Validate_Payment_InstructionPriority_for_Null_Values" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"\") not found in enum (possible values: [\"Normal\",\"Urgent\"])"))
    }

    /*
     * This testcase validates the InstructionPriority for International Schedule Payment for Invalid values.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateInstructionPriorityForInvalidValues", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901028_Validate_Payment_InstructionPriority_for_Invalid_Parameters" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"abcd\") not found in enum (possible values: [\"Normal\",\"Urgent\"])"))
    }

    /*
    * This testcase validates the initiation payloads without the Purpose filed.
    * Flows: All international payment flows.
    * Status code:201 Created.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "WithoutPurposeParameter", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001052_Validate_Payment_API_Without_Purpose_Parameter" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This testcase validates the Purpose filed for null inputs.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(dataProvider = "ValidatePurposeFieldForNullValues", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801028_Validate_Payment_API_Purpose_Parameter_for_Null_Parameters" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This testcase validates the Purpose field field of international Payment  and international schedule payments for
     * exceeding the max length.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     * Flows: All international payment flows.
     * */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidatePurposeForMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801029_Validate_Payment_API_Purpose_Field_For_Exceeding_Max_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"101-1212121212\" is too long (length: 14, maximum allowed: 4)"))
    }

    /*
     * This testcase validates the ChargeBearer filed field for null values.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     * Flows: International Schedule Payment /International Standing Order.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "PaymentValidateChargeBearerForNull", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901033_Validate_Payment_API_ChargeBearer_Parameter_For_Null_Values" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"\") not found in enum (possible values: [\"BorneByCreditor\"," +
                        "\"BorneByDebtor\",\"FollowingServiceLevel\",\"Shared\"])"))
    }

    /*
     * This testcase validates the ChargeBearer filed field of International  Payment flows for invalid values.
     * Expected response status code of 201 Created.
     * Flows: International Payments/International Schedule Payment /International Standing Order.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "Initiation_Payloads_Without_The_ChargeBearer", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801030_Validate_International_Payment_API_Without_The_ChargeBearer_Parameter" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This testcase validates the ChargeBearer filed field of International  Payment flows for invalid values.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     * Flows: International Payments/International Schedule Payment /International Standing Order.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ChargeBearerForInvalidInputs", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801031_Validate_International_Payment_API_ChargeBearer_for_Invalid_Inputs" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"!@@\") not found in enum (possible values: [\"BorneByCreditor\"," +
                        "\"BorneByDebtor\",\"FollowingServiceLevel\",\"Shared\"])"))
    }

    /*
     * This testcase validates the RequestExecutionDateTime filed  for invalid values.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.InvalidDate.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateRequestExecutionDateTimeForInvalidFormat",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501016_Validate_RequestExecutionDateTime_for_Invalid_Format" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"2019-12-12\" is invalid against requested date format(s) [yyyy-MM-dd'T'HH:mm:ssZ," +
                        " yyyy-MM-dd'T'HH:mm:ss.[0-9]{1,12}Z]"))
    }

    /*
     * This testcase validates if the initiation request is without the RequestExecutionDateTime parameter.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateRequestWithoutRequestExecutionDateTimeParameter",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801036_Validate_Initiation_Request_Without_The_RequestExecutionDateTime_Parameter" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"RequestedExecutionDateTime\"])"))
    }

    /*
    * This testcase validates if the initiation request is without the RequestExecutionDateTime parameter.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Unsuppoered.Currency.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateUnitCurrency_For_InvalidValue", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801039_Validate_Payment_API_ExchangeRateInformationUnitCurrency_for_Invalid_Parameters" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"abc\""))
    }

    /*
     * This testcase validates if the initiation request is without the UnitCurrency parameter.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ExchangeRateInformationWithoutUnitCurrency", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901197_Validate_Payment_API_Without_ExchangeRateInformationUnitCurrency" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"UnitCurrency\"])"))
    }

    /*
     * This testcase validates if the initiation request is with a null value for the UnitCurrency parameter.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Unsupported.Currency.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateExchangeRateInformationUnitCurrency", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801040_Validate_Payment_API_For_Null_ExchangeRateInformationUnitCurrency" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ECMA 262 regex \"^[A-Z]{3,3}\$\" does not match input string \"\""))
    }

    /*
     * This testcase validates if the initiation request is with an invalid value for the ExchangeRateInformation RateType
     * parameter.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateRateTypeForInvalidValue", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801042_Validate_Payment_API_ExchangeRateInformation_RateType_For_InvalidParameters" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"@!!@\") not found in enum (possible values:" +
                        " [\"Actual\",\"Agreed\",\"Indicative\"])"))
    }

    /*
    * This testcase validates if the initiation request is with a null value for ExchangeRateInformation ExchangeRate
    * parameter.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.InvalidFormat.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateNullExchangeRate", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901199_Validate_Payment_API_ExchangeRateInformation_Exchange_Rate_For_Null" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance type (string) does not match any allowed primitive type" +
                        " (allowed: [\"integer\",\"number\"])"))
    }

    /*
     * This testcase validates the initiation request with an invalid value for ExchangeRateInformation ExchangeRate.
     * parameter. Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.InvalidFormat.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidExchangeRate", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901198_Validate_Payment_API_ExchangeRateInformation_Exchange_Rate_For_Invalid_Values" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance type (string) does not match any allowed primitive type " +
                        "(allowed: [\"integer\",\"number\"])"))
    }

    /*
    * This testcase validates if the initiation request is with a null value for the ExchangeRateInformation RateType.
    * parameter. Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateExchangeRateInformationRateTypeForNullValue",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801044_Validate_Payment_API_ExchangeRateInformation_RateType_For_Null_Parameters" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"\") not found in enum (possible values:" +
                        " [\"Actual\",\"Agreed\",\"Indicative\"])"))
    }

    /*
     * This testcase validates if the initiation request is without the ExchangeRateInformation RateType parameter.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Missing.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateExchangeRateInformationWithoutRateTypeParameter",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801045_Validate_Payment_API_ExchangeRateInformation_Without_RateType_Parameter" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"RateType\"])"))
    }

    /*
     * This testcase validates the ExchangeRateInformation payload  when the ExchangeRateInformation RateType is set as
     * agreed.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Expected.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateRateTypeParameter_Agreed", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801046_Validate_Payment_API_ExchangeRateInformation_When_RateType_Parameter_is_Set_as_Agreed" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Exchange rate and ContractIdentification must be specified for agreed rate type"))
    }

    /*
    * This testcase validates the ExchangeRateInformation payload  when the ExchangeRateInformation RateType is set as
    * Actual.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Unexpected.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateRateTypeParameter_Actual", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801047_Validate_Payment_API_ExchangeRateInformation_When_RateType_Parameter_is_Set_as_Actual" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ExchangeRateInformation,must not specify ExchangeRate and/or" +
                        " ContractIdentification when requesting an Actual or Indicative RateType"))
    }

    /*
     * This testcase validates the ExchangeRateInformation payload  when the ExchangeRateInformation RateType is set as
     * Indicative.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Unexpected.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateRateTypeParameter_Indicative", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801048_Validate_Payment_API_ExchangeRateInformation_When_RateType_Parameter_is_Set_as_Indicative" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("ExchangeRateInformation,must not specify ExchangeRate and/or ContractIdentification when " +
                        "requesting an Actual or Indicative RateType"))
    }

    /*
    * This testcase validates the CurrencyOfTransfer filed without the CurrencyOfTransfer parameter.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Missing.
    * Flows: All International payment flows.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "WithoutCurrencyOfTransferParameter", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801024_Validate_Payment_API_Flows_Without_The_CurrencyOfTransfer_Parameter" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"CurrencyOfTransfer\"])"))
    }

    /*
     * This testcase validates the CurrencyOfTransfer filed field for invalid values.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Unsupported.Currency.
     * Flows:All international flows.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "CurrencyOfTransferForInvalidInputs", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801025_Validate_International_Schedule_Payment_For_CurrencyOfTransfer_for_Invalid_Inputs" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"abc\""))
    }

    /*
    * This testcase validates the CurrencyOfTransfer filed field of International Schedule Payment for null values.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Unsupported.Currency.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateCurrencyOfTransferForNullValues", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801023_Validate_InternationalSchedulePaymentValidateCurrencyOfTransferForNullValues" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"\""))
    }

    /*
     * This testcase validates the ExchangeRateInformation_ContractIdentification for null values.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ExchangeRateInformationContractIdentification", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801027_Validate_International_Schedule_Payment_With_Null_ExchangeRateInformation_ContractIdentification"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This testcase validates the CurrencyOfTransfer filed for validation for MaxLength.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateContractIdentificationMaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801051_Validate_InternationalSchedule_Payment_ExchangeRateInformation_ContractIdentification_For_MaxLength" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("CME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412 CME412-ACME412-ACME412-ACME412" +
                        "-ACME412-ACME412-ACME412-ACME412CME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412 " +
                        "CME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412CME412-ACME412-ACME412-ACME412-" +
                        "ACME412-ACME412-ACME412-\" is too long (length: 305, maximum allowed: 256)"))
    }

    /*
     * This testcase validates the Creditor Name  for null values.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddressName_MinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801053_Validate_Payment_API_Creditor_PostalAddress_Name_for_Minimum_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This testcase validates the Creditor Name  for max length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8"],
            dataProvider = "validateCreditorPostalAddressName_MaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801054_Validate_Payment_API_Creditor_PostalAddress_Name_for_Maximum_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("(length: 415, maximum allowed: 140)"))
    }

    /*
    * This testcase validates the Creditor PostalAddress  Department  for null values.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
   */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_Department_MinLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801055_Validate_Payment_API_Creditor_PostalAddress_Department_for_Minimum_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress  Department  for max length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_Department_MaxLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801056_Validate_Payment_API_Creditor_PostalAddress_Department_for_Maximum_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"department test value 121212121 2department test value 1 " +
                        "department test value 1\" is too long (length: 80, maximum allowed: 70)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress Sub department  for null values.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_SubDepartment_MinLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801057_Validate_Payment_API_Creditor_PostalAddress_SubDepartment_for_Minimum_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This testcase validates the Creditor PostalAddress SubDepartment for Max Length.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_SubDepartment_MaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801058_Validate_Payment_API_Creditor_PostalAddress_SubDepartment_For_Max_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"department test value 121212121 2department test value 1 department test value 1\"" +
                        " is too long (length: 80, maximum allowed: 70)"))
    }

    /*
    * This testcase validates the Creditor PostalAddress StreetName  for null values.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_StreetName_MinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801061_Validate_Payment_API_Creditor_Postal_Address_StreetName_For_Min_Length " (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This testcase validates the Creditor PostalAddress StreetName  for max length values.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_StreetName_MaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801062_Validate_Payment_API_Creditor_Postal_Address_StreetName_For_Max_Length " (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"delivery address street name test value 121212121 2department test value 1 " +
                        "department test value 1\" is too long (length: 98, maximum allowed: 70)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress BuildingNumber for min length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_BuildingNumber_For_MinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801064_Validate_Payment_API_Creditor_PostalAddress_BuildingNumber_For_Min_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress BuildingNumber for max length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_BuildingNumber_For_MaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801065_Validate_Payment_API_Creditor_PostalAddress_BuildingNumber_For_Max_Length " (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"10234567891212435445\" is too long (length: 20, maximum allowed: 16)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress PostCode for min length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_PostCode_For_MinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801067_Validate_Payment_API_Creditor_PostalAddress_PostCode_For_Min_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress PostCode for max length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_PostCode_For_MaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801068_Validate_Payment_API_Creditor_PostalAddress_PostCode_For_Max_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"102121212-102121212-102121212\" is too long (length: 29, maximum allowed: 16)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress TownName for min length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_TownName_For_MinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801070_Validate_Payment_API_Creditor_PostalAddress_TownName_For_Min_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This testcase validates the Creditor PostalAddress TownName for max length.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_TownName_For_MaxLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801071_Validate_Payment_API_Creditor_PostalAddress_TownName_For_Max_Length " (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"London-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" is" +
                        " too long (length: 72, maximum allowed: 35)"))
    }

    /*
    * This testcase validates the Creditor PostalAddress Country for valid regex.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_Country_For_ValidRegex", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801073_Validate_Payment_API_Creditor_PostalAddress_Country_For_Valid_Regex" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"TestCountry\""))
    }

    /*
    * This testcase validates the Creditor PostalAddress CountrySubDivision for min length.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_CountrySubDivision_For_MinLength", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801075_Validate_Payment_API_Creditor_PostalAddress_CountrySubDivision_For_MinLength" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
    * This testcase validates the Creditor PostalAddress CountrySubDivision for max length.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_CountrySubDivision_For_MaxLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801076_Validate_Payment_API_Creditor_PostalAddress_CountrySubDivision_For_MaxLength" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"London-1212121212-1212121212-1212121212-1212121212-1212121212-1212121212\" " +
                        "is too long (length: 72, maximum allowed: 35)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress AddressType for invalid values.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_AddressType_For_Invalid_Values",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0801078_Validate_Payment_API_Creditor_PostalAddress_AddressType_For_Invalid_Values" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"Business123\") not found in enum (possible values: [\"Business\"," +
                        "\"Correspondence\",\"DeliveryTo\",\"MailTo\",\"POBox\",\"Postal\"," +
                        "\"Residential\",\"Statement\"])"))
    }

    /*
    * This testcase validates the Creditor PostalAddress AddressType for min length.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_AddressType_For_MinLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901080_Validate_Payment_API_Creditor_PostalAddress_AddressType_For_Min_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Instance value (\"\") not found in enum (possible values: [\"Business\",\"Correspondence\"," +
                        "\"DeliveryTo\",\"MailTo\",\"POBox\",\"Postal\",\"Residential\",\"Statement\"])"))
    }

    /*
     * This testcase validates the Creditor PostalAddress AddressLine for min length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_AddressLine_For_MinLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901106_Validate_Payment_API_Creditor_PostalAddress_AddressLine_For_Min_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"\" is too short (length: 0, required minimum: 1)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress AddressLine for max length.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateCreditorPostalAddress_AddressLine_For_MaxLength",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901107_Validate_Payment_API_Creditor_PostalAddress_AddressLine_For_Max_Length" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"CME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-ACME412-CME412-ACME412-ACME412" +
                        "-ACME412-ACME412-ACME412-ACME412-ACME412\" is too long (length: 125, maximum allowed: 70)"))
    }

    /*
     * This testcase validates the Creditor PostalAddress AddressLine for max items on the list.
     * Flows: All International payment flows.
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Invalid.
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateAddressLine_For_Max_Items", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0901109_Validate_Payment_API_Creditor_PostalAddress_AddressLine_For_Max_Items_On_List" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("String \"[Coventry, Newcastel, birmingham, London, nottingham, heathrow, sutton, Swindon]\" " +
                        "is too long (length: 80, maximum allowed: 70)"))
    }

    /*
    * This testcase validates each payment type without the initiation payload.
    * Flows: All International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.Missing.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateWithoutInitiationPayload", dataProviderClass = PaymentRequestPayloads.class )
    void "TC0404003_Validate_International_Request_Without_Initiation_Payload" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Object has missing required properties ([\"Initiation\"])"))
    }

    /*
    * This testcase validates the initiation payload by passing parameters not related to the initiation request.
    * Flows: All Domestic and  International payment flows.
    * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.InvalidFormat.
    */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "validateInitiationWithUnrelatedPayloads", dataProviderClass = PaymentRequestPayloads.class)
    void "TC1001459_Validate_Initiation_Request_With_Wrong_Payload" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InitiationWithNonExistingResource", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401085_Validate_Initiation_Request_with_nonexisting_resource" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_404)
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidNumericFormatForAmount", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401086_Validate_Initiation_Request_with_InstructedAmount_Amount_Not_In_Correct_Numeric_Format" (Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains(" does not match input string \"124.17896790\""))
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidAccountIdentification", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401087_Validate_Initiation_Request_with_invalid_account_identitfier" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "InvalidPaymentContextCodeInAddressInRisk", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401089_Validate_Initiation_Request_with_invalid_PaymentContexCode" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
    }

    /*
     * This testcase validates the RequestExecutionDateTime filed  for outdated dates
     * Expected response status code of 400 and OBIE Error Code of UK.OBIE.Field.InvalidDate
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "ValidateRequestExecutionDateTimeForPastDate", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501018_Validate_RequestExecutionDateTime_for_Past_date" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("Requested Execution Date specified is a past date"))
    }

    /*
     * This testcase validates the name  field of Debtor account in each payment
     * Expected response status code of 201
     */
    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentTypesWithoutDebtorAccount_Name", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501036_Validate_Payments_without_debtor_name" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    /*
     * This testcase validates optional free form supplementary field.
     */
    //TODO: Uncomment after fixing https://github.com/wso2-enterprise/ob-compliance-toolkit-uk/issues/32
//    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
//            dataProvider = "paymentInitiationWithSupplementaryField", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0501037_Validate_Payments_with_supplementary_field" (Map<String,String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
    }

    @Test(groups = [ "3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "paymentInitiationConsentTypes", dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401113_Payments_Initiation_Response_Self_Link"(Map<String, String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.PAYLOAD),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_201)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse,"Links.Self").contains("consents/"))
    }

    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "initiationWithNegativeInstructedAmount",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401116_Validate_Payments_Initiation_With_With_Negative_InstructedAmount_Amount"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains("does not match input string \"-1\""))
    }

    @Test(groups = ["3.1.5", "3.1.6", "3.1.7", "3.1.8", "3.1.9", "3.1.10", "3.1.11"],
            dataProvider = "invalidStringPaymentInitiationConsentTypes",
            dataProviderClass = PaymentRequestPayloads.class)
    void "TC0401115_Validate Payments Initiation With InstructedAmount_Amount zero"(Map<String,
            String> maps) {

        //initiation
        def consentResponse = paymentInitiationRequest(maps.get(ConnectorTestConstants.INVALID_PARAM),
                maps.get(ConnectorTestConstants.PATH))

        Assert.assertEquals(consentResponse.statusCode(), ConnectorTestConstants.STATUS_CODE_400)
        Assert.assertTrue(TestUtil.parseResponseBody(consentResponse, ConnectorTestConstants.ERROR_ERRORS_DESCRIPTION)
                .contains( "Instructed Amount specified should be grater than zero"))
    }
}
