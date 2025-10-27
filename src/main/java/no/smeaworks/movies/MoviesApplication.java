package no.smeaworks.movies;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import no.smeaworks.movies.controller.MoviesController;
import no.smeaworks.movies.service.MoviesService;
import no.smeaworks.movies.service.MoviesServiceImpl;

public class MoviesApplication extends Application {

    public static void main(String[] args) {
        MoviesApplication.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("movies.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {

            @Override
            public Object call(Class<?> param) {
                MoviesService moviesService = new MoviesServiceImpl();
                MoviesController controller = new MoviesController(moviesService);
                return controller;
            }

        });

        final BorderPane root = fxmlLoader.load();
        final Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();

        //ImporterDvdProfiler importer = new ImporterDvdProfiler();
        //importer.readCollection();
    }

}
