package com.JejuOreum.model.service;

import com.JejuOreum.model.repository.OreumVisitPicRepository;
import com.JejuOreum.model.repository.OreumVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OreumVisitPicService {

    private OreumVisitPicRepository oreumVisitPicRepository;

    @Autowired
    public OreumVisitPicService(OreumVisitPicRepository oreumVisitPicRepository){
        this.oreumVisitPicRepository = oreumVisitPicRepository;
    }
}
