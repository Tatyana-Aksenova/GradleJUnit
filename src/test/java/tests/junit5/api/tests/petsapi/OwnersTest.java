package tests.junit5.api.tests.petsapi;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.junit5.api.addons.CustomTpl;
import tests.junit5.api.models.swaggerpets.Userowner;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class OwnersTest {
    private static Random random;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                CustomTpl.customLogFilter().withCustomTemplates());
        random = new Random();
    }

    @Test
    public void createNewUserTest() {

        String userName = "MissNastya" + Math.abs(random.nextInt());
        String firstName = "Nastya" + Math.abs(random.nextInt());
        String lastName = "Ivanova" + Math.abs(random.nextInt());

        Userowner userowner =  Userowner
                .builder()
                .username(userName)
                .firstName(firstName)
                .lastName(lastName)
                .password("pa$$w0rd")
                .build();

        String statusCodeFromJson = given().contentType(ContentType.JSON)
                .body(userowner)
                .post("/user")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("code");

        Assertions.assertEquals(Integer.parseInt(statusCodeFromJson), 200);
    }

    @Test
    public void createUserAndLogInTest() {

        String userName = "MissNastya" + Math.abs(random.nextInt());
        String firstName = "Nastya" + Math.abs(random.nextInt());
        String lastName = "Ivanova" + Math.abs(random.nextInt());
        String password = "pa$$w0rd";

        Userowner userowner =  Userowner
                .builder()
                .username(userName)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .build();

        given().contentType(ContentType.JSON)
                .body(userowner)
                .post("/user")
                .then()
                .statusCode(200);

        String message = given().auth().basic(userName, password)
                .get("/user/login")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .getString("message");

        Assertions.assertTrue(message.contains("logged in user session:"));

    }

    @Test
    public void createAndUpdateUserTest() {
        String userName = "MissNastya" + Math.abs(random.nextInt());
        String firstName = "Nastya" + Math.abs(random.nextInt());
        String lastName = "Ivanova" + Math.abs(random.nextInt());
        String password = "pa$$w0rd";
        String email = "testem@gmail.com";

        Userowner userowner =  Userowner
                .builder()
                .username(userName)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .build();

        given().contentType(ContentType.JSON)
                .body(userowner)
                .post("/user")
                .then()
                .statusCode(200);

        Userowner updatedUserOwner = Userowner
                .builder()
                .username("upd" +userName)
                .firstName("upd" +firstName)
                .lastName("upd" +lastName)
                .email(email)
                .build();

        given().contentType(ContentType.JSON)
                .body(updatedUserOwner)
                .pathParam("username", userName)
                .put("/user/{username}")
                .then()
                .statusCode(200);
    }
}
