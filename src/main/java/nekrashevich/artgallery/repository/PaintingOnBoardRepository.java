package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.PaintingOnBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PaintingOnBoardRepository extends JpaRepository<PaintingOnBoard, UUID> {
}
