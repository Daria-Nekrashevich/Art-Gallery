package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
}
