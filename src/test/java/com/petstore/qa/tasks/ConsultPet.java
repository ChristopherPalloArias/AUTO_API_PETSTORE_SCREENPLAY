package com.petstore.qa.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.annotations.Step;

public class ConsultPet implements Task {
    private final Long petId;

    public ConsultPet(Long petId) {
        this.petId = petId;
    }

    public static ConsultPet withId(Long petId) {
        return Tasks.instrumented(ConsultPet.class, petId);
    }

    @Override
    @Step("{0} queries the pet information by id")
    public <T extends Actor> void performAs(T actor) {
        SerenityRest
                .get("/pet/" + petId);
    }
}

