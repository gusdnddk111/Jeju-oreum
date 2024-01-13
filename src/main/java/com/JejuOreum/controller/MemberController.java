package com.JejuOreum.controller;

import com.JejuOreum.model.service.MemberService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MemberController {

    private MemberService memberService;
    private JSONParser parser;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
        parser = new JSONParser();
    }

    @PostMapping("/member/getMemberInfo")
    public JSONObject getMemberInfo(@RequestBody Map<String,Object> body) throws Exception {
        Long custNo = Long.parseLong(body.get("custNo").toString());
        //memberService.
        return null;
    }
}
