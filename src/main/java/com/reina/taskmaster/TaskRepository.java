package com.reina.taskmaster;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableScan
public interface TaskRepository extends CrudRepository<Task, String> {


//    Optional<Task> findById(UUID id);
    Task findById(UUID id);
    List<Task> findByAssignee(String name);


}
