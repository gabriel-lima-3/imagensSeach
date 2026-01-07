package gabriel_lima_3.imagelitapi.applications;

import gabriel_lima_3.imagelitapi.domain.entity.Image;
import gabriel_lima_3.imagelitapi.domain.service.ImageService;
import gabriel_lima_3.imagelitapi.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public Optional<Image> getById(UUID id) {
        return repository.findById(id);
    }
}
