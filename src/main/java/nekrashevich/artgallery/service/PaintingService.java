package nekrashevich.artgallery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nekrashevich.artgallery.model.Painting;
import nekrashevich.artgallery.repository.PaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaintingService {
    private static final Logger logger = LoggerFactory.getLogger(PaintingService.class);

    private final PaintingRepository paintingRepository;

    @Autowired
    public PaintingService(PaintingRepository paintingRepository) {
        this.paintingRepository = paintingRepository;
    }

    public List<Painting> getAllPaintings() {
        List<Painting> paintings = paintingRepository.findAll();
        logger.debug("Retrieved {} paintings from the database", paintings.size());
        return paintings;
    }

    public Optional<Painting> getPaintingById(UUID id) {
        logger.debug("Retrieving painting with id {}", id);
        return paintingRepository.findById(id);
    }

    public List<Painting> getPaintingsByArtistId(UUID artistId) {
        logger.debug("Retrieving paintings for artist with id {}", artistId);
        return paintingRepository.findByArtistId(artistId);
    }

    public List<Painting> searchPaintings(String searchTerm) {
        return paintingRepository.findAll().stream()
                .filter(painting -> painting.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }
}
