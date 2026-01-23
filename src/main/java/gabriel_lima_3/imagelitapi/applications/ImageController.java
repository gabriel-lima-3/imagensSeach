package gabriel_lima_3.imagelitapi.applications;

import gabriel_lima_3.imagelitapi.domain.entity.Image;
import gabriel_lima_3.imagelitapi.domain.enums.ImageExtensions;
import gabriel_lima_3.imagelitapi.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/image")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final ImageService service;
    private final ImageMapper mapper;


    @PostMapping("/save")
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags)
            throws IOException {
        log.info("imagem recebida: nome: {}, tamanho: {}  ", file.getOriginalFilename(), file.getSize());

        Image image = mapper.mapToImage(file, name, tags);
        Image savedImage = service.save(image);
        URI imageUri = BuildImageUrl(savedImage);

        return ResponseEntity.created(imageUri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable UUID id){
        System.out.println("Recebido id: " + id);
        var possibleImage = service.getById(id);
        if (possibleImage.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var image = possibleImage.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtensions().getMediaType());
        headers.setContentLength(image.getSize());
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getFileName() + "\"");
        return new ResponseEntity<>(image.getFiles(), headers, HttpStatus.OK);

    }

    //localhost:8080/image?extension=PNG&Query= Nature

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(

            @RequestParam(value = "extension", required = false, defaultValue = "") String extension,
            @RequestParam(value = "query", required = false) String query) {

        // Converte para Enum apenas se não estiver vazio
        ImageExtensions extensionEnum = StringUtils.hasText(extension)
                ? ImageExtensions.OfName(extension.toUpperCase())
                : null;

        var result = service.search(extensionEnum, query);

        var images = result.stream().map(image -> {

           var url = BuildImageUrl(image);

          return mapper.domainToDTO(image, url.toString());
       }).collect(Collectors.toList());

        return ResponseEntity.ok(images);
    }


    private URI BuildImageUrl(Image image){
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/image/{id}")
                .buildAndExpand(image.getId())
                .toUri();
    }

}
