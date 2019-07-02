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

    protected final String[] state = {"Available", "Assigned","Accepted", "Finished"};

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

    @PostMapping("/tasks")
    public void createTask( String title, String description) {
        Task task = new Task(title,description);
        taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}/state")
    public void advanceTask(@PathVariable String id) throws ParseException {
        Optional<Task> task = taskRepository.findById(id);

        System.out.println(task);

        task.ifPresent( value -> {
//            for (int i = 0; i < state.length; i++) {
//                if (task.get().getStatus() == state[i]) {
//                    task.get().setStatus(state[++i]);
//                    break;
//                }
//            }
            task.get().setStatus("Assigned");

            taskRepository.save(task.get());
        });

    }


}
