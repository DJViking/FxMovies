module no.smeaworks.movies {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    /* Named modules */
    requires jfxtras.controls;
    requires com.jfoenix;

    /* Automatic modules */
    requires controlsfx;

    requires org.apache.logging.log4j;

    exports no.smeaworks.movies to javafx.graphics, javafx.fxml;

    opens no.smeaworks.movies to javafx.graphics, javafx.fxml;

}