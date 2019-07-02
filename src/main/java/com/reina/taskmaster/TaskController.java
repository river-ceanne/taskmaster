package com.reina.taskmaster;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.text.ParseException;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    private DynamoDBMapper dynamoDBMapper;

    @GetMapping("/tasks")
    public String getTenantAddPage() {
        return "tenant";
    }

    @PostMapping("/tasks")
    public void createTenant( String title, String description) throws ParseException {
        Task task = new Task(title,description);
        taskRepository.save(task);
    }



    @PutMapping("/tasks/{id}/state")
    public void editTenant(@PathVariable String id) throws ParseException {
        Optional<Task> task = taskRepository.findById(id);
//        taskRepository.save(task);
    }


}
