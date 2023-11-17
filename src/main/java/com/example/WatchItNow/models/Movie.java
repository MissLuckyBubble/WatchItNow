package com.example.WatchItNow.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Movies")
public class Movie extends MainModel{
    @Column(nullable = false,length = 255)
    private String title;
    @Column(nullable = false)
    private LocalDate release_date;
    @Column(length = 255)
    private String trailer;
    @Column(nullable = false )
    private String description;
    @Column(nullable = false)
    private String poster_url;
    @Column()
    private String where_to_watch;
}
