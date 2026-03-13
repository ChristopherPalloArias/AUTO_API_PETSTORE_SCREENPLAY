package com.petstore.qa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PetWasDeleted implements Question<Boolean> {

    public static PetWasDeleted successfully() {
        return new PetWasDeleted();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Integer statusCode = actor.recall("deleteStatusCode");
        return statusCode != null && (statusCode == 200 || statusCode == 204);
    }
}

