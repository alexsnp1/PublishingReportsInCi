package api.requests.steps;

import api.models.AdminCreateUserRequest;
import api.models.UserLoginRequest;
import api.requests.skeleton.requesters.CrudRequester;
import api.requests.skeleton.requesters.Endpoint;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import api.utils.Headers;
import common.helpers.StepLogger;

public class AuthenticationStep {
    public static String getUserTokenStep(AdminCreateUserRequest user) {
        return StepLogger.log("Get user's token", () -> {
            UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();

            return new CrudRequester(RequestSpecs.unAuthSpec(),
                    Endpoint.LOGIN,
                    ResponseSpecs.returnsOK())
                    .post(userLoginRequest)
                    .extract()
                    .header(Headers.AUTHORIZATION);
        });
    }
}
