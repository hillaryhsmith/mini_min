package com.hillaryhsmith.mini_min.mineral;

import com.hillaryhsmith.mini_min.photo.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "https://mini-min-front-end.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping
public class MineralController {
    private final MineralService mineralService;

    @Autowired
    public MineralController(MineralService mineralService) {
        this.mineralService = mineralService;
    }

    // Mineral API Routes

    @PostMapping(path="/minerals")
    @ResponseBody
    public ResponseEntity addNewMineral(@RequestBody Mineral mineral) {
        mineralService.addNewMineral(mineral);
        return new ResponseEntity<>("mineral successfully added", HttpStatus.CREATED);
    }

    @GetMapping(path="/minerals")
    public Set<Mineral> getMinerals() {
        return mineralService.getMinerals();
    }

    @GetMapping(path="/minerals/{mineralId}")
    public Mineral getMineralById(@PathVariable("mineralId") Integer mineralId) {
        return mineralService.getMineralById(mineralId);
    }

    @GetMapping(path="/minerals/name/{name}")
    public Mineral getMineralByName(@PathVariable("name") String name) {
        return mineralService.getMineralByName(name);
    }

    @PutMapping(path="minerals/{mineralId}")
    @ResponseBody
    public ResponseEntity updateMineralEntry (@PathVariable("mineralId") Integer mineralId, @RequestBody Mineral mineralDetails) {
        mineralService.updateMineralEntry(mineralId, mineralDetails);
        return new ResponseEntity<>("mineral entry successfully updated", HttpStatus.OK);
    }

    @DeleteMapping(path="minerals/{mineralId}")
    @ResponseBody
    public ResponseEntity deleteMineral(@PathVariable("mineralId") Integer mineralId) {
        mineralService.deleteMineral(mineralId);
        return new ResponseEntity<>("mineral successfully deleted", HttpStatus.OK);
    }

    // Mineral-Photo API Routes

    @PostMapping(path="/minerals/{mineralName}/photos")
    @ResponseBody
    public ResponseEntity addPhotoForMineralByName(@PathVariable("mineralName") String mineralName, @RequestBody String photoLocation) {
        mineralService.addPhotoForMineralByName(mineralName, photoLocation);
        return new ResponseEntity<>("photo successfully added for mineral", HttpStatus.CREATED);
    }

    @GetMapping(path="/minerals/{mineralId}/photos")
    @ResponseBody
    public Set<Photo> getPhotosForMineral(@PathVariable("mineralId") Integer mineralId) {
        return mineralService.getPhotosForMineral(mineralId);
    }

    @GetMapping(path="/minerals/{mineralId}/randomPhoto")
    public Photo getRandomPhoto(@PathVariable("mineralId") Integer mineralId) {
        return mineralService.getRandomPhoto(mineralId);
    }

    @GetMapping(path="/minerals/{mineralId}/{photoId}/randomDifferentPhoto")
    public Photo getDifferentRandomPhoto(@PathVariable("mineralId") Integer mineralId,
                                         @PathVariable("photoId") Integer photoId) {
        return mineralService.getDifferentRandomPhoto(mineralId, photoId);
    }

}