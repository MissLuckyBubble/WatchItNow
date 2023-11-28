package com.example.WatchItNow.dto;

import com.example.WatchItNow.models.Movie;
import com.example.WatchItNow.models.Platform;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO extends BaseDTO<Movie> {
    private String title;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate release_date;
    private String description;
    private String trailer;
    private String poster_url;
    private Set<Platform> moviePlatforms; // Store only the Platform IDs

    public MovieDTO(Movie movie){
        super(movie);
    }

    @Override
    public BaseDTO<Movie> convertToDTO(Movie entity) {
        setId(entity.getId());
        setTitle(entity.getTitle());
        setDescription(entity.getDescription());
        setRelease_date(entity.getRelease_date());
        setTrailer(entity.getTrailer());
        setPoster_url(entity.getPoster_url());
        setCreatedAt(entity.getCreatedAt());
        setUpdatedAt(entity.getUpdatedAt());

        setMoviePlatforms(entity.getMoviePlatforms()
                .stream()
                .map(Platform::new)
                .collect(Collectors.toSet()));
        return this;
    }


}
