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

    @Override
    protected Movie convertDTOtoModel(BaseDTO<Movie> baseDTO) {
        Movie movie = new Movie();
        MovieDTO movieDTO = (MovieDTO)baseDTO;
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setTrailer(movieDTO.getTrailer());
        movie.setRelease_date(movieDTO.getRelease_date());
        movie.setPoster_url(movieDTO.getPoster_url());
        movie.setWhere_to_watch(movieDTO.getWhere_to_watch());
        movie.setCreatedAt(movieDTO.getCreatedAt());
        movie.setUpdatedAt(movieDTO.getUpdatedAt());
        return movie;
    }

    @Override
    protected BaseDTO<Movie> convert(Movie entity) {
        return new MovieDTO(entity);
    }

    @Override
    protected void updateEntity(Movie entity, BaseDTO<Movie> baseDTO) {
        MovieDTO movieDTO = (MovieDTO)baseDTO;
        entity.setTitle(movieDTO.getTitle());
        entity.setDescription(movieDTO.getDescription());
        entity.setTrailer(movieDTO.getTrailer());
        entity.setRelease_date(movieDTO.getRelease_date());
        entity.setPoster_url(movieDTO.getPoster_url());
        entity.setWhere_to_watch(movieDTO.getWhere_to_watch());
        entity.setUpdatedAt(LocalDateTime.now());
    }


}
