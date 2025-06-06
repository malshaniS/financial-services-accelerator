/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
 * <p>
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.financial.services.accelerator.consent.mgt.extensions.authorize.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wso2.financial.services.accelerator.common.config.FinancialServicesConfigParser;
import org.wso2.financial.services.accelerator.common.exception.ConsentManagementException;
import org.wso2.financial.services.accelerator.consent.mgt.dao.models.ConsentResource;
import org.wso2.financial.services.accelerator.consent.mgt.dao.models.DetailedConsentResource;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.authorize.ConsentPersistStep;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.authorize.model.ConsentData;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.authorize.model.ConsentPersistData;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.authorize.util.ConsentAuthorizeConstants;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.common.AuthErrorCode;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.common.ConsentException;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.common.ConsentExtensionConstants;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.common.ResponseStatus;
import org.wso2.financial.services.accelerator.consent.mgt.extensions.internal.ConsentExtensionsDataHolder;
import org.wso2.financial.services.accelerator.consent.mgt.service.ConsentCoreService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Consent persist step default implementation.
 */
public class DefaultConsentPersistStep implements ConsentPersistStep {

    private final boolean isPreInitiatedConsent;
    private static final Log log = LogFactory.getLog(DefaultConsentPersistStep.class);

    public DefaultConsentPersistStep() {

        FinancialServicesConfigParser configParser = FinancialServicesConfigParser.getInstance();
        isPreInitiatedConsent = configParser.isPreInitiatedConsent();
    }

    @Override
    public void execute(ConsentPersistData consentPersistData) throws ConsentException {

        try {
            ConsentData consentData = consentPersistData.getConsentData();
            ConsentResource consentResource;

            if (consentData == null) {
                log.error("Consent data is not available");
                throw new ConsentException(ResponseStatus.BAD_REQUEST, AuthErrorCode.SERVER_ERROR.name(),
                        "Consent data is not available");
            }

            if (isPreInitiatedConsent && consentData.getConsentId() == null) {
                log.error("Consent ID not available in consent data");
                throw new ConsentException(consentData.getRedirectURI(), AuthErrorCode.SERVER_ERROR,
                        "Consent ID not available in consent data", consentData.getState());
            }

            if (consentData.getConsentResource() == null) {
                consentResource = ConsentExtensionsDataHolder.getInstance().getConsentCoreService()
                        .getConsent(consentData.getConsentId(), false);
            } else {
                consentResource = consentData.getConsentResource();
            }

            if (isPreInitiatedConsent && consentData.getAuthResource() == null) {
                log.error("Auth resource not available in consent data");
                throw new ConsentException(consentData.getRedirectURI(), AuthErrorCode.SERVER_ERROR,
                        "Auth resource not available in consent data", consentData.getState());
            }

            consentPersist(consentPersistData, consentResource);

        } catch (ConsentManagementException e) {
            throw new ConsentException(ResponseStatus.INTERNAL_SERVER_ERROR,
                    "Exception occurred while persisting consent");
        }
    }

    public void consentPersist(ConsentPersistData consentPersistData, ConsentResource consentResource)
            throws ConsentManagementException {

        ConsentData consentData = consentPersistData.getConsentData();
        boolean isApproved = consentPersistData.getApproval();
        JSONObject payload = consentPersistData.getPayload();
        String userId = consentPersistData.getConsentData().getUserId();
        String authorizationId;
        String consentStatus;
        String authStatus;

        consentStatus = isApproved ? ConsentExtensionConstants.AUTHORIZED_STATUS :
                ConsentExtensionConstants.REJECTED_STATUS;
        authStatus = isApproved ? ConsentExtensionConstants.AUTHORIZED_STATUS :
                ConsentExtensionConstants.REJECTED_STATUS;

        ConsentCoreService consentCoreService = ConsentExtensionsDataHolder.getInstance().getConsentCoreService();

        // Create the consent if it is not pre initiated.
        if (isPreInitiatedConsent) {
            authorizationId = consentData.getAuthResource().getAuthorizationID();
        } else {
            DetailedConsentResource createdConsent = consentCoreService.createAuthorizableConsent(
                    consentResource, userId, authStatus, ConsentExtensionConstants.DEFAULT_AUTH_TYPE, true);
            String consentId = createdConsent.getConsentID();
            authorizationId = consentCoreService.searchAuthorizations(consentId).get(0).getAuthorizationID();
            // Getting commonAuthId to add as a consent attribute. This is to find the consent in later stages.
            if (consentPersistData.getBrowserCookies() != null) {
                String commonAuthId = consentPersistData.getBrowserCookies().get(
                        ConsentExtensionConstants.COMMON_AUTH_ID);
                Map<String, String> consentAttributes = new HashMap<>();
                consentAttributes.put(ConsentExtensionConstants.COMMON_AUTH_ID, commonAuthId);
                consentCoreService.storeConsentAttributes(consentId, consentAttributes);
            }
        }
        consentCoreService.bindUserAccountsToConsent(consentResource, consentData.getUserId(), authorizationId,
                getConsentedAccounts(payload, isApproved), authStatus, consentStatus);
    }

