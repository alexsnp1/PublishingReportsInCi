package api;

import api.models.AdminCreateUserRequest;
import api.models.UserRole;
import api.requests.skeleton.requesters.CrudRequester;
import api.requests.skeleton.requesters.Endpoint;
import api.requests.steps.AuthenticationStep;
import api.requests.steps.UserCreationStep;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import api.utils.RandomData;
import org.junit.jupiter.api.Test;

public class UserCreatingNegativeApiTests {
    @Test
    public void unauthenticatedUserCannotCreateUser() {
        AdminCreateUserRequest user = AdminCreateUserRequest.builder()
                .build();
        new CrudRequester(
                RequestSpecs.unAuthSpec(),
                Endpoint.ADMIN_USERS,
                ResponseSpecs.unauthorizedUser())
                .post(user);
    }

    @Test
    public void adminCannotCreateUserWithInvalidData() {
        AdminCreateUserRequest user = AdminCreateUserRequest.builder()
                .username(RandomData.getRandomString())
                .password(RandomData.getRandomString())
                .role(UserRole.USER.toString())
                .build();
        new CrudRequester(
                RequestSpecs.adminAuthSpec(),
                Endpoint.ADMIN_USERS,
                ResponseSpecs.returnsBadRequest())
                .post(user);
    }

    @Test
    public void regularUserCannotCreateUser() {
        AdminCreateUserRequest use2r = UserCreationStep.createUserRequest();
        String authTokenUser = AuthenticationStep.getUserTokenStep(use2r);

        AdminCreateUserRequest user = AdminCreateUserRequest.builder()
                .username(RandomData.getRandomString())
                .password(RandomData.getRandomString())
                .role(UserRole.USER.toString())
                .build();
        new CrudRequester(
                RequestSpecs.userAuthSpec(authTokenUser),
                Endpoint.ADMIN_USERS,
                ResponseSpecs.forbidden())
                .post(user);
    }
}
