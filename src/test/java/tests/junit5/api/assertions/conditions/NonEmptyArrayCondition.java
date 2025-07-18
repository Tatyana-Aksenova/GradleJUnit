package tests.junit5.api.assertions.conditions;


import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import tests.junit5.api.assertions.Condition;

import java.util.List;



@RequiredArgsConstructor
public class NonEmptyArrayCondition implements Condition {
    @Override
    public void check(ValidatableResponse response) {
        List<?> list = response.extract().jsonPath().getList("");
        Assertions.assertFalse(list.isEmpty());
    }
}
