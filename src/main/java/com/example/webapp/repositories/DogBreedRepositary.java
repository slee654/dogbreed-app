package com.example.webapp.repositories;

import com.example.webapp.models.DogBreed;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by EL on 8/17/18.
 */
public interface DogBreedRepositary extends CrudRepository <DogBreed, Long> {

} 
