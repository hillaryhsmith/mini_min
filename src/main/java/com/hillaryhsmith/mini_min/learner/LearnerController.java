package com.hillaryhsmith.mini_min.learner;

import com.hillaryhsmith.mini_min.mineral.Mineral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping

public class LearnerController {
    private final LearnerService learnerService;

    @Autowired
    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    // Learner API Routes

    @PostMapping(path="/learners")
    @ResponseBody
    public ResponseEntity registerNewLearner(@RequestBody Learner learner) {
        learnerService.registerNewLearner(learner);
        return new ResponseEntity<>("learner successfully registered", HttpStatus.CREATED);
    }

    @GetMapping(path="/learners")
    public List<Learner> getLearners() {
        return learnerService.getLearners();
    }

    @PatchMapping(path="/learners/{learnerId}/email")
    @ResponseBody
    public ResponseEntity updateLearnerEmail (@PathVariable("learnerId") Integer id, @RequestBody String newEmail) {
        learnerService.updateLearnerEmail(id, newEmail);
        return new ResponseEntity<>("email successfully updated", HttpStatus.OK);
    }

    @PatchMapping(path="/learners/{learnerId}/password")
    @ResponseBody
    public ResponseEntity updateLearnerPassword(@PathVariable("learnerId") Integer id, @RequestBody String newPassword) {
        learnerService.updateLearnerPassword(id, newPassword);
        return new ResponseEntity<>("password successfully updated", HttpStatus.OK);
    }

    @DeleteMapping(path="/learners/{learnerId}")
    @ResponseBody
    public ResponseEntity deleteLearner(@PathVariable("learnerId") Integer id){
        learnerService.deleteLearner(id);
        return new ResponseEntity<>("learner successfully deleted", HttpStatus.OK);
    }

    // Learner-Mineral API Routes

    @PostMapping(path="/learners/{learnerId}/{mineralId}")
    @ResponseBody
    public ResponseEntity learnMineral(@PathVariable("learnerId") Integer learnerId,
                                       @PathVariable("mineralId") Integer mineralId) {
        learnerService.learnMineral(learnerId, mineralId);
        return new ResponseEntity("mineral learned", HttpStatus.CREATED);
    }

    @GetMapping(path="/learners/{learnerId}/learnedMinerals")
    public Set<Mineral> getLearnedMinerals(@PathVariable("learnerId") Integer learnerId) {
        return learnerService.getLearnedMinerals(learnerId);
    }

    @GetMapping(path="/learners/{learnerId}/learnedMineralIds")
    public List<Integer> getLearnedMineralIds(@PathVariable("learnerId") Integer learnerId) {
        return learnerService.getLearnedMineralIds(learnerId);
    }

    @GetMapping(path="/learners/{learnerId}/randomLearnedMineral")
    public Mineral getRandomLearnedMineral(@PathVariable("learnerId") Integer learnerId) {
        return learnerService.getRandomLearnedMineral(learnerId);
    }

    @GetMapping(path="/learners/{learnerId}/{mineralId}/randomDifferentLearnedMineral")
    public Mineral getDifferentRandomLearnedMineral(@PathVariable("learnerId") Integer learnerId,
                                                    @PathVariable("mineralId") Integer mineralId) {
        return learnerService.getDifferentRandomLearnedMineral(learnerId, mineralId);
    }

    @GetMapping(path="/learners/{learnerId}/unlearnedMinerals")
    public Set<Mineral> getUnlearnedMinerals(@PathVariable("learnerId") Integer learnerId) {
        return learnerService.getUnlearnedMinerals(learnerId);
    }

    @GetMapping(path="/learners/{learnerId}/unlearnedMineralIds")
    public List<Integer> getUnlearnedMineralIds(@PathVariable("learnerId") Integer learnerId) {
        return learnerService.getUnlearnedMineralIds(learnerId);
    }

    @GetMapping(path="/learners/{learnerId}/randomUnlearnedMineral")
    public Mineral getRandomUnlearnedMineral(@PathVariable("learnerId") Integer learnerId) {
        return learnerService.getRandomUnlearnedMineral(learnerId);
    }

    @DeleteMapping(path="/learners/{learnerId}/{mineralId}/unlearnMineral")
    @ResponseBody
    public ResponseEntity unlearnMineral(@PathVariable("learnerId") Integer learnerId,
                                         @PathVariable("mineralId") Integer mineralId) {
        if (learnerService.unlearnMineral(learnerId, mineralId)) {
            return new ResponseEntity<>("mineral unlearned", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("relationship not found", HttpStatus.BAD_REQUEST);
        }
    }
}

