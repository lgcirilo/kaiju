package com.lgcirilo.kaiju.repositories;

import com.lgcirilo.kaiju.entities.Task;
import com.lgcirilo.kaiju.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTop10ByUser_Id(Long userId);

    List<Task> findAllByUser_Id(Long userId);
}
