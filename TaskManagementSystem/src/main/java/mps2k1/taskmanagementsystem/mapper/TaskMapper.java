package mps2k1.taskmanagementsystem.mapper;
import mps2k1.taskmanagementsystem.dto.TaskDTO;
import mps2k1.taskmanagementsystem.entity.Task;
import mps2k1.taskmanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {
    private final UserRepository userRepository;

    public TaskMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TaskDTO mapToDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .description(task.getDescription())
                .priority(task.getPriority())
                .createdTime(task.getCreatedTime())
                .completed(task.isCompleted())
                .finishedTime(task.getFinishedTime())
                .build();
    }
    public Task mapToDao(TaskDTO dto) {
        Task task = Task.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .createdTime(dto.getCreatedTime())
                .completed(dto.isCompleted())
                .finishedTime(dto.getFinishedTime())
                .build();

        return task;
    }
}