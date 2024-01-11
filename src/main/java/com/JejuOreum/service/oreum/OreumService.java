package com.JejuOreum.service.oreum;

import com.JejuOreum.model.entity.OreumInfoEntity;
import com.JejuOreum.model.repository.OreumInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

@Service
@Slf4j
public class OreumService {

    private final RestTemplate restTemplate;
    private JSONParser parser;
    private OreumInfoRepository oreumInfoRepository;

    @Autowired
    public OreumService(RestTemplate restTemplate, OreumInfoRepository oreumInfoRepository){
        this.restTemplate = restTemplate;
        this.oreumInfoRepository = oreumInfoRepository;
        this.parser = new JSONParser();
    }

    public void insertOreunInfo() throws Exception {
        System.out.println();
        Reader reader = new FileReader("src/main/resources/static/OreumInfo.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray array = (JSONArray) jsonObject.get("oreumList");
        for(Object o : array){
            JSONObject oreumInfo = (JSONObject)o;
            System.out.println(oreumInfo);
        }
    }

    public List<OreumInfoEntity> getAllOreumInfo(){
        return oreumInfoRepository.findAll();
    }

    public List<OreumInfoEntity> getOreumInfoByPosition(float minLongitude, float maxLongitude, float minLatitude, float maxLatitude){
        return oreumInfoRepository.findAllByLongitudeBetweenAndLatitudeBetween(minLongitude, maxLongitude, minLatitude, maxLatitude);
    }

}
