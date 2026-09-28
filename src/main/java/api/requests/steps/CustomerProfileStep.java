package api.requests.steps;

import api.models.CustomerProfileGetResponse;
import api.requests.skeleton.requesters.Endpoint;
import api.requests.skeleton.requesters.ValidatedCrudRequester;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import common.helpers.StepLogger;

public class CustomerProfileStep {
    public static CustomerProfileGetResponse getCustomerProfileResponse(String authTokenUser) {
        return StepLogger.log("Get customer's profile response", () -> {
            return new ValidatedCrudRequester<CustomerProfileGetResponse>(
                    RequestSpecs.userAuthSpec(authTokenUser),
                    Endpoint.CUSTOMER_PROFILE_GET,
                    ResponseSpecs.returnsOK())
                    .get();
        });
    }
}
