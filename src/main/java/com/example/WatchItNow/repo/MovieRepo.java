package com.example.WatchItNow.repo;

import com.example.WatchItNow.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie,Long> {
}
