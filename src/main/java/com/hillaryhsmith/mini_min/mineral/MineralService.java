package com.hillaryhsmith.mini_min.mineral;

import com.hillaryhsmith.mini_min.learner.Learner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MineralService {

    private final MineralRepository mineralRepository;

    @Autowired
    public MineralService(MineralRepository mineralRepository) {this.mineralRepository = mineralRepository;}

    public List<Mineral> getMinerals() {
        return mineralRepository.findAll();
    }

    public Mineral getMineralById(Integer id) {
        return mineralRepository.findById(id)
                .orElseThrow(() -> mineralNotFoundException(id));
    }

    public Mineral getMineralByName(String name) {
        return mineralRepository.findMineralByName(name)
                .orElseThrow(() -> mineralNotFoundException(name));
    }

    public void addNewMineral(Mineral mineral) {
        Optional<Mineral> mineralByName = mineralRepository
                .findMineralByName(mineral.getName());
        if (mineralByName.isPresent()) {
            throw new IllegalStateException("mineral is already in system");
        }
       mineralRepository.save(mineral);
    }

    @Transactional
    public void updateMineralEntry(Integer id, Mineral mineralDetails){
        Mineral updateMineral = mineralRepository.findById(id)
                .orElseThrow(() -> mineralNotFoundException(id));
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

    public void deleteMineral(Integer id){
        if (!mineralRepository.existsById(id)){
            throw mineralNotFoundException(id);
        }
        mineralRepository.deleteById(id);
    }

    private IllegalStateException mineralNotFoundException(Integer id) {
        return new IllegalStateException("mineral with id "
                + id + " does not exist");
    }

    private IllegalStateException mineralNotFoundException(String name) {
        return new IllegalStateException("mineral with name "
                + name + " does not exist");
    }
}
