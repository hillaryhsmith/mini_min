package com.hillaryhsmith.mini_min.photo;

import com.hillaryhsmith.mini_min.learner.Learner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping

public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @DeleteMapping(path="/photos/{photoId}")
    @ResponseBody
    public ResponseEntity deletePhoto(@PathVariable("photoId") Integer photoId) {
        photoService.deletePhoto(photoId);
        return new ResponseEntity<>("photo successfully deleted", HttpStatus.OK);
    }
}
