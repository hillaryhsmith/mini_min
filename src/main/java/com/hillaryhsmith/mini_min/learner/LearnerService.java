package com.hillaryhsmith.mini_min.learner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnerService {

    private final LearnerRepository learnerRepository;

    @Autowired
    public LearnerService(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }

    public List<Learner> getLearners() {
        return learnerRepository.findAll();
//        returns list
    }
}