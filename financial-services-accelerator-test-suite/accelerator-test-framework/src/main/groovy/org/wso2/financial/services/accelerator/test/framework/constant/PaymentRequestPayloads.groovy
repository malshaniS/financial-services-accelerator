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

package org.wso2.financial.services.accelerator.test.framework.constant

import org.wso2.financial.services.accelerator.test.framework.configuration.ConfigurationService
import org.wso2.financial.services.accelerator.test.framework.utility.TestUtil
import java.time.format.DateTimeFormatter

/**
 * Payment Request Payloads.
 */
class PaymentRequestPayloads {

    static ConfigurationService configuration = new ConfigurationService()

    static final def defaultValueMap = [
            "ReadRefundAccount" : "Yes",
            "Permission": "Create",
            "InstructionIdentification":"ACME412",
            "EndToEndIdentification":"FRESCO.21302.GFX.20",
            "LocalInstrument": "UK.OBIE.BACS",
            "Amount":"30.80",
            "Currency":"GBP",
            "CreditorSchemeName":"UK.OBIE.SortCodeAccountNumber",
            "CreditorIdentification":"08080021325698",
            "CreditorAccountName":"ACME Inc",
            "CreditorSecondaryIdentification":"0002",
            "DebtorSchemeName":"UK.OBIE.SortCodeAccountNumber",
            "DebtorIdentification":"30080012343456",
            "DebtorName":"Andrea Smith",
            "DebtorSecondaryIdentification":"30080012343456",
            "RemittanceInformationReference":"FRESCO-101",
            "RemittanceInformationUnstructured":"Internal ops code 5120101",
            "Frequency": "EvryDay",
            "Reference": "Pocket money for Damien",
            "NumberOfPayments" : "10",
            "Purpose" : "1234",
            "ExtendedPurpose" : "string",
            "ChargeBearer" : "BorneByCreditor",
            "FirstPaymentAmount": "6.66",
            "FirstPaymentCurrency": "GBP",
            "RecurringPaymentAmount": "7.00",
            "RecurringPaymentCurrency": "GBP",
            "FinalPaymentAmount": "12.00",
            "FinalPaymentCurrency": "GBP",
            "InstructionPriority": "Normal",
            "CurrencyOfTransfer": "USD",
            "DestinationCountryCode" : "UK",
            "UnitCurrency": "GBP",
            "RateType": "Agreed",
            "ExchangeRate":"1.34",
            "ContractIdentification":"1234",
            "CreditorName":"ACME Inc",
            "CreditorAddressType":"Correspondence",
            "CreditorDepartment":"department1",
            "CreditorSubDepartment":"sub dept",
            "CreditorStreetName":"Acacia Avenue",
            "CreditorBuildingNumber":"27",
            "CreditorPostCode":"GU31 2ZZ",
            "CreditorTownName":"Sparsholt",
            "CreditorCountrySubDivision":"Wessex",
            "CreditorCountry":"UK",
            "CreditorAddressLine": "[\"Flat 7\",\"Acacia Lodge\"]",
            "CreditorAgentSchemeName": "UK.OBIE.IBAN",
            "CreditorAgentIdentification":"121212121212",
            "CreditorAgentName":"Andrea Smith",
            "CreditorAgentAddressType":"Correspondence",
            "CreditorAgentDepartment":"department1",
            "CreditorAgentSubDepartment":"sub dept",
            "CreditorAgentStreetName":"Acacia Avenue",
            "CreditorAgentBuildingNumber":"27",
            "CreditorAgentPostCode":"GU31 2ZZ",
            "CreditorAgentTownName":"Sparsholt",
            "CreditorAgentCountrySubDivision":"Wessex",
            "CreditorAgentCountry":"UK",
            "CreditorAgentAddressLine": "[\"Flat 7\",\"Acacia Lodge\"]",
            "AuthorisationType": "Any",
            "RequestedSCAExemptionType" : "BillPayment",
            "AppliedAuthenticationApproach" : "CA",
            "ReferencePaymentOrderId" : "string",
            "PaymentContextCode":"EcommerceGoods",
            "MerchantCategoryCode":"5967",
            "MerchantCustomerIdentification":"053598653254",
            "AddressLine":"[\"Flat 7\",\"Acacia Lodge\"]",
            "StreetName":"Acacia Avenue",
            "BuildingNumber":"27",
            "PostCode":"GU31 2ZZ",
            "TownName":"Sparsholt",
            "CountrySubDivision":"Wessex",
            "Country":"UK"
    ]

    static String initiationPayloadDomesticPayment(def parameterMap)  {

        String initiationPayloadDomestic = """
            { 
               "Data":{ 
                  "ReadRefundAccount" : "${this.getParameterValue("ReadRefundAccount", parameterMap)}",
                  "Initiation":{ 
                     "InstructionIdentification":"${this.getParameterValue("InstructionIdentification", parameterMap)}",
                     "EndToEndIdentification":"${this.getParameterValue("EndToEndIdentification", parameterMap)}",
                     "LocalInstrument": "${this.getParameterValue("LocalInstrument", parameterMap)}",
                     "InstructedAmount":{ 
                        "Amount":"${this.getParameterValue("Amount", parameterMap)}",
                        "Currency":"${this.getParameterValue("Currency", parameterMap)}"
                     },
                     "DebtorAccount": {
                        "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                        "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                        "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                        "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                        "Identification":"${this.getParameterValue("CreditorIdentification", parameterMap)}",
                        "Name":"${this.getParameterValue("CreditorAccountName", parameterMap)}",
                        "SecondaryIdentification":"${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                     },
                     "CreditorPostalAddress":{
                        "AddressType":"${this.getParameterValue("CreditorAddressType", parameterMap)}",
                        "Department":"${this.getParameterValue("CreditorDepartment", parameterMap)}",
                        "SubDepartment":"${this.getParameterValue("CreditorSubDepartment", parameterMap)}",
                        "StreetName":"${this.getParameterValue("CreditorStreetName", parameterMap)}",
                        "BuildingNumber":"${this.getParameterValue("CreditorBuildingNumber", parameterMap)}",
                        "PostCode":"${this.getParameterValue("CreditorPostCode", parameterMap)}",
                        "TownName":"${this.getParameterValue("CreditorTownName", parameterMap)}",
                        "CountrySubDivision":"${this.getParameterValue("CreditorCountrySubDivision", parameterMap)}",
                        "Country":"${this.getParameterValue("CreditorCountry", parameterMap)}"
                     },
                     "RemittanceInformation":{ 
                        "Reference":"${this.getParameterValue("RemittanceInformationReference", parameterMap)}",
                        "Unstructured":"${this.getParameterValue("RemittanceInformationUnstructured", parameterMap)}"
                     }
                  },
                  "Authorisation": {
                          "AuthorisationType": "${this.getParameterValue("AuthorisationType", parameterMap)}",
                          "CompletionDateTime": "${this.getParameterValue("CompletionDateTime", parameterMap)}"
                    },
                  "SCASupportData": {
                          "RequestedSCAExemptionType": "${this.getParameterValue("RequestedSCAExemptionType", parameterMap)}",
                          "AppliedAuthenticationApproach": "${this.getParameterValue("AppliedAuthenticationApproach", parameterMap)}",
                          "ReferencePaymentOrderId": "${this.getParameterValue("ReferencePaymentOrderId", parameterMap)}"
                  }  
               },
               "Risk":{ 
                  "PaymentContextCode":"${this.getParameterValue("PaymentContextCode", parameterMap)}",
                  "MerchantCategoryCode":"${this.getParameterValue("MerchantCategoryCode", parameterMap)}",
                  "MerchantCustomerIdentification":"${this.getParameterValue("MerchantCustomerIdentification", parameterMap)}",
                  "DeliveryAddress":{ 
                     "AddressLine":${this.getParameterValue("AddressLine", parameterMap)},
                     "StreetName":"${this.getParameterValue("StreetName", parameterMap)}",
                     "BuildingNumber":"${this.getParameterValue("BuildingNumber", parameterMap)}",
                     "PostCode":"${this.getParameterValue("PostCode", parameterMap)}",
                     "TownName":"${this.getParameterValue("TownName", parameterMap)}",
                     "CountrySubDivision": "${this.getParameterValue("CountrySubDivision", parameterMap)}",
                     "Country":"${this.getParameterValue("Country", parameterMap)}"
                  }
               }
            }
        """.stripIndent()

        return initiationPayloadDomestic

    }

    public static String initiationPayload = """
            {
  "Data": {
    "ReadRefundAccount": "Yes",
    "Initiation": {
      "InstructionIdentification": "ACME412",
      "EndToEndIdentification": "FRESCO.21302.GFX.20",
      "InstructedAmount": {
        "Amount": "165.88",
        "Currency": "GBP"
      },
      "CreditorAccount": {
        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
        "Identification": "08080021325698",
        "Name": "ACME Inc",
        "SecondaryIdentification": "0002"
      },
      "RemittanceInformation": {
        "Reference": "FRESCO-101",
        "Unstructured": "Internal ops code 5120101"
      }
    }
  },
  "Risk": {
    "PaymentContextCode": "EcommerceMerchantInitiatedPayment",
    "ContractPresentIndicator": false,
    "PaymentPurposeCode": "EPAY",
    "BeneficiaryPaymentDetailsPrepopulatedIndicator": false,
    "BeneficiaryAccountType": "Business",
    "MerchantCustomerIdentification": "053598653254",
    "DeliveryAddress": {
      "AddressLine": [
        "Flat 7",
        "Acacia Lodge"
      ],
      "StreetName": "Acacia Avenue",
      "BuildingNumber": "27",
      "PostCode": "GU31 2ZZ",
      "TownName": "Sparsholt",
      "CountrySubDivision": "Wessex",
      "Country": "UK"
    }
  }
}
        """.stripIndent()

    String initiationPayloadDomesticSchedulePayment(def parameterMap)  {

        String initiationPayloadDomesticSchedule = """
            {
                "Data": {
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "ReadRefundAccount" : "${this.getParameterValue("ReadRefundAccount", parameterMap)}",
                    "Initiation": {
                          "InstructionIdentification": "${this.getParameterValue("InstructionIdentification", parameterMap)}",
                          "EndToEndIdentification":"${this.getParameterValue("EndToEndIdentification", parameterMap)}",
                          "LocalInstrument": "${this.getParameterValue("LocalInstrument", parameterMap)}",
                          "RequestedExecutionDateTime": "${this.getParameterValue("RequestedExecutionDateTime", parameterMap)}",
                          "InstructedAmount": {
                                "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                                "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                          },
                          "DebtorAccount": {
                                "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                                "SecondaryIdentification":"${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                          },
                          "CreditorPostalAddress":{
                                "AddressType":"${this.getParameterValue("CreditorAddressType", parameterMap)}",
                                "Department":"${this.getParameterValue("CreditorDepartment", parameterMap)}",
                                "SubDepartment":"${this.getParameterValue("CreditorSubDepartment", parameterMap)}",
                                "StreetName":"${this.getParameterValue("CreditorStreetName", parameterMap)}",
                                "BuildingNumber":"${this.getParameterValue("CreditorBuildingNumber", parameterMap)}",
                                "PostCode":"${this.getParameterValue("CreditorPostCode", parameterMap)}",
                                "TownName":"${this.getParameterValue("CreditorTownName", parameterMap)}",
                                "CountrySubDivision":"${this.getParameterValue("CreditorCountrySubDivision", parameterMap)}",
                                "Country":"${this.getParameterValue("CreditorCountry", parameterMap)}"
                          },
                          "RemittanceInformation": {
                                "Reference": "${this.getParameterValue("RemittanceInformationReference", parameterMap)}",
                                "Unstructured": "${this.getParameterValue("RemittanceInformationUnstructured", parameterMap)}"
                          }
                    },
                    "Authorisation": {
                          "AuthorisationType": "${this.getParameterValue("AuthorisationType", parameterMap)}",
                          "CompletionDateTime": "${this.getParameterValue("CompletionDateTime", parameterMap)}"
                    },
                    "SCASupportData": {
                          "RequestedSCAExemptionType": "${this.getParameterValue("RequestedSCAExemptionType", parameterMap)}",
                          "AppliedAuthenticationApproach": "${this.getParameterValue("AppliedAuthenticationApproach", parameterMap)}",
                          "ReferencePaymentOrderId": "${this.getParameterValue("ReferencePaymentOrderId", parameterMap)}"
                  }  
                },
                "Risk": {
                    "PaymentContextCode": "${this.getParameterValue("PaymentContextCode", parameterMap)}",
                    "MerchantCategoryCode": "${this.getParameterValue("MerchantCategoryCode", parameterMap)}",
                    "MerchantCustomerIdentification": "${this.getParameterValue("MerchantCustomerIdentification", parameterMap)}",
                    "DeliveryAddress": {
                          "AddressLine": ${this.getParameterValue("AddressLine", parameterMap)},
                          "StreetName": "${this.getParameterValue("StreetName", parameterMap)}",
                          "BuildingNumber": "${this.getParameterValue("BuildingNumber", parameterMap)}",
                          "PostCode": "${this.getParameterValue("PostCode", parameterMap)}",
                          "TownName": "${this.getParameterValue("TownName", parameterMap)}",
                          "CountrySubDivision": "${this.getParameterValue("CountrySubDivision", parameterMap)}",
                          "Country": "${this.getParameterValue("Country", parameterMap)}"
                    }
                }
            }
        """.stripIndent()

        return initiationPayloadDomesticSchedule

    }

    String initiationPayloadDomesticStandingOrderPayment(def parameterMap)  {

        String initiationPayloadDomesticStandingOrder = """
            {
                "Data": {
                    "ReadRefundAccount" : "${this.getParameterValue("ReadRefundAccount", parameterMap)}",
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "Authorisation": {
                          "AuthorisationType": "${this.getParameterValue("AuthorisationType", parameterMap)}",
                          "CompletionDateTime": "${this.getParameterValue("CompletionDateTime", parameterMap)}"
                    },
                    "SCASupportData": {
                          "RequestedSCAExemptionType": "${this.getParameterValue("RequestedSCAExemptionType", parameterMap)}",
                          "AppliedAuthenticationApproach": "${this.getParameterValue("AppliedAuthenticationApproach", parameterMap)}",
                          "ReferencePaymentOrderId": "${this.getParameterValue("ReferencePaymentOrderId", parameterMap)}"
                    },
                    "Initiation": {
                          "Frequency": "${this.getParameterValue("Frequency", parameterMap)}",
                          "Reference": "${this.getParameterValue("Reference", parameterMap)}",
                          "FirstPaymentDateTime": "${this.getParameterValue("FirstPaymentDateTime", parameterMap)}",
                          "RecurringPaymentDateTime": "${this.getParameterValue("RecurringPaymentDateTime", parameterMap)}",
                          "FirstPaymentAmount": {
                                "Amount": "${this.getParameterValue("FirstPaymentAmount", parameterMap)}",
                                "Currency": "${this.getParameterValue("FirstPaymentCurrency", parameterMap)}"
                          },
                          "RecurringPaymentAmount": {
                                "Amount": "${this.getParameterValue("RecurringPaymentAmount", parameterMap)}",
                                "Currency": "${this.getParameterValue("RecurringPaymentCurrency", parameterMap)}"
                          },
                          "FinalPaymentDateTime": "${this.getParameterValue("FinalPaymentDateTime", parameterMap)}",
                          "FinalPaymentAmount": {
                                "Amount": "${this.getParameterValue("FinalPaymentAmount", parameterMap)}",
                                "Currency": "${this.getParameterValue("FinalPaymentCurrency", parameterMap)}"
                          },
                          "DebtorAccount": {
                                "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                          }
                    }
                },
                "Risk": {
                    "PaymentContextCode": "${this.getParameterValue("PaymentContextCode", parameterMap)}",
                    "MerchantCategoryCode": "${this.getParameterValue("MerchantCategoryCode", parameterMap)}",
                    "MerchantCustomerIdentification": "${this.getParameterValue("MerchantCustomerIdentification", parameterMap)}",
                    "DeliveryAddress": {
                          "AddressLine": ${this.getParameterValue("AddressLine", parameterMap)},
                          "StreetName": "${this.getParameterValue("StreetName", parameterMap)}",
                          "BuildingNumber": "${this.getParameterValue("BuildingNumber", parameterMap)}",
                          "PostCode": "${this.getParameterValue("PostCode", parameterMap)}",
                          "TownName": "${this.getParameterValue("TownName", parameterMap)}",
                          "CountrySubDivision": "${this.getParameterValue("CountrySubDivision", parameterMap)}",
                          "Country": "${this.getParameterValue("Country", parameterMap)}"
                    }
                }
            }
        """.stripIndent()

        return initiationPayloadDomesticStandingOrder

    }

