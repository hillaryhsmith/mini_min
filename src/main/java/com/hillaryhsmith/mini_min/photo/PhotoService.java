package com.hillaryhsmith.mini_min.photo;

import com.hillaryhsmith.mini_min.mineral.Mineral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    // Photo API Routes

    // POST
    @Transactional
    public void addNewPhoto(Mineral mineral, String photoLocation) {
        Photo photo = new Photo();
        photo.setMineral(mineral);
        photo.setLocation(photoLocation);
        photoRepository.save(photo);
    }

    // DELETE
    @Transactional
    public void deletePhoto(Integer photoId) {
        if (!photoRepository.existsById(photoId)) {
            throw photoNotFoundException(photoId);
        }

        photoRepository.deleteById(photoId);

    }

    // Helper Functions
    private IllegalStateException photoNotFoundException(Integer photoId) {
        return new IllegalStateException("photo with id "
                + photoId + " does not exist");
    }
}
