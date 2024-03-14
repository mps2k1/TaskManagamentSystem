package mps2k1.taskmanagementsystem.service;
import mps2k1.taskmanagementsystem.dto.TaskDTO;

import java.util.List;


public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO);
    void markTaskAsCompleted(Long id);
    List<TaskDTO> getUserTasks(Long userId);
    List<TaskDTO> getUserTasksByPriority(Long userId, int priority);


}
