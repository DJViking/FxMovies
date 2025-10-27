package no.smeaworks.movies.importer;

import no.smeaworks.movies.jaxb.CollectionType;
import no.smeaworks.movies.jaxb.DVDType;
import no.smeaworks.movies.jaxb.GenresType;
import no.smeaworks.movies.jaxb.MediaTypesType;

import javax.xml.bind.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ImporterDvdProfilerCsv {

    private final Unmarshaller unmarshaller;

    public ImporterDvdProfilerCsv() throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(CollectionType.class);
        unmarshaller = context.createUnmarshaller();
    }

    public void readCollection() throws JAXBException, IOException {
        final Path collectionFile = Paths.get("Collection.xml");
        final JAXBElement<CollectionType> element = (JAXBElement<CollectionType>) unmarshaller.unmarshal(collectionFile.toFile());

        final CollectionType collection = element.getValue();
        final List<DVDType> movies = collection.getDVD();
        movies.sort(Comparator.comparing(DVDType::getTitle));

        final List<String> movieList = new ArrayList<>();
        movieList.add("Type;Tittel;Utgave;År;Media");

        movies.forEach(movie -> {
            final GenresType genres = movie.getGenres();
            boolean isSeries = false;
            for (Serializable s : genres.getContent()) {
                if (s instanceof JAXBElement<?>) {
                    final JAXBElement genreElement = (JAXBElement) s;
                    final String genre = (String) genreElement.getValue();
                    if (genre.equals("Television")) {
                        isSeries = true;
                        break;
                    }
                }
            }

            final StringBuilder stringBuilder = new StringBuilder();
            if (isSeries) {
                stringBuilder.append("TV-Serie").append(";");
            } else {
                stringBuilder.append("Film").append(";");
            }

            final MediaTypesType mediaTypes = movie.getMediaTypes();
            final String medias = createMedias(mediaTypes);

            stringBuilder.append("\"").append(movie.getTitle()).append("\"").append(";");
            stringBuilder.append(movie.getDistTrait()).append(";");
            stringBuilder.append(movie.getProductionYear()).append(";");
            stringBuilder.append(medias).append(";");
            movieList.add(stringBuilder.toString());
        });

        Files.write(Paths.get("Collection.csv"), movieList);

        final int total = (movieList.size());
        System.out.println(total);
    }

    private String createMedias(final MediaTypesType mediaTypes) {
        final boolean ultra = Boolean.parseBoolean(mediaTypes.getUltraHD());
        final boolean bluray = Boolean.parseBoolean(mediaTypes.getBluRay());
        final boolean dvd = Boolean.parseBoolean(mediaTypes.getDVD());

        final StringBuilder mediaBuilder = new StringBuilder();
        if (ultra) {
            mediaBuilder.append("UltraHD BluRay");
        }
        if (bluray) {
            if (ultra) {
                mediaBuilder.append("/");
            }
            mediaBuilder.append("BluRay");
        }
        if (dvd) {
            if (bluray) {
                mediaBuilder.append("/");
            }
            mediaBuilder.append("DVD");
        }

        return mediaBuilder.toString();
    }

}
