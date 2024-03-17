package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {
}
