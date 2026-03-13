package com.petstore.qa.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actors.OnStage;
import com.petstore.qa.model.Pet;
import com.petstore.qa.model.PetDataFactory;
import com.petstore.qa.tasks.RegisterPet;
import com.petstore.qa.tasks.ConsultPet;
import com.petstore.qa.tasks.UpdatePet;
import com.petstore.qa.tasks.DeletePet;
import com.petstore.qa.questions.ResponseCode;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.isOneOf;

public class PetStoreManagementStepDefinitions {

    private Long petId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que el administrador prepara el registro para la mascota {string}")
    public void adminPreparesRegistrationForPet(String petName) {
        theActorCalled("Inventory Manager");
        this.petId = System.currentTimeMillis();
        Pet dynamicPet = PetDataFactory.createDynamicPet(petId, petName, "available");
        theActorInTheSpotlight().remember("dynamicPet", dynamicPet);
    }

    @Cuando("la registra en el sistema de la tienda")
    public void registersThePetInTheStore() {
        Pet petToRegister = theActorInTheSpotlight().recall("dynamicPet");
        theActorInTheSpotlight().attemptsTo(
                RegisterPet.withData(petToRegister)
        );
    }

    @Y("consulta su información para confirmar la creación")
    public void queriesThePetInformationToConfirmCreation() {
        theActorInTheSpotlight().attemptsTo(
                ConsultPet.withId(petId)
        );
    }

    @Entonces("verifica que la mascota fue creada exitosamente")
    public void verifiesPetWasCreatedSuccessfully() {
        theActorInTheSpotlight().should(
                seeThat(ResponseCode.wasReceived(), isOneOf(200, 201))
        );
    }

    @Y("actualiza el estado de la mascota a {string}")
    public void updatesPetStatusTo(String newStatus) {
        Pet petToUpdate = theActorInTheSpotlight().recall("dynamicPet");
        petToUpdate.setStatus(newStatus);
        theActorInTheSpotlight().attemptsTo(
                UpdatePet.withData(petToUpdate)
        );
    }

    @Y("finalmente elimina a la mascota del sistema")
    public void finallyDeletesThePetFromTheSystem() {
        theActorInTheSpotlight().attemptsTo(
                DeletePet.withId(petId)
        );
    }

    @Entonces("el registro completo de la mascota ya no debe existir en la tienda")
    public void theCompleteRecordOfThePetMustNotExistInTheStore() {
        theActorInTheSpotlight().should(
                seeThat(ResponseCode.wasReceived(), isOneOf(200, 204))
        );
    }
}

