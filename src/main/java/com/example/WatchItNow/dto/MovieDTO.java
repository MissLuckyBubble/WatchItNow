package com.example.WatchItNow.dto;

import com.example.WatchItNow.models.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO extends BaseDTO<Movie> {
    private String title;
    private LocalDate release_date;
    private String description;
    private String trailer;
    private String poster_url;
    private String where_to_watch;

    public MovieDTO(Movie movie){super(movie);}
    @Override
    public BaseDTO<Movie> convertToDTO(Movie entity) {
        setId(entity.getId());
        setTitle(entity.getTitle());
        setDescription(getDescription());
        setRelease_date(getRelease_date());
        setTrailer(getTrailer());
        setPoster_url(getPoster_url());
        setWhere_to_watch(getWhere_to_watch());
        return this;
    }
}
