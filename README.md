# Taskmaster

Java application made with SpringBoot and DynamoDB for task management purposes. 

## Technologies Used
* SpringBoot
* DynamoDB
* Elasic Beanstalk

## Tables
* Task
    - id :String
    - title :String
    - description :String
    - status :String

## Routes

* @GetMapping("/tasks")
        
        public ResponseEntity<Task> getTasks() 

* @PostMapping("/tasks")
      
        public void createTask( String title, String description)
        
* @PutMapping("/tasks/{id}/state")
      
        public void advanceTask(@PathVariable String id)
        
        

### Deployed application: