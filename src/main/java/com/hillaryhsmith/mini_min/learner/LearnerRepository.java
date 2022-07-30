package com.hillaryhsmith.mini_min.learner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearnerRepository
        extends JpaRepository<Learner, Integer> {
    @Query("SELECT s FROM Learner s WHERE s.username = ?1")
    Optional<Learner> findLearnerByUsername(String username);
}
