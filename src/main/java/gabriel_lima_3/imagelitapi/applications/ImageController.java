package gabriel_lima_3.imagelitapi.applications;

import gabriel_lima_3.imagelitapi.domain.entity.Image;
import gabriel_lima_3.imagelitapi.domain.enums.ImageExtensions;
import gabriel_lima_3.imagelitapi.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("image")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final ImageService service;


    @PostMapping("/save")
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags)
            throws IOException {
        log.info("imagem recebida: nome: {}, tamanho: {}  ", file.getOriginalFilename(), file.getSize());



        Image image = Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extensions(ImageExtensions.fromMediaType(MediaType.valueOf(file.getContentType())))
                .files(file.getBytes())
                .build();

        service.save(image);

        return ResponseEntity.ok().build();
    }

}
