package no.smeaworks.movies;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoviesApplication extends Application {

    private Logger logger = LogManager.getLogger(this.getClass());

    public static void main(String[] args) {
        MoviesApplication.launch(args);
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("movies.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        BorderPane root = (BorderPane) fxmlLoader.load();

        logger.log(Level.INFO, "Start application");

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
