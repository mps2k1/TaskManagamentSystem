package mps2k1.taskmanagementsystem.repository;

import mps2k1.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
