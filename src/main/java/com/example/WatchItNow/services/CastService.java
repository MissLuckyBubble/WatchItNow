package com.example.WatchItNow.services;

import com.example.WatchItNow.models.Cast;
import com.example.WatchItNow.models.Movie;
import com.example.WatchItNow.models.Person;
import com.example.WatchItNow.repo.CastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CastService extends  BaseService<Cast> {
    @Autowired
    CastRepo castRepo;

    @Override
    protected JpaRepository getRepo() {
        return  castRepo;
    }

    public boolean existsByMovieAndPerson(Movie movie, Person person) {
        return castRepo.existsByMovieAndPerson(movie, person);
    }
}
