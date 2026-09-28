package api.specs;

import api.configs.Config;
import api.utils.Headers;
import com.github.viclovsky.swagger.coverage.FileSystemOutputWriter;
import com.github.viclovsky.swagger.coverage.SwaggerCoverageRestAssured;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.nio.file.Paths;

import static com.github.viclovsky.swagger.coverage.SwaggerCoverageConstants.OUTPUT_DIRECTORY;

public class RequestSpecs {
    private RequestSpecs() {
    }

    private static RequestSpecBuilder defaultRequestBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
//                .addFilter(new RequestLoggingFilter())
//                .addFilter(new ResponseLoggingFilter())
                .addFilter(new AllureRestAssured())
                .addFilter(new SwaggerCoverageRestAssured(
                        new FileSystemOutputWriter(Paths.get("target/" + OUTPUT_DIRECTORY))))
                .setBaseUri(Config.getProperty("apiBaseUrl"));
    }

    public static RequestSpecification unAuthSpec() {
        return defaultRequestBuilder().build();
    }

    public static RequestSpecification adminAuthSpec() {
        return defaultRequestBuilder()
                .addHeader(Headers.AUTHORIZATION, Config.getProperty("admin.auth"))
                .build();
    }

    public static RequestSpecification userAuthSpec(String token) {
        return defaultRequestBuilder()
                .addHeader(Headers.AUTHORIZATION, token)
                .build();
    }

}
