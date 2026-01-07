package gabriel_lima_3.imagelitapi.domain.service;
import gabriel_lima_3.imagelitapi.domain.entity.Image;

import java.util.Optional;
import java.util.UUID;

public interface ImageService {

    Image save(Image image);

    Optional<Image> getById(UUID id);
}
