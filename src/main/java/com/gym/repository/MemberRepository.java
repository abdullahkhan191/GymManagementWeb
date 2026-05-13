package com.gym.repository;

import com.gym.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface MemberRepository extends MongoRepository<Member, Integer> {
    List<Member> findByStatus(String status);

    @Query("{ 'full_name': { $regex: ?0, $options: 'i' } }")
    List<Member> findByFull_nameContainingIgnoreCase(String name);
}
