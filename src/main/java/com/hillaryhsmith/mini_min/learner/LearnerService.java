package com.hillaryhsmith.mini_min.learner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LearnerService {

    private final LearnerRepository learnerRepository;

    @Autowired
    public LearnerService(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }

    public List<Learner> getLearners() {
        return learnerRepository.findAll();
    }

    public void registerNewLearner(Learner learner) {
        Optional<Learner> learnerByUsername = learnerRepository
                .findLearnerByUsername(learner.getUsername());
        if (learnerByUsername.isPresent()) {
            throw new IllegalStateException("username is already in use");
        }
        learnerRepository.save(learner);
        System.out.println(learner);
    }

   @Transactional
    public void updateLearnerEmail(Integer learnerId, String newEmail){
        getLearnerById(learnerId).setEmail(newEmail);
    }

   @Transactional
    public void updateLearnerPassword(Integer learnerId, String newPassword){
        getLearnerById(learnerId).setPassword(newPassword);
    }

    public void deleteLearner(Integer learnerId){
        if (!learnerRepository.existsById(learnerId)){
            throw learnerNotFoundException(learnerId);
        }
        learnerRepository.deleteById(learnerId);
    }

    private IllegalStateException learnerNotFoundException(Integer id) {
        return new IllegalStateException("learner with id "
                + id + " does not exist");
    }

    private Learner getLearnerById(Integer id) {
        return learnerRepository.findById(id)
                .orElseThrow(() -> learnerNotFoundException(id));
    }
}