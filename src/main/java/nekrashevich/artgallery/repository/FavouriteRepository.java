package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, UUID> {
}
