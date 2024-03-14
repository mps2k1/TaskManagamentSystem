package mps2k1.taskmanagementsystem.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mps2k1.taskmanagementsystem.enums.Priority;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class TaskDTO {
    private Long id;
    private String description;
    private Priority priority;
    private LocalDateTime createdTime;
    private Long userId;
    private boolean completed;
    private LocalDateTime finishedTime;
}
