package com.todo.todo.task;

import org.springframework.stereotype.Service;

@Service
public class IdService {
    private IdRepository idRepository;

    public IdService(IdRepository idRepository){
        this.idRepository = idRepository;
    }

    // public void addValue(IdTracker idTrack){
    //     idRepository.save(idTrack);
    // }

    public int getValue(){
        return idRepository.getValue().get().getValue();
    }

    public void updateValue(int newValue){
        idRepository.updateValue(1, newValue);
    }
}
