package gabriel_lima_3.imagelitapi.applications;
import gabriel_lima_3.imagelitapi.domain.entity.Image;
import gabriel_lima_3.imagelitapi.domain.enums.ImageExtensions;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Component
public class ImageMapper {

    public Image mapToImage(MultipartFile file, String name, List<String> tags) throws IOException {

        return Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extensions(ImageExtensions.fromMediaType(MediaType.valueOf(file.getContentType())))
                .files(file.getBytes())
                .build();

    }

    public ImageDTO domainToDTO(Image image, String url){

        return ImageDTO.builder()
                .url(url)
                .extension(image.getExtensions().name())
                .name(image.getName())
                .size(image.getSize())
                .uploadDate(image.getUploadoTime().toLocalDate())
                .build();
    }

}
