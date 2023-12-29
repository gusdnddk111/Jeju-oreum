package com.JejuOreum.model.service;

import com.JejuOreum.model.repository.OreumVisitPicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OreumVisitPicDbService {

    private OreumVisitPicRepository oreumVisitPicRepository;

    @Autowired
    public OreumVisitPicDbService(OreumVisitPicRepository oreumVisitPicRepository){
        this.oreumVisitPicRepository = oreumVisitPicRepository;
    }
}
