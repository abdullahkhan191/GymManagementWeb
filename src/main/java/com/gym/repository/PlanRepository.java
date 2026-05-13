package com.gym.repository;

import com.gym.model.MembershipPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface PlanRepository extends MongoRepository<MembershipPlan, Integer> {
    @Query("{ 'is_active': ?0 }")
    List<MembershipPlan> findByIs_active(String isActive);
}
