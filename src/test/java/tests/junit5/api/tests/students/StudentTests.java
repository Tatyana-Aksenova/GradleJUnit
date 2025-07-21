package tests.junit5.api.tests.students;


import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.junit5.api.addons.CustomTpl;
import tests.junit5.api.models.students.Student;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8085/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                CustomTpl.customLogFilter().withCustomTemplates());
    }

    @Test
    @DisplayName("Получение списка студентов")
    public void getAll100Students() {
        List<Student> students = given()
                .get("/student/list")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<Student>>() {
                });
        //students.forEach(System.out::println);
        Assertions.assertTrue(students.size() >= 100);
    }

    @Test
    public void getFirstStudent() {
        String expectedName = "Vernon";
        String expectedLastName = "Harper";
        String expectedEmail = "egestas.rhoncus.Proin@massaQuisqueporttitor.org";

        Student student = given().pathParam("idUser",1)
                .get("/student/{idUser}")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<Student>() {});

        Assertions.assertEquals(expectedName, student.getFirstName());
        Assertions.assertEquals(expectedLastName, student.getLastName());
        Assertions.assertEquals(expectedEmail, student.getEmail());
    }



}

