package com.JejuOreum.model.service;

import com.JejuOreum.model.repository.OreumVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OreumVisitService {

    private OreumVisitRepository oreumVisitRepository;

    @Autowired
    public OreumVisitService(OreumVisitRepository oreumVisitRepository){
        this.oreumVisitRepository = oreumVisitRepository;
    }
}
