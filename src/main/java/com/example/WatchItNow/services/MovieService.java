package com.example.WatchItNow.services;

import com.example.WatchItNow.dto.BaseDTO;
import com.example.WatchItNow.dto.MovieDTO;
import com.example.WatchItNow.models.Movie;
import com.example.WatchItNow.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovieService extends BaseService<Movie> {
    @Autowired
    MovieRepo movieRepo;

    @Override
    protected JpaRepository<Movie,Long> getRepo() {return movieRepo;}

}
