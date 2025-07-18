package tests.junit5.api.assertions;

import tests.junit5.api.assertions.conditions.*;

public class Conditions {
    public static MessageCondition hasMessage(String expectedMessage){
        return new MessageCondition(expectedMessage);
    }

    public static StatusCodeCondition hasStatusCode(Integer expectedStatus){
        return new StatusCodeCondition(expectedStatus);
    }

    public static StatusAvailableCondition hasAvailablePet() {
        return new StatusAvailableCondition();
    }

    public static NonEmptyArrayCondition nonEmptyArray() {
        return new NonEmptyArrayCondition();
    }

    public static ResponseMessageCondition hasResponseMessage(String expectedMessage) {
        return new ResponseMessageCondition(expectedMessage);
    }

}
