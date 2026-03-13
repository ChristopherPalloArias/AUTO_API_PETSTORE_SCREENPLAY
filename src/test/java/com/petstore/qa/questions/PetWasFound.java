package com.petstore.qa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PetWasFound implements Question<Boolean> {

    public static PetWasFound inTheSystem() {
        return new PetWasFound();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Integer statusCode = actor.recall("consultStatusCode");
        return statusCode != null && statusCode == 200;
    }
}

