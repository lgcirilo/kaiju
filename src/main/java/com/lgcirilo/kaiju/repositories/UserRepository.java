package com.lgcirilo.kaiju.repositories;

import com.lgcirilo.kaiju.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
