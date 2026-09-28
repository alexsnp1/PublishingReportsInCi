package api;

import api.models.UserLoginRequest;
import api.requests.skeleton.requesters.CrudRequester;
import api.requests.skeleton.requesters.Endpoint;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import api.utils.RandomData;
import org.junit.jupiter.api.Test;


public class UserLoginNegativeApiTests {

    @Test
    public void userCannotLoginWithInvalidCredentials() {
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username(RandomData.getRandomString())
                .password(RandomData.getRandomString())
                .build();

        new CrudRequester(RequestSpecs.unAuthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.unauthorizedUser())
                .post(userLoginRequest);
    }
}
