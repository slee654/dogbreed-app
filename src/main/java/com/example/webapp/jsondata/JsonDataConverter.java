package com.example.webapp.jsondata;

import com.example.webapp.models.DogBreed;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by EL on 8/17/18.
 */
@Component
public class JsonDataConverter {
    @Value("${dogbreed.url}")
    private String dogbreedURL;

    public JSONObject getJsonObjectFromURL()throws IOException{
        InputStream is = new URL(dogbreedURL).openStream();
        JSONParser parser = new JSONParser();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            Object obj = parser.parse(rd);
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }

        return null;
    }


    public List<DogBreed> getEntitySet(JSONObject jsonObject) {
        List<DogBreed> dogBreedList = new ArrayList<>();
        String status = (String) jsonObject.get("status");
        if (status!= null && status.equalsIgnoreCase("success")) {
            JSONObject messages = (JSONObject) jsonObject.get("message");
            Iterator iterator = messages.keySet().iterator();
            while (iterator.hasNext()) {
                String breedName = (String)iterator.next();
                DogBreed dogBreed = new DogBreed(breedName);
                dogBreedList.add(dogBreed);
            }
        }
        return dogBreedList;
    }
}
