package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    Artist findArtistById(UUID id);
}
