package no.smeaworks.movies.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import no.smeaworks.movies.domain.Movie;
import no.smeaworks.movies.service.MoviesService;

public class MoviesController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem quitMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Menu helpMenu;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ListView<Movie> movieListView;

    @FXML
    private StackPane contentPane;

    @FXML
    private ImageView selectedMovieImageView;

    private ObservableList<Movie> movies;

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
        this.movies = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        List<Movie> foundMovies = moviesService.findAllMovies();
        movies.addAll(foundMovies);
    }

    @FXML
    public void openAbout() {
    }

    @FXML
    public void quit() {
    }

}
