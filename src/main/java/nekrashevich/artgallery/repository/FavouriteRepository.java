package nekrashevich.artgallery.repository;

import nekrashevich.artgallery.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavouriteRepository extends JpaRepository<Favourite, UUID> {
}
