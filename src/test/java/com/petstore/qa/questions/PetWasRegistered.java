package com.petstore.qa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PetWasRegistered implements Question<Boolean> {

    public static PetWasRegistered successfully() {
        return new PetWasRegistered();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Integer statusCode = actor.recall("registrationStatusCode");
        return statusCode != null && (statusCode == 200 || statusCode == 201);
    }
}

