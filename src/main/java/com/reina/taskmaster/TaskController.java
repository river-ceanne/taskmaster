package com.reina.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.text.ParseException;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;


    @PutMapping("/tasks/{id}/state")
    public void editTenant(@PathVariable String id) throws ParseException {
        Optional<Task> task = taskRepository.findById(id);
//        taskRepository.save(task);
    }


}
