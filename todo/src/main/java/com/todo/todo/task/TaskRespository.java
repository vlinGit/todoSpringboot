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
public interface TaskRespository extends JpaRepository<TaskItem, Integer>{
    @Query("SELECT t FROM TaskItem t WHERE t.name = ?1")
    Optional<TaskItem> findTaskByName(String name);
    
    @Modifying
    @Query("UPDATE TaskItem t SET t.name = :name, t.done = :done WHERE t.id = :id")
    int updateTaskById(int id, String name, boolean done);
}