    String initiationPayloadInternationalPayment(def parameterMap)  {

        String initiationPayloadInternational = """
            {
                "Data": {
                    "ReadRefundAccount" : "${this.getParameterValue("ReadRefundAccount", parameterMap)}",
                    "Initiation": {
                          "InstructionIdentification": "${this.getParameterValue("InstructionIdentification", parameterMap)}",
                          "EndToEndIdentification": "${this.getParameterValue("EndToEndIdentification", parameterMap)}",
                          "LocalInstrument": "${this.getParameterValue("LocalInstrument", parameterMap)}",
                          "InstructionPriority": "${this.getParameterValue("InstructionPriority", parameterMap)}",
                          "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                          "Purpose": "${this.getParameterValue("Purpose", parameterMap)}",
                          "ChargeBearer": "${this.getParameterValue("ChargeBearer", parameterMap)}",
                          "InstructedAmount": {
                                "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                                "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                          },
                          "DebtorAccount": {
                                "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                          },
                          "RemittanceInformation": {
                                "Reference": "${this.getParameterValue("RemittanceInformationReference", parameterMap)}",
                                "Unstructured": "${this.getParameterValue("RemittanceInformationUnstructured", parameterMap)}"
                          },
                          "ExchangeRateInformation": {
                                "UnitCurrency": "${this.getParameterValue("UnitCurrency", parameterMap)}",
                                "RateType": "${this.getParameterValue("RateType", parameterMap)}",
                                "ExchangeRate": ${this.getParameterValue("ExchangeRate", parameterMap)},
                                "ContractIdentification": "${this.getParameterValue("ContractIdentification", parameterMap)}"
                          },
                          "Creditor":{
                                "Name":"${this.getParameterValue("CreditorName", parameterMap)}",
                                "PostalAddress":{
                                    "AddressType":"${this.getParameterValue("CreditorAddressType", parameterMap)}",
                                    "Department":"${this.getParameterValue("CreditorDepartment", parameterMap)}",
                                    "SubDepartment":"${this.getParameterValue("CreditorSubDepartment", parameterMap)}",
                                    "StreetName":"${this.getParameterValue("CreditorStreetName", parameterMap)}",
                                    "BuildingNumber":"${this.getParameterValue("CreditorBuildingNumber", parameterMap)}",
                                    "PostCode":"${this.getParameterValue("CreditorPostCode", parameterMap)}",
                                    "TownName":"${this.getParameterValue("CreditorTownName", parameterMap)}",
                                    "CountrySubDivision":"${this.getParameterValue("CreditorCountrySubDivision", parameterMap)}",
                                    "Country":"${this.getParameterValue("CreditorCountry", parameterMap)}",
                                    "AddressLine": ${this.getParameterValue("CreditorAddressLine", parameterMap)}
                                 }
                          }
                    },
                    "Authorisation": {
                          "AuthorisationType": "${this.getParameterValue("AuthorisationType", parameterMap)}",
                          "CompletionDateTime": "${this.getParameterValue("CompletionDateTime", parameterMap)}"
                    },
                    "SCASupportData": {
                          "RequestedSCAExemptionType": "${this.getParameterValue("RequestedSCAExemptionType", parameterMap)}",
                          "AppliedAuthenticationApproach": "${this.getParameterValue("AppliedAuthenticationApproach", parameterMap)}",
                          "ReferencePaymentOrderId": "${this.getParameterValue("ReferencePaymentOrderId", parameterMap)}"
                    }
                },
                "Risk": {
                    "PaymentContextCode": "${this.getParameterValue("PaymentContextCode", parameterMap)}",
                    "MerchantCategoryCode": "${this.getParameterValue("MerchantCategoryCode", parameterMap)}",
                    "MerchantCustomerIdentification": "${this.getParameterValue("MerchantCustomerIdentification", parameterMap)}",
                    "DeliveryAddress": {
                          "AddressLine": ${this.getParameterValue("AddressLine", parameterMap)},
                          "StreetName": "${this.getParameterValue("StreetName", parameterMap)}",
                          "BuildingNumber": "${this.getParameterValue("BuildingNumber", parameterMap)}",
                          "PostCode": "${this.getParameterValue("PostCode", parameterMap)}",
                          "TownName": "${this.getParameterValue("TownName", parameterMap)}",
                          "CountrySubDivision": "${this.getParameterValue("CountrySubDivision", parameterMap)}",
                          "Country": "${this.getParameterValue("Country", parameterMap)}"
                    }
                }
            }
        """.stripIndent()

        return initiationPayloadInternational

    }

    String initiationPayloadInternationalSchedulePayment(def parameterMap)  {

        String initiationPayloadInternationalSchedule = """
            {
                "Data": {
                    "ReadRefundAccount" : "${this.getParameterValue("ReadRefundAccount", parameterMap)}",
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "Initiation": {
                        "InstructionIdentification": "${this.getParameterValue("InstructionIdentification", parameterMap)}",
                        "EndToEndIdentification": "${this.getParameterValue("EndToEndIdentification", parameterMap)}",
                        "RequestedExecutionDateTime": "${this.getParameterValue("RequestedExecutionDateTime", parameterMap)}",
                        "LocalInstrument": "${this.getParameterValue("LocalInstrument", parameterMap)}",
                        "InstructionPriority": "${this.getParameterValue("InstructionPriority", parameterMap)}",
                        "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                        "Purpose": "${this.getParameterValue("Purpose", parameterMap)}",
                        "ChargeBearer": "${this.getParameterValue("ChargeBearer", parameterMap)}",
                        "InstructedAmount": {
                            "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                            "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                        },
                        "DebtorAccount": {
                            "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                            "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                            "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                            "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                        },
                        "CreditorAccount": {
                            "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                            "Identification":"${this.getParameterValue("CreditorIdentification", parameterMap)}",
                            "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                            "SecondaryIdentification": "${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                        },        
                        "RemittanceInformation": {
                            "Reference": "${this.getParameterValue("RemittanceInformationReference", parameterMap)}",
                            "Unstructured": "${this.getParameterValue("RemittanceInformationUnstructured", parameterMap)}"
                        },
                        "ExchangeRateInformation": {
                             "UnitCurrency": "${this.getParameterValue("UnitCurrency", parameterMap)}",
                             "RateType": "${this.getParameterValue("RateType", parameterMap)}",
                             "ExchangeRate": ${this.getParameterValue("ExchangeRate", parameterMap)},
                             "ContractIdentification": "${this.getParameterValue("ContractIdentification", parameterMap)}"
                        },
                        "Creditor":{
                                "Name":"${this.getParameterValue("CreditorName", parameterMap)}",
                                "PostalAddress":{
                                    "AddressType":"${this.getParameterValue("CreditorAddressType", parameterMap)}",
                                    "Department":"${this.getParameterValue("CreditorDepartment", parameterMap)}",
                                    "SubDepartment":"${this.getParameterValue("CreditorSubDepartment", parameterMap)}",
                                    "StreetName":"${this.getParameterValue("CreditorStreetName", parameterMap)}",
                                    "BuildingNumber":"${this.getParameterValue("CreditorBuildingNumber", parameterMap)}",
                                    "PostCode":"${this.getParameterValue("CreditorPostCode", parameterMap)}",
                                    "TownName":"${this.getParameterValue("CreditorTownName", parameterMap)}",
                                    "CountrySubDivision":"${this.getParameterValue("CreditorCountrySubDivision", parameterMap)}",
                                    "Country":"${this.getParameterValue("CreditorCountry", parameterMap)}",
                                    "AddressLine": ${this.getParameterValue("CreditorAddressLine", parameterMap)}
                                }
                        }
                    },                  
                    "Authorisation": {
                        "AuthorisationType": "${this.getParameterValue("AuthorisationType", parameterMap)}",
                        "CompletionDateTime": "${this.getParameterValue("CompletionDateTime", parameterMap)}"
                    },
                    "SCASupportData": {
                          "RequestedSCAExemptionType": "${this.getParameterValue("RequestedSCAExemptionType", parameterMap)}",
                          "AppliedAuthenticationApproach": "${this.getParameterValue("AppliedAuthenticationApproach", parameterMap)}",
                          "ReferencePaymentOrderId": "${this.getParameterValue("ReferencePaymentOrderId", parameterMap)}"
                    }  
                },
                "Risk": {
                    "PaymentContextCode": "${this.getParameterValue("PaymentContextCode", parameterMap)}",
                    "MerchantCategoryCode": "${this.getParameterValue("MerchantCategoryCode", parameterMap)}",
                    "MerchantCustomerIdentification": "${this.getParameterValue("MerchantCustomerIdentification", parameterMap)}",
                    "DeliveryAddress": {
                          "AddressLine": ${this.getParameterValue("AddressLine", parameterMap)},
                          "StreetName": "${this.getParameterValue("StreetName", parameterMap)}",
                          "BuildingNumber": "${this.getParameterValue("BuildingNumber", parameterMap)}",
                          "PostCode": "${this.getParameterValue("PostCode", parameterMap)}",
                          "TownName": "${this.getParameterValue("TownName", parameterMap)}",
                          "CountrySubDivision": "${this.getParameterValue("CountrySubDivision", parameterMap)}",
                          "Country": "${this.getParameterValue("Country", parameterMap)}"
                    }
                }
            }
        """.stripIndent()

        return initiationPayloadInternationalSchedule

    }

    String initiationPayloadInternationalStandingOrderPayment(def parameterMap)  {

        String initiationPayloadInternationalStandingOrder = """
            {
            "Data": {
                "ReadRefundAccount" : "${this.getParameterValue("ReadRefundAccount", parameterMap)}",
                "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                "Initiation": {
                    "Frequency": "${this.getParameterValue("Frequency", parameterMap)}",
                    "Reference": "${this.getParameterValue("Reference", parameterMap)}",
                    "NumberOfPayments" : "${this.getParameterValue("NumberOfPayments", parameterMap)}",
                    "Purpose": "${this.getParameterValue("Purpose", parameterMap)}",
                    "ChargeBearer": "${this.getParameterValue("ChargeBearer", parameterMap)}",
                    "FirstPaymentDateTime": "${this.getParameterValue("FirstPaymentDateTime", parameterMap)}",
                    "FinalPaymentDateTime": "${this.getParameterValue("FinalPaymentDateTime", parameterMap)}",
                    "DebtorAccount": {
                        "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                        "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                        "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                        "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                    },
                    "CreditorAccount": {
                        "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                        "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                        "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                        "SecondaryIdentification":"${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                    },
                    "InstructedAmount": {
                        "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                        "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                    },
                    "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                    "Creditor":{
                        "Name":"${this.getParameterValue("CreditorName", parameterMap)}",
                        "PostalAddress":{
                            "AddressType":"${this.getParameterValue("CreditorAddressType", parameterMap)}",
                            "Department":"${this.getParameterValue("CreditorDepartment", parameterMap)}",
                            "SubDepartment":"${this.getParameterValue("CreditorSubDepartment", parameterMap)}",
                            "StreetName":"${this.getParameterValue("CreditorStreetName", parameterMap)}",
                            "BuildingNumber":"${this.getParameterValue("CreditorBuildingNumber", parameterMap)}",
                            "PostCode":"${this.getParameterValue("CreditorPostCode", parameterMap)}",
                            "TownName":"${this.getParameterValue("CreditorTownName", parameterMap)}",
                            "CountrySubDivision":"${this.getParameterValue("CreditorCountrySubDivision", parameterMap)}",
                            "Country":"${this.getParameterValue("CreditorCountry", parameterMap)}",
                                    "AddressLine": ${this.getParameterValue("CreditorAddressLine", parameterMap)}
                         }
                    }
                },
                "Authorisation": {
                    "AuthorisationType": "${this.getParameterValue("AuthorisationType", parameterMap)}",
                    "CompletionDateTime": "${this.getParameterValue("CompletionDateTime", parameterMap)}"
                },
                "SCASupportData": {
                          "RequestedSCAExemptionType": "${this.getParameterValue("RequestedSCAExemptionType", parameterMap)}",
                          "AppliedAuthenticationApproach": "${this.getParameterValue("AppliedAuthenticationApproach", parameterMap)}",
                          "ReferencePaymentOrderId": "${this.getParameterValue("ReferencePaymentOrderId", parameterMap)}"
                }  
            },
            "Risk": {
                "PaymentContextCode": "${this.getParameterValue("PaymentContextCode", parameterMap)}",
                "MerchantCategoryCode": "${this.getParameterValue("MerchantCategoryCode", parameterMap)}",
                "MerchantCustomerIdentification": "${this.getParameterValue("MerchantCustomerIdentification", parameterMap)}",
                "DeliveryAddress": {
                      "AddressLine": ${this.getParameterValue("AddressLine", parameterMap)},
                      "StreetName": "${this.getParameterValue("StreetName", parameterMap)}",
                      "BuildingNumber": "${this.getParameterValue("BuildingNumber", parameterMap)}",
                      "PostCode": "${this.getParameterValue("PostCode", parameterMap)}",
                      "TownName": "${this.getParameterValue("TownName", parameterMap)}",
                      "CountrySubDivision": "${this.getParameterValue("CountrySubDivision", parameterMap)}",
                      "Country": "${this.getParameterValue("Country", parameterMap)}"
                }
            }
        }
    """.stripIndent()

        return initiationPayloadInternationalStandingOrder

    }


    String initiationPayloadDomesticStandingOrderPaymentWithNumberOfPayments(def parameterMap)  {

        String initiationPayloadDomesticStandingOrder = """
            {
                "Data": {
                    "ReadRefundAccount" : "${this.getParameterValue("ReadRefundAccount", parameterMap)}",
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "Authorisation": {
                          "AuthorisationType": "${this.getParameterValue("AuthorisationType", parameterMap)}",
                          "CompletionDateTime": "${this.getParameterValue("CompletionDateTime", parameterMap)}"
                    },
                    "SCASupportData": {
                          "RequestedSCAExemptionType": "${this.getParameterValue("RequestedSCAExemptionType", parameterMap)}",
                          "AppliedAuthenticationApproach": "${this.getParameterValue("AppliedAuthenticationApproach", parameterMap)}",
                          "ReferencePaymentOrderId": "${this.getParameterValue("ReferencePaymentOrderId", parameterMap)}"
                    },
                    "Initiation": {
                          "Frequency": "${this.getParameterValue("Frequency", parameterMap)}",
                          "Reference": "${this.getParameterValue("Reference", parameterMap)}",
                          "FirstPaymentDateTime": "${this.getParameterValue("FirstPaymentDateTime", parameterMap)}",
                          "RecurringPaymentDateTime": "${this.getParameterValue("RecurringPaymentDateTime", parameterMap)}",
                          "FirstPaymentAmount": {
                                "Amount": "${this.getParameterValue("FirstPaymentAmount", parameterMap)}",
                                "Currency": "${this.getParameterValue("FirstPaymentCurrency", parameterMap)}"
                          },
                          "RecurringPaymentAmount": {
                                "Amount": "${this.getParameterValue("RecurringPaymentAmount", parameterMap)}",
                                "Currency": "${this.getParameterValue("RecurringPaymentCurrency", parameterMap)}"
                          },
                          "FinalPaymentDateTime": "${this.getParameterValue("FinalPaymentDateTime", parameterMap)}",
                          "NumberOfPayments" : "${this.getParameterValue("NumberOfPayments", parameterMap)}",
                          "DebtorAccount": {
                                "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                          }
                    }
                },
                "Risk": {
                    "PaymentContextCode": "${this.getParameterValue("PaymentContextCode", parameterMap)}",
                    "MerchantCategoryCode": "${this.getParameterValue("MerchantCategoryCode", parameterMap)}",
                    "MerchantCustomerIdentification": "${this.getParameterValue("MerchantCustomerIdentification", parameterMap)}",
                    "DeliveryAddress": {
                          "AddressLine": ${this.getParameterValue("AddressLine", parameterMap)},
                          "StreetName": "${this.getParameterValue("StreetName", parameterMap)}",
                          "BuildingNumber": "${this.getParameterValue("BuildingNumber", parameterMap)}",
                          "PostCode": "${this.getParameterValue("PostCode", parameterMap)}",
                          "TownName": "${this.getParameterValue("TownName", parameterMap)}",
                          "CountrySubDivision": "${this.getParameterValue("CountrySubDivision", parameterMap)}",
                          "Country": "${this.getParameterValue("Country", parameterMap)}"
                    }
                }
            }
        """.stripIndent()

        return initiationPayloadDomesticStandingOrder

    }

