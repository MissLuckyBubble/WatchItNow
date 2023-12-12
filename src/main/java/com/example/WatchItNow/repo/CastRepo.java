package com.example.WatchItNow.repo;

import com.example.WatchItNow.models.Cast;
import com.example.WatchItNow.models.Movie;
import com.example.WatchItNow.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CastRepo extends JpaRepository<Cast,Long> {
    boolean existsByMovieAndPerson(Movie movie, Person person);
    List<Cast> findAllByPersonId(Long PersonId);
}
