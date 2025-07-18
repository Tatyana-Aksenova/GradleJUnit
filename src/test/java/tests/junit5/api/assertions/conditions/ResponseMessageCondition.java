package tests.junit5.api.assertions.conditions;


import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import tests.junit5.api.assertions.Condition;


@RequiredArgsConstructor
public class ResponseMessageCondition implements Condition {
    private final String expectedMessage;

    @Override
    public void check(ValidatableResponse response) {
        String actualMessage = response.extract().jsonPath().getString("message");
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
