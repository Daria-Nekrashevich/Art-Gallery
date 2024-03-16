package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.PaintingOnBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaintingOnBoardRepository extends JpaRepository<PaintingOnBoard, UUID> {
}
