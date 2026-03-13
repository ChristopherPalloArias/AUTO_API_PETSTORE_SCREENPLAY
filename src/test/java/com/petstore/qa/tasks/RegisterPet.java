package com.petstore.qa.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.annotations.Step;
import com.petstore.qa.model.Pet;

public class RegisterPet implements Task {
    private final Pet pet;

    public RegisterPet(Pet pet) {
        this.pet = pet;
    }

    public static RegisterPet withData(Pet pet) {
        return Tasks.instrumented(RegisterPet.class, pet);
    }

    @Override
    @Step("{0} registers a new pet")
    public <T extends Actor> void performAs(T actor) {
        SerenityRest
                .given()
                    .contentType("application/json")
                    .body(pet)
                .post("/pet");
    }
}

