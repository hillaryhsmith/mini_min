package com.hillaryhsmith.mini_min.mineral;

import com.hillaryhsmith.mini_min.learner.Learner;
import com.hillaryhsmith.mini_min.photo.Photo;
import com.hillaryhsmith.mini_min.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class MineralService {

    private final MineralRepository mineralRepository;

    private final PhotoService photoService;

    private final Random random;

    @Autowired
    public MineralService(MineralRepository mineralRepository, PhotoService photoService) {
        this.mineralRepository = mineralRepository;
        this.photoService = photoService;
        this.random = new Random();
    }

    // Mineral API Routes

    // POST
    @Transactional
    public void addNewMineral(Mineral mineral) {
        Optional<Mineral> mineralByName = mineralRepository
                .findMineralByName(mineral.getName());
        if (mineralByName.isPresent()) {
            throw new IllegalStateException("mineral is already in system");
        }
        mineralRepository.save(mineral);
    }

    // GET
    public Set<Mineral> getMinerals() {
        return mineralRepository.getAllMinerals();
    }

    public Mineral getMineralById(Integer mineralId) {
        return mineralRepository.findById(mineralId)
                .orElseThrow(() -> mineralNotFoundException(mineralId));
    }

    public Mineral getMineralByName(String name) {
        return mineralRepository.findMineralByName(name)
                .orElseThrow(() -> mineralNotFoundException(name));
    }

    // PUT
    @Transactional
    public void updateMineralEntry(Integer mineralId, Mineral mineralDetails) {
        Mineral updateMineral = mineralRepository.findById(mineralId)
                .orElseThrow(() -> mineralNotFoundException(mineralId));
        updateMineral.setColor(mineralDetails.getColor());
        updateMineral.setCrystalSystem(mineralDetails.getCrystalSystem());
        updateMineral.setDescription(mineralDetails.getDescription());
        updateMineral.setFormula(mineralDetails.getFormula());
        updateMineral.setHardness(mineralDetails.getHardness());
        updateMineral.setLustre(mineralDetails.getLustre());
        updateMineral.setMindatId(mineralDetails.getMindatId());
        updateMineral.setName(mineralDetails.getName());
        updateMineral.setPolymorphs(mineralDetails.getPolymorphs());
        updateMineral.setSignificance(mineralDetails.getSignificance());
        updateMineral.setSpecificGravity(mineralDetails.getSpecificGravity());

        mineralRepository.save(updateMineral);
    }

    // DELETE
    @Transactional
    public void deleteMineral(Integer mineralId) {
        Mineral mineral = getMineralById(mineralId);
        for (Learner learner : mineral.getLearnedBy()) {
            learner.getLearnedMinerals().remove(mineral);
        }
        mineralRepository.deleteById(mineralId);
    }

    // Mineral-Photo API Routes

    // POST
    @Transactional
    public void addPhotoForMineral(Integer mineralId, String photoLocation) {
        Mineral mineral = getMineralById(mineralId);

        photoService.addNewPhoto(mineral, photoLocation);
    }

    // GET
    public Set<Photo> getPhotosForMineral(Integer mineralId) {
        return getMineralById(mineralId).getPhotos();
    }

    // Helper Functions
    private IllegalStateException mineralNotFoundException(Integer mineralId) {
        return new IllegalStateException("mineral with id "
                + mineralId + " does not exist");
    }

    private IllegalStateException mineralNotFoundException(String name) {
        return new IllegalStateException("mineral with name "
                + name + " does not exist");
    }
}
