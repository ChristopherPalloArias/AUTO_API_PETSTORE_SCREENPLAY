package com.petstore.qa.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.annotations.Step;

public class DeletePet implements Task {
    private final Long petId;

    public DeletePet(Long petId) {
        this.petId = petId;
    }

    public static DeletePet withId(Long petId) {
        return Tasks.instrumented(DeletePet.class, petId);
    }

    @Override
    @Step("{0} removes the pet from the system")
    public <T extends Actor> void performAs(T actor) {
        SerenityRest
                .delete("/pet/" + petId);
    }
}

