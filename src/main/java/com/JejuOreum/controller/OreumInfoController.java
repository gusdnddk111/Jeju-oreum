package com.JejuOreum.controller;

import com.JejuOreum.service.OreumService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OreumInfoController {

    private OreumService oreumService;

    @Autowired
    public OreumInfoController(OreumService oreumService){
        this.oreumService = oreumService;
    }

    @GetMapping("/oreum/insert")
    public JSONObject oreumInfoInsert() throws Exception{

        //oreumService.insertOreunInfo();
        System.out.println("success");

        return null;
    }

}
