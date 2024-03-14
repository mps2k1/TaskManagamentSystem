package mps2k1.taskmanagementsystem.service.imp;
import lombok.RequiredArgsConstructor;
import mps2k1.taskmanagementsystem.dto.TaskDTO;
import mps2k1.taskmanagementsystem.entity.Task;
import mps2k1.taskmanagementsystem.entity.UserEntity;
import mps2k1.taskmanagementsystem.exception.TaskNotFoundException;
import mps2k1.taskmanagementsystem.exception.UserNotFoundException;
import mps2k1.taskmanagementsystem.mapper.TaskMapper;
import mps2k1.taskmanagementsystem.repository.TaskRepository;
import mps2k1.taskmanagementsystem.repository.UserRepository;
import mps2k1.taskmanagementsystem.service.TaskService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class TaskServiceImp implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        UserEntity user = userRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id: " + taskDTO.getUserId() + " not found"));
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setUser(user);
        taskRepository.save(task);

        return taskMapper.mapToDTO(task);
    }
    public void markTaskAsCompleted(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new TaskNotFoundException("Task with id: " + id+ " not found"));
                task.setCompleted(true);
                task.setFinishedTime(LocalDateTime.now());
                taskRepository.save(task);
    }
    @Override
    public List<TaskDTO> getUserTasks(Long userId) {
        return taskRepository.findAll().
                stream()
                .filter(task -> task.getUser().getId().equals(userId))
                .map(taskMapper::mapToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<TaskDTO> getUserTasksByPriority(Long userId, int priority) {
       return getUserTasks(userId).stream()
                .filter(taskDTO -> taskDTO.getPriority().equals(priority))
                .collect(Collectors.toList());
    }
}
