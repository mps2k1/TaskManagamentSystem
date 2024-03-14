package mps2k1.taskmanagementsystem.controller;
import lombok.RequiredArgsConstructor;
import mps2k1.taskmanagementsystem.dto.TaskDTO;
import mps2k1.taskmanagementsystem.exception.TaskNotFoundException;
import mps2k1.taskmanagementsystem.exception.UserNotFoundException;
import mps2k1.taskmanagementsystem.service.imp.TaskServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskServiceImp taskServiceImp;
    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            taskServiceImp.createTask(taskDTO);
            return new ResponseEntity<>("Task created succes", HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> markTaskAsCompleted(Long id){
        try {
            taskServiceImp.markTaskAsCompleted(id);
            return new ResponseEntity<>("Task updated succes", HttpStatus.OK);
        }
        catch(TaskNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
