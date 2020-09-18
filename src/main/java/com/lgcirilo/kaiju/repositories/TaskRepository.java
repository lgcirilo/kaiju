package com.lgcirilo.kaiju.repositories;

import com.lgcirilo.kaiju.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
