package com.gym.repository;

import com.gym.model.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface TrainerRepository extends MongoRepository<Trainer, Integer> {
    @Query("{ 'is_active': ?0 }")
    List<Trainer> findByIs_active(String isActive);
}
