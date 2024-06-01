package com.todo.todo.task;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    // Runs on start, populates table with a sampleTask if table is empty
    @Bean
    CommandLineRunner commandLineRunner(TaskRespository repository) {
        return args -> {
            TaskItem sampleTask = new TaskItem("Sample Task");
            
            if (repository.findAll().isEmpty()){
                repository.saveAll(
                    new ArrayList<TaskItem>(){{
                        add(sampleTask);
                    }}
                );
            }
        };
    }

}
