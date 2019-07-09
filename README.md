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
    - assignee:String

## Routes

* @GetMapping("/tasks")
        
        public ResponseEntity<Task> getTasks() 

* @PostMapping("/tasks")
      
        public ResponseEntity<Task> createTask( String title, String description, String assignee)
        
* @PutMapping("/tasks/{id}/state")
      
        public void advanceTask(@PathVariable String id)
        
* @GetMapping("/users/{name}/tasks")
      
        public ResponseEntity<Task> getUserTasks(@PathVariable String name)

* @PutMapping("/tasks/{id}/assign/{assignee}")
      
        public String assignTask(@PathVariable UUID id, @PathVariable String assignee)

* @GetMapping("/tasks/{id}")
        
        public ResponseEntity<Task> getTask(@PathVariable UUID id)
        
* @PostMapping("/tasks/{id}/images")

        public ResponseEntity<Task> uploadImage(@PathVariable UUID id, @RequestPart(value = "file") MultipartFile file)                 

### Deployed application:
[http://Taskmaster-env.csiiybveap.us-east-1.elasticbeanstalk.com](http://Taskmaster-env.csiiybveap.us-east-1.elasticbeanstalk.com)