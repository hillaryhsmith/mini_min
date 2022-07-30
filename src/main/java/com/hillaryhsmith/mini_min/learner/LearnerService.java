package com.hillaryhsmith.mini_min.learner;

import com.hillaryhsmith.mini_min.mineral.Mineral;
import com.hillaryhsmith.mini_min.mineral.MineralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LearnerService {

    private final LearnerRepository learnerRepository;

    // learners know about minerals
    private final MineralService mineralService;

    @Autowired
    public LearnerService(LearnerRepository learnerRepository, MineralService mineralService) {
        this.learnerRepository = learnerRepository;
        this.mineralService = mineralService;
    }

    // Learner API Routes

    // POST
    public void registerNewLearner(Learner learner) {
        Optional<Learner> learnerByUsername = learnerRepository
                .findLearnerByUsername(learner.getUsername());
        if (learnerByUsername.isPresent()) {
            throw new IllegalStateException("username is already in use");
        }
        learnerRepository.save(learner);
    }

    // GET
    public List<Learner> getLearners() {
        return learnerRepository.findAll();
    }

    // PATCH
    @Transactional
    public void updateLearnerEmail(Integer learnerId, String newEmail){
        getLearnerById(learnerId).setEmail(newEmail);
    }

    @Transactional
    public void updateLearnerPassword(Integer learnerId, String newPassword){
        getLearnerById(learnerId).setPassword(newPassword);
    }

    // DELETE
    public void deleteLearner(Integer learnerId){
        if (!learnerRepository.existsById(learnerId)){
            throw learnerNotFoundException(learnerId);
        }
        learnerRepository.deleteById(learnerId);
    }

    // mineral_learner API Routes

    // POST
    @Transactional
    public void learnMineral(Integer learnerId, Integer mineralId) {
        Learner learner = getLearnerById(learnerId);
        Mineral mineral = mineralService.getMineralById(mineralId);

        learner.learnedMinerals.add(mineral);
    }

    // GET
    public Set<Mineral> getLearnedMinerals(Integer learnerId) {
        return getLearnerById(learnerId).learnedMinerals;
    }

    public List<Integer> getLearnedMineralIds(Integer learnerId) {
        return mineralSetToIdList(getLearnedMinerals(learnerId));
    }

    public Set<Mineral> getUnlearnedMinerals(Integer learnerId) {
        Set<Mineral> minerals = mineralService.getMinerals();
        minerals.removeAll(getLearnedMinerals(learnerId));

        return minerals;
    }

    public List<Integer> getUnlearnedMineralIds(Integer learnerId) {
        return mineralSetToIdList(getUnlearnedMinerals(learnerId));
    }

    // DELETE
    @Transactional
    public boolean unlearnMineral(Integer learnerId, Integer mineralId){
        Learner learner = getLearnerById(learnerId);
        Mineral mineral = mineralService.getMineralById(mineralId);

        return learner.learnedMinerals.remove(mineral);
    }

    // Helper functions
    private IllegalStateException learnerNotFoundException(Integer id) {
        return new IllegalStateException("learner with id "
                + id + " does not exist");
    }

    private Learner getLearnerById(Integer id) {
        return learnerRepository.findById(id)
                .orElseThrow(() -> learnerNotFoundException(id));
    }
    private List<Integer> mineralSetToIdList(Set<Mineral> minerals) {
        List<Integer> idList = new ArrayList<>();

        for (Mineral mineral : minerals) {
            idList.add(mineral.getId());
        }

        return idList;
    }
}