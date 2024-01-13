package com.JejuOreum.controller;

import com.JejuOreum.model.entity.OreumInfoEntity;
import com.JejuOreum.model.service.OreumInfoService;
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

    private final OreumInfoService oreumInfoService;
    private final JSONParser parser;

    @Autowired
    public OreumInfoController(OreumInfoService oreumInfoService){
        this.oreumInfoService = oreumInfoService;
        parser = new JSONParser();
    }

    @GetMapping("/oreum/insert")
    public JSONObject oreumInfoInsert() throws Exception{

        oreumInfoService.insertOreunInfo();
        //System.out.println("success");

        return null;
    }

    @GetMapping("/oreum/getAllOreumInfo")
    public List<OreumInfoEntity> getAllOreumInfo() throws Exception {
        return oreumInfoService.getAllOreumInfo();
    }

    @GetMapping("/oreum/getOreumInfoByPosition")
    public List<OreumInfoEntity> getOreumInfoByPosition(@RequestParam Map<String, String> reqParams) throws Exception {
        float minLongitude = Float.parseFloat(reqParams.get("minLongitude").toString());
        float maxLongitude = Float.parseFloat(reqParams.get("maxLongitude").toString());
        float minLatitude = Float.parseFloat(reqParams.get("minLatitude").toString());
        float maxLatitude = Float.parseFloat(reqParams.get("maxLatitude").toString());

        return oreumInfoService.getOreumInfoByPosition(minLongitude, maxLongitude, minLatitude, maxLatitude);
    }
}
