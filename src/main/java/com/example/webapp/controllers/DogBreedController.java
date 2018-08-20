package com.example.webapp.controllers;

import com.example.webapp.models.DogBreed;
import com.example.webapp.repositories.DogBreedRepositary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by EL on 8/17/18.
 */
@Controller
public class DogBreedController {
    private DogBreedRepositary dogBreedRepositary;

    @Autowired
    public DogBreedController(DogBreedRepositary dogBreedRepositary) {
        this.dogBreedRepositary = dogBreedRepositary;
    }

    @RequestMapping("/dogs")
    public String getDogBreeds(Model model){
        model.addAttribute("dogs", dogBreedRepositary.findAll());
        return "dogs";
    }
    
    @RequestMapping(value = "searchDogBreed", method = RequestMethod.GET)
    @ResponseBody
    public List<DogBreed> getBreedList(Model model){
    	List<DogBreed> breedList = new ArrayList<DogBreed>();
    	Iterable<DogBreed> dogBreeds = dogBreedRepositary.findAll();
    	//get a list of breedName
    	for(DogBreed breed : dogBreeds) {
    		DogBreed newBreed = new DogBreed();
    		Long id = breed.getId();
    		String breedName = breed.getBreedName();
    		newBreed.setId(id);
    		newBreed.setBreedName(breedName);
    		breedList.add(newBreed);    		
    	}
        model.addAttribute("breedlist", breedList);
        return breedList;
    }
}
