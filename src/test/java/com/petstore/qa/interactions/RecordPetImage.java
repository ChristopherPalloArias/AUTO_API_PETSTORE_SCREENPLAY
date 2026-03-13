package com.petstore.qa.interactions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Tasks;

public class RecordPetImage implements Interaction {

    private final String imageUrl;

    public RecordPetImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static RecordPetImage withUrl(String imageUrl) {
        return Tasks.instrumented(RecordPetImage.class, imageUrl);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Serenity.recordReportData()
                .withTitle("Generated Pet Image")
                .andContents(imageUrl);
    }
}

