package tests.junit5.api.tests.petsapi;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.junit5.api.addons.CustomTpl;
import tests.junit5.api.models.swaggerpets.Category;
import tests.junit5.api.models.swaggerpets.Pet;
import tests.junit5.api.models.swaggerpets.TagsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetsTest {
    private static Random random;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                CustomTpl.customLogFilter().withCustomTemplates());
        random = new Random();
    }

    @Test
    public void createNewPetTest() {
        String petName = "Busya"+Math.abs(random.nextInt());
        List<TagsItem> tagsItems = new ArrayList<>();
        tagsItems.add(new TagsItem(1, "cute"));
        tagsItems.add(new TagsItem(2, "fluffy"));
        Category category = new Category("1", "cat");
        String postStatus = "available";

        Pet pet = Pet
                .builder()
                .name(petName)
                .category(category)
                .status(postStatus)
                .tags(tagsItems)
                .build();

        String statusResponse = given().contentType(ContentType.JSON)
                .body(pet)
                .post("/pet")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getString("status");

        Assertions.assertEquals(postStatus, statusResponse);

    }

    @Test
    public void createNewPetAndFindPetsByIdTest() {
        String petName = "Busya"+Math.abs(random.nextInt());
        List<TagsItem> tagsItems = new ArrayList<>();
        tagsItems.add(new TagsItem(1, "cute"));
        tagsItems.add(new TagsItem(2, "fluffy"));
        Category category = new Category("1", "cat");
        String postStatus = "available";

        Pet pet = Pet
                .builder()
                .name(petName)
                .category(category)
                .status(postStatus)
                .tags(tagsItems)
                .build();

        String petId = given().contentType(ContentType.JSON)
                .body(pet)
                .post("/pet")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("id");

        given()
                .pathParam("petId", petId)
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("name", equalTo(petName))
                .body("id", equalTo(Long.parseLong(petId)))
                .body("status", equalTo(postStatus));
    }
}
