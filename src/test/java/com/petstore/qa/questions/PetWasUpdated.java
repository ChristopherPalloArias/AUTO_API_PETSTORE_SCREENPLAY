package com.petstore.qa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PetWasUpdated implements Question<Boolean> {

    public static PetWasUpdated successfully() {
        return new PetWasUpdated();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Integer statusCode = actor.recall("updateStatusCode");
        return statusCode != null && (statusCode == 200 || statusCode == 201);
    }
}

