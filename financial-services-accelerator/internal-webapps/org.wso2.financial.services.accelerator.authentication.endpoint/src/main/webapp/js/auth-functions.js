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


// Update the selected account list according to the selected checkbox values.
function updateAcc() {
    var accIds = "";
    var accNames = "";
    //Get values from checked checkboxes
    $("input:checkbox[name=chkAccounts]:checked").each(function(){
        accIds =  accIds.concat(":", $(this).val());
        accNames =  accNames.concat(":", $(this).attr("id"));
    });
    accIds = accIds.replace(/^\:/, '');
    accNames = accNames.replace(/^\:/, '');
    document.getElementById('account').value = accIds;
    document.getElementById('accountName').value = accNames;
}

function deny() {
    document.getElementById('consent').value = false;
    document.getElementById("oauth2_authz_confirm").submit();
}

// Confirm sharing data
function approvedConsent() {
    document.getElementById('consent').value = true;
    validateFrm();
}

// Submit data sharing from
function validateFrm() {
    if (document.getElementById('type').value === "accounts") {
        document.getElementById("oauth2_authz_confirm").submit();
    }

    if (document.getElementById('type').value === "payments") {
        if (document.getElementById('selectedAccount').value === "" ||
            document.getElementById('selectedAccount').value === "default") {
            $(".acc-err").show();
            return false;
        } else {
            document.getElementById('paymentAccount').value =
                document.getElementById('selectedAccount').value;
            document.getElementById("oauth2_authz_confirm").submit();
        }
    }

    if (document.getElementById('type').value === "fundsconfirmations") {
        document.getElementById("oauth2_authz_confirm").submit();
    }

    if (document.getElementById('type').value === "default") {
            document.getElementById("oauth2_authz_confirm").submit();
    }

}

function approvedDefaultClaim() {
    var mandatoryClaimCBs = $(".mandatory-claim");
    var checkedMandatoryClaimCBs = $(".mandatory-claim:checked");
    var scopeApproval = $("input[name='scope-approval']");

    // If scope approval radio button is rendered then we need to validate that it's checked
    if (scopeApproval.length > 0) {
        if (scopeApproval.is(":checked")) {
            var checkScopeConsent = $("input[name='scope-approval']:checked");
            $('#consent').val(checkScopeConsent.val());
        } else {
            $("#modal_scope_validation").modal();
            return;
        }
    } else {
        // Scope radio button was not rendered therefore set the consent to 'approve'
        document.getElementById('consent').value = "approve";
    }

    if (checkedMandatoryClaimCBs.length === mandatoryClaimCBs.length) {
        document.getElementById("profile").submit();
    } else {
        $("#modal_claim_validation").modal();
    }
}

function denyDefaultClaim() {
    document.getElementById('consent').value = "deny";
    document.getElementById("profile").submit();
}
