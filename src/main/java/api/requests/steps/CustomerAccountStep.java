package api.requests.steps;

import api.models.CustomerAccountsGetResponse;
import api.requests.skeleton.requesters.Endpoint;
import api.requests.skeleton.requesters.ValidatedCrudRequester;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import common.helpers.StepLogger;

public class CustomerAccountStep {
    public static CustomerAccountsGetResponse[] getCustomerAccountResponse(String authTokenUser) {
        return StepLogger.log("Get customer's account response", () -> {
            return new ValidatedCrudRequester<CustomerAccountsGetResponse[]>(
                    RequestSpecs.userAuthSpec(authTokenUser),
                    Endpoint.CUSTOMER_ACCOUNTS,
                    ResponseSpecs.returnsOK())
                    .get();
        });
    }
}
