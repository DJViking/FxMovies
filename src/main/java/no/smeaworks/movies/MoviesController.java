package no.smeaworks.movies;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane contentPane;

    public MoviesController() {
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void openAbout() {
    }

    @FXML
    public void quit() {
    }

}
