package tests.junit5.api.services;



import io.restassured.http.ContentType;
import tests.junit5.api.assertions.AssertableResponse;
import tests.junit5.api.models.swaggerpets.Pet;

import static io.restassured.RestAssured.given;


public class PetService {
    public AssertableResponse createPet(Pet pet){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(pet)
                .post("/pet")
                .then());
    }

    public AssertableResponse getPetInfo(String petId){
        return new AssertableResponse(given()
                .pathParam("petId", petId)
                .get("/pet/{petId}")
                .then());
    }
    public  AssertableResponse updatePet(Pet pet) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(pet)
                .put("/pet")
                .then());
    }
    public  AssertableResponse deletePet(Pet pet) {
        return new AssertableResponse(given()
                .pathParam("petId", pet.getId())
                .delete("/pet/{petId}")
                .then());
    }
    public  AssertableResponse getPetsByStatus(String status) {
        return new AssertableResponse(given()
                .pathParam("status", status)
                .get("/pet/findByStatus?status={status}")
                .then());
    }
}
