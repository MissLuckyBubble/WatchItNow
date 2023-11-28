package com.example.WatchItNow.repo;

import com.example.WatchItNow.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepo  extends JpaRepository<Platform,Long> {
}
