package api.requests.skeleton.requesters;

import api.configs.Config;
import api.models.BaseModel;
import api.requests.skeleton.HttpRequest;
import api.requests.skeleton.interfaces.CrudEndpointInterface;
import common.helpers.StepLogger;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class CrudRequester extends HttpRequest implements CrudEndpointInterface {
    private final static String API_VERSION = Config.getProperty("apiVersion");
    public CrudRequester(RequestSpecification requestSpecification, Endpoint endpoint,
                         ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        return StepLogger.log("POST request to " + endpoint.getUrl(), () -> {
            if (model == null) {
                throw new IllegalArgumentException("POST body cannot be null. Use post() instead");
            }
            return given()
                    .spec(requestSpecification)
                    .body(model)
                    .post(API_VERSION + endpoint.getUrl())
                    .then()
                    .assertThat()
                    .spec(responseSpecification);
        });
    }


    @Override
    public ValidatableResponse post() {
        return StepLogger.log("POST request to " + endpoint.getUrl(), () -> {
            return given()
                    .spec(requestSpecification)
                    .post(API_VERSION + endpoint.getUrl())
                    .then()
                    .assertThat()
                    .spec(responseSpecification);
        });
    }

    @Override
    public ValidatableResponse get() {
        return StepLogger.log("GET request to " + endpoint.getUrl(), () -> {
            return given()
                    .spec(requestSpecification)
                    .get(API_VERSION + endpoint.getUrl())
                    .then()
                    .assertThat()
                    .spec(responseSpecification);
        });
    }

    @Override
    public ValidatableResponse put(BaseModel model) {
        return StepLogger.log("PUT request to " + endpoint.getUrl(), () -> {
            var body = model == null ? "" : model;
            return given()
                    .spec(requestSpecification)
                    .body(body)
                    .put(API_VERSION + endpoint.getUrl())
                    .then()
                    .assertThat()
                    .spec(responseSpecification);
        });
    }


    @Override
    public Object delete(BaseModel model) {
        return StepLogger.log("DELETE request to " + endpoint.getUrl(), () -> {
            return null;
        });
    }
}