    String initiationPayloadInternationalStandingOrderPaymentWithNumberOfPayments(def parameterMap)  {

        String initiationPayloadInternationalStandingOrder = """
            {
            "Data": {
                "ReadRefundAccount" : "${this.getParameterValue("ReadRefundAccount", parameterMap)}",
                "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                "Initiation": {
                    "Frequency": "${this.getParameterValue("Frequency", parameterMap)}",
                    "Reference": "${this.getParameterValue("Reference", parameterMap)}",
                    "NumberOfPayments" : "${this.getParameterValue("NumberOfPayments", parameterMap)}",
                    "Purpose": "${this.getParameterValue("Purpose", parameterMap)}",
                    "ChargeBearer": "${this.getParameterValue("ChargeBearer", parameterMap)}",
                    "FirstPaymentDateTime": "${this.getParameterValue("FirstPaymentDateTime", parameterMap)}",
                    "FinalPaymentDateTime": "${this.getParameterValue("FinalPaymentDateTime", parameterMap)}",
                    "DebtorAccount": {
                        "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                        "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                        "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                        "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                    },
                    "CreditorAccount": {
                        "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                        "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                        "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                        "SecondaryIdentification":"${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                    },
                    "InstructedAmount": {
                        "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                        "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                    },
                    "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                    "Creditor":{
                        "Name":"${this.getParameterValue("CreditorName", parameterMap)}",
                        "PostalAddress":{
                            "AddressType":"${this.getParameterValue("CreditorAddressType", parameterMap)}",
                            "Department":"${this.getParameterValue("CreditorDepartment", parameterMap)}",
                            "SubDepartment":"${this.getParameterValue("CreditorSubDepartment", parameterMap)}",
                            "StreetName":"${this.getParameterValue("CreditorStreetName", parameterMap)}",
                            "BuildingNumber":"${this.getParameterValue("CreditorBuildingNumber", parameterMap)}",
                            "PostCode":"${this.getParameterValue("CreditorPostCode", parameterMap)}",
                            "TownName":"${this.getParameterValue("CreditorTownName", parameterMap)}",
                            "CountrySubDivision":"${this.getParameterValue("CreditorCountrySubDivision", parameterMap)}",
                            "Country":"${this.getParameterValue("CreditorCountry", parameterMap)}",
                                    "AddressLine": ${this.getParameterValue("CreditorAddressLine", parameterMap)}
                         }
                    }
                },
                "Authorisation": {
                    "AuthorisationType": "${this.getParameterValue("AuthorisationType", parameterMap)}",
                    "CompletionDateTime": "${this.getParameterValue("CompletionDateTime", parameterMap)}"
                },
                "SCASupportData": {
                          "RequestedSCAExemptionType": "${this.getParameterValue("RequestedSCAExemptionType", parameterMap)}",
                          "AppliedAuthenticationApproach": "${this.getParameterValue("AppliedAuthenticationApproach", parameterMap)}",
                          "ReferencePaymentOrderId": "${this.getParameterValue("ReferencePaymentOrderId", parameterMap)}"
                }  
            },
            "Risk": {
                "PaymentContextCode": "${this.getParameterValue("PaymentContextCode", parameterMap)}",
                "MerchantCategoryCode": "${this.getParameterValue("MerchantCategoryCode", parameterMap)}",
                "MerchantCustomerIdentification": "${this.getParameterValue("MerchantCustomerIdentification", parameterMap)}",
                "DeliveryAddress": {
                      "AddressLine": ${this.getParameterValue("AddressLine", parameterMap)},
                      "StreetName": "${this.getParameterValue("StreetName", parameterMap)}",
                      "BuildingNumber": "${this.getParameterValue("BuildingNumber", parameterMap)}",
                      "PostCode": "${this.getParameterValue("PostCode", parameterMap)}",
                      "TownName": "${this.getParameterValue("TownName", parameterMap)}",
                      "CountrySubDivision": "${this.getParameterValue("CountrySubDivision", parameterMap)}",
                      "Country": "${this.getParameterValue("Country", parameterMap)}"
                }
            }
        }
    """.stripIndent()

        return initiationPayloadInternationalStandingOrder

    }

    //For Creditor Agent
    String initiationPayloadInternationalPaymentForCreditorAgent(def parameterMap)  {

        String initiationPayloadInternational = """
            {
                "Data": {
                    "Initiation": {
                          "InstructionIdentification": "${this.getParameterValue("InstructionIdentification", parameterMap)}",
                          "EndToEndIdentification": "${this.getParameterValue("EndToEndIdentification", parameterMap)}",
                          "LocalInstrument": "${this.getParameterValue("LocalInstrument", parameterMap)}",
                          "InstructionPriority": "${this.getParameterValue("InstructionPriority", parameterMap)}",
                          "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                          "Purpose": "${this.getParameterValue("Purpose", parameterMap)}",
                          "ChargeBearer": "${this.getParameterValue("ChargeBearer", parameterMap)}",
                          "InstructedAmount": {
                                "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                                "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                          },
                          "DebtorAccount": {
                                "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                                "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                          },
                          "CreditorAgent":{
                                "SchemeName":"${this.getParameterValue("CreditorAgentSchemeName", parameterMap)}",
                                "Identification":"${this.getParameterValue("CreditorAgentIdentification", parameterMap)}",
                                "Name":"${this.getParameterValue("CreditorAgentName", parameterMap)}",
                                "PostalAddress":{
                                    "AddressType":"${this.getParameterValue("CreditorAgentAddressType", parameterMap)}",
                                    "Department":"${this.getParameterValue("CreditorAgentDepartment", parameterMap)}",
                                    "SubDepartment":"${this.getParameterValue("CreditorAgentSubDepartment", parameterMap)}",
                                    "StreetName":"${this.getParameterValue("CreditorAgentStreetName", parameterMap)}",
                                    "PostCode":"${this.getParameterValue("CreditorAgentPostCode", parameterMap)}",
                                    "BuildingNumber":"${this.getParameterValue("CreditorAgentBuildingNumber", parameterMap)}",
                                    "TownName":"${this.getParameterValue("CreditorAgentTownName", parameterMap)}",
                                    "CountrySubDivision":"${this.getParameterValue("CreditorAgentCountrySubDivision", parameterMap)}",
                                    "Country":"${this.getParameterValue("CreditorAgentCountry", parameterMap)}",
                                    "AddressLine": ${this.getParameterValue("CreditorAgentAddressLine", parameterMap)}
                                }
                          }
                    }
                },
                "Risk": {
                    
                }
            }
        """.stripIndent()

        return initiationPayloadInternational

    }

    String initiationPayloadInternationalSchedulePaymentForCreditorAgent(def parameterMap)  {

        String initiationPayloadInternationalSchedule = """
            {
                "Data": {
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "Initiation": {
                        "InstructionIdentification": "${this.getParameterValue("InstructionIdentification", parameterMap)}",
                        "EndToEndIdentification": "${this.getParameterValue("EndToEndIdentification", parameterMap)}",
                        "RequestedExecutionDateTime": "${this.getParameterValue("RequestedExecutionDateTime", parameterMap)}",
                        "LocalInstrument": "${this.getParameterValue("LocalInstrument", parameterMap)}",
                        "InstructionPriority": "${this.getParameterValue("InstructionPriority", parameterMap)}",
                        "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                        "Purpose": "${this.getParameterValue("Purpose", parameterMap)}",
                        "ChargeBearer": "${this.getParameterValue("ChargeBearer", parameterMap)}",
                        "InstructedAmount": {
                            "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                            "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                        },
                        "DebtorAccount": {
                            "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                            "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                            "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                            "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                        },
                        "CreditorAccount": {
                            "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                            "Identification":"${this.getParameterValue("CreditorIdentification", parameterMap)}",
                            "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                            "SecondaryIdentification": "${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                        },
                        "CreditorAgent":{
                                "SchemeName":"${this.getParameterValue("CreditorAgentSchemeName", parameterMap)}",
                                "Identification":"${this.getParameterValue("CreditorAgentIdentification", parameterMap)}",
                                "Name":"${this.getParameterValue("CreditorAgentName", parameterMap)}",
                                "PostalAddress":{
                                    "AddressType":"${this.getParameterValue("CreditorAgentAddressType", parameterMap)}",
                                    "Department":"${this.getParameterValue("CreditorAgentDepartment", parameterMap)}",
                                    "SubDepartment":"${this.getParameterValue("CreditorAgentSubDepartment", parameterMap)}",
                                    "StreetName":"${this.getParameterValue("CreditorAgentStreetName", parameterMap)}",
                                    "PostCode":"${this.getParameterValue("CreditorAgentPostCode", parameterMap)}",
                                    "BuildingNumber":"${this.getParameterValue("CreditorAgentBuildingNumber", parameterMap)}",
                                    "TownName":"${this.getParameterValue("CreditorAgentTownName", parameterMap)}",
                                    "CountrySubDivision":"${this.getParameterValue("CreditorAgentCountrySubDivision", parameterMap)}",
                                    "Country":"${this.getParameterValue("CreditorAgentCountry", parameterMap)}",
                                    "AddressLine": ${this.getParameterValue("CreditorAgentAddressLine", parameterMap)}
                                }
                        }
                    }
                },
                "Risk": {
                   
                }
            }
        """.stripIndent()

        return initiationPayloadInternationalSchedule

    }

    String initiationPayloadInternationalStandingOrderPaymentForCreditorAgent(def parameterMap)  {

        String initiationPayloadInternationalStandingOrder = """
            {
            "Data": {
                "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                "Initiation": {
                    "Frequency": "${this.getParameterValue("Frequency", parameterMap)}",
                    "Reference": "${this.getParameterValue("Reference", parameterMap)}",
                    "NumberOfPayments" : "${this.getParameterValue("NumberOfPayments", parameterMap)}",
                    "Purpose": "${this.getParameterValue("Purpose", parameterMap)}",
                    "ChargeBearer": "${this.getParameterValue("ChargeBearer", parameterMap)}",
                    "FirstPaymentDateTime": "${this.getParameterValue("FirstPaymentDateTime", parameterMap)}",
                    "FinalPaymentDateTime": "${this.getParameterValue("FinalPaymentDateTime", parameterMap)}",
                    "DebtorAccount": {
                        "SchemeName": "${this.getParameterValue("DebtorSchemeName", parameterMap)}",
                        "Identification": "${this.getParameterValue("DebtorIdentification", parameterMap)}",
                        "Name": "${this.getParameterValue("DebtorName", parameterMap)}",
                        "SecondaryIdentification": "${this.getParameterValue("DebtorSecondaryIdentification", parameterMap)}"
                    },
                    "CreditorAccount": {
                        "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                        "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                        "Name": "${this.getParameterValue("CreditorAccountName", parameterMap)}",
                        "SecondaryIdentification":"${this.getParameterValue("CreditorSecondaryIdentification", parameterMap)}"
                    },
                    "InstructedAmount": {
                        "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                        "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                    },
                    "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                    "CreditorAgent":{
                        "SchemeName":"${this.getParameterValue("CreditorAgentSchemeName", parameterMap)}",
                        "Identification":"${this.getParameterValue("CreditorAgentIdentification", parameterMap)}",
                        "Name":"${this.getParameterValue("CreditorAgentName", parameterMap)}",
                        "PostalAddress":{
                            "AddressType":"${this.getParameterValue("CreditorAgentAddressType", parameterMap)}",
                            "Department":"${this.getParameterValue("CreditorAgentDepartment", parameterMap)}",
                            "SubDepartment":"${this.getParameterValue("CreditorAgentSubDepartment", parameterMap)}",
                            "StreetName":"${this.getParameterValue("CreditorAgentStreetName", parameterMap)}",
                            "PostCode":"${this.getParameterValue("CreditorAgentPostCode", parameterMap)}",
                            "BuildingNumber":"${this.getParameterValue("CreditorAgentBuildingNumber", parameterMap)}",
                            "TownName":"${this.getParameterValue("CreditorAgentTownName", parameterMap)}",
                            "CountrySubDivision":"${this.getParameterValue("CreditorAgentCountrySubDivision", parameterMap)}",
                            "Country":"${this.getParameterValue("CreditorAgentCountry", parameterMap)}",
                            "AddressLine": ${this.getParameterValue("CreditorAgentAddressLine", parameterMap)}
                        }
                    }
                }
            },
            "Risk": {
              
            }
        }
    """.stripIndent()

        return initiationPayloadInternationalStandingOrder

    }

    //Payloads with mandatory parameters only
    String domesticPaymentWithMandatoryParameters(def parameterMap)  {

        String initiationPayloadDomestic = """
            { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"${this.getParameterValue("InstructionIdentification", parameterMap)}",
                     "EndToEndIdentification":"${this.getParameterValue("EndToEndIdentification", parameterMap)}",
                     "InstructedAmount":{ 
                        "Amount":"${this.getParameterValue("Amount", parameterMap)}",
                        "Currency":"${this.getParameterValue("Currency", parameterMap)}"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                        "Identification":"${this.getParameterValue("CreditorIdentification", parameterMap)}",
                        "Name":"${this.getParameterValue("CreditorName", parameterMap)}"
                     }
                  }
               },
               "Risk":{ 
                  
               }
            }
        """.stripIndent()

        return initiationPayloadDomestic

    }

    String domesticSchedulePaymentWithMandatoryParameters(def parameterMap)  {

        String initiationPayloadDomesticSchedule = """
            {
                "Data": {
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "Initiation": {
                          "InstructionIdentification": "${this.getParameterValue("InstructionIdentification", parameterMap)}",
                          "RequestedExecutionDateTime": "${this.getParameterValue("RequestedExecutionDateTime", parameterMap)}",
                          "InstructedAmount": {
                                "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                                "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorName", parameterMap)}"
                          }
                    }
                },
                "Risk": {
                    
                }
            }
        """.stripIndent()

        return initiationPayloadDomesticSchedule

    }

    String domesticStandingOrderPaymentWithMandatoryParameters(def parameterMap)  {

        String initiationPayloadDomesticStandingOrder = """
            {
                "Data": {
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "Initiation": {
                          "Frequency": "${this.getParameterValue("Frequency", parameterMap)}",
                          "FirstPaymentDateTime": "${this.getParameterValue("FirstPaymentDateTime", parameterMap)}",
                          "FinalPaymentDateTime": "${this.getParameterValue("FinalPaymentDateTime", parameterMap)}",
                          "FirstPaymentAmount": {
                                "Amount": "${this.getParameterValue("FirstPaymentAmount", parameterMap)}",
                                "Currency": "${this.getParameterValue("FirstPaymentCurrency", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorName", parameterMap)}"
                          }
                    }
                },
                "Risk": {
                    
                }
            }
        """.stripIndent()

        return initiationPayloadDomesticStandingOrder

    }

    String internationalPaymentWithMandatoryParameters(def parameterMap)  {

        String initiationPayloadInternational = """
            {
                "Data": {
                    "Initiation": {
                          "InstructionIdentification": "${this.getParameterValue("InstructionIdentification", parameterMap)}",
                          "EndToEndIdentification": "${this.getParameterValue("EndToEndIdentification", parameterMap)}",
                          "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                          "InstructedAmount": {
                                "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                                "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorName", parameterMap)}"
                          } 
                    }
                },
                "Risk": {
                 
                }
            }
        """.stripIndent()

        return initiationPayloadInternational

    }

    String internationalSchedulePaymentWithMandatoryParameters(def parameterMap)  {

        String initiationPayloadInternationalSchedule = """
            {
                "Data": {
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "Initiation": {
                        "InstructionIdentification": "${this.getParameterValue("InstructionIdentification", parameterMap)}",
                        "RequestedExecutionDateTime": "${this.getParameterValue("RequestedExecutionDateTime", parameterMap)}",
                        "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}",
                        "InstructedAmount": {
                            "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                            "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                        },
                        "CreditorAccount": {
                            "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                            "Identification":"${this.getParameterValue("CreditorIdentification", parameterMap)}",
                            "Name": "${this.getParameterValue("CreditorName", parameterMap)}"
                        }
                    }
                },
                "Risk": {
                    
                }
            }
        """.stripIndent()

        return initiationPayloadInternationalSchedule

    }

