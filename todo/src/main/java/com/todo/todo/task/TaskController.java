package com.todo.todo.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller has the api endpoints
@CrossOrigin(origins="*")
@RestController
@RequestMapping(path = "api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final IdService idService;
    
    @Autowired
    public TaskController(TaskService taskService, IdService idService){
        this.taskService = taskService;
        this.idService = idService;
    }

    @GetMapping
	public List<TaskItem> getTasks() {
        return taskService.getTasks();
	}
    
    @PostMapping("/newTask")
    public TaskItem addTask(@RequestBody TaskItem taskItem){
        int curId = idService.getValue();
        System.out.println(curId);
        idService.updateValue(curId + 1);
        return taskService.addTask(taskItem);
    }

    @DeleteMapping("{taskID}")
    public void removeTask(@PathVariable("taskID") int id) {
        taskService.removeTask(id);
    }

    @PutMapping("/updateTask")
    public TaskItem updateTask(@RequestBody TaskItem taskItem){
        return taskService.updateTask(taskItem);
    }

    // @PostMapping("/postId")
    // public void addTaks(@RequestBody IdTracker idTrack){
    //     idService.addValue(idTrack);
    // }

    @GetMapping("/latestId")
    public int getLatestId(){
        return idService.getValue();
    }
    
}
