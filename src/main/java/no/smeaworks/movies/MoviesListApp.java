package no.smeaworks.movies;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import no.smeaworks.movies.importer.ImporterDvdProfiler;
import no.smeaworks.movies.importer.ImporterDvdProfilerCsv;

public class MoviesListApp {

    public static void main(String[] args) throws JAXBException, IOException {
        ImporterDvdProfiler importer = new ImporterDvdProfiler();
        importer.readCollection();

        ImporterDvdProfilerCsv importerCsv = new ImporterDvdProfilerCsv();
        //importerCsv.readCollection();
    }

}