    String internationalStandingOrderPaymentWithMandatoryParameters(def parameterMap)  {

        String initiationPayloadInternationalStandingOrder = """
            {
            "Data": {
                "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                "Initiation": {
                    "Frequency": "${this.getParameterValue("Frequency", parameterMap)}",
                    "FirstPaymentDateTime": "${this.getParameterValue("FirstPaymentDateTime", parameterMap)}",
                    "CreditorAccount": {
                        "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                        "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                        "Name": "${this.getParameterValue("CreditorName", parameterMap)}"
                    },
                    "InstructedAmount": {
                        "Amount": "${this.getParameterValue("Amount", parameterMap)}",
                        "Currency": "${this.getParameterValue("Currency", parameterMap)}"
                    },
                    "CurrencyOfTransfer": "${this.getParameterValue("CurrencyOfTransfer", parameterMap)}"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

        return initiationPayloadInternationalStandingOrder

    }

    //Without End to End Identiication
    String domesticPaymentWithoutEndToEndIdentification = """
            { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"ACME412",
                     "InstructedAmount":{ 
                        "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                        "Currency":"GBP"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Identification":"08080021325698",
                        "Name":"ACME Inc",
                        "SecondaryIdentification":"0002"
                     },
                     "RemittanceInformation":{ 
                        "Reference":"FRESCO-101",
                        "Unstructured":"Internal ops code 5120101"
                     }
                  }
               },
               "Risk":{ 
                  
               }
            }
    """.stripIndent()

    String initiationPayloadInternationalPaymentWithoutEndToEndIdentification = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount": {
                            "Amount": "165.88",
                            "Currency": "GBP"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    //Without Intruction Identification
    String domesticPaymentValidateWithoutInstructionIdentification = """
        { 
           "Data":{ 
              "Initiation":{ 
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                    "Currency":"GBP"
                 },
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 }
              }
           },
           "Risk":{ 
              
           }
        }
    """.stripIndent()


    String domesticScheduleWithoutInstructionIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "EndToEndIdentification":"FRESCO.21302.GFX.20",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Tom Kirkman"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalPaymentValidateWithoutInstructionIdentification = """
        {
            "Data": {
                "Initiation": {
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount": {
                            "Amount": "165.88",
                            "Currency": "GBP"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()


    String internationalScheduleWithoutInstructionIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    //Without Instructed Amount
    String validateInstructedAmountPayloadDomesticPayment = """
        { 
            "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",                     
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 }
              }
            },
            "Risk":{ 
              
            }
        }
    """.stripIndent()

    String validateInsrtructedAmountPayloadDomesticSchedulePayment = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Tom Kirkman"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String validateInsrtructedAmountPayloadDomesticStandingOrder = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Pocket money for Damien",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      }
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalPaymentWithoutInstructedAmountPayload = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification":"FRESCO.21302.GFX.20",                     
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalScheduleWithoutInstructedAmount = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification":"FRESCO.21302.GFX.20",                     
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalStandingOrderWithoutInstructedAmount = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },             
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    //Without Instructed Amount Amount
    String initiationPayloadDomesticWithoutAmount_Amount = """
            { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"ACME412",
                     "EndToEndIdentification":"FRESCO.21302.GFX.20",
                     "InstructedAmount":{          
                        "Currency":"GBP"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Identification":"08080021325698",
                        "Name":"ACME Inc",
                        "SecondaryIdentification":"0002"
                     }
                  }
               },
               "Risk":{ 
                  
               }
            }
    """.stripIndent()

    String domesticSchedulePaymentWithout_InstrutedAmount_Amount = """
         {
              "Data": {
                    "Permission": "Create",
                    "Initiation": {
                          "InstructionIdentification": "ACME412",
                          "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                          "InstructedAmount":{          
                                "Currency":"GBP"
                          },
                          "DebtorAccount": {
                                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                                "Identification": "30080012343456",
                                "Name": "Andrea Frost"
                          },
                          "CreditorAccount": {
                                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                                "Identification": "08080021325698",
                                "Name": "Tom Kirkman"
                          }
                    }
              },
              "Risk": {
               
              }
        }
    """.stripIndent()

    String internationalPaymentWithoutInstructedAmountPayload_Amount = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount":{          
                            "Currency":"GBP"
                         },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalSchedule_Amount = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalStandingOrderWithoutInstructedAmount_Amount = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "InstructedAmount": {
                    "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },             
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    //Without Instructed Amount Currency
    String domesticPaymentWithoutAmountCurrency = """
        { 
            "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{          
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}"
                 },
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 }
              }
            },
            "Risk":{ 
              
            }
        }
    """.stripIndent()

    String domesticSchedulePaymentWithoutInstrutedAmountCurrency = """
         {
              "Data": {
                    "Permission": "Create",
                    "Initiation": {
                          "InstructionIdentification": "ACME412",
                          "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                          "InstructedAmount":{          
                                "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}"
                          },
                          "DebtorAccount": {
                                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                                "Identification": "30080012343456",
                                "Name": "Andrea Frost"
                          },
                          "CreditorAccount": {
                                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                                "Identification": "08080021325698",
                                "Name": "Tom Kirkman"
                          }
                    }
              },
              "Risk": {
              
              }
        }
    """.stripIndent()

    String internationalPaymentWithoutInstructedAmountPayloadCurrency = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount":{          
                            "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}"
                         },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalScheduleWithoutInstructedAmountCurrency = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "${ConnectorTestConstants.INSTRUCTED_AMOUNT}"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalStandingOrderWithoutInstructedAmount_Currency = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "InstructedAmount": {
                    "Amount": "${ConnectorTestConstants.INSTRUCTED_AMOUNT}"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },             
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    //Without debtor account identification
    String domesticWithouthDebtorAccountIdentification = """
        { 
           "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                    "Currency":"GBP"
                 },
                 "DebtorAccount": {
                  "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                   "Name": "Andrea Smith"
                 },
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 }
              }
           },
           "Risk":{ 
              
           }
        }
    """.stripIndent()

    String domesticScheduleWithouthDebtorAccountIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount":"15.88",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                          "SchemeName":"UK.OBIE.SortCodeAccountNumber",                  
                          "Name": "Andrea Smith"
                      },
                      "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String domnesticStandingOrderWithoutDebtorAccountIdentification = """
        {
          "Data": {
            "Permission": "Create",
            "Initiation": {
              "Frequency": "EvryDay",
              "Reference": "Pocket money for Damien",
              "FirstPaymentDateTime": "1976-06-06T06:06:06+00:00",
              "FirstPaymentAmount": {
                "Amount": "6.66",
                "Currency": "GBP"
              },
              "DebtorAccount": {
                "SchemeName":"UK.OBIE.SortCodeAccountNumber",                  
                "Name": "Andrea Smith"
              },
              "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "08080021325698",
                "Name": "Bob Clements"
              }
            }
          },
          "Risk": {
            "PaymentContextCode": "PartyToParty"
          }
        }
    """.stripIndent()

    String internationalPaymentWithoutDebtorAccountIdentification = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD", 
                      "InstructedAmount": {
                          "Amount": "165.88",
                          "Currency": "USD"
                      },       
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName":"UK.OBIE.SortCodeAccountNumber",       
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      }
                }
            },
            "Risk": {
                 
            }
        }
    """.stripIndent()

    String internationalSchedulePaymentWithoutDebtorAccountIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "DebtorAccount": {
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    },
                    "InstructedAmount": {
                        "Amount":"15.88",
                        "Currency": "GBP"
                    }
                }
            },
            "Risk": {
                  
            }
        }
    """.stripIndent()

    String internationalStandingOrderWithoutDebtorAccountIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                  
            }
        }
    """.stripIndent()

    //Without Creditor Account
    String domesticPaymentWithoutCreditorAccount = """
        { 
            "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                    "Currency":"GBP"
                 },                     
                 "RemittanceInformation":{ 
                    "Reference":"FRESCO-101",
                    "Unstructured":"Internal ops code 5120101"
                 }
              }
            },
            "Risk":{ 
              
            }
        }
    """.stripIndent()

    String domesticScheduleWithoutCreditorAccountPayload = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String domesticStandingOrdersWithoutCreditorAccountPayload = """
        {
            "Data": {
                "Permission": "Create",
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                },
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Pocket money for Damien",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalPaymentWithoutCreditorAccount = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount": {
                            "Amount": "165.88",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                             "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                             "Identification":"${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                             "Name":"ACME Inc",
                             "SecondaryIdentification":"0002"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalScheduleWithoutCreditorAccount = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "InstructedAmount": {
                              "Amount": "200.00",
                              "Currency": "GBP"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalStandingOrderWithoutCreditorAccountPayload = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },              
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    //Without Creditor Account Scheme Name
    String validateCreditorAccountSchemeNamePayloadDomesticPayment = """
        { 
            "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                    "Currency":"GBP"
                 },
                 "CreditorAccount":{ 
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 },
                 "RemittanceInformation":{ 
                    "Reference":"FRESCO-101",
                    "Unstructured":"Internal ops code 5120101"
                 }
              }
            },
            "Risk":{ 
              
              }
            }
        }
    """.stripIndent()

    String validateCreditorAccountSchemeNamePayloadDomesticSchedulePayment = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "CreditorAccount":{ 
                            "Identification":"08080021325698",
                            "Name":"ACME Inc",
                            "SecondaryIdentification":"0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String validateCreditorAccountSchemeNamePayloadDomesticStandingOrder = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Pocket money for Damien",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "CreditorAccount":{ 
                            "Identification":"08080021325698",
                            "Name":"ACME Inc",
                            "SecondaryIdentification":"0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String validateCreditorAccountSchemeNamePayloadInternationalPayment = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount": {
                            "Amount": "165.88",
                            "Currency": "GBP"
                      },
                      "CreditorAccount":{ 
                            "Identification":"08080021325698",
                            "Name":"ACME Inc",
                            "SecondaryIdentification":"0002"
                      },
                      "DebtorAccount": {
                             "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                             "Identification":"${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                             "Name":"ACME Inc",
                             "SecondaryIdentification":"0002"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String validateCreditorAccountSchemeNamePayloadInternationalSchedulePayment = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    },
                    "CreditorAccount": {
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String validateCreditorAccountSchemeNamePayloadIInternationalStandingOrder = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                          "SchemeName": "UK.OBIE.IBAN",
                          "Identification": "08080021325698",
                          "Name": "Tom Kirkman",
                          "SecondaryIdentification":"0002"
                    },
                    "CreditorAccount": {
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    //Without Creditor Account Identification
    String initiationPayloadDomesticWithoutCreditorAccountIdentification = """
        { 
           "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                    "Currency":"GBP"
                 },
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 },
                 "RemittanceInformation":{ 
                    "Reference":"FRESCO-101",
                    "Unstructured":"Internal ops code 5120101"
                 }
              }
           },
           "Risk":{ 
              
           }
        }
    """.stripIndent()

    String initiationPayloadDomesticScheduleWithoutCreditorAccountIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Name": "Tom Kirkman"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadDomesticStandingOrderWithoutCreditorAccountIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                },                
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Pocket money for Damien",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalPaymentWithoutCreditorAccountIdentification = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount": {
                            "Amount": "165.88",
                            "Currency": "GBP"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalStandingOrderWithoutCreditorAccountIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()


    String initiationPayloadInternationalScheduleWithoutCreditorAccountIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    //WIthout Risk Payload
    String initiationPayloadDomesticWithoutRiskPayload = """
        { 
           "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                    "Currency":"GBP"
                 },
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 }
              }
               }
        }
    """.stripIndent()

    String domesticScheduleWithoutRiskPayload = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Tom Kirkman"
                      }
                }
            }
        }
    """.stripIndent()

    String domesticStandingOrdersWithoutRiskPayload = """
        {
            "Data": {
                "Permission": "Create",
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                },              
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Pocket money for Damien",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            }   
        }
    """.stripIndent()

    String internationalPaymentWithoutRiskPayload = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount": {
                            "Amount": "165.88",
                            "Currency": "GBP"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      }
                }
            }  
        }
    """.stripIndent()

    String internationalScheduleWithoutRiskPayload  = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            }
        }
    """.stripIndent()

    String internationalStandingOrderWithoutRiskPayload  = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            }     
        }
    """.stripIndent()

    //Payment Context Code EcommerceGoods Without DeliveryAddress
    String paymentContextCodeEcommerceGoodsWithoutDeliveryAddress = """
            { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"ACME412",
                     "EndToEndIdentification":"FRESCO.21302.GFX.20",
                     "InstructedAmount":{ 
                        "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                        "Currency":"GBP"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Identification":"08080021325698",
                        "Name":"ACME Inc",
                        "SecondaryIdentification":"0002"
                     },
                     "RemittanceInformation":{ 
                        "Reference":"FRESCO-101",
                        "Unstructured":"Internal ops code 5120101"
                     }
                  }
               },
               "Risk":{ 
                  "PaymentContextCode":"EcommerceGoods",
                  "MerchantCategoryCode":"5967",
                  "MerchantCustomerIdentification":"053598653254"
                 
                }
            }
""".stripIndent()

    //Without DeliveryAddress
    String initiationPayloadDomesticScheduleWithoutDeliveryAddress  = """
            {
      "Data": {
            "Permission": "Create",
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                  "InstructedAmount": {
                        "Amount": "200.00",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "1212",
            "MerchantCustomerIdentification": "121212"  
      }
}
""".stripIndent()


    String initiationPayloadDomesticStandingOrdersWithoutDeliveryAddress  = """
            {
      "Data": {
            "Permission": "Create",
            "Initiation": {
                  "Frequency": "EvryDay",
                  "Reference": "Pocket money for Damien",
                  "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                  "FinalPaymentDateTime": "${TestUtil.getDateAndTime(10)}",
                  "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                  "FirstPaymentAmount": {
                        "Amount": "6.66",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Smith",
                        "SecondaryIdentification": "30080012343456"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Bob Clements",
                        "SecondaryIdentification": "30080012343456"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "5967",
            "MerchantCustomerIdentification": "053598653254"
          
      }
}
""".stripIndent()


    String initiationPayloadInternationalPaymentWithoutDeliveryAddress  = """
        {
      "Data": {
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "EndToEndIdentification": "FRESCO.21302.GFX.20",
                  "InstructionPriority": "Normal",
                  "CurrencyOfTransfer": "USD",
                  "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "GBP"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "1234",
            "MerchantCustomerIdentification": "1234"
            
      }
}
""".stripIndent()

    String internationalScheduleWithoutDeliveryAddress = """
    {
    "Data": {
        "Permission": "Create",
        "Initiation": {
            "InstructionIdentification": "ACME412",
            "EndToEndIdentification": "FRESCO.21302.GFX.20",
            "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
            "LocalInstrument": "UK.OBIE.BACS",
            "InstructionPriority": "Normal",
            "CurrencyOfTransfer": "USD",
            "InstructedAmount": {
                "Amount": "165.88",
                "Currency": "USD"
            },
            "DebtorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                "Name": "test user",
                "SecondaryIdentification": "30080098763459"
            },
            "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
            }
        }
    },
    "Risk": {
        "PaymentContextCode": "EcommerceGoods",
        "MerchantCategoryCode": "1234",
        "MerchantCustomerIdentification": "1234"
    }
}
""".stripIndent()


    String internationalStandingOrderWithoutDeliveryAddress  = """
    {
        "Data": {
            "Permission": "Create",
            "Initiation": {
                "Frequency": "EvryWorkgDay",
                "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "30080012343456",
                    "Name": "Andrea Frost"
                },
                "CreditorAccount": {
                    "SchemeName": "UK.OBIE.IBAN",
                    "Identification": "08080021325698",
                    "Name": "Tom Kirkman"
                },
                "InstructedAmount": {
                    "Amount": "20.12",
                    "Currency": "EUR"
                },
                "CurrencyOfTransfer": "EUR"
            }
        },
        "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "1234",
            "MerchantCustomerIdentification": "1234"   
        }
    }
""".stripIndent()

    //WithoutRiskDeliveryAddressAddressLine
    public String domesticPaymentWithoutRiskDeliveryAddressAddressLine = """
        { 
           "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"158.88",
                    "Currency":"GBP"
                 },
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 },
                 "RemittanceInformation":{ 
                    "Reference":"FRESCO-101",
                    "Unstructured":"Internal ops code 5120101"
                 }
              }
           },
           "Risk":{ 
              "PaymentContextCode":"EcommerceGoods",
              "MerchantCategoryCode":"5967",
              "MerchantCustomerIdentification":"053598653254",
              "DeliveryAddress":{              
                 "StreetName":"Acacia Avenue",
                 "BuildingNumber":"27",
                 "PostCode":"GU31 2ZZ",
                 "TownName":"Sparsholt",
                 "CountrySubDivision": 
                    "Wesses"
                 ,
                 "Country":"UK"
              }
           }
        }
    """.stripIndent()

    //Without DeliveryAddress StreetName
    String domesticPaymentWithoutDeliveryAddressStreetName = """
            { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"ACME412",
                     "EndToEndIdentification":"FRESCO.21302.GFX.20",
                     "InstructedAmount":{ 
                        "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                        "Currency":"GBP"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Identification":"08080021325698",
                        "Name":"ACME Inc",
                        "SecondaryIdentification":"0002"
                     }
                  }
               },
               "Risk":{ 
                  "PaymentContextCode":"EcommerceGoods",
                  "MerchantCategoryCode":"5967",
                  "MerchantCustomerIdentification":"053598653254",
                  "DeliveryAddress":{ 
                     "AddressLine":[ 
                        "Flat 7",
                        "Acacia Lodge"
                     ],
                     "BuildingNumber":"27",
                     "PostCode":"GU31 2ZZ",
                     "TownName":"Sparsholt",
                     "CountrySubDivision": 
                        "Wessex"
                     ,
                     "Country":"UK"
                  }
               }
            }
