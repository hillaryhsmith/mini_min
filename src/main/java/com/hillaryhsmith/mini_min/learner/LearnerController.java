package com.hillaryhsmith.mini_min.learner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping

public class LearnerController {
    private final LearnerService learnerService;

    @Autowired
    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @GetMapping(path="/learners")
    public List<Learner> getLearners() {
        return learnerService.getLearners();
    }

    @PostMapping(path="/learners")
    @ResponseBody
    public ResponseEntity registerNewLearner(@RequestBody Learner learner) {
        learnerService.registerNewLearner(learner);
        return new ResponseEntity<>("learner successfully registered", HttpStatus.CREATED);
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
}

