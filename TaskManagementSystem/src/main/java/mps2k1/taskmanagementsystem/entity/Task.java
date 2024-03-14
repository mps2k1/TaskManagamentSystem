package mps2k1.taskmanagementsystem.entity;
import jakarta.persistence.*;
import lombok.*;
import mps2k1.taskmanagementsystem.enums.Priority;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDateTime createdTime =LocalDateTime.now();
    private boolean completed = false;
    private LocalDateTime finishedTime =null;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