""".stripIndent()


    String domesticScheduleWithoutDeliveryAddressStreetName = """
            {
      "Data": {
            "Permission": "Create",
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                  "InstructedAmount": {
                        "Amount": "200.00",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "1212",
            "MerchantCustomerIdentification": "121212",
            "DeliveryAddress": {
                  "AddressLine": ["Flat 7", "Acacia Lodge"],
                  "BuildingNumber": "27",
                  "PostCode": "GU31 2ZZ",
                  "TownName": "town1",
                  "CountrySubDivision": "Wessex",
                  "Country": "UK"
            }
      }
}
""".stripIndent()

    String domesticStandingOrdersWithoutDeliveryAddressStreetName = """
            {
      "Data": {
            "Permission": "Create",
            "Authorisation": {
                  "AuthorisationType": "Any",
                  "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
            },
            "Initiation": {
                  "Frequency": "EvryDay",
                  "Reference": "Pocket money for Damien",
                  "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                  "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                  "FinalPaymentDateTime": "${TestUtil.getDateAndTime(10)}",
                  "FirstPaymentAmount": {
                        "Amount": "6.66",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Smith",
                        "SecondaryIdentification": "30080012343456"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Bob Clements",
                        "SecondaryIdentification": "30080012343456"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "5967",
            "MerchantCustomerIdentification": "053598653254",
            "DeliveryAddress": {
                  "AddressLine": ["Flat 7"],
                  "BuildingNumber": "27",
                  "PostCode": "GU31 2ZZ",
                  "TownName": "Sparsholt",
                  "CountrySubDivision": "Wessex",
                  "Country": "UK"
            }
      }
}
""".stripIndent()



    String internationalPaymentWithoutDeliveryAddressStreetName = """
        {
      "Data": {
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "EndToEndIdentification": "FRESCO.21302.GFX.20",
                  "InstructionPriority": "Normal",
                  "CurrencyOfTransfer": "USD",
                  "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "GBP"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                  }    
            }
      },
      "Risk": {
            "PaymentContextCode": "BillPayment",
            "MerchantCategoryCode": "1234",
            "MerchantCustomerIdentification": "1234",
            "DeliveryAddress": {
                  "AddressLine": ["string"],
                  "BuildingNumber": "string",
                  "PostCode": "string",
                  "TownName": "string",
                  "CountrySubDivision": "string",
                  "Country": "EU"
            }
      }
}
""".stripIndent()

    String internationalStandingOrderWithoutDeliveryAddressStreetName = """
    {
        "Data": {
            "Permission": "Create",
            "Initiation": {
                "Frequency": "EvryWorkgDay",
                "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "30080012343456",
                    "Name": "Andrea Frost"
                },
                "CreditorAccount": {
                    "SchemeName": "UK.OBIE.IBAN",
                    "Identification": "08080021325698",
                    "Name": "Tom Kirkman"
                },
                "InstructedAmount": {
                    "Amount": "20.12",
                    "Currency": "EUR"
                },
                "CurrencyOfTransfer": "EUR"
            }
        },
        "Risk": {
            "PaymentContextCode": "BillPayment",
            "MerchantCategoryCode": "1234",
            "MerchantCustomerIdentification": "1234",
            "DeliveryAddress": {
                "CountrySubDivision": "string",
                "AddressLine": ["string"],
                "BuildingNumber": "string",
                "TownName": "string",
                "Country": "EU",
                "PostCode": "10"
            }
        }
    }
""".stripIndent()



    String internationalScheduleWithoutDeliveryAddressStreetName = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                "PaymentContextCode": "BillPayment",
                "MerchantCategoryCode": "1234",
                "MerchantCustomerIdentification": "1234",
                "DeliveryAddress": {
                    "AddressLine": ["string"],
                    "BuildingNumber": "string",
                    "PostCode": "string",
                    "TownName": "string",
                    "CountrySubDivision": "string",
                    "Country": "UK"
                }
            }
        }
    """.stripIndent()

    //WithoutDeliveryAddressBuildingNumber
    String domesticPaymentWithoutDeliveryAddressBuildingNumber = """
            { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"ACME412",
                     "EndToEndIdentification":"FRESCO.21302.GFX.20",
                     "InstructedAmount":{ 
                        "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                        "Currency":"GBP"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Identification":"08080021325698",
                        "Name":"ACME Inc",
                        "SecondaryIdentification":"0002"
                     }
                  }
               },
               "Risk":{ 
                  "PaymentContextCode":"EcommerceGoods",
                  "MerchantCategoryCode":"5967",
                  "MerchantCustomerIdentification":"053598653254",
                  "DeliveryAddress":{ 
                     "AddressLine":[ 
                        "Flat 7",
                        "Acacia Lodge"
                     ],
                     "StreetName":"street 123",
                     "PostCode":"GU31 2ZZ",
                     "TownName":"Sparsholt",
                     "CountrySubDivision": 
                        "Wessex"
                     ,
                     "Country":"UK"
                  }
               }
            }
""".stripIndent()

    String domesticScheduleWithoutDeliveryAddressBuildingNumber = """
            {
      "Data": {
            "Permission": "Create",
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                  "InstructedAmount": {
                        "Amount": "200.00",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "1212",
            "MerchantCustomerIdentification": "121212",
            "DeliveryAddress": {
                  "AddressLine": ["Flat 7", "Acacia Lodge"],
                  "StreetName": "Acacia Avenue",
                  "PostCode": "GU31 2ZZ",
                  "TownName": "town1",
                  "CountrySubDivision": "Wessex",
                  "Country": "UK"
            }
      }
}
""".stripIndent()

    String domesticStandingOrdersWithoutDeliveryAddressBuildingNumber = """
            {
      "Data": {
            "Permission": "Create",
            "Initiation": {
                  "Frequency": "EvryDay",
                  "Reference": "Pocket money for Damien",
                  "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                  "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                  "FinalPaymentDateTime": "${TestUtil.getDateAndTime(10)}",
                  "FirstPaymentAmount": {
                        "Amount": "6.66",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Smith",
                        "SecondaryIdentification": "30080012343456"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Bob Clements",
                        "SecondaryIdentification": "30080012343456"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "5967",
            "MerchantCustomerIdentification": "053598653254",
            "DeliveryAddress": {
                  "AddressLine": ["Flat 7"],
                  "StreetName": "Acacia Avenue",
                  "PostCode": "GU31 2ZZ",
                  "TownName": "Sparsholt",
                  "CountrySubDivision": "Wessex",
                  "Country": "UK"
            }
      }
}
""".stripIndent()

    String internationalPaymentWithoutDeliveryAddressBuildingNumber = """
        {
      "Data": {
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "EndToEndIdentification": "FRESCO.21302.GFX.20",
                  "InstructionPriority": "Normal",
                  "CurrencyOfTransfer": "USD",
                  "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "GBP"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "BillPayment",
            "MerchantCategoryCode": "1234",
            "MerchantCustomerIdentification": "1234",
            "DeliveryAddress": {
                  "AddressLine": ["string"],
                  "StreetName": "string",
                  "PostCode": "string",
                  "TownName": "string",
                  "CountrySubDivision": "string",
                  "Country": "EU"
            }
      }
}
""".stripIndent()


    String internationalStandingOrderWithoutDeliveryAddressBuildingNumber = """
    {
        "Data": {
            "Permission": "Create",
            "Initiation": {
                "Frequency": "EvryWorkgDay",
                "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "30080012343456",
                    "Name": "Andrea Frost"
                },
                "CreditorAccount": {
                    "SchemeName": "UK.OBIE.IBAN",
                    "Identification": "08080021325698",
                    "Name": "Tom Kirkman"
                },
                "InstructedAmount": {
                    "Amount": "20.12",
                    "Currency": "EUR"
                },
                "CurrencyOfTransfer": "EUR"
            }
        },
        "Risk": {
            "PaymentContextCode": "BillPayment",
            "MerchantCategoryCode": "1234",
            "MerchantCustomerIdentification": "1234",
            "DeliveryAddress": {
                "StreetName": "string",
                "CountrySubDivision": "string",
                "AddressLine": ["string"],
                "BuildingNumber": "string",
                "TownName": "string",
                "Country": "EU",
                "PostCode": "10"
            }
        }
    }
""".stripIndent()


    String internationalScheduleWithoutDeliveryAddressBuildingNumber = """
    {
    "Data": {
        "Permission": "Create",
        "Initiation": {
            "InstructionIdentification": "ACME412",
            "EndToEndIdentification": "FRESCO.21302.GFX.20",
            "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
            "LocalInstrument": "UK.OBIE.BACS",
            "InstructionPriority": "Normal",
            "CurrencyOfTransfer": "USD",
            "InstructedAmount": {
                "Amount": "165.88",
                "Currency": "USD"
            },
            "DebtorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                "Name": "test user",
                "SecondaryIdentification": "30080098763459"
            },
            "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
            }
        }
    },
    "Risk": {
        "PaymentContextCode": "BillPayment",
        "MerchantCategoryCode": "1234",
        "MerchantCustomerIdentification": "1234",
        "DeliveryAddress": {
            "AddressLine": ["string"],
            "StreetName": "string",
            "PostCode": "string",
            "TownName": "string",
            "CountrySubDivision": "string",
            "Country": "UK"
        }
    }
}
""".stripIndent()

    //WithoutDeliveryAddressPostCode
    String domesticPaymentWithoutDeliveryAddressPostCode = """
            { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"ACME412",
                     "EndToEndIdentification":"FRESCO.21302.GFX.20",
                     "InstructedAmount":{ 
                        "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                        "Currency":"GBP"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Identification":"08080021325698",
                        "Name":"ACME Inc",
                        "SecondaryIdentification":"0002"
                     }
                  }
               },
               "Risk":{ 
                  "PaymentContextCode":"EcommerceGoods",
                  "MerchantCategoryCode":"5967",
                  "MerchantCustomerIdentification":"053598653254",
                  "DeliveryAddress":{ 
                     "AddressLine":[ 
                        "Flat 7",
                        "Acacia Lodge"
                     ],
                     "StreetName":"street 123",
                     "BuildingNumber":"10",
                     "TownName":"Sparsholt",
                     "CountrySubDivision": 
                        "Wessex"
                     ,
                     "Country":"UK"
                  }
               }
            }
""".stripIndent()

    public String domesticPaymentWithoutRiskDeliveryAddressCountrySubDivisionParameter = """
             { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"ACME412",
                     "EndToEndIdentification":"FRESCO.21302.GFX.20",
                     "InstructedAmount":{ 
                        "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                        "Currency":"GBP"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Identification":"08080021325698",
                        "Name":"ACME Inc",
                        "SecondaryIdentification":"0002"
                     }
                  }
               },
               "Risk":{ 
                  "PaymentContextCode":"EcommerceGoods",
                  "MerchantCategoryCode":"5967",
                  "MerchantCustomerIdentification":"053598653254",
                  "DeliveryAddress":{ 
                     "AddressLine":[ 
                        "Flat 7",
                        "Acacia Lodge"
                     ],
                     "StreetName":"Acacia Avenue",
                     "BuildingNumber":"27",
                     "PostCode":"GU31 2ZZ",
                     "TownName":"Sparsholt",               
                     "Country":"UK"
                  }
               }
            }
""".stripIndent()

    String domesticPaymentWithoutRiskDeliveryAddressCountryParameter = """
             { 
               "Data":{ 
                  "Initiation":{ 
                     "InstructionIdentification":"ACME412",
                     "EndToEndIdentification":"FRESCO.21302.GFX.20",
                     "InstructedAmount":{ 
                        "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                        "Currency":"GBP"
                     },
                     "CreditorAccount":{ 
                        "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                        "Identification":"08080021325698",
                        "Name":"ACME Inc",
                        "SecondaryIdentification":"0002"
                     }
                  }
               },
               "Risk":{ 
                  "PaymentContextCode":"EcommerceGoods",
                  "MerchantCategoryCode":"5967",
                  "MerchantCustomerIdentification":"053598653254",
                  "DeliveryAddress":{ 
                     "AddressLine":[ 
                        "Flat 7",
                        "Acacia Lodge"
                     ],
                     "StreetName":"Acacia Avenue",
                     "BuildingNumber":"27",
                     "PostCode":"GU31 2ZZ",
                     "TownName":"Sparsholt",               
                     "CountrySubDivision": "string"
                  }
               }
            }
""".stripIndent()

    String domesticScheduleWithoutRiskDeliveryAddressCountryParameter  = """
            {
      "Data": {
            "Permission": "Create",
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                  "InstructedAmount": {
                        "Amount": "200.00",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "1212",
            "MerchantCustomerIdentification": "121212",
            "DeliveryAddress": {
                  "AddressLine": ["Flat 7", "Acacia Lodge"],
                  "StreetName": "Acacia Avenue",
                  "BuildingNumber": "27",
                  "PostCode": "GU31 2ZZ",
                  "TownName": "town1",
                  "CountrySubDivision": "Wessex"
            }
      }
}
""".stripIndent()


    String domesticStandingOrdersWithoutRiskDeliveryAddressCountryParameter  = """
            {
      "Data": {
            "Permission": "Create",
            "Authorisation": {
                  "AuthorisationType": "Any",
                  "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
            },
            "Initiation": {
                  "Frequency": "EvryDay",
                  "Reference": "Pocket money for Damien",
                  "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                  "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                  "FirstPaymentAmount": {
                        "Amount": "6.66",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Smith",
                        "SecondaryIdentification": "30080012343456"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Bob Clements",
                        "SecondaryIdentification": "30080012343456"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "EcommerceGoods",
            "MerchantCategoryCode": "5967",
            "MerchantCustomerIdentification": "053598653254",
            "DeliveryAddress": {
                  "AddressLine": ["Flat 7"],
                  "StreetName": "Acacia Avenue",
                  "BuildingNumber": "27",
                  "PostCode": "GU31 2ZZ",
                  "TownName": "Sparsholt",
                  "CountrySubDivision": "Wessex"
            }
      }
}
""".stripIndent()

    String internationalPaymentWithoutRiskDeliveryAddressCountryParameter  = """
        {
      "Data": {
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "EndToEndIdentification": "FRESCO.21302.GFX.20",
                  "InstructionPriority": "Normal",
                  "CurrencyOfTransfer": "USD",
                  "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "GBP"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification":"${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                  }
            }
      },
      "Risk": {
            "PaymentContextCode": "BillPayment",
            "MerchantCategoryCode": "1234",
            "MerchantCustomerIdentification": "1234",
            "DeliveryAddress": {
                  "AddressLine": ["string"],
                  "StreetName": "string",
                  "BuildingNumber": "string",
                  "PostCode": "string",
                  "TownName": "string",
                  "CountrySubDivision": "string"
            }
      }
}
""".stripIndent()


    String internationalStandingOrderWithoutRiskDeliveryAddressCountryParameter   = """
    {
        "Data": {
            "Permission": "Create",
            "Initiation": {
                "Frequency": "EvryWorkgDay",
                "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "30080012343456",
                    "Name": "Andrea Frost"
                },
                "CreditorAccount": {
                    "SchemeName": "UK.OBIE.IBAN",
                    "Identification": "08080021325698",
                    "Name": "Tom Kirkman"
                },
                "InstructedAmount": {
                    "Amount": "20.12",
                    "Currency": "EUR"
                },
                "CurrencyOfTransfer": "EUR"
            }
        },
        "Risk": {
            "PaymentContextCode": "BillPayment",
            "MerchantCategoryCode": "1234",
            "MerchantCustomerIdentification": "1234",
            "DeliveryAddress": {
                "StreetName": "string",
                "CountrySubDivision": "string",
                "AddressLine": ["string"],
                "BuildingNumber": "string",
                "TownName": "string",
                "PostCode": "10"
            }
        }
    }
""".stripIndent()




    String internationalScheduleWithoutRiskDeliveryAddressCountryParameter  = """
    {
    "Data": {
        "Permission": "Create",
        "Initiation": {
            "InstructionIdentification": "ACME412",
            "EndToEndIdentification": "FRESCO.21302.GFX.20",
            "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
            "LocalInstrument": "UK.OBIE.BACS",
            "InstructionPriority": "Normal",
            "CurrencyOfTransfer": "USD",
            "InstructedAmount": {
                "Amount": "165.88",
                "Currency": "USD"
            },
            "DebtorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                "Name": "test user",
                "SecondaryIdentification": "30080098763459"
            },
            "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
            }
        },
        "Authorisation": {
            "AuthorisationType": "Any",
            "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
        } 
    },
    "Risk": {
        "PaymentContextCode": "BillPayment",
        "MerchantCategoryCode": "1234",
        "MerchantCustomerIdentification": "1234",
        "DeliveryAddress": {
            "AddressLine": ["string"],
            "StreetName": "string",
            "BuildingNumber": "string",
            "PostCode": "string",
            "TownName": "string",
            "CountrySubDivision": "string"
        }
    }
}
""".stripIndent()

    String domesticPaymentWithoutAuthorisationType = """
            {
   "Data":{
      "Initiation":{
         "InstructionIdentification":"ACME412",
         "EndToEndIdentification":"FRESCO.21302.GFX.20",
         "InstructedAmount":{
            "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
            "Currency":"GBP"
         },
         "CreditorAccount":{
            "SchemeName":"UK.OBIE.SortCodeAccountNumber",
            "Identification":"08080021325698",
            "Name":"ACME Inc",
            "SecondaryIdentification":"0002"
         },
         "RemittanceInformation":{
            "Reference":"FRESCO-101",
            "Unstructured":"Internal ops code 5120101"
         }
      },
      "Authorisation":{
         "CompletionDateTime":"2019-05-01T12:35:22.532Z"
      }
   },
   "Risk":{
      
   }
}
""".stripIndent()

    String initiationPayloadDomesticScheduleWithoutAuthorisationType = """
            {
      "Data": {
            "Permission": "Create",
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                  "InstructedAmount": {
                        "Amount": "200.00",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                  }
            },
            "Authorisation":{
                 "CompletionDateTime":"2019-05-01T12:35:22.532Z"
            }
      },
      "Risk": {
            
      }
}
""".stripIndent()

    String domesticStandingOrderWithoutAuthorisationType = """
            {
      "Data": {
            "Permission": "Create",
            
            "Initiation": {
                  "Frequency": "EvryDay",
                  "Reference": "Pocket money for Damien",
                  "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                  "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                  "FirstPaymentAmount": {
                        "Amount": "6.66",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Smith",
                        "SecondaryIdentification": "30080012343456"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Bob Clements",
                        "SecondaryIdentification": "30080012343456"
                  }
            },
            "Authorisation":{
                 "CompletionDateTime":"${TestUtil.getDateAndTime(6)}"
            }
      },
      "Risk": {
            }
      }
}
""".stripIndent()


    String internationalPaymentWithoutAuthorisationType = """
        {
      "Data": {
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "EndToEndIdentification": "FRESCO.21302.GFX.20",
                  "InstructionPriority": "Normal",
                  "CurrencyOfTransfer": "USD",
                  "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "GBP"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                  },
                  "ExchangeRateInformation": {
                        "UnitCurrency": "GBP",
                        "RateType": "Actual"
                  }
            },
            "Authorisation":{
                 "CompletionDateTime":"2019-05-01T12:35:22.532Z"
            }
           
      },
      "Risk": {
            
      }
}
""".stripIndent()


    String internationalStandingOrderWithoutAuthorisationType = """
    {
        "Data": {
            "Permission": "Create",
            "Initiation": {
                "Frequency": "EvryWorkgDay",
                "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "30080012343456",
                    "Name": "Andrea Frost"
                },
                "CreditorAccount": {
                    "SchemeName": "UK.OBIE.IBAN",
                    "Identification": "08080021325698",
                    "Name": "Tom Kirkman"
                },
                "InstructedAmount": {
                    "Amount": "20.12",
                    "Currency": "EUR"
                },
                "CurrencyOfTransfer": "EUR"
            },
            "Authorisation":{
                 "CompletionDateTime":"2019-05-01T12:35:22.532Z"
            }
        },
        "Risk": {
            
        }
    }
""".stripIndent()

    String internationalScheduleWithoutAuthorisationType = """
    {
    "Data": {
        "Permission": "Create",
        "Initiation": {
            "InstructionIdentification": "ACME412",
            "EndToEndIdentification": "FRESCO.21302.GFX.20",
            "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
            "LocalInstrument": "UK.OBIE.BACS",
            "InstructionPriority": "Normal",
            "CurrencyOfTransfer": "USD",
            "InstructedAmount": {
                "Amount": "165.88",
                "Currency": "USD"
            },
            "DebtorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                "Name": "test user",
                "SecondaryIdentification": "30080098763459"
            },
            "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
            }
        },
        "Authorisation":{
             "CompletionDateTime":"2019-05-01T12:35:22.532Z"
        }
    },
    "Risk": {
        
    }
}
""".stripIndent()

    String domesticPaymentWithoutCompletionDateTimeParameter = """
            {
   "Data":{
      "Initiation":{
         "InstructionIdentification":"ACME412",
         "EndToEndIdentification":"FRESCO.21302.GFX.20",
         "InstructedAmount":{
            "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
            "Currency":"GBP"
         },
         "CreditorAccount":{
            "SchemeName":"UK.OBIE.SortCodeAccountNumber",
            "Identification":"08080021325698",
            "Name":"ACME Inc",
            "SecondaryIdentification":"0002"
         }
      },
      "Authorisation":{
         "AuthorisationType":"Any"

      }
   },
   "Risk":{
      
   }
}
""".stripIndent()

    String domesticScheduleWithoutCompletionDateTimeParameter  = """
            {
      "Data": {
            "Permission": "Create",
            "Authorisation":{
                 "AuthorisationType":"Any"

            },
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                  "InstructedAmount": {
                        "Amount": "200.00",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                  }
            }
      },
      "Risk": {
            
      }
}
""".stripIndent()


    String domesticStandingOrdersWithoutCompletionDateTimeParameter   = """
            {
      "Data": {
            "Permission": "Create",
            "Authorisation": {
                  "AuthorisationType": "Any"
            },
            "Initiation": {
                  "Frequency": "EvryDay",
                  "Reference": "Pocket money for Damien",
                  "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                  "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                  "FinalPaymentDateTime": "${TestUtil.getDateAndTime(10)}",
                  "FirstPaymentAmount": {
                        "Amount": "6.66",
                        "Currency": "GBP"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Smith",
                        "SecondaryIdentification": "30080012343456"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "08080021325698",
                        "Name": "Bob Clements",
                        "SecondaryIdentification": "30080012343456"
                  }
            }
      },
      "Risk": {
            
      }
}
""".stripIndent()

    String internationalPaymentWithoutCompletionDateTimeParameter   = """
        {
      "Data": {
            "Initiation": {
                  "InstructionIdentification": "ACME412",
                  "EndToEndIdentification": "FRESCO.21302.GFX.20",
                  "InstructionPriority": "Normal",
                  "CurrencyOfTransfer": "USD",
                  "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "GBP"
                  },
                  "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                  },
                  "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                  }
            },
            "Authorisation": {
                  "AuthorisationType": "Any"

            }
      },
      "Risk": {
            
      }
}
""".stripIndent()


    String internationalStandingOrderWithoutCompletionDateTimeParameter   = """
    {
        "Data": {
            "Permission": "Create",
            "Initiation": {
                "Frequency": "EvryWorkgDay",
                "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "30080012343456",
                    "Name": "Andrea Frost"
                },
                "CreditorAccount": {
                    "SchemeName": "UK.OBIE.IBAN",
                    "Identification": "08080021325698",
                    "Name": "Tom Kirkman"
                },
                "InstructedAmount": {
                    "Amount": "20.12",
                    "Currency": "EUR"
                },
                "CurrencyOfTransfer": "EUR"
            },
            "Authorisation": {
                "AuthorisationType": "Any"
            }
        },
        "Risk": {
            
        }
    }
""".stripIndent()

    String internationalScheduleWithoutCompletionDateTimeParameter   = """
    {
    "Data": {
        "Permission": "Create",
        "Initiation": {
            "InstructionIdentification": "ACME412",
            "EndToEndIdentification": "FRESCO.21302.GFX.20",
            "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
            "LocalInstrument": "UK.OBIE.BACS",
            "InstructionPriority": "Normal",
            "CurrencyOfTransfer": "USD",
            "InstructedAmount": {
                "Amount": "165.88",
                "Currency": "USD"
            },
            "DebtorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                "Name": "test user",
                "SecondaryIdentification": "30080098763459"
            },
            "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
            }
        },
        "Authorisation": {
            "AuthorisationType": "Any"
        }
    },
    "Risk": {
        
    }
}
""".stripIndent()

    String domesticPaymentWithWrongPayloadMapping = """
            {
   "Data":{
      "Initiation":{
         "InstructionIdentification":"ACME412",
         "EndToEndIdentification":"FRESCO.21302.GFX.20",
         "InstructedAmount":{
            "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
            "Currency":"GBP"
         },
         "CreditorAccount":{
            "SchemeName":"UK.OBIE.SortCodeAccountNumber",
            "Identification":"08080021325698",
            "Name":"ACME Inc",
            "SecondaryIdentification":"0002"
         },
         "RemittanceInformation":{
            "Reference":"FRESCO-101",
            "Unstructured":"Internal ops code 5120101"
         },
         "Authorisation":{
             "AuthorisationType":"Any",
             "CompletionDateTime":"2019-05-01T12:35:22.532Z"
         }
      }
      
   },
   "Risk":{
      
   }
}
""".stripIndent()

    String paymentInitiationConsentTypesWithEmptyPayload = """
            { 
              
            }
""".stripIndent()

    String emptyStringPayload = '""'

    String domesticScheduleWithoutPermission = """
        {
            "Data": {
            
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Tom Kirkman"
                      },
                      "RemittanceInformation": {
                            "Reference": "DSR-037",
                            "Unstructured": "Internal ops code 5120103"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()


    String internationalScheduleWithoutPermission = """
        {
            "Data": {
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                },
                "Authorisation": {
                    "AuthorisationType": "Any",
                    "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    public String initiationPayloadDomesticStandingOrdersWithoutPermission = """
        {
            "Data": {
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                },
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Pocket money for Damien",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "RecurringPaymentAmount": {
                            "Amount": "7.00",
                            "Currency": "GBP"
                      },
                      "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                      "FinalPaymentAmount": {
                            "Amount": "12.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                "PaymentContextCode": "EcommerceGoods",
                "MerchantCategoryCode": "5967",
                "MerchantCustomerIdentification": "053598653254",
                "DeliveryAddress": {
                      "AddressLine": ["Flat 7"],
                      "StreetName": "Acacia Avenue",
                      "BuildingNumber": "27",
                      "PostCode": "GU31 2ZZ",
                      "TownName": "Sparsholt",
                      "CountrySubDivision": "Wessex",
                      "Country": "UK"
                }
            }
        }
    """.stripIndent()

    String initiationPayloadDomesticStandingOrdersWithoutFrequency = """
        {
            "Data": {
                "Permission": "Create",
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                },
                "Initiation": {
                      "Reference": "Pocket money for Damien",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalStandingOrderWithoutFrequency = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadDomesticStandingOrdersWithoutReference = """
        {
            "Data": {
                "Permission": "Create",
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                },
                "Initiation": {
                      "Frequency": "EvryDay",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FinalPaymentDateTime": "${TestUtil.getDateAndTime(10)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalStandingOrderWithoutReference = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadDomesticStandingOrdersWithoutFirstPaymentDateTime = """
        {
            "Data": {
                "Permission": "Create",
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                },
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Test123",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String internationalStandingOrderWithoutFirstPaymentDateTime = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    public String initiationPayloadDomesticStandingOrdersWithoutFirstPaymentAmount = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Test123",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(3)}",                
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadDomesticStandingOrdersWithoutFirstPaymentAmount_Amount = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Test123",
            
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                       "FirstPaymentAmount": {
                            "Currency": "GBP"
                      },                
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadDomesticStandingOrdersWithoutFirstPaymentAmount_Currency = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Test123",
            
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FirstPaymentAmount": {
                            "Amount":"100.00"
                      },              
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalPaymentWithoutCreditorAgentSchemeName = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount": {
                            "Amount": "165.88",
                            "Currency": "GBP"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.IBAN",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.IBAN",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      },
                      "CreditorAgent": {
                            "Identification": "30080012343456",
                            "PostalAddress": {
                                "AddressType": "Correspondence",
                                "Department": "department1",
                                "SubDepartment": "sub dept",
                                "StreetName": "Acacia Avenue",
                                "PostCode": "GU31 2ZZ",
                                "BuildingNumber": "27",
                                "TownName": "Sparsholt",
                                "CountrySubDivision": "Wessex",
                                "Country": "UK",
                                "AddressLine": [
                                    "Flat 7",
                                    "Acacia Lodge"
                                ]
                            }
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalSchedulePaymentWithoutCreditorAgentSchemeName = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    },
                    "CreditorAgent": {
                        "Identification": "30080012343456",
                        "PostalAddress": {
                            "AddressType": "Correspondence",
                            "Department": "department1",
                            "SubDepartment": "sub dept",
                            "StreetName": "Acacia Avenue",
                            "PostCode": "GU31 2ZZ",
                            "BuildingNumber": "27",
                            "TownName": "Sparsholt",
                            "CountrySubDivision": "Wessex",
                            "Country": "UK",
                            "AddressLine": [
                                "Flat 7",
                                "Acacia Lodge"
                            ]
                        }
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalStandingOrderWithoutCreditorAgentSchemeName = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "CreditorAgent": {
                        "Identification": "30080012343456",
                        "PostalAddress": {
                            "AddressType": "Correspondence",
                            "Department": "department1",
                            "SubDepartment": "sub dept",
                            "StreetName": "Acacia Avenue",
                            "PostCode": "GU31 2ZZ",
                            "BuildingNumber": "27",
                            "TownName": "Sparsholt",
                            "CountrySubDivision": "Wessex",
                            "Country": "UK",
                            "AddressLine": [
                                "Flat 7",
                                "Acacia Lodge"
                            ]
                        }
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String domesticPaymentWithoutMerchantCustomerIdentification = """
        { 
           "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                    "Currency":"GBP"
                 },
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 }
              }
           },
           "Risk":{ 
              "PaymentContextCode":"EcommerceGoods",
              "MerchantCategoryCode":"5967",
              "DeliveryAddress":{ 
                 "AddressLine":[ 
                    "Flat 7",
                    "Acacia Lodge"
                 ],
                 "StreetName":"Acacia Avenue",
                 "BuildingNumber":"27",
                 "PostCode":"GU31 2ZZ",
                 "TownName":"Sparsholt",
                 "CountrySubDivision": 
                    "Wessex"
                 ,
                 "Country":"UK"
              }
           }
        }
    """.stripIndent()


    String domesticScheduleWithoutMerchantCustomerIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Tom Kirkman"
                      }
                }
            },
            "Risk": {
                "PaymentContextCode": "EcommerceGoods",
                "MerchantCategoryCode": "1212",
                "DeliveryAddress": {
                      "AddressLine": ["Flat 7", "Acacia Lodge"],
                      "StreetName": "Acacia Avenue",
                      "BuildingNumber": "27",
                      "PostCode": "GU31 2ZZ",
                      "TownName": "town1",
                      "CountrySubDivision": "Wessex",
                      "Country": "UK"
                }
            }
        }
    """.stripIndent()

    String domesticStandingOrdersWithoutMerchantCustomerIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "Frequency": "EvryDay",
                      "Reference": "Pocket money for Damien",
                      "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                      "RecurringPaymentDateTime": "${TestUtil.getDateAndTime(3)}",
                      "FinalPaymentDateTime": "${TestUtil.getDateAndTime(10)}",
                      "FirstPaymentAmount": {
                            "Amount": "6.66",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Smith",
                            "SecondaryIdentification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Bob Clements",
                            "SecondaryIdentification": "30080012343456"
                      }
                }
            },
            "Risk": {
                "PaymentContextCode": "EcommerceGoods",
                "MerchantCategoryCode": "5967",
                "DeliveryAddress": {
                      "AddressLine": ["Flat 7"],
                      "StreetName": "Acacia Avenue",
                      "BuildingNumber": "27",
                      "PostCode": "GU31 2ZZ",
                      "TownName": "Sparsholt",
                      "CountrySubDivision": "Wessex",
                      "Country": "UK"
                }
            }
        }
    """.stripIndent()


    String internationalPaymentWithoutMerchantCustomerIdentification = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",
                      "InstructedAmount": {
                            "Amount": "165.88",
                            "Currency": "GBP"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "Name": "test user",
                            "SecondaryIdentification": "30080098763459"
                      },                   
                      "ExchangeRateInformation": {
                            "UnitCurrency": "GBP",
                            "RateType": "Actual"
                      }
                },
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                }
            },
            "Risk": {
                "PaymentContextCode": "EcommerceGoods",
                "MerchantCategoryCode": "1234",
                "DeliveryAddress": {
                      "AddressLine": ["string"],
                      "StreetName": "string",
                      "BuildingNumber": "string",
                      "PostCode": "string",
                      "TownName": "string",
                      "CountrySubDivision": "string",
                      "Country": "EU"
                }
            }
        }
    """.stripIndent()


    String internationalStandingOrderWithoutMerchantCustomerIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                }
            },
            "Risk": {
                "PaymentContextCode": "BillPayment",
                "MerchantCategoryCode": "1234",
                "DeliveryAddress": {
                    "StreetName": "string",
                    "CountrySubDivision": "string",
                    "AddressLine": ["string"],
                    "BuildingNumber": "string",
                    "TownName": "string",
                    "Country": "EU",
                    "PostCode": "10"
                }
            }
        }
    """.stripIndent()

    String internationalScheduleWithoutMerchantCustomerIdentification = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                "PaymentContextCode": "BillPayment",
                "MerchantCategoryCode": "1234",
                "DeliveryAddress": {
                    "AddressLine": ["string"],
                    "StreetName": "string",
                    "BuildingNumber": "string",
                    "PostCode": "string",
                    "TownName": "string",
                    "CountrySubDivision": "string",
                    "Country": "UK"
                }
            }
        }
    """.stripIndent()

    String domesticScheduleWithoutRequestExecutionDateTime = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Tom Kirkman"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()


    String internationalSchedulePaymentWithoutRequestExecutionDateTime = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()


    String validateExchangeRateInformationWithoutUnitCurrency = """
        {
            "Data": {
            "Initiation": {
              "InstructionIdentification": "ACME412",
              "EndToEndIdentification": "FRESCO.21302.GFX.20",
              "CurrencyOfTransfer":"GBP",
              "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "08080021325698",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
              },
              "RemittanceInformation": {
                "Reference": "FRESCO-101",
                "Unstructured": "Internal ops code 5120101"
              },
              "ExchangeRateInformation": {
                "RateType": "Actual",
                "ContractIdentification":"test"
              },
              "InstructedAmount": {
                   "Amount": "165.88",
                   "Currency": "GBP"
              }
            }  
            },
            "Risk": {
            "PaymentContextCode": "PartyToParty"
            }
        }
    """.stripIndent()


    String validateInternationalScheduleExchangeRateInformationWithoutUnitCurrency = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "CurrencyOfTransfer":"GBP",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    },        
                    "ExchangeRateInformation": {
                        "RateType": "Actual",
                        "ContractIdentification":"test"
                    }
                }
            },
            "Risk": {
                 
            }
        }
    """.stripIndent()

    String validateExchangeRateInformation_Without_RateType = """
        {
            "Data": {
            "Initiation": {
              "InstructionIdentification": "ACME412",
              "EndToEndIdentification": "FRESCO.21302.GFX.20",
              "CurrencyOfTransfer":"GBP",
              "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "08080021325698",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
              },
              "RemittanceInformation": {
                "Reference": "FRESCO-101",
                "Unstructured": "Internal ops code 5120101"
              },
              "ExchangeRateInformation": {
                "UnitCurrency": "GBP",
                "ContractIdentification":"test"
              },
              "InstructedAmount": {
                   "Amount": "165.88",
                   "Currency": "GBP"
              }
            }  
            },
            "Risk": {
            "PaymentContextCode": "PartyToParty"
            }
        }
    """.stripIndent()


    String validateInternationalSchedule_WithoutExchangeRateInformation_RateTypeParameter = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "CurrencyOfTransfer":"GBP",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    },        
                    "ExchangeRateInformation": {
                        "UnitCurrency": "GBP",
                        "ContractIdentification":"test"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String validateExchangeRateInformation_RateType = """
        {
            "Data": {
            "Initiation": {
              "InstructionIdentification": "ACME412",
              "EndToEndIdentification": "FRESCO.21302.GFX.20",
              "CurrencyOfTransfer":"GBP",
              "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "08080021325698",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
              },
              "ExchangeRateInformation": {
                "UnitCurrency": "GBP",
                "RateType": "Agreed"
              },
              "InstructedAmount": {
                   "Amount": "165.88",
                   "Currency": "GBP"
              }
            }  
          },
          "Risk": {
            "PaymentContextCode": "PartyToParty"
          }
        }
    """.stripIndent()

    String validateInternationalScheduleExchangeRateInformation_RateType = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "CurrencyOfTransfer":"GBP",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456",
                            "Name": "Andrea Frost"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Tom Kirkman"
                      },
                      "ExchangeRateInformation": {
                        "UnitCurrency": "GBP",
                        "RateType": "Agreed"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String validateInternationalPaymentWithoutCurrencyOfTransfer = """
        {
          "Data": {
            "Initiation": {
              "InstructionIdentification": "ACME412",
              "EndToEndIdentification": "FRESCO.21302.GFX.20",
              "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "08080021325698",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
              },
              "ExchangeRateInformation": {
                "UnitCurrency": "GBP",
                "RateType": "Actual"
              },
              "InstructedAmount": {
                "Amount": {
                   "Amount": "165.88",
                   "Currency": "GBP"
                 }
              }
            }  
          },
          "Risk": {
            "PaymentContextCode": "PartyToParty"
          }
        }
    """.stripIndent()

    String validateInternationalSchedulePaymentWithoutCurrencyOfTransfer = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "InstructedAmount": {
                        "Amount": "165.88",
                        "Currency": "USD"
                    },
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalStandingOrderWithoutCurrencyOfTransfer = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456",
                        "Name": "Andrea Frost"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String initiationPayloadDomesticWithoutInitiationPayload = """
        { 
           "Data":{ 
             
           },
           "Risk":{ 
              "PaymentContextCode":"EcommerceGoods",
              "MerchantCategoryCode":"5967",
              "MerchantCustomerIdentification":"053598653254",
              "DeliveryAddress":{ 
                 "AddressLine":[ 
                    "Flat 7",
                    "Acacia Lodge"
                 ],
                 "StreetName":"Acacia Avenue",
                 "BuildingNumber":"27",
                 "PostCode":"GU31 2ZZ",
                 "TownName":"Sparsholt",
                 "CountrySubDivision": 
                    "Wessex"
                 ,
                 "Country":"UK"
              }
           }
        }
    """.stripIndent()


    String initiationPayloadInternationalPaymentWithoutInitiationPayload = """
        {
            "Data": {
                
            },
            "Risk": {
                "PaymentContextCode": "BillPayment",
                "MerchantCategoryCode": "1234",
                "MerchantCustomerIdentification": "1234",
                "DeliveryAddress": {
                      "AddressLine": ["string"],
                      "StreetName": "string",
                      "BuildingNumber": "string",
                      "PostCode": "string",
                      "TownName": "string",
                      "CountrySubDivision": "string",
                      "Country": "EU"
                }
            }
        }
    """.stripIndent()


    String initiationPayloadDomesticScheduleWithoutInitiationPayload = """
        {
            "Data": {
                "Permission": "Create"        
            },
            "Risk": {
                "PaymentContextCode": "EcommerceGoods",
                "MerchantCategoryCode": "1212",
                "MerchantCustomerIdentification": "121212",
                "DeliveryAddress": {
                      "AddressLine": ["Flat 7", "Acacia Lodge"],
                      "StreetName": "Acacia Avenue",
                      "BuildingNumber": "27",
                      "PostCode": "GU31 2ZZ",
                      "TownName": "town1",
                      "CountrySubDivision": "Wessex",
                      "Country": "UK"
                }
            }
        }
    """.stripIndent()

    String initiationPayloadDomesticStandingOrdersWithoutInitiationPayload = """
        {
            "Data": {
                "Permission": "Create",
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                }     
            },
            "Risk": {
                "PaymentContextCode": "EcommerceGoods",
                "MerchantCategoryCode": "5967",
                "MerchantCustomerIdentification": "053598653254",
                "DeliveryAddress": {
                      "AddressLine": ["Flat 7"],
                      "StreetName": "Acacia Avenue",
                      "BuildingNumber": "27",
                      "PostCode": "GU31 2ZZ",
                      "TownName": "Sparsholt",
                      "CountrySubDivision": "Wessex",
                      "Country": "UK"
                }
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalScheduleWithoutInitiationPayload = """
        {
            "Data": {
                "Permission": "Create"
               
            },
            "Risk": {
                "PaymentContextCode": "BillPayment",
                "MerchantCategoryCode": "1234",
                "MerchantCustomerIdentification": "1234",
                "DeliveryAddress": {
                    "AddressLine": ["string"],
                    "StreetName": "string",
                    "BuildingNumber": "string",
                    "PostCode": "string",
                    "TownName": "string",
                    "CountrySubDivision": "string",
                    "Country": "UK"
                }
            }
        }
    """.stripIndent()

    String initiationPayloadInternationalStandingOrderWithoutInitiationPayload = """
        {
            "Data": {
                "Permission": "Create",
                "Authorisation": {
                    "AuthorisationType": "Any",
                    "CompletionDateTime": "${TestUtil.getDateAndTime(2)}"
                }
            },
            "Risk": {
                "PaymentContextCode": "BillPayment",
                "MerchantCategoryCode": "1234",
                "MerchantCustomerIdentification": "1234",
                "DeliveryAddress": {
                    "StreetName": "string",
                    "CountrySubDivision": "string",
                    "AddressLine": ["string"],
                    "BuildingNumber": "string",
                    "TownName": "string",
                    "Country": "EU",
                    "PostCode": "10"
                }
            }
        }
    """.stripIndent()

    //Without Debtor Name
    String validateDomesticPayDebtorWithoutName= """
        { 
           "Data":{ 
              "Initiation":{ 
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{ 
                    "Amount":"${ConnectorTestConstants.INSTRUCTED_AMOUNT}",
                    "Currency":"GBP"
                 },
                 "DebtorAccount": {
                   "SchemeName":"UK.OBIE.PAN",
                   "Identification":"08080021325697",
                   "SecondaryIdentification":"0002"
                 },
                 "CreditorAccount":{ 
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 },
                 "RemittanceInformation":{ 
                    "Reference":"FRESCO-101",
                    "Unstructured":"Internal ops code 5120101"
                 }
              }
           },
           "Risk":{ 
              
           }
        }
    """.stripIndent()

    String domesticScheduleDebtorAccountWithoutName = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(3)}",
                      "InstructedAmount": {
                            "Amount": "200.00",
                            "Currency": "GBP"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "30080012343456"
                      },
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "08080021325698",
                            "Name": "Tom Kirkman"
                      },
                      "RemittanceInformation": {
                            "Reference": "DSR-037",
                            "Unstructured": "Internal ops code 5120103"
                      }
                }
            },
            "Risk": {
                
            }
        }
    """.stripIndent()

    String validateDomesticStandingOrderDebtorWithoutName = """
        {
          "Data": {
            "Permission": "Create",
            "Initiation": {
              "Frequency": "EvryDay",
              "Reference": "Pocket money for Damien",
              "FirstPaymentDateTime": "2025-06-06T06:06:06+00:00",
              "FinalPaymentDateTime": "2025-12-06T06:06:06+00:00",
              "FirstPaymentAmount": {
                "Amount": "6.66",
                "Currency": "GBP"
              },
              "DebtorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "11280001234567"
              },
              "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "08080021325698",
                "Name": "Bob Clements"
              }
            }
          },
          "Risk": {
            "PaymentContextCode": "PartyToParty"
          }
        }
    """.stripIndent()

    String validateInternationalPaymentDebtorWithoutName = """
        {
            "Data": {
                "Initiation": {
                      "InstructionIdentification": "ACME412",
                      "EndToEndIdentification": "FRESCO.21302.GFX.20",
                      "InstructionPriority": "Normal",
                      "CurrencyOfTransfer": "USD",        
                      "CreditorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                            "Name": "ACME Inc",
                            "SecondaryIdentification": "0002"
                      },
                      "DebtorAccount": {
                            "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                            "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                            "SecondaryIdentification": "30080098763459"
                      },
                       "InstructedAmount": {
                          "Amount": "200.00",
                          "Currency": "GBP"
                      },
                      "RemittanceInformation": {
                            "Reference": "FRESCO-101",
                            "Unstructured": "Internal ops code 5120101"
                      },
                      "ExchangeRateInformation": {
                            "UnitCurrency": "GBP",
                            "RateType": "Actual"
                      }
                },
                "Authorisation": {
                      "AuthorisationType": "Any",
                      "CompletionDateTime": "${DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)}"
                }
            },
            "Risk": {
            
            }
        }
    """.stripIndent()

    String validateInternationalSchedulePaymentDebtorWithoutName = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "InstructionIdentification": "ACME412",
                    "EndToEndIdentification": "FRESCO.21302.GFX.20",
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "InstructionPriority": "Normal",
                    "CurrencyOfTransfer": "USD",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.CREDITOR_ACCOUNT_ID}",
                        "Name": "ACME Inc",
                        "SecondaryIdentification": "0002"
                    },
                    "ExchangeRateInformation": {
                        "UnitCurrency": "GBP",
                        "RateType": "Actual"
                    },
                     "InstructedAmount": {
                       "Amount": "200.00",
                       "Currency": "GBP"
                    },
                    "RemittanceInformation": {
                        "Reference": "FRESCO-101",
                        "Unstructured": "Internal ops code 5120101"
                    }
                },
                "Authorisation": {
                    "AuthorisationType": "Any",
                    "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
                }
            },
            "Risk": {
                   
            }
        }
    """.stripIndent()

    String validateInternationalStandingOrderDebtorWithoutName = """
        {
            "Data": {
                "Permission": "Create",
                "Initiation": {
                    "Frequency": "EvryWorkgDay",
                    "FirstPaymentDateTime": "${TestUtil.getDateAndTime(1)}",
                    "FinalPaymentDateTime": "${TestUtil.getDateAndTime(5)}",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "30080012343456"
                    },
                    "CreditorAccount": {
                        "SchemeName": "UK.OBIE.IBAN",
                        "Identification": "08080021325698",
                        "Name": "Tom Kirkman"
                    },
                    "InstructedAmount": {
                        "Amount": "20.12",
                        "Currency": "EUR"
                    },
                    "CurrencyOfTransfer": "EUR"
                },
                "Authorisation": {
                    "AuthorisationType": "Any",
                    "CompletionDateTime": "${TestUtil.getDateAndTime(2)}"
                }
            },
            "Risk": {
                 
            }
        }
    """.stripIndent()

    String getInitiationPayloadOfVersion2() {
        String initiationPayload = """{
        "Data": {
            "Initiation": {
            "InstructionIdentification": "ANSM023",
            "EndToEndIdentification": "FRESCO.21302.GFX.37",
            "InstructedAmount": {
                "Amount": "20.00",
                "Currency": "GBP"
                },
            "DebtorAccount": {
                "SchemeName": "SortCodeAccountNumber",
                "Identification": "30080012343456",
                "Name": "Andrea Smith"
                },
            "CreditorAccount": {
                "SchemeName": "SortCodeAccountNumber",
                "Identification": "08080021325698",
                "Name": "Bob Clements"
                },
            "RemittanceInformation": {
                "Reference": "FRESCO-037",
                "Unstructured": "Internal ops code 5120103"
                }
            }
        },
        "Risk": {
            
            }
        }
        """.stripIndent()

        return initiationPayload
    }

    String validateDomesticInitiationWithSupplementaryField = """
        {
           "Data":{
              "Initiation":{
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructedAmount":{
                    "Amount":"165.88",
                    "Currency":"GBP"
                 },
                 "CreditorAccount":{
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 },
                 "RemittanceInformation":{
                    "Reference":"FRESCO-101",
                    "Unstructured":"Internal ops code 5120101"
                 },
                 "SupplementaryData":{
                    "DebtorReference":"PaymentForGoods"
                 }
              }
           },
           "Risk":{
              "PaymentContextCode":"EcommerceGoods",
              "MerchantCategoryCode":"5967",
              "MerchantCustomerIdentification":"053598653254",
              "DeliveryAddress":{
                 "AddressLine":[
                    "Flat 7",
                    "Acacia Lodge"
                 ],
                 "StreetName":"Acacia Avenue",
                 "BuildingNumber":"27",
                 "PostCode":"GU31 2ZZ",
                 "TownName":"Sparsholt",
                 "CountrySubDivision":
                    "Wessex"
                 ,
                 "Country":"UK"
              }
           }
        }
    """.stripIndent()

    String validateDomesticScheduleInitiationWithSupplementaryField = """
        {
           "Data":{
              "Permission":"Create",
              "Initiation":{
                 "InstructionIdentification":"ACME412",
                 "RequestedExecutionDateTime":"${TestUtil.getDateAndTime(5)}",
                 "InstructedAmount":{
                    "Amount":"200.00",
                    "Currency":"GBP"
                 },
                 "DebtorAccount":{
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"11280001234567",
                    "Name":"Andrea Frost"
                 },
                 "CreditorAccount":{
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"Tom Kirkman"
                 },
                 "RemittanceInformation":{
                    "Reference":"DSR-037",
                    "Unstructured":"Internal ops code 5120103"
                 },
                 "SupplementaryData":{
                    "DebtorReference":"PaymentForGoods"
                 }
              }
           },
           "Risk":{
              "PaymentContextCode":"PartyToParty"
           }
        }
    """.stripIndent()

    String validateDomesticStandingOrderInitiationWithSupplementaryField = """
        {
           "Data":{
              "Permission":"Create",
              "Initiation":{
                 "Frequency":"EvryDay",
                 "Reference":"Pocket money for Damien",
                 "FirstPaymentDateTime":"${TestUtil.getDateAndTime(1)}",
                 "FirstPaymentAmount":{
                    "Amount":"6.66",
                    "Currency":"GBP"
                 },
                 "RecurringPaymentAmount":{
                    "Amount":"7.00",
                    "Currency":"GBP"
                 },
                 "FinalPaymentDateTime":"${TestUtil.getDateAndTime(5)}",
                 "FinalPaymentAmount":{
                    "Amount":"7.00",
                    "Currency":"GBP"
                 },
                 "DebtorAccount":{
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"11280001234567",
                    "Name":"Andrea Smith"
                 },
                 "CreditorAccount":{
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"Bob Clements"
                 },
                 "SupplementaryData":{
                    "DebtorReference":"PaymentForGoods"
                 }
              }
           },
           "Risk":{
              "PaymentContextCode":"PartyToParty"
           }
        }
    """.stripIndent()

    String validateInternationalPaymentInitiationWithSupplementaryField = """
        {
           "Data":{
              "Initiation":{
                 "InstructionIdentification":"ACME412",
                 "EndToEndIdentification":"FRESCO.21302.GFX.20",
                 "InstructionPriority":"Normal",
                 "CurrencyOfTransfer":"USD",
                 "InstructedAmount":{
                    "Amount":"165.88",
                    "Currency":"GBP"
                 },
                 "CreditorAccount":{
                    "SchemeName":"UK.OBIE.SortCodeAccountNumber",
                    "Identification":"08080021325698",
                    "Name":"ACME Inc",
                    "SecondaryIdentification":"0002"
                 },
                 "RemittanceInformation":{
                    "Reference":"FRESCO-101",
                    "Unstructured":"Internal ops code 5120101"
                 },
                 "ExchangeRateInformation":{
                    "UnitCurrency":"GBP",
                    "RateType":"Actual"
                 },
                 "SupplementaryData":{
                    "DebtorReference":"PaymentForGoods"
                 }
              }
           },
           "Risk":{
              "PaymentContextCode":"PartyToParty"
           }
        }
    """.stripIndent()

    String validateInternationalSchedulePaymentInitiationWithSupplementaryField = """
        {
          "Data": {
            "Permission":"Create",
            "Initiation": {
              "InstructionIdentification": "ACME412",
              "EndToEndIdentification": "FRESCO.21302.GFX.20",
              "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
              "InstructedAmount": {
                 "Amount": "165.88",
                 "Currency": "USD"
              },
              "CurrencyOfTransfer":"USD",
              "CreditorAccount": {
                "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                "Identification": "08080021325698",
                "Name": "ACME Inc",
                "SecondaryIdentification": "0002"
              },
              "RemittanceInformation": {
                "Reference": "FRESCO-101",
                "Unstructured": "Internal ops code 5120101"
              },
              "ExchangeRateInformation": {
                "UnitCurrency": "GBP",
                "RateType": "Actual"
              },
              "SupplementaryData":{
                "DebtorReference":"PaymentForGoods"
              }
            }
          },
          "Risk": {
            "PaymentContextCode": "PartyToParty"
          }
        }
    """.stripIndent()

    String validateInternationalStandingOrderInitiationWithSupplementaryField = """
        {
           "Data":{
              "Permission":"Create",
              "Initiation":{
                 "Frequency":"EvryWorkgDay",
                 "FirstPaymentDateTime":"${TestUtil.getDateAndTime(1)}",
                 "FinalPaymentDateTime":"${TestUtil.getDateAndTime(5)}",
                 "CreditorAccount":{
                    "SchemeName":"UK.OBIE.IBAN",
                    "Identification":"08080021325698",
                    "Name":"Tom Kirkman"
                 },
                 "SupplementaryData":{
                    "DebtorReference":"PaymentForGoods"
                 },
                 "InstructedAmount":{
                    "Amount":"20.12",
                    "Currency":"EUR"
                 },
                 "CurrencyOfTransfer":"EUR"
              },
              "Authorisation":{
                 "AuthorisationType":"Any",
                 "CompletionDateTime":"${TestUtil.getDateAndTime(2)}"
              }
           },
           "Risk":{
              "PaymentContextCode":"BillPayment",
              "MerchantCategoryCode":"1234",
              "MerchantCustomerIdentification":"1234",
              "DeliveryAddress":{
                 "StreetName":"string",
                 "CountrySubDivision":
                    "string"
                 ,
                 "AddressLine":[
                    "string"
                 ],
                 "BuildingNumber":"string",
                 "TownName":"string",
                 "Country":"EU",
                 "PostCode":"10"
              }
           }
        }
    """.stripIndent()

    String domesticStandingOrderPaymentWithoutFinalPayment(def parameterMap)  {

        String initiationPayloadDomesticStandingOrder = """
            {
                "Data": {
                    "Permission": "${this.getParameterValue("Permission", parameterMap)}",
                    "Initiation": {
                          "Frequency": "${this.getParameterValue("Frequency", parameterMap)}",
                          "NumberOfPayments" : "${this.getParameterValue("NumberOfPayments", parameterMap)}",
                          "FirstPaymentDateTime": "${this.getParameterValue("FirstPaymentDateTime", parameterMap)}",
                          "FirstPaymentAmount": {
                                "Amount": "${this.getParameterValue("FirstPaymentAmount", parameterMap)}",
                                "Currency": "${this.getParameterValue("FirstPaymentCurrency", parameterMap)}"
                          },
                          "CreditorAccount": {
                                "SchemeName": "${this.getParameterValue("CreditorSchemeName", parameterMap)}",
                                "Identification": "${this.getParameterValue("CreditorIdentification", parameterMap)}",
                                "Name": "${this.getParameterValue("CreditorName", parameterMap)}"
                          }
                    }
                },
                "Risk": {
                    
                }
            }
        """.stripIndent()

        return initiationPayloadDomesticStandingOrder

    }

    String jwsHeaderWithUnsupportedClaims = """
        {
            "alg": "PS256",
            "kid": "_Lm7TUV4_2KwruhIC6TY7mz_6Y412XZlnxtyyApzxL8",
            "crit": [
                "b64",
                "http://openbanking.org.uk/iat",
                "http://openbanking.org.uk/tan",
                "http://openbanking.org.uk/iss",
                "http://openbanking.org.uk/invalid"
            ],
            "b64": true,
            "http://openbanking.org.uk/iat": 1501497671,
            "http://openbanking.org.uk/tan": "openbanking.org.uk",
            "http://openbanking.org.uk/iss": "456/def"
            "http://openbanking.org.uk/invalid": "invalid"
        }
    """.stripIndent()

    String getParameterValue(String parameter, def parameterMap) {

        if (parameterMap.containsKey(parameter)) {
            return parameterMap.get(parameter)
        } else {
            if (parameter == "RequestedExecutionDateTime") {
                return TestUtil.getDateAndTime(5)
            } else if (parameter == "FirstPaymentDateTime") {
                return TestUtil.getDateAndTime(1)
            } else if (parameter == "RecurringPaymentDateTime") {
                return TestUtil.getDateAndTime(3)
            } else if(parameter == "FinalPaymentDateTime") {
                return TestUtil.getDateAndTime(5)
            } else if (parameter == "CompletionDateTime") {
                return DateTimeFormatter.ISO_INSTANT.format(ConnectorTestConstants.DATE_TIME)
            } else {
                return this.defaultValueMap[parameter]
            }

        }

    }

    public String initiationPayloadFilePayment = """
    {
        "Data": {
            "Initiation": {
                "FileType": "UK.OBIE.pain.001.001.08",
                "FileHash": "${TestUtil.generateFileHash("/FilePayments/file.xml")}",    
                "FileReference": "test",
                "NumberOfTransactions": "2",
                "ControlSum": 70,
                "RequestedExecutionDateTime": "2026-10-01T08:41:10.136Z",
                "LocalInstrument": "UK.OBIE.BACS",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                    "Name": "test user",
                    "SecondaryIdentification": "30080098763459"
                },
                "RemittanceInformation": {
                    "Reference": "FRESCO-101",
                    "Unstructured": "Internal ops code 5120101"
                }
            },
            "Authorisation": {
                "AuthorisationType": "Any",
                "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
            }
        }
    }
