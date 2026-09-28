package api.requests.steps;

import api.models.UserCreateAccountResponse;
import api.requests.skeleton.requesters.Endpoint;
import api.requests.skeleton.requesters.ValidatedCrudRequester;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import common.helpers.StepLogger;

public class AccountCreationStep {
    public static UserCreateAccountResponse userCreateAccount(String authTokenUser) {
        return StepLogger.log("User create account ", () -> {
            return new ValidatedCrudRequester<UserCreateAccountResponse>(
                    RequestSpecs.userAuthSpec(authTokenUser),
                    Endpoint.ACCOUNTS,
                    ResponseSpecs.returnsCreated())
                    .post();
        });
    }
}