    /**
     * Retrieve account ID consented by the user from the object.
     *
     * @param persistPayload payload to persist
     * @return Account data map
     */
    private static Map<String, ArrayList<String>> getConsentedAccounts(JSONObject persistPayload, boolean isApproved) {

        Map<String, ArrayList<String>> accountIDsMapWithPermissions = new HashMap<>();
        ArrayList<String> permissionsDefault = new ArrayList<>();
        permissionsDefault.add(ConsentExtensionConstants.PRIMARY);

        //Check whether payment account exists
        //Payment Account is the debtor account sent in the payload
        if (persistPayload.has(ConsentExtensionConstants.PAYMENT_ACCOUNT) &&
                StringUtils.isNotBlank(persistPayload.getString(ConsentExtensionConstants.PAYMENT_ACCOUNT))) {
            //Check whether account Id is in String format
            if (!(persistPayload.get(ConsentExtensionConstants.PAYMENT_ACCOUNT) instanceof String)) {
                log.error(ConsentAuthorizeConstants.ACCOUNT_ID_NOT_FOUND_ERROR);
                throw new ConsentException(ResponseStatus.BAD_REQUEST,
                        ConsentAuthorizeConstants.ACCOUNT_ID_NOT_FOUND_ERROR);
            }

            String paymentAccount = persistPayload.getString(ConsentExtensionConstants.PAYMENT_ACCOUNT);
            accountIDsMapWithPermissions.put(paymentAccount, permissionsDefault);
        } else if (persistPayload.has(ConsentExtensionConstants.COF_ACCOUNT) &&
                StringUtils.isNotBlank(persistPayload.getString(ConsentExtensionConstants.COF_ACCOUNT))) {
            //Check whether account Id is in String format
            if (!(persistPayload.get(ConsentExtensionConstants.COF_ACCOUNT) instanceof String)) {
                log.error(ConsentAuthorizeConstants.ACCOUNT_ID_NOT_FOUND_ERROR);
                throw new ConsentException(ResponseStatus.BAD_REQUEST,
                        ConsentAuthorizeConstants.ACCOUNT_ID_NOT_FOUND_ERROR);
            }

            String paymentAccount = persistPayload.getString(ConsentExtensionConstants.COF_ACCOUNT);
            accountIDsMapWithPermissions.put(paymentAccount, permissionsDefault);
        } else {
            //Check whether account Ids are in array format
            if (!(persistPayload.get(ConsentExtensionConstants.ACCOUNT_IDS) instanceof JSONArray)) {
                log.error(ConsentAuthorizeConstants.ACCOUNT_ID_NOT_FOUND_ERROR);
                throw new ConsentException(ResponseStatus.BAD_REQUEST,
                        ConsentAuthorizeConstants.ACCOUNT_ID_NOT_FOUND_ERROR);
            }

            //Check whether account Ids are strings
            JSONArray accountIds = persistPayload.getJSONArray(ConsentExtensionConstants.ACCOUNT_IDS);
            for (Object account : accountIds) {
                if (!(account instanceof String)) {
                    log.error(ConsentAuthorizeConstants.ACCOUNT_ID_FORMAT_ERROR);
                    throw new ConsentException(ResponseStatus.BAD_REQUEST,
                            ConsentAuthorizeConstants.ACCOUNT_ID_FORMAT_ERROR);
                }
                if (((String) account).isEmpty()) {
                    if (isApproved) {
                        log.error(ConsentAuthorizeConstants.ACCOUNT_ID_NOT_FOUND_ERROR);
                        throw new ConsentException(ResponseStatus.BAD_REQUEST,
                                ConsentAuthorizeConstants.ACCOUNT_ID_NOT_FOUND_ERROR);
                    } else {
                        account = "n/a";
                    }
                }
                accountIDsMapWithPermissions.put((String) account, permissionsDefault);
            }
        }
        return accountIDsMapWithPermissions;
    }
}
