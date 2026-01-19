package gabriel_lima_3.imagelitapi.domain.service;
import gabriel_lima_3.imagelitapi.domain.entity.Image;
import gabriel_lima_3.imagelitapi.domain.enums.ImageExtensions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageService {

    Image save(Image image);

    Optional<Image> getById(UUID id);

    List<Image> search (ImageExtensions extensions, String query );

}
