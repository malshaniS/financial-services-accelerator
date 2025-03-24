package org.wso2.financial.services.accelerator.test.framework.utility

import org.testng.annotations.DataProvider
import org.wso2.financial.services.accelerator.test.framework.configuration.ConfigurationService
import org.wso2.financial.services.accelerator.test.framework.constant.ConnectorTestConstants
import org.wso2.financial.services.accelerator.test.framework.constant.PaymentRequestPayloads

import java.util.logging.Logger

class PaymentDataProviders {

    static ConfigurationService acceleratorConfiguration = new ConfigurationService()

    static log = Logger.getLogger(PaymentDataProviders.class.toString())

    static PaymentRequestPayloads paymentInitiationPayloads

    static {
        paymentInitiationPayloads = new PaymentRequestPayloads()
    }

    @DataProvider(name = "paymentInitiationConsentTypes")
    Iterator<Object[]> paymentTypes() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_DOMESTIC)
        domesticParameterMap.put("payload", PaymentRequestPayloads.initiationPayloadDomesticPayment(paymentMap))
        domesticParameterMap.put("paymentType", ConnectorTestConstants.DOMESTIC_PAYMENTS)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))
        domesticScheduleParameterMap.put("paymentType", ConnectorTestConstants.DOMESTIC_SCHEDULE)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))
        domesticStandingOrderParameterMap.put("paymentType", ConnectorTestConstants.DOMESTIC_STANDING)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))
        internationalPaymentParameterMap.put("paymentType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))
        internationalStandingOrderPaymentParameterMap.put("paymentType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))
        internationalSchedulePaymentParameterMap.put("paymentType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    @DataProvider(name = "initiationPayments")
    Iterator<Object[]> initiationPayments() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path",UKConstants.KM_CONSENT_PATH)
        domesticParameterMap.put("payload", InitiationPayloads.initiationPayloadDomestic)
        listOfParamMaps.add(domesticParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * Data provider for validating the EndToEndIdentification payload
    * Flows: Domestic payments and International payments flows
    * Called by TC0401002
    *
    */

    @DataProvider(name = "paymentValidateWithoutEndToEndIdentificationForPayment")
    Iterator<Object[]> paymentValidateWithoutEndToEndIdentificationForPayment() {
        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentWithoutEndToEndIdentification)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadInternationalPaymentWithoutEndToEndIdentification)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    @DataProvider(name = "paymentValidateWithoutEndToEndIdentificationForSchedulePayments")
    Iterator<Object[]> paymentValidateWithoutEndToEndIdentificationForSchedulePayments() {
        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def parameterMap = [:]

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads
                .domesticSchedulePaymentWithMandatoryParameters(parameterMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalSchedulePaymentWithMandatoryParameters(parameterMap))

        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the EndToEndIdentification payload
     * Flows: Domestic payments payment flows
     * Called by TC0401003
     */
    @DataProvider(name = "validateWithNullEndToEndIdentification")
    Iterator<Object[]> validateEndToEndIdentification() {


        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["EndToEndIdentification" : ""]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the EndToEndIdentification payload
     * Flows: Domestic payments and International payments flows
     * Called by TC0401004
     */
    @DataProvider(name = "paymentValidateEndToEndIdentificationMaxLength")
    Iterator<Object[]> paymentValidateEndToEndIdentificationMaxLength() {


        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["EndToEndIdentification" : ConnectorTestConstants.END_TO_END_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Instruction Identification payload
     * Flows: Domestic payments and International payments flows
     * Called by TC0401005
     */
    @DataProvider(name = "validateWithoutInstructionIdentification")
    Iterator<Object[]> validateWithoutInstructionIdentification() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentValidateWithoutInstructionIdentification)
        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleWithoutInstructionIdentification)
        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentValidateWithoutInstructionIdentification)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutInstructionIdentification)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Instruction Identification payload
    * Flows: Domestic payments and International payments flows
    * Called by TC0401006
    */
    @DataProvider(name = "validateInstructionIdentificationForNull")
    Iterator<Object[]> validateInstructionIdentificationForNull() {


        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["InstructionIdentification" : ""]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))
        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))
        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the EndToEndIdentification payload
    * Flows: Domestic payments and International payments flows
    * Called by TC0401007
    */
    @DataProvider(name = "validateInstructionIdentificationMaxLength")
    Iterator<Object[]> validateInstructionIdentificationMax() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["InstructionIdentification" : ConnectorTestConstants.INSTRUCTION_IDENTIFICATION]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))
        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))
        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the missing InstructedAmount payload
     * Flows: Domestic payments and International payments flows
     * Called by TC0401008
     */
    @DataProvider(name = "validateWithoutInstructedAmountPayload")
    Iterator<Object[]> validateWithoutInstructedAmount() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()

        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateInstructedAmountPayloadDomesticPayment)
        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateInsrtructedAmountPayloadDomesticSchedulePayment)
        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateInsrtructedAmountPayloadDomesticStandingOrder)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutInstructedAmountPayload)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutInstructedAmount)
        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutInstructedAmount)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Instruction Identification payload
     * Flows: Domestic payments and International payments flows
     * Called by TC0401009
     */
    @DataProvider(name = "validateWithoutInstructedAmount_Amount")
    Iterator<Object[]> validateWithoutInstructedAmount_Amount() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadDomesticWithoutAmount_Amount)
        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticSchedulePaymentWithout_InstrutedAmount_Amount)
        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutInstructedAmountPayload_Amount)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadInternationalSchedule_Amount)
        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutInstructedAmount_Amount)
        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider that validates the amount field
     * Flows: all domestic and international flows
     * Called by TC0401010
     */
    @DataProvider(name = "invalidPaymentInitiationConsentTypes")
    Iterator<Object[]> paymentTypesForInvalidInput() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Amount" : ConnectorTestConstants.INVALID_AMOUNT]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    @DataProvider(name = "invalidStringPaymentInitiationConsentTypes")
    Iterator<Object[]> invalidStringPaymentInitiationConsentTypes() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Amount" : "0"]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for null values in instructed amount
     * Flows: all Domestic and International flows
     * Called by TC0401011
     */
    @DataProvider(name = "nullInstructedAmount")
    Iterator<Object[]> paymentTypesForNullInput() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Amount" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Test the instructed amount without the mandetory currency parameter
     * Flows: all domestic and international flows
     * Called by TC0401012
     */
    @DataProvider(name = "withoutInstructedAmountCurrency")
    Iterator<Object[]> withoutInstructedAmountCurrency() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentWithoutAmountCurrency)
        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticSchedulePaymentWithoutInstrutedAmountCurrency)
        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutInstructedAmountPayloadCurrency)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutInstructedAmountCurrency)
        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutInstructedAmount_Currency)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the instructed amount max allowed limit
     * Flows: domestic and international flows
     * Called by TC0401013
     */
    @DataProvider(name = "validateInstructedAmountMaxLimit")
    Iterator<Object[]> instructedAmountMaxValue() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Amount" : ConnectorTestConstants.LARGE_INSTRUCTED_AMOUNT]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the currency fields
     * Flows: all domestic and international flows
     * TC0401014
     */
    @DataProvider(name = "ValidateInstructedAmountCurrency")
    Iterator<Object[]> paymentTypesWithInvalidCurrency() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Currency" : ConnectorTestConstants.INVALID_CURRENCY]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the currency fields for null values
     * Flows: all domestic and international flows
     * Called By TC0401015
     */
    @DataProvider(name = "NullInstructedAmountCurrency")
    Iterator<Object[]> NullInstructedAmountCurrency() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Currency" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the LocalInstrument parameter
     * Flows: domestic payments
     * Called by TC0401016
     */
    @DataProvider(name = "validateLocalInstrument")
    Iterator<Object[]> validateLocalInstrument() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["LocalInstrument" : ConnectorTestConstants.INVALID_LOCALINS]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the missing debtor account SchemeName fields
     * Flows: for domestic payments flow
     * Called by TC0401017
     */
    @DataProvider(name = "paymentTypesWithoutDebtorAccount_SchemeName")
    Iterator<Object[]> paymentTypesWithoutDebtorAccount_SchemeName() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorSchemeName" : ""]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the  debtor account SchemeName for invalid value
     * Flows: for domestic payments flow
     * Called by TC0401019
     */
    @DataProvider(name = "validateDebtorAccountSchemeNameType")
    Iterator<Object[]> paymentTypesWithDebtorAccount_SchemeName_as_IBAN() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorSchemeName" : ConnectorTestConstants.LOCALINS]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the SchemeName fields
     * Flows: all domestic and international flows
     * Called by TC0401019
     */
    @DataProvider(name = "invalidSchemeName")
    Iterator<Object[]> paymentTypesWithInvalidSchemeName() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorSchemeName" : ConnectorTestConstants.INVALID_SCHEME_NAME]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Initiation payload
     * Flows: All Domestic and international payment flows
     * Called by TC0401020
     */
    @DataProvider(name = "validateDebtorAccountSchemeNameMaxLength")
    Iterator<Object[]> debtorAccountSchemeNameMaxKLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorSchemeName" : ConnectorTestConstants.SCHEME_NAME_MAX_LENGTH]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Initiation payload
     * Flows: Domestic payments
     * Called by TC0401021
     */
    @DataProvider(name = "validateDebtorAccountWithoutIdentification")
    Iterator<Object[]> debtorAccountWithoutIdentification() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.domesticWithouthDebtorAccountIdentification)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleWithouthDebtorAccountIdentification)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                domnesticStandingOrderWithoutDebtorAccountIdentification)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutDebtorAccountIdentification)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalSchedulePaymentWithoutDebtorAccountIdentification)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutDebtorAccountIdentification)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the Initiation payload
     * Flows: Domestic payments
     * Called by TC0401022
    */
    @DataProvider(name = "validateDebtorAccountIdentification")
    Iterator<Object[]> debtorAccountNullIdentification() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorIdentification" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))


        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Initiation payload
     * Flows: Domestic payments
     * Called by TC0401023
    */
    @DataProvider(name = "validateDebtorAccountIdentificationLength")
    Iterator<Object[]> debtorAccountIdentificationLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorIdentification" : ConnectorTestConstants.SHORT_IDENTIFICATION]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Initiation payload
     * Flows: Domestic payments flow
     * Called by TC0401024
    */
    @DataProvider(name = "validateDebtorAccountIdentificationMaxLength")
    Iterator<Object[]> debtorAccountIdentificationMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorIdentification" : ConnectorTestConstants.MAX_IDENTIFICATION]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Initiation payload
     * Flows: Domestic payments
     * Called by TC0401025
    */
    @DataProvider(name = "validateDebtorAccountNameMaxLength")
    Iterator<Object[]> debtorAccountNameMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorName" : ConnectorTestConstants.NAME_VALUE_MAX_LENGTH]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the Initiation payload
     * Flows: Domestic payments
     * Called by TC0801086
    */
    @DataProvider(name = "validateDebtorAccountNameForNullValues")
    Iterator<Object[]> validateDebtorAccountNameForNullValues() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorName" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the DebtorAccount secondary identification  for max length
     * Flows: Domestic payments
     * Called by TC0401026
     */
    @DataProvider(name = "validateDebtorAccountSecondaryIdentificationMaxLength")
    Iterator<Object[]> debtorAccountSecondaryIdentificationMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorSecondaryIdentification" : ConnectorTestConstants.SECONDARY_IDENTIFICATION_MAX_LENGTH]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the DebtorAccount secondary identification for min length
     * Flows: Domestic payments
     * Called by TC0801089
     */
    @DataProvider(name = "validateDebtorAccountSecondaryIdentificationForNull")
    Iterator<Object[]> validateDebtorAccountSecondaryIdentificationForNull() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorSecondaryIdentification" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Creditor account payload
     * Flows: Domestic payments
     * Related to TC0401027
     */
    @DataProvider(name = "paymentWithoutCreditorAccount")
    Iterator<Object[]> paymentWithoutCreditorAccount() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentWithoutCreditorAccount)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleWithoutCreditorAccountPayload)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticStandingOrdersWithoutCreditorAccountPayload)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutCreditorAccount)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutCreditorAccount)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutCreditorAccountPayload)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the Creditor account payload
     * Flows: Domestic payments
     * Related to TC0401028
     */
    @DataProvider(name = "paymentWithoutCreditorAccountSchemeName")
    Iterator<Object[]> paymentWithoutCreditorAccountSchemeName() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateCreditorAccountSchemeNamePayloadDomesticPayment)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateCreditorAccountSchemeNamePayloadDomesticSchedulePayment)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateCreditorAccountSchemeNamePayloadDomesticStandingOrder)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateCreditorAccountSchemeNamePayloadInternationalPayment)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateCreditorAccountSchemeNamePayloadInternationalSchedulePayment)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateCreditorAccountSchemeNamePayloadIInternationalStandingOrder)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Creditor Account scheme name for null values
    * Flows: Domestic payments
    * Called by TC0401029
    */
    @DataProvider(name = "validateCreditorAccountNullSchemeName")
    Iterator<Object[]> creditorAccountNullSchemeName() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorSchemeName" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * Data provider for validating the Creditor Account scheme name for max length values
    * Flows: Domestic payments
    * Called by TC0501044
    */
    @DataProvider(name = "validateCreditorAccountSchemeNameMaxLength")
    Iterator<Object[]> validateCreditorAccountSchemeNameMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorSchemeName" : ConnectorTestConstants.SCHEME_NAME_MAX_LENGTH]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * Data provider for validating the Creditor Account identification length less than 14
    * Flows: Domestic payments
    * Called by TC0401030
    */
    @DataProvider(name = "validateCreditorAccountIdentificationLength")
    Iterator<Object[]> creditorAccountIdentificationLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorIdentification" : ConnectorTestConstants.SHORT_IDENTIFICATION]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Creditor Account identification for null values
    * Flows: Domestic payments
    * Called by TC0401031
    */
    @DataProvider(name = "validateCreditorAccountIdentificationForNull")
    Iterator<Object[]> validateCreditorAccountIdentificationForNull() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorIdentification" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the Creditor Account without the identification parameter
     * Flows: Domestic payments
     * Called by TC1001084
     */
    @DataProvider(name = "validateCreditorAccountWithoutIdentification")
    Iterator<Object[]> validateCreditorAccountWithoutIdentification() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadDomesticWithoutCreditorAccountIdentification)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadDomesticScheduleWithoutCreditorAccountIdentification)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadDomesticStandingOrderWithoutCreditorAccountIdentification)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadInternationalPaymentWithoutCreditorAccountIdentification)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadInternationalStandingOrderWithoutCreditorAccountIdentification)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadInternationalScheduleWithoutCreditorAccountIdentification)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * Data provider for validating the Creditor Account identification having more than 40 characters
    * Flows: Domestic payments
    * Called by TC0401031
    */
    @DataProvider(name = "validateCreditorAccountIdentificationMaxLength")
    Iterator<Object[]> creditorAccountIdentificationMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorIdentification" : ConnectorTestConstants.MAX_IDENTIFICATION]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the Creditor Account Name parameter having a null value
     * Flows: Domestic payments
     * Called by TC0401032
     */
    @DataProvider(name = "validateCreditorAccountNullName")
    Iterator<Object[]> creditorAccountIdentificationNullName() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAccountName" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the Creditor Account Name parameter exceeding the max length
     * Flows: Domestic payments
     * Called by TC0401033
     */
    @DataProvider(name = "validateCreditorAccountNameMaxLength")
    Iterator<Object[]> creditorAccountIdentificationNameMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAccountName" : ConnectorTestConstants.NAME_VALUE_MAX_LENGTH]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the CreditorAccount secondary identification  for max length
     * Flows: Domestic payments
     * Called by TC0401034
     */
    @DataProvider(name = "CreditorAccountSecondaryIdentificationMaxLength")
    Iterator<Object[]> CreditorAccountSecondaryIdentificationMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorSecondaryIdentification" : ConnectorTestConstants.SECONDARY_IDENTIFICATION_MAX_LENGTH]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * Data provider for validating the CreditorAccount secondary identification  for min length
    * Flows: Domestic payments
    * Called by TC1001091
    */
    @DataProvider(name = "CreditorAccountSecondaryIdentificationMinLength")
    Iterator<Object[]> CreditorAccountSecondaryIdentificationMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorSecondaryIdentification" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the Creditor department for max length
     * Flows: Domestic payments
     * Called by TC0401035
     */
    @DataProvider(name = "validateCreditorDepartmentForMaxLength")
    Iterator<Object[]> validateCreditorDepartmentForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorDepartment" : ConnectorTestConstants.DEPARTMENT_MAXLENGTH]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for validating the Creditor department for max length
     * Flows: Domestic payments
     * Called by TC0401035
     */
    @DataProvider(name = "validateCreditorDepartmentForMinLength")
    Iterator<Object[]> validateCreditorDepartmentForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorDepartment" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)



        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Creditor sub department for max length
     * Flows: Domestic payments and Domestic Schedule payments flow
     * Called by TC0401036
    */
    @DataProvider(name = "validateCreditorSubDepartmentForMaxLength")
    Iterator<Object[]> validateCreditorSubDepartmentForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorSubDepartment" : ConnectorTestConstants.SUBDEPT_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Creditor sub department for min length
     * Flows: Domestic payments and Domestic Schedule payments flow
     * Called by TC0501058
    */
    @DataProvider(name = "validateCreditorSubDepartmentForMinLength")
    Iterator<Object[]> validateCreditorSubDepartmentForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorSubDepartment" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Creditor building number for max length
    * Flows: Domestic payments and Domestic schedule payments flow
    * Called by TC0401037
    */
    @DataProvider(name = "validateCreditorBuildingNumberForMaxLength")
    Iterator<Object[]> validateCreditorBuildingNumberForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorBuildingNumber" : ConnectorTestConstants.BUILDING_NUM_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Creditor building number for max length
    * Flows: Domestic payments and Domestic schedule payments flow
    * Called by TC0401037
    */
    @DataProvider(name = "validateCreditorBuildingNumberForMinLength")
    Iterator<Object[]> validateCreditorBuildingNumberForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorBuildingNumber" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Creditor postal code for max length
     * Flows: Domestic payments and Domestic schedule payments flow
     * Called by TC0401038
     */
    @DataProvider(name = "validateCreditorPostCodeForMaxLength")
    Iterator<Object[]> validateCreditorPostCodeForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorPostCode" : ConnectorTestConstants.POSTCODE_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Creditor postal code is less than the min length
     * Flows: Domestic payments and Domestic schedule payments flow
     * Called by TC0501066
     */
    @DataProvider(name = "validateCreditorPostCodeForMinLength")
    Iterator<Object[]> validateCreditorPostCodeForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorPostCode" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Creditor town name for max length
    * Flows: Domestic payments
    * Called by TC0401039
    */
    @DataProvider(name = "validateCreditorTownNameForMaxLength")
    Iterator<Object[]> validateCreditorTownNameForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorTownName" : ConnectorTestConstants.TOWN_NAME_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Creditor town name for max length
     * Flows: Domestic payments
     * Called by TC0501069
     */
    @DataProvider(name = "validateCreditorTownNameForMinLength")
    Iterator<Object[]> validateCreditorTownNameForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorTownName" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Creditor CountrySubDivision For max length
    * Flows: Domestic payments
    * Called by TC0401040
    */
    @DataProvider(name = "validateCreditorCountrySubDivisionForMaxLength")
    Iterator<Object[]> validateCreditorCountrySubDivisionForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorCountrySubDivision" : ConnectorTestConstants.COUNTRY_SUB_DEV_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Creditor CountrySubDivision For max length
     * Flows: Domestic payments
     * Called by TC0401040
     */
    @DataProvider(name = "validateCreditorCountrySubDivisionForMinLength")
    Iterator<Object[]> validateCreditorCountrySubDivisionForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorCountrySubDivision" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Creditor address type for max length
    * Flows: Domestic payments and Domestic Schedule payments
    * Called by TC0401041
    */
    @DataProvider(name = "validateCreditorAddressTypeForMaxLength")
    Iterator<Object[]> validateCreditorAddressTypeForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAddressType" : ConnectorTestConstants.ADDRESS_TYPE_MAX_LENGTH]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Creditor address type list of items
     * Flows: Domestic payments and Domestic Schedule Payments
     * Called by TC0401042
     */
    @DataProvider(name = "validateCreditorAddressTypeListOfItems")
    Iterator<Object[]> validateCreditorAddressTypeListOfItems() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAddressType" : ConnectorTestConstants.ADDRESS_TYPE_LIST]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Remittance Information Unstructured for max length
     * Flows: Domestic payments,Domestic Schedule payments,International payments and International Schedule payment flows
     * Called by TC0401043
     */
    @DataProvider(name = "validateRemittanceInformationUnstructuredForMaxLength")
    Iterator<Object[]> validateRemittanceInformationUnstructuredForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RemittanceInformationUnstructured" : ConnectorTestConstants.UNSTRUCTURED_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Remittance Information Unstructured for min length
     * Flows: Domestic payments,Domestic Schedule payments,International payments and International Schedule payment flows
     * Called by TC0501081
     */
    @DataProvider(name = "RemittanceInformationUnstructuredForMinLength")
    Iterator<Object[]> RemittanceInformationUnstructuredForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RemittanceInformationUnstructured" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Remittance Information Reference for minlength
     * Flows: Domestic payments,Domestic Schedule payments,International payments and International Schedule payment flows
     * Called by TC0501084
     */
    @DataProvider(name = "RemittanceInformationReferenceForMinLength")
    Iterator<Object[]> RemittanceInformationReferenceForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RemittanceInformationReference" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for validating the Remittance Information Reference for max length
    * Flows: Domestic payments,Domestic Schedule payments,International payments and International Schedule payment flows
    * Called by TC0401044
    */
    @DataProvider(name = "RemittanceInformationReferenceMaxLength")
    Iterator<Object[]> RemittanceInformationReferenceMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RemittanceInformationReference" : ConnectorTestConstants.REFERENCE_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the Remittance Information Reference for max length
     * Flows: Domestic payments and International Payment
     * Called by TC0401045
     */
    @DataProvider(name = "validateInitiationWithoutRiskPayload")
    Iterator<Object[]> validateInitiationWithoutRiskPayload() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadDomesticWithoutRiskPayload)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleWithoutRiskPayload)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticStandingOrdersWithoutRiskPayload)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutRiskPayload)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutRiskPayload)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutRiskPayload)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates parameters for risk payload to validate an invalid merchant category code
     * Flows: For all domestic and international flows
     * Called by TC0401046
     */
    @DataProvider(name = "validateRiskPayloadParameters")
    Iterator<Object[]> validateRiskPayloadParameters() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["MerchantCategoryCode" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates parameters for risk payload
     * Flows: For all domestic and international flows
     * Called by TC0401047
     */
    @DataProvider(name = "validateMerchantCategoryCodeLength")
    Iterator<Object[]> validateMerchantCategoryCodeLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["MerchantCategoryCode" : ConnectorTestConstants.MERCHANT_CATEGORY_CODE_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates parameters for risk payload
    * Flows: For domestic payment flows
    * Called by TC0401048
    */
    @DataProvider(name = "validateMerchantCategoryCodeForPaymentContexCodeEcommerceGoods")
    Iterator<Object[]> validateMerchantCategoryCodeForPaymentContexCodeEcommerceGoods() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["PaymentContextCode":"EcommerceGoods", "MerchantCategoryCode" : ""]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates parameters for risk payload
     * Flows: For domestic payment flows
     * Called by TC0501089
     */
    @DataProvider(name = "validateMerchantCategoryCodeForPaymentContexCodeEcommerceServices")
    Iterator<Object[]> validateMerchantCategoryCodeForPaymentContexCodeEcommerceServices() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["PaymentContextCode":"EcommerceServices", "MerchantCategoryCode" : ""]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates parameters for risk payload
     * Flows: For domestic payment and International payment flow
     * Called by TC0401049
     */
    @DataProvider(name = "validatePaymentContextCodeEcomerceGoodsWithoutDeliveryAddress")
    Iterator<Object[]> validatePaymentContextCodeEcomerceGoodsWithoutDeliveryAddress() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                paymentContextCodeEcommerceGoodsWithoutDeliveryAddress)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadDomesticScheduleWithoutDeliveryAddress)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadDomesticStandingOrdersWithoutDeliveryAddress)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadInternationalPaymentWithoutDeliveryAddress)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutDeliveryAddress)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.internationalScheduleWithoutDeliveryAddress)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates Risk Delivery Address Country for Regex
    * Flows: For domestic payment and International payment flow
    * Called by TC1001455
    */
    @DataProvider(name = "validateRiskDeliveryAddressCountry")
    Iterator<Object[]> validateRiskDeliveryAddressCountry() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Country" : "UnitedKingdom"]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address address line parameter for max length
     * Flows: For all domestic and international flows
     * Called by TC0401050
     */
    @DataProvider(name = "RiskAddressLineForMaxLength")
    Iterator<Object[]> RiskAddressLineForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["AddressLine" : "[\""+ConnectorTestConstants.ADDRESS_LINE_MAX+"\"]"]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address address line parameter for min length
     * Flows: For all domestic and international flows
     * Called by TC0401051
     */
    @DataProvider(name = "RiskDeliveryAddressAddressLineForMinLength")
    Iterator<Object[]> RiskDeliveryAddressAddressLineForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["AddressLine" : "[\""+ConnectorTestConstants.NULL+"\"]"]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))


        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address street name parameter for max length
     * Flows: For all domestic and international flows
     * Called by TC0401053
     */
    @DataProvider(name = "RiskDeliveryAddressStreetNameForMaxLength")
    Iterator<Object[]> RiskDeliveryAddressStreetNameForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["StreetName" : ConnectorTestConstants.STREETNAME_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address street name parameter for min length
     * Flows: For all domestic and international flows
     * Called by TC0401054
     */
    @DataProvider(name = "RiskDeliveryAddressStreetNameForMinLength")
    Iterator<Object[]> RiskDeliveryAddressStreetNameForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["StreetName" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address without street name parameter for 201 response code
     * Flows: For domestic payment flows
     * Called by TC0401055
     */
    @DataProvider(name = "validateRiskDeliveryAddressWithoutStreetName")
    Iterator<Object[]> validateRiskDeliveryAddressWithoutStreetName() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentWithoutDeliveryAddressStreetName)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleWithoutDeliveryAddressStreetName)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticStandingOrdersWithoutDeliveryAddressStreetName)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutDeliveryAddressStreetName)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutDeliveryAddressStreetName)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutDeliveryAddressStreetName)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address building number is more than the max length
     * Flows: For all domestic and international flows
     * Called by TC0401056
     */
    @DataProvider(name = "RiskDeliveryAddressBuildingNumberForMaxLength")
    Iterator<Object[]> RiskDeliveryAddressBuildingNumberForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["BuildingNumber" : ConnectorTestConstants.BUILDING_NUM_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address building number parameter for min length
     * Flows: For all domestic and international flows
     * Called by TC0401057
    */
    @DataProvider(name = "RiskDeliveryAddressBuildingNumberForMinLength")
    Iterator<Object[]> RiskDeliveryAddressBuildingNumberForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["BuildingNumber" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address without the building number parameter
     * Flows: For domestic payment flows
     * Called by TC0401058
     */
    @DataProvider(name = "validateRiskDeliveryAddressWithoutBuildingNumberParameter")
    Iterator<Object[]> validateRiskDeliveryAddressWithoutBuildingNumberParameter() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentWithoutDeliveryAddressBuildingNumber)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleWithoutDeliveryAddressBuildingNumber)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticStandingOrdersWithoutDeliveryAddressBuildingNumber)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutDeliveryAddressBuildingNumber)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutDeliveryAddressBuildingNumber)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutDeliveryAddressBuildingNumber)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * This data provider validates the delivery address postcode parameter for max length
    * Flows: For all domestic payment and international flows
    * Called by TC0401059
    */
    @DataProvider(name = "RiskDeliveryAddressPostCodeForMaxLength")
    Iterator<Object[]> RiskDeliveryAddressPostCodeForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["PostCode" : ConnectorTestConstants.POSTCODE_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the delivery address post code parameter for min length
    * Flows: For all domestic and international flows
    * Called by TC0401060
    */
    @DataProvider(name = "RiskDeliveryAddressPostCodeForMinLength")
    Iterator<Object[]> RiskDeliveryAddressPostCodeForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["PostCode" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address town name parameter for min length
     * Flows: For all domestic and international flows
     * Called by TC0401062
     */
    @DataProvider(name = "RiskDeliveryAddressTownNameForMinLength")
    Iterator<Object[]> RiskDeliveryAddressTownNameForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["TownName" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the delivery address towm name parameter for max length
    * Flows: For all domestic and international flows
    * Called by TC0401063
    */
    @DataProvider(name = "RiskDeliveryAddressTownNameForMaxLength")
    Iterator<Object[]> RiskDeliveryAddressTownNameForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["TownName" : ConnectorTestConstants.TOWN_NAME_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address CountrySubDivision parameter for max length
     * Flows: For all domestic and international flows
     * Called by TC0401064
    */
    @DataProvider(name = "RiskDeliveryAddressCountrySubDivisionForMaxLength")
    Iterator<Object[]> RiskDeliveryAddressCountrySubDivisionForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap
        switch (apiVersion) {
            case [TestConstants.UK_SPEC_VERSION_300,TestConstants.UK_SPEC_VERSION_311,TestConstants.UK_SPEC_VERSION_312]:
                paymentMap = ["CountrySubDivision" : "[\""+ConnectorTestConstants.COUNTRY_SUB_DEV_MAX+"\"]"]
                break
            default:
                paymentMap = ["CountrySubDivision" : "$ConnectorTestConstants.COUNTRY_SUB_DEV_MAX"]
                break
        }

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address CountrySubDivision parameter for min length
     * Flows: For all domestic and international flows
     * Called by TC0401065
     */
    @DataProvider(name = "RiskDeliveryAddressCountrySubDivisionForMinLength")
    Iterator<Object[]> RiskDeliveryAddressCountrySubDivisionForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap
        switch (apiVersion) {
            case [TestConstants.UK_SPEC_VERSION_300,TestConstants.UK_SPEC_VERSION_311,TestConstants.UK_SPEC_VERSION_312]:
                paymentMap = ["CountrySubDivision" : "[\""+ConnectorTestConstants.NULL+"\"]"]
                break
            default:
                paymentMap = ["CountrySubDivision" : "$ConnectorTestConstants.NULL"]
                break
        }

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address Country parameter for min length
     * Flows: For domestic payment flows
     * Called by TC0401067
     */
    @DataProvider(name = "RiskDeliveryAddressWithoutCountryParameter")
    Iterator<Object[]> validateRiskDeliveryAddressWithoutCountryParameter() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentWithoutRiskDeliveryAddressCountryParameter)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleWithoutRiskDeliveryAddressCountryParameter)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticStandingOrdersWithoutRiskDeliveryAddressCountryParameter)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutRiskDeliveryAddressCountryParameter)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutRiskDeliveryAddressCountryParameter)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutRiskDeliveryAddressCountryParameter)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * This data provider validates the initiation payloads with unrelated parameters
    * Flows: Domestic and international payment flows
    * Called by TC0401067
    */
    @DataProvider(name = "validateInitiationWithUnrelatedPayloads")
    Iterator<Object[]> validateInitiationWithUnrelatedPayloads() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the delivery address Country parameter for min length
     * Flows: For all domestic and international flows
     * Called by TC0401068
     */

    @DataProvider(name = "RiskDeliveryAddressCountryForMinLength")
    Iterator<Object[]> RiskDeliveryAddressCountryForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Country" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the for the availability of AuthorisationType
    * Flows: For all payment flows
    * Called by TC0401069
    */
    @DataProvider(name = "validateAuthorisationPayloadForAuthorisationType")
    Iterator<Object[]> validateAuthorisationPayloadForAuthorisationType() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentWithoutAuthorisationType)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                initiationPayloadDomesticScheduleWithoutAuthorisationType)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticStandingOrderWithoutAuthorisationType)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutAuthorisationType)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutAuthorisationType)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutAuthorisationType)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Authorisation.AuthorisationType for proper values
     * Flows: For domestic payment flows
     * Called by TC0401070
    */
    @DataProvider(name = "validateAuthorisationPayloadForAuthorisationTypeValue")
    Iterator<Object[]> validateAuthorisationPayloadForAuthorisationTypeValue() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["AuthorisationType" : ConnectorTestConstants.INVALID_AUTH_TYPE]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Authorisation.AuthorisationType for min length
    * Flows: For domestic payment flows
    * Called by TC0401071
   */
    @DataProvider(name = "validateNullAuthorisationTypeValue")
    Iterator<Object[]> validateNullAuthorisationTypeValue() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["AuthorisationType" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Authorisation.CompletionDateTime value for proper values
    * Flows: For domestic payment flows
    * Called by TC0401072
   */
    @DataProvider(name = "validateForInvalidCompletionDateTimeValue")
    Iterator<Object[]> validateForInvalidCompletionDateTimeValue() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()


        def paymentMap = ["CompletionDateTime" : ConnectorTestConstants.INVALID_COMPLETION_DT]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Authorisation.CompletionDateTime value for proper values
     * Flows: For domestic payment flows
     * Called by TC0901120
     */
    @DataProvider(name = "validateForNullCompletionDateTimeValue")
    Iterator<Object[]> validateForNullCompletionDateTimeValue() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CompletionDateTime" : ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the initiation payloae without the Authorisation.CompletionDateTime parameter
    * Flows: For domestic payment flows
    * Called by TC0401073
    */
    @DataProvider(name = "WithoutCompletionDateTimeParameter")
    Iterator<Object[]> WithoutCompletionDateTimeParameter() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticPaymentWithoutCompletionDateTimeParameter)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleWithoutCompletionDateTimeParameter)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticStandingOrdersWithoutCompletionDateTimeParameter)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalPaymentWithoutCompletionDateTimeParameter)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalStandingOrderWithoutCompletionDateTimeParameter)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                internationalScheduleWithoutCompletionDateTimeParameter)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * This data provider validates the initiation payload for unrelated values
     * Flows: For domestic and international flows
     * Called by TC0401074
    */
    @DataProvider(name = "paymentPayloadWithUnrelatedValues")
    Iterator<Object[]> paymentPayloadWithUnrelatedValues() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