""".stripIndent()

    public String filePaymentInitPayloadWithNoFileType = """
         {
            "Data": {
                "Initiation": {
                    "FileHash": "${TestUtil.generateFileHash("/FilePayments/file.xml")}",
                    "FileReference": "test",
                    "NumberOfTransactions": "10",
                    "ControlSum": 10,
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "RemittanceInformation": {
                        "Reference": "FRESCO-101",
                        "Unstructured": "Internal ops code 5120101"
                    }
                },
                "Authorisation": {
                    "AuthorisationType": "Any",
                    "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
                }
            }
        }
        """.stripIndent()

    public String filePaymentInitPayloadWithNoFileHash = """
         {
            "Data": {
                "Initiation": {
                    "FileType": "UK.OBIE.PaymentInitiation.3.1",
                    "FileReference": "test",
                    "NumberOfTransactions": "10",
                    "ControlSum": 10,
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "RemittanceInformation": {
                        "Reference": "FRESCO-101",
                        "Unstructured": "Internal ops code 5120101"
                    }
                },
                "Authorisation": {
                    "AuthorisationType": "Any",
                    "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
                }
            }
        }
        """.stripIndent()

    public String filePaymentInitPayloadWithInvalidFileType = """
         {
            "Data": {
                "Initiation": {
                    "FileHash": "${TestUtil.generateFileHash("/FilePayments/file.xml")}",
                    "FileType": "abcd",
                    "FileReference": "test",
                    "NumberOfTransactions": "10",
                    "ControlSum": 10,
                    "RequestedExecutionDateTime": "${TestUtil.getDateAndTime(5)}",
                    "LocalInstrument": "UK.OBIE.BACS",
                    "DebtorAccount": {
                        "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                        "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                        "Name": "test user",
                        "SecondaryIdentification": "30080098763459"
                    },
                    "RemittanceInformation": {
                        "Reference": "FRESCO-101",
                        "Unstructured": "Internal ops code 5120101"
                    }
                },
                "Authorisation": {
                    "AuthorisationType": "Any",
                    "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
                }
            }
        }
        """.stripIndent()

    public String initiationPayloadFilePaymentWithInvalidExecutionDate = """
    {
        "Data": {
            "Initiation": {
                "FileType": "UK.OBIE.pain.001.001.08",
                "FileHash": "${TestUtil.generateFileHash("/FilePayments/file.xml")}",    
                "FileReference": "test",
                "NumberOfTransactions": "10",
                "ControlSum": 10,
                "RequestedExecutionDateTime": "${TestUtil.getPastDateAndTime(5)}",
                "LocalInstrument": "UK.OBIE.BACS",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                    "Name": "test user",
                    "SecondaryIdentification": "30080098763459"
                },
                "RemittanceInformation": {
                    "Reference": "FRESCO-101",
                    "Unstructured": "Internal ops code 5120101"
                }
            },
            "Authorisation": {
                "AuthorisationType": "Any",
                "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
            }
        }
    }
