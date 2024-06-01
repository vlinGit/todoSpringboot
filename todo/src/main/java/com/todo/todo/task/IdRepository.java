package com.todo.todo.task;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

// Repositories are for data accessing
@Repository
@Transactional
public interface IdRepository extends JpaRepository<IdTracker, Integer>{
    @Query("SELECT t FROM IdTracker t WHERE t.Id = 1")
    Optional<IdTracker> getValue();
    
    @Modifying
    @Query("UPDATE IdTracker t SET t.value = :value WHERE t.id = :id")
    int updateValue(int id, int value);
}
