package com.example.WatchItNow.rest;

import com.example.WatchItNow.dto.BaseDTO;
import com.example.WatchItNow.dto.MovieDTO;
import com.example.WatchItNow.models.Movie;
import com.example.WatchItNow.models.Platform;
import com.example.WatchItNow.services.MovieService;

import com.example.WatchItNow.services.PlatformService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/movie")
@RestController
public class MovieRestController {
    private MovieService movieService;
    private PlatformService platformService;
    private final ModelMapper modelMapper;

    @Autowired
    private MovieRestController(MovieService movieService, PlatformService platformService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.platformService = platformService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<MovieDTO> list() {
        List<Movie> movies = movieService.findAll();
        return movies
                .stream()
                .map(this::convertToMovieDTO)
                .collect(Collectors.toList());
    }

    private MovieDTO convertToMovieDTO(Movie movie) {
        final MovieDTO result = modelMapper.map(movie, MovieDTO.class);
        return result;
    }

    @GetMapping("/{movieId}")
    public MovieDTO getMovie(@PathVariable(name = "movieId") long movieId) {
        Optional<Movie> optionalMovie = movieService.getEntity(movieId);
        return optionalMovie.map(this::convertToMovieDTO).orElse(null);
    }

    @PostMapping()
    public BaseDTO<Movie> create(@RequestBody MovieDTO newMovie) {
        Movie movie = convertMovieDTOtoModel(newMovie);
        return convertToMovieDTO(movieService.create(movie));
    }

    private Movie convertMovieDTOtoModel(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        return movie;
    }

    @PutMapping()
    public BaseDTO<Movie> update(@RequestBody MovieDTO updatedMovie) {
        Movie movie = convertMovieDTOtoModel(updatedMovie);
        return convertToMovieDTO(movieService.update(movie));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> delete(@PathVariable long movieId) {
        boolean isRemoved = movieService.remove(movieId);
        String deletedMessage = "Movie with id: " + movieId + " was deleted";
        String notDeletedMessage = "Movie with id: " + movieId + " does not exist";
        return isRemoved ? new ResponseEntity(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }

    @PutMapping("/{movieId}/platforms/{platformId}")
    public ResponseEntity<String> addMovieToPlatform(@PathVariable long movieId, @PathVariable long platformId) {
        Optional<Movie> optionalMovie = movieService.getEntity(movieId);
        Optional<Platform> optionalPlatform = platformService.getEntity(platformId);

        if (optionalMovie.isPresent() && optionalPlatform.isPresent()) {
            Movie movie = optionalMovie.get();
            Platform platform = optionalPlatform.get();

            // Add the movie to the platform
            movie.getMoviePlatforms().add(platform);
            platform.getMovies().add(movie);

            movieService.update(movie);
            platformService.update(platform);
            return new ResponseEntity<>("Movie added to Platform.", HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>("Movie or Platform not found.", HttpStatusCode.valueOf(404));
        }
    }
}
