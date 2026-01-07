package gabriel_lima_3.imagelitapi.repository;
import gabriel_lima_3.imagelitapi.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID>{

}
