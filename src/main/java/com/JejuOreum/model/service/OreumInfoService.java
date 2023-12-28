package com.JejuOreum.model.service;

import com.JejuOreum.model.repository.OreumInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OreumInfoService {

    private OreumInfoRepository oreumInfoRepository;

    @Autowired
    public OreumInfoService(OreumInfoRepository oreumInfoRepository){
        this.oreumInfoRepository = oreumInfoRepository;
    }
}
