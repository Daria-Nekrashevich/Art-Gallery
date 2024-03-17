package nekrashevich.artgallery.service;

import nekrashevich.artgallery.model.Artist;
import nekrashevich.artgallery.model.Painting;
import nekrashevich.artgallery.repository.ArtistRepository;
import nekrashevich.artgallery.repository.PaintingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArtistService {
    private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository artistRepository;
    private final PaintingRepository paintingRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, PaintingRepository paintingRepository) {
        this.artistRepository = artistRepository;
        this.paintingRepository = paintingRepository;
    }

    public List<Artist> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        logger.debug("Retrieved {} artists from the database", artists.size());
        return artists;
    }

    public Artist getArtistById(UUID id) {
        return artistRepository.findArtistById(id);
    }

    public List<Painting> getPaintingsByArtistId(UUID artistId) {
        return paintingRepository.findByArtistId(artistId);
    }
}
