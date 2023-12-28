package com.JejuOreum.service;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

@Service
@Slf4j
public class OreumService {

    private final RestTemplate restTemplate;
    private JSONParser parser;

    @Autowired
    public OreumService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.parser = new JSONParser();
    }

    public void insertOreunInfo() throws Exception {
        Reader reader = new FileReader("/src/main/resources/static/Oreuminfo.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        System.out.println(jsonObject);

    }

}
