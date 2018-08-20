package com.example.webapp.bootstrap;

import com.example.webapp.jsondata.JsonDataConverter;
import com.example.webapp.models.DogBreed;
import com.example.webapp.repositories.DogBreedRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by EL on 8/17/18.
 */
@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {
    private DogBreedRepositary dogBreedRepositary;
    private JsonDataConverter jsonDataConverter;

    @Autowired
    public DevBootStrap(DogBreedRepositary dogBreedRepositary, JsonDataConverter jsonDataConverter) {
        this.dogBreedRepositary = dogBreedRepositary;
        this.jsonDataConverter = jsonDataConverter;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initURLData();
    }

    private void initURLData() {
        try {
            List<DogBreed> dogBreedList = jsonDataConverter.getEntitySet(jsonDataConverter.getJsonObjectFromURL());
            if (dogBreedList != null && dogBreedList.size() > 0) {
            	Collections.sort(dogBreedList);
                dogBreedRepositary.saveAll(dogBreedList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
