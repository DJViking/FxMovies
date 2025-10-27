package no.smeaworks.movies.service;

import no.smeaworks.movies.domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesServiceImpl implements MoviesService {

    @Override
    public List<Movie> findAllMovies() {
        return new ArrayList<>();
    }

}
