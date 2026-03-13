package com.petstore.qa.model;

import java.util.Collections;
import java.util.List;

public class PetDataFactory {

    public static Pet createDynamicPet(Long petId, String name, String status) {
        Category category = new Category(1L, "Dogs");
        Tag tag = new Tag(1L, "cute");
        List<Tag> tags = Collections.singletonList(tag);

        String dynamicPhotoUrl = "https://loremflickr.com/320/240/dog?random=" + System.currentTimeMillis();
        List<String> photoUrls = Collections.singletonList(dynamicPhotoUrl);

        return new Pet(petId, category, name, photoUrls, tags, status);
    }
}

