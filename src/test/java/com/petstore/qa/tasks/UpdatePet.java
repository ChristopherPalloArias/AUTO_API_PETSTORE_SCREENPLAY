package com.petstore.qa.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
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
        SerenityRest
                .given()
                    .contentType("application/json")
                    .body(pet)
                .put("/pet");
    }
}