""".stripIndent()

    public String initiationPayloadFilePaymentWithInvalidFileHash = """
    {
        "Data": {
            "Initiation": {
                "FileType": "UK.OBIE.pain.001.001.08",
                "FileHash": "ugwuEGGUUDGVUGGHUGRgejguaurtus7uhtugftuggupgrpughu",    
                "FileReference": "test",
                "NumberOfTransactions": "2",
                "ControlSum": 70,
                "RequestedExecutionDateTime": "2023-10-01T08:41:10.136Z",
                "LocalInstrument": "UK.OBIE.BACS",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                    "Name": "test user",
                    "SecondaryIdentification": "30080098763459"
                },
                "RemittanceInformation": {
                    "Reference": "FRESCO-101",
                    "Unstructured": "Internal ops code 5120101"
                }
            },
            "Authorisation": {
                "AuthorisationType": "Any",
                "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
            }
        }
    }
""".stripIndent()

    public String initiationPayloadFilePaymentWithInvalidFileReference = """
    {
        "Data": {
            "Initiation": {
                "FileType": "UK.OBIE.pain.001.001.08",
                "FileHash": "${TestUtil.generateFileHash("/FilePayments/file.xml")}",    
                "FileReference": "ugwuEGGUUDGVUGGHUGRfgejguaurtus7uhtugtuggupgrpughu",
                "NumberOfTransactions": "2",
                "ControlSum": 70,
                "RequestedExecutionDateTime": "2023-10-01T08:41:10.136Z",
                "LocalInstrument": "UK.OBIE.BACS",
                "DebtorAccount": {
                    "SchemeName": "UK.OBIE.SortCodeAccountNumber",
                    "Identification": "${ConnectorTestConstants.DEBTOR_ACCOUNT_ID}",
                    "Name": "test user",
                    "SecondaryIdentification": "30080098763459"
                },
                "RemittanceInformation": {
                    "Reference": "FRESCO-101",
                    "Unstructured": "Internal ops code 5120101"
                }
            },
            "Authorisation": {
                "AuthorisationType": "Any",
                "CompletionDateTime": "${TestUtil.getDateAndTime(6)}"
            }
        }
    }
