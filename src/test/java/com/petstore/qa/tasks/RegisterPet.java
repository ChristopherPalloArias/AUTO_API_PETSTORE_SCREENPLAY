package com.petstore.qa.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
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
        actor.attemptsTo(
                Post.to("/pet").with(request -> request
                        .header("Content-Type", "application/json")
                        .body(pet)
                )
        );

        actor.remember("registrationStatusCode", SerenityRest.lastResponse().statusCode());
    }
}

