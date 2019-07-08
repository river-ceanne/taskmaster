package com.reina.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    protected static String[] state = {"Available", "Assigned","Accepted", "Finished"};

    @CrossOrigin
    @GetMapping({"/tasks","/"})
    public ResponseEntity<Task> getTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID id) {
        Task task = taskRepository.findById(id);
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/users/{name}/tasks")
    public ResponseEntity<Task> getUserTasks(@PathVariable String name) {
        List<Task> tasks = taskRepository.findByAssignee(name);
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask( String title, String description, String assignee) {
        Task task;
        if(assignee != null){
            task = new Task(title,description,assignee);
        }else{
            task = new Task(title,description);
        }

        taskRepository.save(task);

        return new ResponseEntity(task, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/tasks/{id}/state")
    public String advanceTask(@PathVariable UUID id) {
        Task task = taskRepository.findById(id);

        if(task == null) return "redirect:/tasks";

        task.setStatus(nextStatus(task.getStatus()));
        taskRepository.save(task);

        return "redirect:/tasks";

    }

    @CrossOrigin
    @PutMapping("/tasks/{id}/assign/{assignee}")
    public String assignTask(@PathVariable UUID id, @PathVariable String assignee) {
        Task task = taskRepository.findById(id);

        if(task == null) return "redirect:/tasks";

        task.setStatus(state[1]);//set to Assigned state
        task.setAssignee(assignee);
        taskRepository.save(task);

        return "redirect:/tasks";

    }


    private String nextStatus(String status){
        String nextStatus = status;

        if(!status.equals(state[3])){
            for (int i = 0; i < state.length; i++) {
                if (status.equals(state[i])) {
                    return state[++i];
                }
            }

        }
        return nextStatus;
    }


}
