package com.todo.todo.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todo")
public class TaskItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name="DONE")
    private boolean done;

    public TaskItem(){
    }

    public TaskItem(String name){
        this.name = name;
        this.done = false;
    }

    public TaskItem(String name, boolean done){
        this.name = name;
        this.done = done;
    }
    
    public void setDone(boolean done){
        this.done = done;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public boolean getDone(){
        return this.done;
    }
}
