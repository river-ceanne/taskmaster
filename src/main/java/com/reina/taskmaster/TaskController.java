package com.reina.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.text.ParseException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    protected static String[] state = {"Available", "Assigned","Accepted", "Finished"};

    @GetMapping("/tasks")
    public ResponseEntity<Task> getTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID id) {
        Task task = taskRepository.findById(id);
        return new ResponseEntity(task, HttpStatus.OK);
    }


//    @GetMapping("/task/{id}")
//    public String getTask(@PathVariable String id) {
//        return "redirect:/tasks";
//    }


    @PostMapping("/tasks")
    public void createTask( String title, String description) {
        Task task = new Task(title,description);
        taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}/state")
    public String advanceTask(@PathVariable UUID id) {
        Task task = taskRepository.findById(id);
        String newStatus = "--";

        if(task.getStatus() != state[3]){
            for (int i = 0; i < state.length; i++) {
                if (task.getStatus() == state[i]) {
                    i++;
                    newStatus = state[i];
                    break;
                }
            }

        }

        task.setStatus(newStatus);
//        task.setStatus(state[0]);
        taskRepository.save(task);

        return "redirect:/tasks";

    }


}