""".stripIndent()

    /**
     * Build Validation Payload
     * @param clientId - Client Id
     * @param userId - User Id
     * @param consentId - Consent Id
     * @param requestUri - Request url
     * @return
     */
    static String buildPaymentValidationPayload(String accessToken, String userId, String consentId,
                                                String clientId = configuration.getAppInfoClientID(),
                                                String requestUri = "/domestic-payments") {

        String initiationPayload = """{
            "headers": {
                "Authorization": "Basic ${accessToken}",
                "x-request-id": "1b91e649-3d06-4e16-ada7-bf5af2136b44",
                "consent-id": "${consentId}",
                "activityid": "8666aa84-fc5a-425e-91c9-37fa30a95784",
                "Cache-Control": "no-cache",
                "Connection": "keep-alive",
                "User-Agent": "PostmanRuntime/7.28.4",
                "Host": "localhost:8243",
                "Postman-Token": "244d15b6-eb18-4045-ba87-8ee6c830b84c",
                "Accept-Encoding": "gzip, deflate, br",
                "accept": "application/json; charset=utf-8"
                },
                "consentId": "${consentId}",
                "resourceParams": {
                    "resource": "/pisp/domestic-payments",
                    "context": "/open-banking/v3.1/pisp",
                    "httpMethod": "GET"
                },
                "userId": "${userId}",
                "electedResource": "${requestUri}",
                "clientId": "${clientId}",
                "body": ${getSubmissionPaymentPayload(consentId)}
            }
            """.stripIndent()
        return initiationPayload
    }

    static String getSubmissionPaymentPayload(String consentID) {
        return """{
			"Data": {
				"ConsentId": "${consentID}",
				"ReadRefundAccount": "Yes",
				"Initiation": {
					"InstructionIdentification": "ACME412",
					"EndToEndIdentification": "FRESCO.21302.GFX.20",
					"InstructedAmount": {
						"Amount": "165.88",
						"Currency": "GBP"
					},
					"CreditorAccount": {
						"SchemeName": "OB.SortCodeAccountNumber",
						"Identification": "08080021325698",
						"Name": "ACME Inc",
						"SecondaryIdentification": "0002"
					},
					"RemittanceInformation": {
						"Reference": "FRESCO-101",
						"Unstructured": "Internal ops code 5120101"
					}
				}
			},
			"Risk": {
				"PaymentContextCode": "EcommerceMerchantInitiatedPayment"
			}
		}
		""".stripIndent()
    }
}
