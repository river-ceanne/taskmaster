package com.reina.taskmaster;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    private DynamoDBMapper dynamoDBMapper;

    @GetMapping("/tasks")
    public ResponseEntity<Task> getTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public void createTask( String title, String description) {
        Task task = new Task(title,description);
        taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}/state")
    public void advanceTask(@PathVariable String id) throws ParseException {
        Optional<Task> task = taskRepository.findById(id);

//        taskRepository.save(task);
    }


}
