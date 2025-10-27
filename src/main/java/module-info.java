module no.smeaworks.movies {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires transitive javafx.graphics;
    requires javafx.media;

    requires transitive java.xml.bind;
    requires transitive com.sun.xml.bind;

    exports no.smeaworks.movies to javafx.graphics, javafx.fxml;

    opens no.smeaworks.movies to javafx.graphics, javafx.fxml;
    opens no.smeaworks.movies.jaxb to java.xml.bind;
    exports no.smeaworks.movies.controller to javafx.fxml, javafx.graphics;
    opens no.smeaworks.movies.controller to javafx.fxml, javafx.graphics;
    exports no.smeaworks.movies.service to javafx.fxml, javafx.graphics;
    opens no.smeaworks.movies.service to javafx.fxml, javafx.graphics;
    exports no.smeaworks.movies.domain to javafx.fxml, javafx.graphics;
    opens no.smeaworks.movies.domain to javafx.fxml, javafx.graphics;
}
