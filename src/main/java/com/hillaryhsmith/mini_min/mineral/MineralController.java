package com.hillaryhsmith.mini_min.mineral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping
public class MineralController {
    private final MineralService mineralService;

    @Autowired
    public MineralController(MineralService mineralService) {
        this.mineralService = mineralService;
    }

    @GetMapping(path="/minerals")
    public List<Mineral> getMinerals() {
        return mineralService.getMinerals();
    }

    @GetMapping(path="/minerals/{id}")
    public Mineral getMineralById(@PathVariable("id") Integer id) {
        return mineralService.getMineralById(id);
    }

    @GetMapping(path="/minerals/{name}")
    public Mineral getMineralByName(@PathVariable("name") String name) {
        return mineralService.getMineralByName(name);
    }

//
//    @PostMapping(path="/minerals")
//    @ResponseBody
//    public ResponseEntity registerNewMineral(@RequestBody Mineral mineral) {
//        learnerService.registerNewLearner(learner);
//        return new ResponseEntity<>("learner successfully registered", HttpStatus.CREATED);
//    }
//
//    @PatchMapping(path="learners/{learnerId}/email")
//    @ResponseBody
//    public ResponseEntity updateLearnerEmail (@PathVariable("learnerId") Integer id, @RequestBody String newEmail) {
//        learnerService.updateLearnerEmail(id, newEmail);
//        return new ResponseEntity<>("email successfully updated", HttpStatus.OK);
//    }
//
//    @PatchMapping(path="learners/{learnerId}/password")
//    @ResponseBody
//    public ResponseEntity updateLearnerPassword(@PathVariable("learnerId") Integer id, @RequestBody String newPassword) {
//        learnerService.updateLearnerPassword(id, newPassword);
//        return new ResponseEntity<>("password successfully updated", HttpStatus.OK);
//    }
//
//    @DeleteMapping(path="learners/{learnerId}")
//    @ResponseBody
//    public ResponseEntity deleteLearner(@PathVariable("learnerId") Integer id){
//        learnerService.deleteLearner(id);
//        return new ResponseEntity<>("learner successfully deleted", HttpStatus.OK);
//    }
}