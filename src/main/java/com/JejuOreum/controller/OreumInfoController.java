package com.JejuOreum.controller;

import com.JejuOreum.model.entity.OreumInfoEntity;
import com.JejuOreum.service.oreum.OreumService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OreumInfoController {

    private OreumService oreumService;
    private JSONParser parser;

    @Autowired
    public OreumInfoController(OreumService oreumService){
        this.oreumService = oreumService;
        parser = new JSONParser();
    }

    @GetMapping("/oreum/insert")
    public JSONObject oreumInfoInsert() throws Exception{

        oreumService.insertOreunInfo();
        //System.out.println("success");

        return null;
    }

    @GetMapping("/oreum/getAllOreumInfo")
    public List<OreumInfoEntity> getAllOreumInfo() throws Exception{
        return oreumService.getAllOreumInfo();
    }

    @GetMapping("/oreum/getOreumInfoByPosition")
    public List<OreumInfoEntity> getOreumInfoByPosition(@RequestParam Map<String, String> reqParams) throws Exception{
        float minLongitude = Float.parseFloat(reqParams.get("minLongitude").toString());
        float maxLongitude = Float.parseFloat(reqParams.get("maxLongitude").toString());
        float minLatitude = Float.parseFloat(reqParams.get("minLatitude").toString());
        float maxLatitude = Float.parseFloat(reqParams.get("maxLatitude").toString());

        return oreumService.getOreumInfoByPosition(minLongitude, maxLongitude, minLatitude, maxLatitude);
    }
}
