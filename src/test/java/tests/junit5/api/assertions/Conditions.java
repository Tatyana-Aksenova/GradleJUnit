package tests.junit5.api.assertions;

import tests.junit5.api.assertions.conditions.StatusCodeCondition;
import tests.junit5.api.assertions.conditions.MessageCondition;

public class Conditions {
    public static MessageCondition hasMessage(String expectedMessage){
        return new MessageCondition(expectedMessage);
    }

    public static StatusCodeCondition hasStatusCode(Integer expectedStatus){
        return new StatusCodeCondition(expectedStatus);
    }
}
