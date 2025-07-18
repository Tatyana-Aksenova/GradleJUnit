package tests.junit5.api.tests.petsapi;


import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.junit5.api.addons.AdminUserResolver;
import tests.junit5.api.addons.CustomTpl;
import tests.junit5.api.models.swaggerpets.Pet;
import tests.junit5.api.services.PetService;

import static tests.junit5.api.addons.RandomTestData.getRandomPet;
import static tests.junit5.api.addons.RandomTestData.getUpdatedPet;
import static tests.junit5.api.assertions.Conditions.*;


@ExtendWith(AdminUserResolver.class)

public class PetTestRefactor {

    private static PetService petService;
    private Pet pet;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                CustomTpl.customLogFilter().withCustomTemplates());
        petService = new PetService();
    }

    @BeforeEach
    public void initTestPet() {
        pet = getRandomPet();
    }

    @Test
    public void createNewPetTest() {
      String status = petService.createPet(pet)
              .should(hasStatusCode(200))
              .as("status", String.class);

        Assertions.assertEquals("available", status);
    }

    @Test
    public void getPetByIdTest() {
        Pet createdPet = petService.createPet(pet)
                .should(hasStatusCode(200))
                .as(Pet.class);

        Pet requestedPet = petService.getPetInfo(createdPet.getId())
                .should(hasStatusCode(200))
                .as(Pet.class);

        Assertions.assertEquals(createdPet.getId(), requestedPet.getId());
        Assertions.assertEquals(createdPet.getName(), requestedPet.getName());
        Assertions.assertEquals(createdPet.getStatus(), requestedPet.getStatus());
    }

    @Test
    public void updatePetTest() {
        Pet createdPet = petService.createPet(pet)
                .should(hasStatusCode(200))
                .as(Pet.class);

        Pet updatedPet = getUpdatedPet(createdPet);

        Pet actualPet = petService.updatePet(updatedPet)
                .should(hasStatusCode(200))
                .as(Pet.class);

        Assertions.assertEquals(updatedPet.getName(), actualPet.getName());
        Assertions.assertEquals(updatedPet.getStatus(), actualPet.getStatus());
        Assertions.assertFalse(createdPet.getName().equals(actualPet.getName()));
    }

    @Test
    public void deletePetTest() {
        String expectedMessage = "Pet not found";

        Pet createdPet = petService.createPet(pet)
                .should(hasStatusCode(200))
                .as(Pet.class);

        petService.deletePet(createdPet)
                .should(hasStatusCode(200));

        String message = petService.getPetInfo(createdPet.getId())
                .should(hasStatusCode(404))
                .as("message", String.class);

        Assertions.assertEquals(expectedMessage, message);
    }

    @Test
    public void getPetByStatusTest() {
       petService.getPetsByStatus("available")
                .should(hasStatusCode(200))
                .should(hasAvailablePet())
                .should(nonEmptyArray());

    }

    @Test
    public void negativeGetNonExistentPet() {
        petService.getPetInfo("989898989898476")
                .should(hasStatusCode(404))
                .should(hasResponseMessage("Pet not found"));
    }

    @Test
    public void negativeAddPetWithoutRequiredField() {

        Pet emptyPet = Pet.builder().build();
        petService.createPet(emptyPet)
                        .should(hasStatusCode(500));
    }
}
