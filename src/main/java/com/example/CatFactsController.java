package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cat")
public class CatFactsController {

    final String FACT_API_URL = "https://catfact.ninja/fact";
    final String BREED_API_URL = "https://catfact.ninja/breeds";
    final String TRANSLATE_API_URL = "http://10.34.56.141:5540/translate"; 

    @GetMapping("/fact")
    public String getFact() {

        RestTemplate restTemplate = new RestTemplate();
        CatFact fact = restTemplate.getForObject(FACT_API_URL, CatFact.class);

        return fact.getFact();   
    }

    @GetMapping("/limited-fact/{length}")
    public String getLimitedFact(@PathVariable int length) {

        RestTemplate restTemplate = new RestTemplate();
        String urlWithParams = FACT_API_URL + "?max_length=" + length;
        CatFact fact = restTemplate.getForObject(urlWithParams, CatFact.class); 

        return fact.getFact();  
    }

    @GetMapping("/translated-fact")
    public String getTranslatedFact() {

        RestTemplate restTemplate = new RestTemplate();
        CatFact fact = restTemplate.getForObject(FACT_API_URL, CatFact.class);

        TranslateRequest request = new TranslateRequest();
        request.setQ(fact.getFact()); 
        request.setSource("en");
        request.setTarget("es");
        request.setFormat("text");

        String translatedFact = restTemplate.postForObject(TRANSLATE_API_URL, request, String.class);   
 
        return translatedFact;
    }

    @GetMapping("/breed")
    public String getBreed() {

        RestTemplate restTemplate = new RestTemplate(); 
        CatBreed breed = restTemplate.getForObject(BREED_API_URL, CatBreed.class);

        return breed.getBreed();
    }
}

