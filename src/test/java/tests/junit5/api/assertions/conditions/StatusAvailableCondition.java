package tests.junit5.api.assertions.conditions;


import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import tests.junit5.api.assertions.Condition;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class StatusAvailableCondition implements Condition {
    @Override
    public void check(ValidatableResponse response) {
        List<Map<String, Object>> pets = response.extract().jsonPath().getList("");

        boolean hasAvailable = pets.stream()
                .anyMatch(pet -> "available".equalsIgnoreCase((String) pet.get("status")));

        Assertions.assertTrue(hasAvailable);
    }
}

