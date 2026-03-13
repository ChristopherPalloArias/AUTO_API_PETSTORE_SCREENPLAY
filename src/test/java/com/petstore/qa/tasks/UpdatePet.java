package com.petstore.qa.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.annotations.Step;
import com.petstore.qa.model.Pet;

public class UpdatePet implements Task {
    private final Pet pet;

    public UpdatePet(Pet pet) {
        this.pet = pet;
    }

    public static UpdatePet withData(Pet pet) {
        return Tasks.instrumented(UpdatePet.class, pet);
    }

    @Override
    @Step("{0} updates the pet information")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/pet").with(request -> request
                        .header("Content-Type", "application/json")
                        .body(pet)
                )
        );

        actor.remember("updateStatusCode", SerenityRest.lastResponse().statusCode());
    }
}

