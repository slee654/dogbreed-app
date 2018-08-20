package com.example.webapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by EL on 8/17/18.
 */
@Entity
public class DogBreed implements Comparable<DogBreed>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String breedName;
    
    public DogBreed() {
    }

    public DogBreed(String breedName) {
        this.breedName = breedName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /*public Set<String> getImageURLs() {
        return imageURLs;
    }*/

    /*public void setImageURLs(Set<String> imageURLs) {
        this.imageURLs = imageURLs;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DogBreed)) return false;

        DogBreed dogBreed = (DogBreed) o;

        if (!getId().equals(dogBreed.getId())) return false;
        if (!getBreedName().equals(dogBreed.getBreedName())) return false;

        return false;
        //return getImageURLs() != null ? getImageURLs().equals(dogBreed.getImageURLs()) : dogBreed.getImageURLs() == null;
    }
    
    @Override
    public int compareTo(DogBreed d) {
       return this.getBreedName().compareTo(d.getBreedName());
   }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getBreedName().hashCode();

                //+ (getImageURLs() != null ? getImageURLs().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DogBreed{" +
                "id=" + id +
                ", breedName='" + breedName + '\'' +
                '}';
    }
}
