package com.example.WatchItNow.repo;

import com.example.WatchItNow.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
}
