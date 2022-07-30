package com.hillaryhsmith.mini_min.mineral;

import com.hillaryhsmith.mini_min.learner.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MineralRepository extends JpaRepository<Mineral, Integer> {
        @Query("SELECT s FROM Mineral s WHERE s.name = ?1")
        Optional<Mineral> findMineralByName(String name);
}
