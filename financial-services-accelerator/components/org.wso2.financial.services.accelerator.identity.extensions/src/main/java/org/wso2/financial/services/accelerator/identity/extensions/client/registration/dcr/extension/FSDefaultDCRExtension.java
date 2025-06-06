/**
 * Copyright (c) 2025, WSO2 LLC. (https://www.wso2.com).
 * <p>
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *     http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.financial.services.accelerator.identity.extensions.client.registration.dcr.extension;

import org.json.JSONObject;
import org.wso2.financial.services.accelerator.common.exception.FinancialServicesException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class for extending methods to be invoked by FS Additional Attribute Filter.
 * These methods can be used to validate the DCR request.
 */
public class FSDefaultDCRExtension extends FSAbstractDCRExtension {

    @Override
    public Map<String, Object> validateDCRRegisterAttributes(JSONObject appRegistrationRequest,
                                                      Map<String, Object> ssaClaims)
            throws FinancialServicesException {
        return new HashMap<>();
    }

    @Override
    public Map<String, Object> validateDCRUpdateAttributes(JSONObject applicationUpdateRequest,
                                                      Map<String, Object> ssaClaims, List<JSONObject> spProperties)
            throws FinancialServicesException {
        return new HashMap<>();
    }
}
