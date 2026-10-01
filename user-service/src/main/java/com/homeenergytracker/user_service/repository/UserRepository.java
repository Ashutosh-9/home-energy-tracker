package com.homeenergytracker.user_service.repository;

import com.homeenergytracker.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
