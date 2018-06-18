package no.smeaworks.movies;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class MoviesTest {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void movies() {
        System.out.println("movies");
        logger.log(Level.INFO, "MoviesTest movies");
        int i = 2;
        int j = 2;
        assertEquals(i, j);
    }

}
