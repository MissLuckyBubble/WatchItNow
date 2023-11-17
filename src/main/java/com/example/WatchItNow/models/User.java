package com.example.WatchItNow.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User extends MainModel {

    @Column(nullable = false, length = 255)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false, length = 255)
    private String email;
    @Column(name = "last_logged_at")
    private LocalDateTime lastLoggedAt;
}
