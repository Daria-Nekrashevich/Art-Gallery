package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
}
