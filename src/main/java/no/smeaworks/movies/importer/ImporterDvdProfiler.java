package no.smeaworks.movies.importer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.*;

import no.smeaworks.movies.jaxb.CollectionType;
import no.smeaworks.movies.jaxb.DVDType;
import no.smeaworks.movies.jaxb.GenresType;
import no.smeaworks.movies.jaxb.MediaTypesType;

public class ImporterDvdProfiler {

    private final Unmarshaller unmarshaller;

    public ImporterDvdProfiler() throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(CollectionType.class);
        unmarshaller = context.createUnmarshaller();
    }

    public void readCollection() throws JAXBException, IOException {
        final Path collectionFile = Paths.get("Collection.xml");
        final JAXBElement<CollectionType> element = (JAXBElement<CollectionType>) unmarshaller.unmarshal(collectionFile.toFile());

        final CollectionType collection = element.getValue();
        final List<DVDType> movies = collection.getDVD();
        movies.sort(Comparator.comparing(DVDType::getTitle));
        System.out.println("Antall " + movies.size());

        final List<String> moviesDVDList = new ArrayList<>();
        final List<String> moviesBDList = new ArrayList<>();
        final List<String> moviesBDUltraList = new ArrayList<>();
        final List<String> seriesDVDList = new ArrayList<>();
        final List<String> seriesBDList = new ArrayList<>();

        movies.forEach(movie -> {
            final MediaTypesType mediaTypes = movie.getMediaTypes();
            final boolean ultra = Boolean.parseBoolean(mediaTypes.getUltraHD());
            final boolean bluray = Boolean.parseBoolean(mediaTypes.getBluRay());
            final boolean dvd = Boolean.parseBoolean(mediaTypes.getDVD());

            final GenresType genres = movie.getGenres();
            final boolean isSeries = isSeries(genres);

            System.out.println(movie.getTitle() + " BD=" + mediaTypes.getBluRay() + " UHD=" + mediaTypes.getUltraHD() + " DVD=" + mediaTypes.getDVD());

            if (isSeries) {
                if (ultra || bluray) {
                    seriesBDList.add(createText(movie));
                } else {
                    seriesDVDList.add(createText(movie));
                }
            } else {
                if (dvd) {
                    moviesDVDList.add(createText(movie));
                } else if (ultra) {
                    moviesBDUltraList.add(createText(movie));
                } else if (bluray) {
                    moviesBDList.add(createText(movie));
                }
            }
        });

        Files.write(Paths.get("Collection-Movies-DVD.txt"), moviesDVDList);
        Files.write(Paths.get("Collection-Series-DVD.txt"), seriesDVDList);
        Files.write(Paths.get("Collection-Movies-BluRay.txt"), moviesBDList);
        Files.write(Paths.get("Collection-Movies-BluRayUHD.txt"), moviesBDUltraList);
        Files.write(Paths.get("Collection-Series-BluRay.txt"), seriesBDList);
    }

    private boolean isSeries(GenresType genres) {
        for (Serializable s : genres.getContent()) {
            if (s instanceof JAXBElement<?>) {
                final JAXBElement genreElement = (JAXBElement) s;
                final String genre = (String) genreElement.getValue();
                if (genre.equals("Television")) {
                    return true;
                }
            }
        }
        return false;
    }

    private String createText(DVDType movie) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(movie.getTitle());
        if (!movie.getDistTrait().isEmpty()) {
            stringBuilder.append(" (").append(movie.getDistTrait()).append(")");
        }
        stringBuilder.append(" (").append(movie.getProductionYear()).append(") ");

        return stringBuilder.toString();
    }

}
