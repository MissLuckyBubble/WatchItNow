package com.example.WatchItNow.repo;

import com.example.WatchItNow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
