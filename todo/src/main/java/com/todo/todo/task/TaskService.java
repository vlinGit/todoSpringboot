package com.todo.todo.task;

import java.util.List;

import org.springframework.stereotype.Service;

// Contains logic
@Service
public class TaskService {

	private final TaskRespository taskRepository;

	public TaskService(TaskRespository taskRespository){
		this.taskRepository = taskRespository;
	}

    public List<TaskItem> getTasks() {
		return taskRepository.findAll();
	}

	public TaskItem addTask(TaskItem taskItem) {
		boolean exists = taskRepository.existsById(taskItem.getId());

		if (exists){
			throw new IllegalStateException("Task with ID: " + taskItem.getId() + " exists");
		}

		return taskRepository.save(taskItem);
	}

	public void removeTask(int id) {
		boolean exists = taskRepository.existsById(id);

		if (!exists){
			throw new IllegalStateException("Task with ID: " + id + " does not exist");
		}

		taskRepository.deleteById(id);
	}

	public TaskItem updateTask(TaskItem taskItem){
		int id = taskItem.getId();
		String name = taskItem.getName();
		boolean done = taskItem.getDone();
		boolean exists = taskRepository.existsById(id);

		if (!exists){
			throw new IllegalStateException("Task with ID: " + id + "does not exist");
		}

		taskRepository.updateTaskById(id, name, done);
		return taskRepository.findById(id).get();
	}
}
