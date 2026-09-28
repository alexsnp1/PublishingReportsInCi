package api.requests.steps;

import api.models.DepositFundsRequest;
import api.requests.skeleton.requesters.CrudRequester;
import api.requests.skeleton.requesters.Endpoint;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import common.helpers.StepLogger;

public class DepositFundsStep {
    public static void depositFunds(String authTokenUser, int userId, double balance) {
        StepLogger.log("Get customer's profile response", () -> {
            DepositFundsRequest depositFundsRequest = DepositFundsRequest.builder()
                    .id(userId).balance(balance).build();
            new CrudRequester(RequestSpecs.userAuthSpec(authTokenUser),
                    Endpoint.ACCOUNTS_DEPOSIT,
                    ResponseSpecs.returnsOK())
                    .post(depositFundsRequest);
        });
    }
}
