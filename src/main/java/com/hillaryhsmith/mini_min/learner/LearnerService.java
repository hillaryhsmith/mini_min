package com.hillaryhsmith.mini_min.learner;

import com.hillaryhsmith.mini_min.mineral.Mineral;
import com.hillaryhsmith.mini_min.mineral.MineralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class LearnerService {

    private final LearnerRepository learnerRepository;

    // learners know about minerals
    private final MineralService mineralService;

    private final Random random;

    @Autowired
    public LearnerService(LearnerRepository learnerRepository, MineralService mineralService) {
        this.learnerRepository = learnerRepository;
        this.mineralService = mineralService;
        this.random = new Random();
    }

    // Learner API Routes

    // POST
    @Transactional
    public void registerNewLearner(Learner learner) {
        Optional<Learner> learnerByUsername = learnerRepository
                .findLearnerByUsername(learner.getUsername());

        if (learnerByUsername.isPresent()) {
            throw new IllegalStateException("username is already in use");
        }
        learnerRepository.save(learner);
    }

    // GET

    public Integer verifyLogin(String username, String password) {
        Learner learner = learnerRepository.findLearnerByUsername(username)
                .orElseThrow(() -> invalidLogin());

        if (password.equals(learner.getPassword())) {
            return learner.getId();
        }

        throw invalidLogin();
    }

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
    @Transactional
    public void deleteLearner(Integer learnerId){
        if (!learnerRepository.existsById(learnerId)){
            throw learnerNotFoundException(learnerId);
        }
        learnerRepository.deleteById(learnerId);
    }

    // Learner-Mineral API Routes

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

    public Mineral getRandomLearnedMineral(Integer learnerId) {
        Set<Mineral> mineralSet = getLearnedMinerals(learnerId);
        return getRandomSetElement(mineralSet);
    }

    public Mineral getDifferentRandomLearnedMineral(Integer learnerId, Integer mineralId) {
        // copy to avoid modifying the learnedMinerals attribute of the Learner
        Set<Mineral> mineralSet = new HashSet<Mineral>(getLearnedMinerals(learnerId));
        mineralSet.remove(mineralService.getMineralById(mineralId));
        return getRandomSetElement(mineralSet);
    }

    public Set<Mineral> getUnlearnedMinerals(Integer learnerId) {
        Set<Mineral> minerals = mineralService.getMinerals();
        minerals.removeAll(getLearnedMinerals(learnerId));

        return minerals;
    }

    public List<Integer> getUnlearnedMineralIds(Integer learnerId) {
        return mineralSetToIdList(getUnlearnedMinerals(learnerId));
    }

    public Mineral getRandomUnlearnedMineral(Integer learnerId) {
        Set<Mineral> mineralSet = getUnlearnedMinerals(learnerId);
        return getRandomSetElement(mineralSet);
    }

    // DELETE
    @Transactional
    public boolean unlearnMineral(Integer learnerId, Integer mineralId){
        Learner learner = getLearnerById(learnerId);
        Mineral mineral = mineralService.getMineralById(mineralId);

        return learner.learnedMinerals.remove(mineral);
    }

    // Helper Functions
    private IllegalStateException learnerNotFoundException(Integer learnerId) {
        return new IllegalStateException("learner with id "
                + learnerId + " does not exist");
    }

    private IllegalStateException invalidLogin() {
        return new IllegalStateException("Invalid credentials");
    }

    private Learner getLearnerById(Integer learnerId) {
        return learnerRepository.findById(learnerId)
                .orElseThrow(() -> learnerNotFoundException(learnerId));
    }

    private List<Integer> mineralSetToIdList(Set<Mineral> minerals) {
        List<Integer> idList = new ArrayList<>();

        for (Mineral mineral : minerals) {
            idList.add(mineral.getId());
        }

        return idList;
    }

    private Mineral getRandomSetElement(Set<Mineral> mineralSet) {
        int setSize = mineralSet.size();
        if (setSize == 0) {
            throw new IllegalStateException("no minerals available");
        }

        int randomNumber = random.nextInt(setSize);
        int index = 0;
        for (Mineral mineral : mineralSet) {
            if (index == randomNumber) {
                return mineral;
            }

            index++;
        }

        throw new IllegalStateException("I thought this was unreachable");
    }
}