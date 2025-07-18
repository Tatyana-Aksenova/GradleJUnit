package tests.junit5.api.tests.fakeapi;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import listeners.RetryListenerJunit5;
import org.apiguardian.api.API;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.junit5.api.addons.CustomTpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.junit5.api.models.fakeapiuser.*;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


@Tag("FAILAPI")
public class SimpleApiRefactoredTests {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                CustomTpl.customLogFilter().withCustomTemplates());
    }

    @AfterAll
    public static void saveFailed() {
        RetryListenerJunit5.saveFailedTests();
    }

    @Test
    public void getAllUsersTest() {
        given().log().all()
                .get("/users")
                .then()
                .log().all()  // Логирует весь ответ
                .statusCode(200);
    }
}
