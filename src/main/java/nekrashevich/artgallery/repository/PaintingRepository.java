package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.Painting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaintingRepository extends JpaRepository<Painting, UUID> {
}
