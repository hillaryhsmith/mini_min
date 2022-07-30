package com.hillaryhsmith.mini_min.mineral;

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

    private IllegalStateException mineralNotFoundException(Integer id) {
        return new IllegalStateException("mineral with id "
                + id + " does not exist");
    }

    private IllegalStateException mineralNotFoundException(String name) {
        return new IllegalStateException("mineral with name "
                + name + " does not exist");
    }
}
