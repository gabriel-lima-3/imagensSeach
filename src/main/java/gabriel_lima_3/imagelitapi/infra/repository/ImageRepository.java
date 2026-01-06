package gabriel_lima_3.imagelitapi.infra.repository;
import gabriel_lima_3.imagelitapi.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String>{

}
