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

    @GetMapping(path="/minerals/name/{name}")
    public Mineral getMineralByName(@PathVariable("name") String name) {
        return mineralService.getMineralByName(name);
    }

    @PostMapping(path="/minerals")
    @ResponseBody
    public ResponseEntity addNewMineral(@RequestBody Mineral mineral) {
        mineralService.addNewMineral(mineral);
        return new ResponseEntity<>("mineral successfully added", HttpStatus.CREATED);
    }

    @PutMapping(path="minerals/{id}")
    @ResponseBody
    public ResponseEntity updateMineralEntry (@PathVariable("id") Integer id, @RequestBody Mineral mineralDetails) {
        mineralService.updateMineralEntry(id, mineralDetails);
        return new ResponseEntity<>("mineral entry successfully updated", HttpStatus.OK);
    }

    @DeleteMapping(path="minerals/{id}")
    @ResponseBody
    public ResponseEntity deleteMineral(@PathVariable("id") Integer id) {
        mineralService.deleteMineral(id);
        return new ResponseEntity<>("mineral successfully deleted", HttpStatus.OK);
    }
}