//    @DataProvider(name = "filePaymentTypes")
//    Object[] filePaymentUploadTypes() {
//
//        Collection<Object[]> filePaymentsList = new ArrayList<Object[]>()
//        Map<String, String> filePaymentTypes = new HashMap<String, String>()
//
//        filePaymentTypes.put("initPath", ConnectorTestConstants.CONSENT_PATH_FILE_PAYMENTS)
//        filePaymentTypes.put("initPayload", InitiationPayloads.initiationPayloadFilePayment)
//        filePaymentTypes.put("uploadPayloadXML",
//                UKTestUtil.getFileContent(UKTestUtil
//                        .getFileFromResources("/FilePayments/file.xml")).toString())
//        filePaymentTypes.put("uploadPayloadJSON",
//                UKTestUtil.getFileContent(UKTestUtil
//                        .getFileFromResources("/FilePayments/file.json")).toString())
//        filePaymentTypes.put("uploadPayloadInvalid",
//                UKTestUtil.getFileContent(UKTestUtil
//                        .getFileFromResources("/FilePayments/invalid-payment-file.xml")).toString())
//        filePaymentTypes.put("uploadPayloadDifferent",
//                UKTestUtil.getFileContent(UKTestUtil
//                        .getFileFromResources("/FilePayments/different-payment-file.xml")).toString())
//
//        filePaymentTypes.put("initPayloadWithNoFileType", InitiationPayloads.filePaymentInitPayloadWithNoFileType)
//        filePaymentTypes.put("initPayloadWithNoFileHash", InitiationPayloads.filePaymentInitPayloadWithNoFileHash)
//        filePaymentTypes.put("initPayloadWithNoFileType", InitiationPayloads.filePaymentInitPayloadWithNoFileType)
//        filePaymentTypes.put("initPayloadWithInvalidFileType",
//                InitiationPayloads.filePaymentInitPayloadWithInvalidFileType)
//
//        filePaymentTypes.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_FILE_PAYMENTS)
//        filePaymentTypes.put("payloadType", ConnectorTestConstants.FILE_PAYMENTS)
//        filePaymentTypes.put("paymentID", "FilePaymentId")
//
//        filePaymentsList.add(filePaymentTypes as Object)
//
//        return filePaymentsList
//
//    }

    @DataProvider(name = "paymentFundsConfirmationTypes")
    Iterator<Object[]> paymentFundsConfirmationTypes() {

        Collection<Object[]> paymentFundsConfirmationTypesCollection = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentMap = new HashMap<String, String>()

        domesticPaymentParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentParameterMap.put("fundsConfirmPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)

        internationalPaymentParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("fundsConfirmPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
        internationalPaymentParameterMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)


        internationalSchedulePaymentMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentMap.put("fundsConfirmPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)

        listOfParamMaps.add(domesticPaymentParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentFundsConfirmationTypesCollection.add([map] as Object[])
        }
        return paymentFundsConfirmationTypesCollection.iterator()


    }

    @DataProvider(name = "paymentSubmissionTypes")
    Iterator<Object[]> paymentSubmissionTypes() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
//        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()
//        Map<String, String> domesticScheduleSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()
//        Map<String, String> internationalPaymentSubmissionMap = new HashMap<String, String>()
//        Map<String, String> internationalStandingOrderPaymentSubmissionMap = new HashMap<String, String>()
//        Map<String, String> internationalSchedulePaymentSubmissionMap = new HashMap<String, String>()

//        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
//        domesticPaymentSubmissionParameterMap.put("fundsConfirmPath", ConnectorTestConstants.CONSENT_PATH)
//        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
//        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
//        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")
//        domesticPaymentSubmissionParameterMap.put("paymentType", "DomesticPayment")

//        domesticScheduleSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
//        domesticScheduleSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_SCHEDULE)
//        domesticScheduleSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_SCHEDULE)
//        domesticScheduleSubmissionParameterMap.put("paymentID", "DomesticScheduledPaymentId")
//        domesticScheduleSubmissionParameterMap.put("paymentType", "DomesticScheduledPayment")

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")
        domesticStandingOrderSubmissionMap.put("paymentType", "DomesticStandingOrder")

//        internationalPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
//        internationalPaymentSubmissionMap.put("fundsConfirmPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
//        internationalPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
//        internationalPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)
//        internationalPaymentSubmissionMap.put("paymentID", "InternationalPaymentId")
//        internationalPaymentSubmissionMap.put("paymentType", "InternationalPayment")

//        internationalStandingOrderPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
//        internationalStandingOrderPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
//        internationalStandingOrderPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
//        internationalStandingOrderPaymentSubmissionMap.put("paymentID", "InternationalStandingOrderId")
//        internationalStandingOrderPaymentSubmissionMap.put("paymentType", "InternationalStandingOrder")
//
//        internationalSchedulePaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
//        internationalSchedulePaymentSubmissionMap.put("fundsConfirmPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
//        internationalSchedulePaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
//        internationalSchedulePaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
//        internationalSchedulePaymentSubmissionMap.put("paymentID", "InternationalScheduledPaymentId")
//        internationalSchedulePaymentSubmissionMap.put("paymentType", "InternationalScheduledPayment")

//        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)
//        listOfParamMaps.add(domesticScheduleSubmissionParameterMap)
        listOfParamMaps.add(domesticStandingOrderSubmissionMap)
//        listOfParamMaps.add(internationalPaymentSubmissionMap)
//        listOfParamMaps.add(internationalStandingOrderPaymentSubmissionMap)
//        listOfParamMaps.add(internationalSchedulePaymentSubmissionMap)
//

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    @DataProvider(name = "paymentSubmissionDomesticPayment")
    Iterator<Object[]> paymentSubmissionDomesticPayment() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()

    }

    /*
     * This method is related to test case TC0404002 the test submits an EndToEndIdentification that differs from the
     * initiation flow
    */
    @DataProvider(name = "submissionPayloadDomesticPaymentsWithoutDataPayload")
    Iterator<Object[]> submissionPayloadDomesticPaymentsWithoutDataPayload() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
     * This method is related to test case TC0404004 the test submits an EndToEndIdentification that differs from the
     * initiation flow
    */
    @DataProvider(name = "paymentSubmissionTypesWithMissMatchEndToEndIdentification")
    Iterator<Object[]> paymentSubmissionTypesWithoutDataPayload() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentSubmissionMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        domesticScheduleSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("paymentID", "DomesticScheduledPaymentId")

        internationalPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
        internationalPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("paymentID", "InternationalPaymentId")

        internationalSchedulePaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("paymentID", "InternationalScheduledPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)
        listOfParamMaps.add(domesticScheduleSubmissionParameterMap)
        listOfParamMaps.add(internationalPaymentSubmissionMap)
        listOfParamMaps.add(internationalSchedulePaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }


    /*
     * This method is related to test case TC0404005 the test submits without the EndToEndIdentification parameter in the
     * submission flow
     */
    @DataProvider(name = "paymentSubmissionTypesWithoutEndToEndIdentification")
    Iterator<Object[]> paymentSubmissionTypesWithoutEndToEndIdentification() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
     * This method is related to test case TC0404005 the test submits without the EndToEndIdentification parameter in the
     * submission flow
     */
    @DataProvider(name = "paymentSubmissionTypesWithNullInstructionIdentification")
    Iterator<Object[]> paymentSubmissionTypesWithNullInstructionIdentification() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    @DataProvider(name = "PaymentSubmissionFlowValidation_Without_StandingOrder_Flows")
    Iterator<Object[]> PaymentSubmissionFlowValidation_Without_StandingOrder_Flows() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentSubmissionMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        domesticScheduleSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("paymentID", "DomesticScheduledPaymentId")

        internationalPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
        internationalPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("paymentID", "InternationalPaymentId")

        internationalSchedulePaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("paymentID", "InternationalScheduledPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)
        listOfParamMaps.add(domesticScheduleSubmissionParameterMap)
        listOfParamMaps.add(internationalPaymentSubmissionMap)
        listOfParamMaps.add(internationalSchedulePaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }


    /*
     * This method is related to test case TC0504006 the test submits a value for the Delivery Address without the
     * country parameter
     */
    @DataProvider(name = "PaymentSubmissionWithoutInstructionIdentificationParameter")
    Iterator<Object[]> getPaymentSubmissionWithoutInstructionIdentificationParameter() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentSubmissionMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        domesticScheduleSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("paymentID", "DomesticScheduledPaymentId")

        internationalPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
        internationalPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("paymentID", "InternationalPaymentId")

        internationalSchedulePaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("paymentID", "InternationalScheduledPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)
        listOfParamMaps.add(domesticScheduleSubmissionParameterMap)
        listOfParamMaps.add(internationalPaymentSubmissionMap)
        listOfParamMaps.add(internationalSchedulePaymentSubmissionMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
    * This method is related to test case TC0604006 the test submission flow with differed value for frequency
    * parameter
    */
    @DataProvider(name = "getDomesticStandingOrderWithDifferedFrequency")
    Iterator<Object[]> getDomesticStandingOrderWithDifferedFrequency() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()
//        Map<String, String> internationalStandingOrderPaymentSubmissionMap = new HashMap<String, String>()

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

//        internationalStandingOrderPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
//        internationalStandingOrderPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
//        internationalStandingOrderPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
//        internationalStandingOrderPaymentSubmissionMap.put("paymentID", "InternationalStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)
//        listOfParamMaps.add(internationalStandingOrderPaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
     * This method is related to test case TC0604005 the test submits a value for FirstPaymentAmount
     * that differ from the initiation payload
     *
     */
    @DataProvider(name = "DomesticStandingOrderWithDifferedFirstPaymentAmount")
    Iterator<Object[]> getDomesticStandingOrderWithDifferedFirstPaymentAmount() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
     * This method is related to test case TC0604008 the test submits a value for FirstPaymentAmount Currency that
     * differ from the initiation payload
     *
     */
    @DataProvider(name = "DomesticStandingOrderWithDifferedFirstPaymentAmount_Currency")
    Iterator<Object[]> DomesticStandingOrderWithDifferedFirstPaymentAmount_Currency() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }


    /*
    * This method is related to test case TC0604009 the test submits a value for RecurringPaymentAmount  that
    * differ from the initiation payload
    *
    */
    @DataProvider(name = "DomesticStandingOrderWithDifferedRecurringPaymentAmount")
    Iterator<Object[]> DomesticStandingOrderWithDifferedRecurringPaymentAmount() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()


        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }


    /*
    * This method is related to test case TC0604010 the test submits a value for RecurringPaymentAmount Currency that
    * differ from the initiation payload
    *
    */
    @DataProvider(name = "DomesticStandingOrderWithDifferedRecurringPaymentAmount_Currency_Parameter")
    Iterator<Object[]> DomesticStandingOrderWithDifferedRecurringPaymentAmount_Currency_Parameter() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
    * This method is related to test case TC0604011 the test submits a value for RecurringPaymentAmount Currency that
    * differ from the initiation payload
    *
    */
    @DataProvider(name = "DomesticStandingOrderWithDifferedFinalPaymentAmount_Parameter")
    Iterator<Object[]> DomesticStandingOrderWithDifferedFinalPaymentAmount_Parameter() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }


    /*
     * This method is related to test case TC0604012 the test submits a value for RecurringPaymentAmount Currency that
     * differ from the initiation payload
     *
     */
    @DataProvider(name = "DomesticStandingOrderWithDifferedFinalPaymentAmount_Currency_Parameter")
    Iterator<Object[]> DomesticStandingOrderWithDifferedFinalPaymentAmount_Currency_Parameter() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }


    /*
    * This method is related to test case TC0404031 the test submits without the risk payload
    *
    */
    @DataProvider(name = "PaymentSubmissionFlowValidation")
    Iterator<Object[]> PaymentSubmissionFlowValidation() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentSubmissionMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        domesticScheduleSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("paymentID", "DomesticScheduledPaymentId")

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        internationalPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
        internationalPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("paymentID", "InternationalPaymentId")

        internationalStandingOrderPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
        internationalStandingOrderPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("paymentID", "InternationalStandingOrderId")

        internationalSchedulePaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("paymentID", "InternationalScheduledPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)
        listOfParamMaps.add(domesticScheduleSubmissionParameterMap)
        listOfParamMaps.add(domesticStandingOrderSubmissionMap)
        listOfParamMaps.add(internationalPaymentSubmissionMap)
        listOfParamMaps.add(internationalSchedulePaymentSubmissionMap)
        listOfParamMaps.add(internationalStandingOrderPaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
     * This method is related to test case TC0604020 the test submits a null value as the MerchantCategoryCode
     * when the PaymentContextCode is EcommerceGoods
     *
     */
    @DataProvider(name = "DomesticStandingOrderValidateRisk")
    Iterator<Object[]> DomesticStandingOrderValidateRisk() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }



    /*
     * Data provider for validating the initiation payload without the permission parameter
     * Flows: Domestic Schedule Payment
     * Called by TC0501002
     */
    @DataProvider(name = "SchedulePaymentsWithoutPermission")
    Iterator<Object[]> SchedulePaymentsWithoutPermission() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.
                domesticScheduleWithoutPermission)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.
                internationalScheduleWithoutPermission)

        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
    * Data provider for validating the permission filed
    * Flows: Domestic Schedule Payment/International Schedule payment
    * Called by TC0501003
    */
    @DataProvider(name = "PaymentWithInvalidPermission")
    Iterator<Object[]> PaymentWithInvalidPermission() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Permission" : ConnectorTestConstants.INVALID_PERMISSION]

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
     * Data provider for validating the permission filed for null values
     * Flows: Domestic Schedule Payment/ International Schedule Payments
     * Called by TC0501004
    */
    @DataProvider(name = "SchedulePaymentWithNullValueForPermission")
    Iterator<Object[]> SchedulePaymentWithNullValueForPermission() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Permission" : ConnectorTestConstants.NULL]

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }




    /*
     * Data provider for validating the permission filed for invalid permission
     * Flows: Domestic Standing Order
     * Called by TC0601009
     */
    @DataProvider(name = "StandingOrderWithInvalidPermission")
    Iterator<Object[]> StandingOrderWithInvalidPermission() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Permission" : ConnectorTestConstants.INVALID_PERMISSION]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * Data provider for validating the initiation payload without the frequency
    * Flows: Domestic Standing Order
    * Called by TC0601013
    */
    @DataProvider(name = "StandingOrderWithoutFrequencyParameter")
    Iterator<Object[]> StandingOrderWithoutFrequencyParameter() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.
                initiationPayloadDomesticStandingOrdersWithoutFrequency)
        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.
                initiationPayloadInternationalStandingOrderWithoutFrequency)

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
    * Data provider for validating the initiation payload with a null value for frequency
    * Flows: Domestic Standing Order
    * Called by TC0601014
    */
    @DataProvider(name = "StandingOrderPayloadWithNullValueForFrequency")
    Iterator<Object[]> StandingOrderPayloadWithNullValueForFrequency() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.NULL]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001023
     */
    @DataProvider(name = "StandingOrderPayloadValueForFrequencyAsEvryDay")
    Iterator<Object[]> StandingOrderPayloadValueForFrequencyAsEvryDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.FREQUENCYPARAMLIST[0]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
    * Data provider for validating the FrequencyTypes
    * Flows: Domestic Standing Order/International Standing Order
    * Called by TC1001024
    */
    @DataProvider(name = "InvalidFrequencyPatternForEvryDay")
    Iterator<Object[]> InvalidFrequencyPatternForEvryDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.INVALIDFREQUENCYPARAMLIST[0]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }



    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001025
     */
    @DataProvider(name = "StandingOrderPayloadValueForFrequencyAsEvryWorkgDay")
    Iterator<Object[]> StandingOrderPayloadValueForFrequencyAsEvryWorkgDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.FREQUENCYPARAMLIST[1]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001026
     */

    @DataProvider(name = "InvalidFrequencyPatternForEvryWorkgDay")
    Iterator<Object[]> InvalidFrequencyPatternForEvryWorkgDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.INVALIDFREQUENCYPARAMLIST[1]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
    * Data provider for validating the FrequencyTypes
    * Flows: Domestic Standing Order/International Standing Order
    * Called by TC1001027
    */
    @DataProvider(name = "StandingOrderPayloadValueForFrequencyAsIntrvlWkDay")
    Iterator<Object[]> StandingOrderPayloadValueForFrequencyAsIntrvlWkDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.FREQUENCYPARAMLIST[2]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001028
     */
    @DataProvider(name = "InvalidFrequencyPatternForIntrvlWkDay")
    Iterator<Object[]> InvalidFrequencyPatternForIntrvlWkDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.INVALIDFREQUENCYPARAMLIST[2]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * Data provider for validating the FrequencyTypes
    * Flows: Domestic Standing Order/International Standing Order
    * Called by TC1001029
    */
    @DataProvider(name = "StandingOrderPayloadValueForFrequencyAsIntrvlMnthDay")
    Iterator<Object[]> StandingOrderPayloadValueForFrequencyAsIntrvlMnthDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.FREQUENCYPARAMLIST[3]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001030
     */
    @DataProvider(name = "InvalidFrequencyPatternForIntrvlMnthDay")
    Iterator<Object[]> InvalidFrequencyPatternForIntrvlMnthDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.INVALIDFREQUENCYPARAMLIST[3]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * Data provider for validating the FrequencyTypes
    * Flows: Domestic Standing Order/International Standing Order
    * Called by TC1001031
    */
    @DataProvider(name = "StandingOrderPayloadValueForFrequencyAsWkInMnthDay")
    Iterator<Object[]> StandingOrderPayloadValueForFrequencyAsWkInMnthDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.FREQUENCYPARAMLIST[4]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))


        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001032
     */
    @DataProvider(name = "InvalidFrequencyPatternForIWkInMnthDay")
    Iterator<Object[]> InvalidFrequencyPatternForIWkInMnthDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.INVALIDFREQUENCYPARAMLIST[4]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001033
     */
    @DataProvider(name = "StandingOrderPayloadValueForFrequencyAsQtrDay")
    Iterator<Object[]> StandingOrderPayloadValueForFrequencyAsQtrDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.FREQUENCYPARAMLIST[5]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001034
    */
    @DataProvider(name = "InvalidFrequencyPatternForQtrDay")
    Iterator<Object[]> InvalidFrequencyPatternForQtrDay() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.INVALIDFREQUENCYPARAMLIST[5]]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
     * Data provider for validating the FrequencyTypes
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC1001037
    */
    @DataProvider(name = "ValidateFrequencyMaxLength")
    Iterator<Object[]> ValidateFrequencyMaxLength() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.FREQUENCY_MAX]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
    * Data provider for validating the initiation payload with an invalid value for frequency
    * Flows: Domestic Standing Order
    * Called by TC0601015
    */
    @DataProvider(name = "StandingOrderWithInvalidValueForFrequency")
    Iterator<Object[]> StandingOrderWithInvalidValueForFrequency() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Frequency" : ConnectorTestConstants.INVALIDFREQUENCY]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for validating the initiation payload with a null value for reference
     * Flows: Domestic Standing Order
     * Called by TC0601017
     */
    @DataProvider(name = "StandingOrderWithNullReference")
    Iterator<Object[]> StandingOrderWithNullReference() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Reference" : ConnectorTestConstants.NULL]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for validating the initiation payload with a null value for reference
     * Flows: Domestic Standing Order
     * Called by TC0601018
     */
    @DataProvider(name = "ReferenceExceedingMaxLength")
    Iterator<Object[]> ReferenceExceedingMaxLength() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Reference" : ConnectorTestConstants.REFERENCE_MAX]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for validating the initiation payload without reference
     * Flows: Domestic Standing Order and international standing order payloads
     * Called by TC0601019
     */
    @DataProvider(name = "StandingOrderWithoutReference")
    Iterator<Object[]> StandingOrderWithoutReference() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.
                initiationPayloadDomesticStandingOrdersWithoutReference)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.
                initiationPayloadInternationalStandingOrderWithoutReference)

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
     * Data provider for validating the initiation payload with a null value for NumberOfPayments
     * Flows: Domestic Standing Order
     * Called by TC0601020
     */
    @DataProvider(name = "ValidateNullValueForNumberOfPayments")
    Iterator<Object[]> ValidateNullValueForNumberOfPayments() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["NumberOfPayments" : ConnectorTestConstants.NULL]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPaymentWithNumberOfPayments(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentWithNumberOfPayments(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
    * Data provider for validating the initiation payload with  NumberOfPayments exceed max length
    * Flows: Domestic Standing Order
    * Called by TC0601021
    */
    @DataProvider(name = "NumberOfPaymentsExceedMaxLength")
    Iterator<Object[]> NumberOfPaymentsExceedMaxLength() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["NumberOfPayments" : ConnectorTestConstants.NUMBER_OF_PAYMENTS_MAX]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPaymentWithNumberOfPayments(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentWithNumberOfPayments(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }



    /*
     * Data provider for validating the initiation payload without  NumberOfPayments
     * Flows: Domestic Standing Order
     * Called by TC0601022
     */
    @DataProvider(name = "StandingOrderWithoutNumberOfPayments")
    Iterator<Object[]> StandingOrderWithoutNumberOfPayments() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.domesticStandingOrderPaymentWithMandatoryParameters(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.internationalStandingOrderPaymentWithMandatoryParameters(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * Data provider for validating the initiation payload with an invalid  FirstPaymentDateTime
    * Flows: Domestic Standing Order
    * Called by TC0601023
    */
    @DataProvider(name = "StandingOrderWithInvalidFirstPaymentDateTime")
    Iterator<Object[]> StandingOrderWithInvalidFirstPaymentDateTime() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["FirstPaymentDateTime": "2019-11-02"]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * Data provider for validating the initiation payload without the  FirstPaymentDateTime
    * Flows: Domestic Standing Order
    * Called by TC0601024
    */
    @DataProvider(name = "StandingOrderWithoutFirstPaymentDateTime")
    Iterator<Object[]> StandingOrderWithoutFirstPaymentDateTime() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.
                initiationPayloadDomesticStandingOrdersWithoutFirstPaymentDateTime)
        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.
                internationalStandingOrderWithoutFirstPaymentDateTime)

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for validating the initiation payload with null FirstPaymentDateTime
     * Flows: Domestic Standing Order
     * Called by TC0601025
     */
    @DataProvider(name = "NullFirstPaymentDateTime")
    Iterator<Object[]> NullFirstPaymentDateTime() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["FirstPaymentDateTime": ConnectorTestConstants.NULL]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))


        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
     * Data provider for validating the initiation payload with null RecurringPaymentDateTime
     * Flows: Domestic Standing Order
     * Called by TC0601026
     */
    @DataProvider(name = "InvalidRecurringPaymentDateTime")
    Iterator<Object[]> InvalidRecurringPaymentDateTime() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()

        def paymentMap = ["RecurringPaymentDateTime": "2019-02-01"]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
     * Data provider for validating the initiation payload without RecurringPaymentDateTime
     * Flows: Domestic Standing Order
     * Called by TC0601027
     */
    @DataProvider(name = "StandingOrderWithoutRecurringPaymentDateTime")
    Iterator<Object[]> StandingOrderWithoutRecurringPaymentDateTime() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.domesticStandingOrderPaymentWithMandatoryParameters(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.internationalStandingOrderPaymentWithMandatoryParameters(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
    * Data provider for validating the initiation payload with invalid value for FinalPaymentDateTime
    * Flows: Domestic Standing Order
    * Called by TC0601028
    */
    @DataProvider(name = "StandingOrderWithInvalidFinalPaymentDateTime")
    Iterator<Object[]> StandingOrderWithInvalidFinalPaymentDateTime() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["FinalPaymentDateTime" : ConnectorTestConstants.INVALID_FINAL_PAYNENT_DT]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))


        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    /*
    * Data provider for validating the initiation payload between FirstPaymentDateTime to FinalPaymentDateTime
    * Flows: Domestic Standing Order and International Standing Order
    * Called by TC0601029
    */
    @DataProvider(name = "StandingOrderPaymentDateTimeValidation")
    Iterator<Object[]> StandingOrderPaymentDateTimeValidation() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [
                "FirstPaymentDateTime" : UKTestUtil.getDateAndTime(5),
                "FinalPaymentDateTime" : UKTestUtil.getDateAndTime(1)
        ]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))


        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }



    /*
     * Data provider for validating the initiation payload without the FinalPaymentDateTime
     * Flows: Domestic Standing Order/International Standing Order
     * Called by TC0601030
     */
    @DataProvider(name = "WithoutFinalPaymentDateTime")
    Iterator<Object[]> WithoutFinalPaymentDateTime() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.domesticStandingOrderPaymentWithoutFinalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.internationalStandingOrderPaymentWithMandatoryParameters(paymentMap))

        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * This data provider validates the Creditor PostalAddress AddressType for invalid value
    * Flows: For international payment flows
    * Called by TC0801121
    */
    @DataProvider(name = "CreditorAgentAddressTypeForInvalidValues")
    Iterator<Object[]> CreditorAgentAddressTypeForInvalidValues() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentAddressType" : ConnectorTestConstants.ADDRESS_TYPE_INVALID]


        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress Department for null value
     * Flows: For international payment flows
     * Called by TC0801122
     */
    @DataProvider(name = "NullValueForCreditorAgentDepartment")
    Iterator<Object[]> NullValueForCreditorAgentDepartment() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentDepartment" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress Department for max length
     * Flows: For international payment flows
     * Called by TC0801123
     */
    @DataProvider(name = "CreditorAgentDepartmentExceedMaxLength")
    Iterator<Object[]> CreditorAgentDepartmentExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentDepartment" : ConnectorTestConstants.DEPARTMENT_MAXLENGTH]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress SubDepartment for null values
     * Flows: For international payment flows
     * Called by TC0801127
     */
    @DataProvider(name = "CreditorAgentSubDepartmentMinLength")
    Iterator<Object[]> CreditorAgentSubDepartmentMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentSubDepartment" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Creditor PostalAddress SubDepartment for max length
    * Flows: For international payment flows
    * Called by TC0801128
    */
    @DataProvider(name = "CreditorAgentSubDepartmentExceedMaxLength")
    Iterator<Object[]> CreditorAgentSubDepartmentExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentSubDepartment" : ConnectorTestConstants.SUBDEPT_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress StreetName for min length
     * Flows: For international payment flows
     * Called by TC0801129
     */
    @DataProvider(name = "CreditorAgentStreetNameMinLength")
    Iterator<Object[]> CreditorAgentStreetNameMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentStreetName" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Creditor PostalAddress StreetName for exceeding the max length
    * Flows: For international payment flows
    * Called by TC0801130
    */
    @DataProvider(name = "CreditorAgentStreetNameExceedMaxLength")
    Iterator<Object[]> CreditorAgentStreetNameExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentStreetName" : ConnectorTestConstants.STREETNAME_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Creditor PostalAddress BuildingNumber exceed MaxLength
    * Flows: For international payment flows
    * Called by TC0801132
    */
    @DataProvider(name = "CreditorAgentBuildingNumberForMinLength")
    Iterator<Object[]> CreditorAgentBuildingNumberForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentBuildingNumber" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress BuildingNumber exceed MaxLength
     * Flows: For international payment flows
     * Called by TC0801133
     */
    @DataProvider(name = "CreditorAgentBuildingNumberExceedMaxLength")
    Iterator<Object[]> CreditorAgentBuildingNumberExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentBuildingNumber" : ConnectorTestConstants.BUILDING_NUM_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress PostCode less than MinLength
     * Flows: For international payment flows
     * Called by TC0801135
     */
    @DataProvider(name = "CreditorAgentPostCodeForMinLength")
    Iterator<Object[]> CreditorAgentPostCodeForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentPostCode" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress PostCode exceed the max Length
     * Flows: For international payment flows
     * Called by TC0801136
     */
    @DataProvider(name = "CreditorAgentPostCodeExceedMaxLength")
    Iterator<Object[]> CreditorAgentPostCodeExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentPostCode" : ConnectorTestConstants.POSTCODE_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the CreditorAgent PostalAddress Town Name is less than the Min Length
     * Flows: For international payment flows
     * Called by TC0801138
     */
    @DataProvider(name = "CreditorAgentTownNameForMinLength")
    Iterator<Object[]> CreditorAgentTownNameForMinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentTownName" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the CreditorAgent Scheme Name is less than the Min Length
    * Flows: For international payment flows
    * Called by TC0901157
    */
    @DataProvider(name = "CreditorAgentSchemeNameForNullValues")
    Iterator<Object[]> CreditorAgentSchemeNameForNullValues() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentSchemeName" : ConnectorTestConstants.NULL,
                          "CreditorSchemeName" : ConnectorTestConstants.SCHEMENAME_OBIE_PAN,
                          "CreditorIdentification" : ConnectorTestConstants.IDENTIFICATION_OBIE_PAN]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the CreditorAgent Scheme Name is more than the max length
     * Flows: For international payment flows
     * Called by TC0901158
     */
    @DataProvider(name = "CreditorAgentSchemeNameForMaxLength")
    Iterator<Object[]> CreditorAgentSchemeNameForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentSchemeName" : ConnectorTestConstants.SCHEME_NAME_MAX_LENGTH,
                          "CreditorSchemeName" : ConnectorTestConstants.SCHEMENAME_OBIE_PAN,
                          "CreditorIdentification" : ConnectorTestConstants.IDENTIFICATION_OBIE_PAN]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the CreditorAgent payload without the Scheme Name
     * Flows: For international payment flows
     * Called by TC0801173
     */
    @DataProvider(name = "CreditorAgentWithoutSchemeName")
    Iterator<Object[]> CreditorAgentWithoutSchemeName() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentWithoutCreditorAgentSchemeName)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentWithoutCreditorAgentSchemeName)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderWithoutCreditorAgentSchemeName)

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the CreditorAgent Identification  is a null value
     * Flows: For international payment flows
     * Called by TC0901160
     */
    @DataProvider(name = "CreditorAgentIdentificationForNull")
    Iterator<Object[]> CreditorAgentIdentificationForNull() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentIdentification" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the CreditorAgent Identification for max length
     * Flows: For international payment flows
     * Called by TC0901161
     */
    @DataProvider(name = "CreditorAgentIdentificationForMaxLength")
    Iterator<Object[]> CreditorAgentIdentificationForMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentIdentification" : ConnectorTestConstants.MAX_IDENTIFICATION]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the CreditorAgent Name  is a null value
    * Flows: For international payment flows
    * Called by TC0901162
    */
    @DataProvider(name = "CreditorAgentNameForNullValues")
    Iterator<Object[]> CreditorAgentNameForNullValues() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentName" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the CreditorAgent Name  exceeds max length
     * Flows: For international payment flows
     * Called by TC0901163
     */
    @DataProvider(name = "CreditorAgentNameExceedMaxLength")
    Iterator<Object[]> CreditorAgentNameExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentName" : ConnectorTestConstants.CREDITOR_AGENT_NAME_MAX_LENGTH]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * This data provider validates the CreditorAgent AddressType  is a null value
     * Flows: For international payment flows
     * Called by TC0901165
     */
    @DataProvider(name = "CreditorAgentAddressType")
    Iterator<Object[]> CreditorAgentAddressType() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentAddressType" : ConnectorTestConstants.ADDRESS_TYPE_INVALID]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress TownName exceeds the Max Length
     * Flows: For international payment flows
     * Called by TC0801139
     */
    @DataProvider(name = "CreditorAgentTownNameExceedMaxLength")
    Iterator<Object[]> CreditorAgentTownNameExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentTownName" : ConnectorTestConstants.TOWN_NAME_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))


        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress Country code for invalid value
     * Flows: For international payment flows
     * Called by TC0801141
     */
    @DataProvider(name = "CreditorAgentCountryCode")
    Iterator<Object[]> CreditorAgentCountryCode() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentCountry" : ConnectorTestConstants.INVALID_COUNTRY]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress Country less than the min Length
     * Flows: For international payment flows
     * Called by TC0901153
     */
    @DataProvider(name = "CreditorAgentCountryCodeMinLengthValidation")
    Iterator<Object[]> CreditorAgentCountryCodeMinLengthValidation() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentCountry" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress AddressLine less than the min Length
     * Flows: For international payment flows
     * Called by TC1001421
     */
    @DataProvider(name = "CreditorAgentAddressLineMinLengthValidation")
    Iterator<Object[]> CreditorAgentAddressLineMinLengthValidation() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentAddressLine" : "[\""+ConnectorTestConstants.NULL+"\"]"]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Creditor PostalAddress AddressLine more than the max Length
    * Flows: For international payment flows
    * Called by TC1001422
    */
    @DataProvider(name = "CreditorAgentAddressLineMaxLengthValidation")
    Iterator<Object[]> CreditorAgentAddressLinCreditorAgentAddressLineMaxLengthValidationeMinLengthValidation() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentAddressLine" : "[\""+ConnectorTestConstants.ADDRESS_LINE_MAX+"\"]"]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Creditor PostalAddress CountrySubDivision for min length
     * Flows: For international payment flows
     * Called by TC0801143
     */
    @DataProvider(name = "CreditorAgentCountrySubDivision")
    Iterator<Object[]> CreditorAgentCountrySubDivision() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentCountrySubDivision" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Creditor PostalAddress CountrySubDivision for max length
    * Flows: For international payment flows
    * Called by TC0801144
    */
    @DataProvider(name = "CreditorAgentCountrySubDivisionExceedMaxLength")
    Iterator<Object[]> CreditorAgentCountrySubDivisionExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAgentCountrySubDivision" : ConnectorTestConstants.COUNTRY_SUB_DEV_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentForCreditorAgent(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePaymentForCreditorAgent(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Risk Merchant CategoryCode
    * Flows: For international payment flows
    * Called by TC0801152
    */
    @DataProvider(name = "internationalPaymentValidateMerchantCustomerIdentification")
    Iterator<Object[]> internationalPaymentValidateMerchantCustomerIdentification() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["MerchantCustomerIdentification" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the Risk Merchant Customer Identification for max length
     * Flows: For international payment flows
     * Called by TC0801154
     */
    @DataProvider(name = "ValidateMerchantCustomerIdentificationExceedMaxLength")
    Iterator<Object[]> ValidateMerchantCustomerIdentificationExceedMaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["MerchantCustomerIdentification": ConnectorTestConstants.MERCHANT_CUST_IDENTIFICATION_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the Risk Initiation Request without Merchant Customer Identification for max length
    * Flows: For international payment flows
    * Called by TC1001440
    */
    @DataProvider(name = "ValidatePayloadWithoutMerchantCustomerIdentification")
    Iterator<Object[]> ValidatePayloadWithoutMerchantCustomerIdentification() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.
                domesticPaymentWithoutMerchantCustomerIdentification)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.
                domesticScheduleWithoutMerchantCustomerIdentification)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.
                domesticStandingOrdersWithoutMerchantCustomerIdentification)


        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.
                internationalPaymentWithoutMerchantCustomerIdentification)


        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.
                internationalStandingOrderWithoutMerchantCustomerIdentification)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.
                internationalScheduleWithoutMerchantCustomerIdentification)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * This data provider validates the purpose field for max length
     * Flows: For International Schedule Payment
     * Called by TC0901031
     */
    @DataProvider(name = "ValidatePurposeForMaxLength")
    Iterator<Object[]> ValidatePurposeForMaxLength() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Purpose": ConnectorTestConstants.PURPOSE_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with null value for Purpose field
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801028
     */
    @DataProvider(name = "ValidatePurposeFieldForNullValues")
    Iterator<Object[]> ValidatePurposeFieldForNullValues() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Purpose": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with null value for Purpose field
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801028
     */
    @DataProvider(name = "WithoutPurposeParameter")
    Iterator<Object[]> WithoutPurposeParameter() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.internationalPaymentWithMandatoryParameters(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.internationalSchedulePaymentWithMandatoryParameters(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.internationalStandingOrderPaymentWithMandatoryParameters(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the ChargeBearer field for null values
     * Flows: For International Schedule Payment
     * Called by TC0901033
     */
    @DataProvider(name = "PaymentValidateChargeBearerForNull")
    Iterator<Object[]> PaymentValidateChargeBearerForNull() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["ChargeBearer" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * This data provider validates the initiation payloads without the  ChargeBearer payload
    * Flows: For International Schedule Payment
    * Called by TC0801030
    */
    @DataProvider(name = "Initiation_Payloads_Without_The_ChargeBearer")
    Iterator<Object[]> Initiation_Payloads_Without_The_ChargeBearer() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.internationalPaymentWithMandatoryParameters(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.internationalSchedulePaymentWithMandatoryParameters(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.internationalStandingOrderPaymentWithMandatoryParameters(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the ChargeBearer for invalid inputs
     * Flows: For International Schedule Payment
     * Called by TC0801031
     */
    @DataProvider(name = "ChargeBearerForInvalidInputs")
    Iterator<Object[]> ChargeBearerForInvalidInputs() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["ChargeBearer" : ConnectorTestConstants.INVALID_CHARGE_BEARER]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the RequestExecutionDateTime field for invalid inputs
     * Flows: For International Schedule Payment
     * Called by TC0901035
     */
    @DataProvider(name = "ValidateRequestExecutionDateTimeForInvalidFormat")
    Iterator<Object[]> ValidateRequestExecutionDateTimeForInvalidInput() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RequestedExecutionDateTime": "2019-12-12"]

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * This data provider validates the RequestExecutionDateTime field for invalid inputs
    * Flows: For International Schedule Payment
    * Called by TC0801036
    */
    @DataProvider(name = "ValidateRequestWithoutRequestExecutionDateTimeParameter")
    Iterator<Object[]> ValidateRequestWithoutRequestExecutionDateTimeParameter() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()

        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.
                domesticScheduleWithoutRequestExecutionDateTime)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.
                internationalSchedulePaymentWithoutRequestExecutionDateTime)

        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the CurrencyOfTransfer field for Invalid Inputs
     * Flows: For All International payment flows
     * Called by TC0801025
     */
    @DataProvider(name = "CurrencyOfTransferForInvalidInputs")
    Iterator<Object[]> CurrencyOfTransferForInvalidInputs() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CurrencyOfTransfer": ConnectorTestConstants.INVALID_CURRENCY]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request without the CurrencyOfTransfer parameter
     * Flows: All International payment flows
     * Called by TC0801024
     */
    @DataProvider(name = "WithoutCurrencyOfTransferParameter")
    Iterator<Object[]> WithoutCurrencyOfTransferParameter() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.validateInternationalPaymentWithoutCurrencyOfTransfer)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.validateInternationalSchedulePaymentWithoutCurrencyOfTransfer)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderWithoutCurrencyOfTransfer)

        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with null value for ExchangeRateInformation ContractIdentification(
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801027
     */
    @DataProvider(name = "ExchangeRateInformationContractIdentification")
    Iterator<Object[]> ExchangeRateInformationContractIdentification() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["ContractIdentification": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with null value for ExchangeRateInformation ContractIdentification(
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0901199
     */
    @DataProvider(name = "ValidateNullExchangeRate")
    Iterator<Object[]> ValidateNullExchangeRate() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["ExchangeRate" : "\""+ConnectorTestConstants.NULL+"\""]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with null value for ExchangeRateInformation ContractIdentification(
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0901198
     */
    @DataProvider(name = "InvalidExchangeRate")
    Iterator<Object[]> InvalidExchangeRate() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["ExchangeRate" : "\""+ConnectorTestConstants.INVALID_EXCHANGE_RATE+"\""]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with null value for ExchangeRateInformation ContractIdentification
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801028
     */
    @DataProvider(name = "ValidateContractIdentificationMaxLength")
    Iterator<Object[]> ValidateContractIdentificationMaxLength() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["ContractIdentification" : ConnectorTestConstants.CONTRTACT_ID_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with an invalid value for ExchangeRateInformation UnitCurrency
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801039
     */
    @DataProvider(name = "ValidateUnitCurrency_For_InvalidValue")
    Iterator<Object[]> ValidateUnitCurrency_For_InvalidValue() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["UnitCurrency" : ConnectorTestConstants.INVALID_CURRENCY]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with null value for ExchangeRateInformation UnitCurrency
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801040
     */
    @DataProvider(name = "ValidateExchangeRateInformationUnitCurrency")
    Iterator<Object[]> ValidateExchangeRateInformationUnitCurrency() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["UnitCurrency" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
      * This data provider validates the initiation request without the ExchangeRateInformation UnitCurrency
      * Flows: For International payment and International  Schedule Payment
      * Called by TC0901197
      */
    @DataProvider(name = "ExchangeRateInformationWithoutUnitCurrency")
    Iterator<Object[]> ExchangeRateInformationWithoutUnitCurrency() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateExchangeRateInformationWithoutUnitCurrency)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateInternationalScheduleExchangeRateInformationWithoutUnitCurrency)

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with an invalid value for ExchangeRateInformation RateType
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801042
     */
    @DataProvider(name = "ValidateRateTypeForInvalidValue")
    Iterator<Object[]> ValidateRateTypeForInvalidValue() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RateType" : ConnectorTestConstants.INVALID_RATE_TYPE]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request with an invalid value for ExchangeRateInformation RateType
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801042
     */
    @DataProvider(name = "ValidateExchangeRateInformationRateTypeForNullValue")
    Iterator<Object[]> ValidateExchangeRateInformationRateTypeForNullValue() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RateType" : ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the initiation request without the  ExchangeRateInformation RateType
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801045
     */
    @DataProvider(name = "ValidateExchangeRateInformationWithoutRateTypeParameter")
    Iterator<Object[]> ValidateExchangeRateInformationWithoutRateTypeParameter() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateExchangeRateInformation_Without_RateType)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateInternationalSchedule_WithoutExchangeRateInformation_RateTypeParameter)

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the ExchangeRateInformation with the RateType as Agreed
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801046
     */
    @DataProvider(name = "ValidateRateTypeParameter_Agreed")
    Iterator<Object[]> ValidateRateTypeParameter_Agreed() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateExchangeRateInformation_RateType)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateInternationalScheduleExchangeRateInformation_RateType)

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the ExchangeRateInformation with the RateType as Actual
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801047
     */
    @DataProvider(name = "ValidateRateTypeParameter_Actual")
    Iterator<Object[]> ValidateRateTypeParameter_Actual() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RateType" : ConnectorTestConstants.RATETYPE]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the ExchangeRateInformation with the RateType as Indicative
     * Flows: For International payment and International  Schedule Payment
     * Called by TC0801048
     */
    @DataProvider(name = "ValidateRateTypeParameter_Indicative")
    Iterator<Object[]> ValidateRateTypeParameter_Indicative() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RateType" : ConnectorTestConstants.RATE_TYPE_INDICATIVE]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }



    /*
     * This data provider validates the CurrencyOfTransfer field for null Inputs
     * Flows: All International payment Flowa
     * Called by TC0801023
     */
    @DataProvider(name = "ValidateCurrencyOfTransferForNullValues")
    Iterator<Object[]> ValidateCurrencyOfTransferForNullValues() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CurrencyOfTransfer": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the  LocalInstrument for null values
     * Flows: For International Schedule Payment
     * Called by TC0901024
     */
    @DataProvider(name = "PaymentValidateLocalInstrumentForNull")
    Iterator<Object[]> PaymentValidateLocalInstrumentForNull() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["LocalInstrument": ConnectorTestConstants.NULL]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))


        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the  LocalInstrument for exceeding the max length
     * Flows: For International Schedule Payment
     * Called by TC0901025
     */
    @DataProvider(name = "ValidateLocalInstrumentForExceedingMaxLength")
    Iterator<Object[]> ValidateLocalInstrumentForExceedingMaxLength() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["LocalInstrument": ConnectorTestConstants.LOCALINS_MAX]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * This data provider validates the  InstructionPriority for null values
    * Flows: For International Schedule Payment
    * Called by TC0901027
    */
    @DataProvider(name = "ValidateInstructionPriorityForNull")
    Iterator<Object[]> ValidateInstructionPriorityForNull() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["InstructionPriority": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * This data provider validates the  InstructionPriority for null values
     * Flows: For International Schedule Payment
     * Called by TC0901028
     */

    @DataProvider(name = "ValidateInstructionPriorityForInvalidValues")
    Iterator<Object[]> ValidateInstructionPriorityForInvalidValues() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["InstructionPriority": ConnectorTestConstants.INVALID_PRIORTY]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
   * This method is related to test case TC0404031 the test submits without the risk payload
   *
   */
    @DataProvider(name = "PaymentSubmissionFlowValidation_InternationalSchedulePayments")
    Iterator<Object[]> PaymentSubmissionFlowValidation_InternationalSchedulePayments() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalSchedulePaymentSubmissionMap = new HashMap<String, String>()

        internationalSchedulePaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("paymentID", "InternationalScheduledPaymentId")

        listOfParamMaps.add(internationalSchedulePaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    @DataProvider(name = "InternationalStandingOrderSubmission")
    Iterator<Object[]> InternationalStandingOrderSubmission() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalStandingOrderPaymentSubmissionMap = new HashMap<String, String>()

        internationalStandingOrderPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
        internationalStandingOrderPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("paymentID", "InternationalStandingOrderId")

        listOfParamMaps.add(internationalStandingOrderPaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    @DataProvider(name = "StandingOrderSubmission")
    Iterator<Object[]> StandingOrderSubmission() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentSubmissionMap = new HashMap<String, String>()

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        internationalStandingOrderPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
        internationalStandingOrderPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("paymentID", "InternationalStandingOrderId")

        listOfParamMaps.add(domesticStandingOrderSubmissionMap)
        listOfParamMaps.add(internationalStandingOrderPaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
     * Data provider for validating without the initiation payload
     * Flows: Domestic payments and International payments flows
     * Called by TC0404003
     */
    @DataProvider(name = "validateWithoutInitiationPayload")
    Iterator<Object[]> validateWithoutInitiationPayload() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticWithoutInitiationPayload)
        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticScheduleWithoutInitiationPayload)
        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrdersWithoutInitiationPayload)
        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPaymentWithoutInitiationPayload)
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalScheduleWithoutInitiationPayload)
        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderWithoutInitiationPayload)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)


        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
     * Data provider for Creditor Postal Address name for minimum length
     * Flows: all  International flows
     * Called by TC0801053
     */
    @DataProvider(name = "validateCreditorPostalAddressName_MinLength")
    Iterator<Object[]> validateCreditorPostalAddressName_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorName": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for Creditor Postal Address name for maximum length
    * Flows: all  International flows
    * Called by TC0801054
    */
    @DataProvider(name = "validateCreditorPostalAddressName_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddressName_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorName": ConnectorTestConstants.CREDITOR_POSTAL_ADDRESS_NAME_MAX_LENGTH]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for Creditor Postal Address department for minimum length
     * Flows: all  International flows
     * Called by TC0801055
     */
    @DataProvider(name = "validateCreditorPostalAddress_Department_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_Department_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorDepartment": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for Creditor Postal Address department for maximum length
     * Flows: all  International flows
     * Called by TC0801056
     */
    @DataProvider(name = "validateCreditorPostalAddress_Department_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddress_Department_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorDepartment": ConnectorTestConstants.DEPARTMENT_MAXLENGTH]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for Creditor Postal Address sub department for minimum length
     * Flows: all  International flows
     * Called by TC0801058
     */
    @DataProvider(name = "validateCreditorPostalAddress_SubDepartment_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_SubDepartment_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorSubDepartment": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for Creditor Postal Address sub department for maxlength
    * Flows: all  International flows
    * Called by  TC0801059
    */
    @DataProvider(name = "validateCreditorPostalAddress_SubDepartment_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddress_SubDepartment_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorSubDepartment": ConnectorTestConstants.SUBDEPT_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for Creditor Postal Address street name for min length
    * Flows: all  International flows
    * Called by  TC0801061
    */
    @DataProvider(name = "validateCreditorPostalAddress_StreetName_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_StreetName_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorStreetName": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for Creditor Postal Address street name for max length
     * Flows: all  International flows
     * Called by  TC0801062
     */
    @DataProvider(name = "validateCreditorPostalAddress_StreetName_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddress_StreetName_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorStreetName": ConnectorTestConstants.STREETNAME_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * Data provider for Creditor Postal Address BuildingNumber for min length
    * Flows: all  International flows
    * Called by  TC0801064
    */
    @DataProvider(name = "validateCreditorPostalAddress_BuildingNumber_For_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_BuildingNumber_For_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorBuildingNumber": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for Creditor Postal Address BuildingNumber for max length
    * Flows: all  International flows
    * Called by  TC0801065
    */
    @DataProvider(name = "validateCreditorPostalAddress_BuildingNumber_For_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddress_BuildingNumber_For_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorBuildingNumber": ConnectorTestConstants.BUILDING_NUM_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for Creditor Postal Address PostCode for min length
     * Flows: all  International flows
     * Called by  TC0801067
     */
    @DataProvider(name = "validateCreditorPostalAddress_PostCode_For_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_PostCode_For_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorPostCode": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for Creditor Postal Address PostCode for max length
     * Flows: all  International flows
     * Called by  TC0801068
     */
    @DataProvider(name = "validateCreditorPostalAddress_PostCode_For_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddress_PostCode_For_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorPostCode": ConnectorTestConstants.POSTCODE_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for Creditor Postal Address Town name for null
     * Flows: all  International flows
     * Called by  TC0801070
     */
    @DataProvider(name = "validateCreditorPostalAddress_TownName_For_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_TownName_For_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorTownName": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for Creditor Postal Address PostCode for max length
     * Flows: all  International flows
     * Called by  TC0801071
     */
    @DataProvider(name = "validateCreditorPostalAddress_TownName_For_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddress_TownName_For_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorTownName": ConnectorTestConstants.TOWN_NAME_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for Creditor Postal Address Country for Regex Pattern
     * Flows: all  International flows
     * Called by  TC0801073
     */
    @DataProvider(name = "validateCreditorPostalAddress_Country_For_ValidRegex")
    Iterator<Object[]> validateCreditorPostalAddress_Country_For_ValidRegex() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorCountry": ConnectorTestConstants.INVALID_COUNTRY]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for Creditor Postal Address CountrySubDivision for min length
     * Flows: all  International flows
     * Called by  TC0801075
     */
    @DataProvider(name = "validateCreditorPostalAddress_CountrySubDivision_For_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_CountrySubDivision_For_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorCountrySubDivision": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))


        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * Data provider for Creditor Postal Address CountrySubDivision for min length
    * Flows: all  International flows
    * Called by  TC0801076
    */
    @DataProvider(name = "validateCreditorPostalAddress_CountrySubDivision_For_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddress_CountrySubDivision_For_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorCountrySubDivision": ConnectorTestConstants.COUNTRY_SUB_DEV_MAX]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for Creditor Postal Address AddressType for invalid values
     * Flows: all  International flows
     * Called by  TC0801078
     */
    @DataProvider(name = "validateCreditorPostalAddress_AddressType_For_Invalid_Values")
    Iterator<Object[]> validateCreditorPostalAddress_AddressType_For_Invalid_Values() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAddressType": ConnectorTestConstants.ADDRESS_TYPE_INVALID]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
    * Data provider for Creditor Postal Address AddressType for min length
    * Flows: all  International flows
    * Called by  TC0901080
    */
    @DataProvider(name = "validateCreditorPostalAddress_AddressType_For_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_AddressType_For_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAddressType": ConnectorTestConstants.NULL]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * Data provider for Creditor Postal Address AddressLine for min length
    * Flows: all  International flows
    * Called by  TC0901106
    */
    @DataProvider(name = "validateCreditorPostalAddress_AddressLine_For_MinLength")
    Iterator<Object[]> validateCreditorPostalAddress_AddressLine_For_MinLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()


        def paymentMap = ["CreditorAddressLine": "[\""+ConnectorTestConstants.NULL+"\"]"]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     *  Data provider for Creditor Postal Address AddressLine for max length
     * Flows: all  International flows
     * Called by  TC0901107
    */
    @DataProvider(name = "validateCreditorPostalAddress_AddressLine_For_MaxLength")
    Iterator<Object[]> validateCreditorPostalAddress_AddressLine_For_MaxLength() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()


        def paymentMap = ["CreditorAddressLine": "[\""+ConnectorTestConstants.ADDRESS_LINE_MAX+"\"]"]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }


    /*
     * Data provider for Creditor Postal Address AddressLine for max length
     * Flows: all  International flows
     * Called by  TC0901109
    */
    @DataProvider(name = "validateAddressLine_For_Max_Items")
    Iterator<Object[]> validateAddressLine_For_Max_Items() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()
        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["CreditorAddressLine": "[\""+ConnectorTestConstants.ADDRESS_LIEN_MAX_ITEMS+"\"]"]

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
    * This data provider validates the initiation payload with empty payloads
    * Flows: For domestic payment flows
    * Called by TC0401096
   */
    @DataProvider(name = "paymentInitiationConsentTypesWithEmptyStringPayload")
    Iterator<Object[]> paymentInitiationConsentTypesWithEmptyStringPayload() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduledParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalParameterMap = new HashMap<String, String>()
        Map<String, String> internationalScheduledParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload",paymentInitiationPayloads.emptyStringPayload)

        domesticScheduledParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduledParameterMap.put("payload",paymentInitiationPayloads.emptyStringPayload)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload",paymentInitiationPayloads.emptyStringPayload)

        internationalParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalParameterMap.put("payload",paymentInitiationPayloads.emptyStringPayload)

        internationalScheduledParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalScheduledParameterMap.put("payload",paymentInitiationPayloads.emptyStringPayload)

        internationalStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderParameterMap.put("payload",paymentInitiationPayloads.emptyStringPayload)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduledParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalParameterMap)
        listOfParamMaps.add(internationalScheduledParameterMap)
        listOfParamMaps.add(internationalStandingOrderParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    @DataProvider(name = "paymentSubmissions")
    Iterator<Object[]> paymentSubmissions() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    /*
     * This data provider validates the RequestExecutionDateTime field for past dates
     * Flows: For International Schedule Payment, Domestic Schedule Payments
     * Called by TC0501018
     */
    @DataProvider(name = "InitiationForCheckIdempotency")
    Iterator<Object[]> InitiationForCheckIdempotency() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        //payloads for first initiation
        def paymentMap = [:]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        //payloads for second initiation
        def paymentSMap = ["LocalInstrument" : ConnectorTestConstants.LOCALINS_CHAPS, "Frequency" : ConnectorTestConstants.FREQUENCY]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload_changed", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentSMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload_changed", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentSMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload_changed", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentSMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload_changed", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentSMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload_changed", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentSMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload_changed", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentSMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    /*
    * This data provider validates the RequestExecutionDateTime field for past dates
    * Flows: For International Schedule Payment, Domestic Schedule Payments
    * Called by TC0501018
    */
    @DataProvider(name = "ValidateRequestExecutionDateTimeForPastDate")
    Iterator<Object[]> ValidateRequestExecutionDateTimeForPastDate() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()

        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["RequestedExecutionDateTime": "2018-08-06T00:00:00+00:00"]

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))
        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    @DataProvider(name = "InitiationWithNonExistingResource")
    Iterator<Object[]> validateInitiationWithNonExistingResource() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticParameterMap.put("path", ConnectorTestConstants.NON_EXIST_CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.NON_EXIST_CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.NON_EXIST_CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.NON_EXIST_CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.NON_EXIST_CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.NON_EXIST_CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    @DataProvider(name = "InvalidNumericFormatForAmount")
    Iterator<Object[]> InvalidNumericFormatForAmount() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Amount" : ConnectorTestConstants.INVALID_NUMERIC_AMOUNT, "FirstPaymentAmount" : ConnectorTestConstants.INVALID_NUMERIC_AMOUNT]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    @DataProvider(name = "InvalidAccountIdentification")
    Iterator<Object[]> InvalidAccountIdentification() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()


        def paymentMap = ["CreditorIdentification" : ConnectorTestConstants.INVALID_IDENTIFICATION]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    /*
     * Data provider for validating the missing debtor account name fields
     * Flows: for all payment flows
     * Called by TC0501036
     */
    @DataProvider(name = "paymentTypesWithoutDebtorAccount_Name")
    Iterator<Object[]> paymentTypesWithoutDebtorAccount_Name() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateDomesticPayDebtorWithoutName)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam", paymentInitiationPayloads.
                domesticScheduleDebtorAccountWithoutName)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateDomesticStandingOrderDebtorWithoutName)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateInternationalPaymentDebtorWithoutName)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateInternationalSchedulePaymentDebtorWithoutName)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.
                validateInternationalStandingOrderDebtorWithoutName)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    @DataProvider(name = "InvalidPaymentContextCodeInAddressInRisk")
    Iterator<Object[]> InvalidPaymentContextCodeInAddressInRisk() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["PaymentContextCode" : ConnectorTestConstants.INVALID_PAYMENT_CONTEX_CODE]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }

    @DataProvider(name = "PaymentConsentWithConsentCreatedPreviousVersionOfAPI")
    Iterator<Object[]> PaymentConsentWithConsentCreatedPreviousVersionOfAPI() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentSubmissionMap = new HashMap<String, String>()

        domesticPaymentSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_V3)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")

        domesticScheduleSubmissionParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE_V3)
        domesticScheduleSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("paymentID", "DomesticScheduledPaymentId")

        domesticStandingOrderSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS_V3)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")

        internationalPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS_V3)
        internationalPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
        internationalPaymentSubmissionMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("paymentID", "InternationalPaymentId")

        internationalStandingOrderPaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER_V3)
        internationalStandingOrderPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
        internationalStandingOrderPaymentSubmissionMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("paymentID", "InternationalStandingOrderId")

        internationalSchedulePaymentSubmissionMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE_V3)
        internationalSchedulePaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("paymentID", "InternationalScheduledPaymentId")

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)
        listOfParamMaps.add(domesticScheduleSubmissionParameterMap)
        listOfParamMaps.add(domesticStandingOrderSubmissionMap)
        listOfParamMaps.add(internationalPaymentSubmissionMap)
        listOfParamMaps.add(internationalSchedulePaymentSubmissionMap)
        listOfParamMaps.add(internationalStandingOrderPaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    @DataProvider(name = "PaymentConsentWithConsentCreatedPreviousSubVersionOfAPI")
    Iterator<Object[]> PaymentConsentWithConsentCreatedPreviousSubVersionOfAPI() {

        Collection<Object[]> paymentSubmissionTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticPaymentSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleSubmissionParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentSubmissionMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentSubmissionMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticPaymentSubmissionParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_V3)
        domesticPaymentSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH)
        domesticPaymentSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticPaymentSubmissionParameterMap.put("paymentID", "DomesticPaymentId")
        domesticPaymentSubmissionParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleSubmissionParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE_V3)
        domesticScheduleSubmissionParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_SCHEDULE)
        domesticScheduleSubmissionParameterMap.put("paymentID", "DomesticScheduledPaymentId")
        domesticScheduleSubmissionParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderSubmissionMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS_V3)
        domesticStandingOrderSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderSubmissionMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderSubmissionMap.put("paymentID", "DomesticStandingOrderId")
        domesticStandingOrderSubmissionMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentSubmissionMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS_V3)
        internationalPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
        internationalPaymentSubmissionMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)
        internationalPaymentSubmissionMap.put("paymentID", "InternationalPaymentId")
        internationalPaymentSubmissionMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentSubmissionMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER_V3)
        internationalStandingOrderPaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
        internationalStandingOrderPaymentSubmissionMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentSubmissionMap.put("paymentID", "InternationalStandingOrderId")
        internationalStandingOrderPaymentSubmissionMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentSubmissionMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE_V3)
        internationalSchedulePaymentSubmissionMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("retrievePath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentSubmissionMap.put("paymentID", "InternationalScheduledPaymentId")
        internationalSchedulePaymentSubmissionMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticPaymentSubmissionParameterMap)
        listOfParamMaps.add(domesticScheduleSubmissionParameterMap)
        listOfParamMaps.add(domesticStandingOrderSubmissionMap)
        listOfParamMaps.add(internationalPaymentSubmissionMap)
        listOfParamMaps.add(internationalSchedulePaymentSubmissionMap)
        listOfParamMaps.add(internationalStandingOrderPaymentSubmissionMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentSubmissionTypesCollection.add([map] as Object[])
        }
        return paymentSubmissionTypesCollection.iterator()
    }

    @DataProvider(name = "paymentInitiationWithSupplementaryField")
    Iterator<Object[]> paymentInitiationWithSupplementaryField() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.
                validateDomesticInitiationWithSupplementaryField)

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.
                validateDomesticScheduleInitiationWithSupplementaryField)

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.
                validateDomesticStandingOrderInitiationWithSupplementaryField)

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateInternationalPaymentInitiationWithSupplementaryField)

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateInternationalSchedulePaymentInitiationWithSupplementaryField)

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.
                validateInternationalStandingOrderInitiationWithSupplementaryField)

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()

    }

    @DataProvider(name = "jwsHeadersWithInvalidAndUnsupportedClaims")
    Iterator<String> jwsHeadersWithInvalidAndUnsupportedClaims() {

        return [
                JWSHeaders.jwsHeaderWithInvalidKid,
                JWSHeaders.jwsHeaderWithInvalidIat,
                JWSHeaders.jwsHeaderWithInvalidTan,
                JWSHeaders.jwsHeaderWithInvalidIss,
                JWSHeaders.jwsHeaderWithB64True,
                JWSHeaders.jwsHeaderWithUnsupportedClaims
        ].iterator()
    }

    @DataProvider(name = "jwsHeadersWithMissingClaims")
    Iterator<String> jwsHeadersWithMissingClaims() {

        return [
                JWSHeaders.jwsHeaderWithMissingIss,
                JWSHeaders.jwsHeaderWithMissingTan,
                JWSHeaders.jwsHeaderWithMissingIat,
                JWSHeaders.jwsHeaderWithMissingB64,
        ].iterator()
    }

    @DataProvider(name = "paymentInitiationWithInvalidDebtorAccount")
    Iterator<Object[]> paymentInitiationWithInvalidDebtorAccount() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["DebtorIdentification":"30080012343465"]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    @DataProvider(name = "paymentInitiationConsentTypeForReleaseManagement")
    Iterator<Object[]> paymentInitiationConsentTypeForReleaseManagement() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        domesticStandingOrderParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadDomesticStandingOrderPayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payload", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }


    @DataProvider(name = "paymentInitiationWithoutDebtor")
    Iterator<Object[]> paymentInitiationWithoutDebtor() {

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> domesticStandingOrderParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()

        def paymentMap = [:]

        domesticParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("initPayload", paymentInitiationPayloads.domesticPaymentWithMandatoryParameters(paymentMap))
        domesticParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_PAYMENTS)
        domesticParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_PAYMENTS)
        domesticParameterMap.put("paymentID", "DomesticPaymentId")

        domesticScheduleParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("initPayload", paymentInitiationPayloads.domesticSchedulePaymentWithMandatoryParameters(paymentMap))
        domesticScheduleParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_SCHEDULE)
        domesticScheduleParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_SCHEDULE)
        domesticScheduleParameterMap.put("paymentID", "DomesticScheduledPaymentId")

        domesticStandingOrderParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("initPayload", paymentInitiationPayloads.domesticStandingOrderPaymentWithMandatoryParameters(paymentMap))
        domesticStandingOrderParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_DOMESTIC_STANDING_ORDERS)
        domesticStandingOrderParameterMap.put("payloadType", ConnectorTestConstants.DOMESTIC_STANDING)
        domesticStandingOrderParameterMap.put("paymentID", "DomesticStandingOrderId")

        internationalPaymentParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("initPayload", paymentInitiationPayloads.internationalPaymentWithMandatoryParameters(paymentMap))
        internationalPaymentParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL)
        internationalPaymentParameterMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("paymentID", "InternationalPaymentId")

        internationalStandingOrderPaymentParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("initPayload", paymentInitiationPayloads.internationalStandingOrderPaymentWithMandatoryParameters(paymentMap))
        internationalStandingOrderPaymentParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
        internationalStandingOrderPaymentParameterMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("paymentID", "InternationalStandingOrderId")

        internationalSchedulePaymentParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("initPayload", paymentInitiationPayloads.internationalSchedulePaymentWithMandatoryParameters(paymentMap))
        internationalSchedulePaymentParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("paymentID", "InternationalScheduledPaymentId")

        internationalStandingOrderPaymentParameterMap.put("initPath", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("initPayload", paymentInitiationPayloads.internationalStandingOrderPaymentWithMandatoryParameters(paymentMap))
        internationalStandingOrderPaymentParameterMap.put("submissionPath", ConnectorTestConstants.SUBMISSION_PATH_INTERNATIONAL_STANDING_ORDERS)
        internationalStandingOrderPaymentParameterMap.put("payloadType", ConnectorTestConstants.INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("paymentID", "InternationalStandingOrderPaymentId")

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(domesticStandingOrderParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add([map] as Object[])
        }
        return paymentTypesCollection.iterator()
    }

    @DataProvider(name = "initiationWithNegativeInstructedAmount")
    Iterator<Object[]> initiationWithNegativeInstructedAmount() {

        Collection<Object[]> paymentTypesCollectionsNew = new ArrayList<Object[]>()

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>()
        Map<String, String> domesticParameterMap = new HashMap<String, String>()
        Map<String, String> domesticScheduleParameterMap = new HashMap<String, String>()
        Map<String, String> internationalPaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalSchedulePaymentParameterMap = new HashMap<String, String>()
        Map<String, String> internationalStandingOrderPaymentParameterMap = new HashMap<String, String>()

        def paymentMap = ["Amount" : "-1"]

        domesticParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH)
        domesticParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadDomesticPayment(paymentMap))

        domesticScheduleParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_SCHEDULE)
        domesticScheduleParameterMap.put("invalidParam",paymentInitiationPayloads.initiationPayloadDomesticSchedulePayment(paymentMap))

        internationalPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_PAYMENTS)
        internationalPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalPayment(paymentMap))

        internationalSchedulePaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_SCHEDULE)
        internationalSchedulePaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalSchedulePayment(paymentMap))

        internationalStandingOrderPaymentParameterMap.put("path", ConnectorTestConstants.CONSENT_PATH_INTERNATIONAL_STANDING_ORDER)
        internationalStandingOrderPaymentParameterMap.put("invalidParam", paymentInitiationPayloads.initiationPayloadInternationalStandingOrderPayment(paymentMap))

        listOfParamMaps.add(domesticParameterMap)
        listOfParamMaps.add(domesticScheduleParameterMap)
        listOfParamMaps.add(internationalPaymentParameterMap)
        listOfParamMaps.add(internationalSchedulePaymentParameterMap)
        listOfParamMaps.add(internationalStandingOrderPaymentParameterMap)

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollectionsNew.add([map] as Object[])
        }
        return paymentTypesCollectionsNew.iterator()
    }
}
