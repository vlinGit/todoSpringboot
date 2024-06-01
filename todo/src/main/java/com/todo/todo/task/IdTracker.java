package com.todo.todo.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "idTracker")
public class IdTracker {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="VALUE")
    private int value;

    public IdTracker(){}

    public IdTracker(int value){
        this.value = value;
    }

    public int getId(){
        return this.id;
    }

    public int getValue(){
        return this.value;
    }
}
