package gabriel_lima_3.imagelitapi.applications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("v1/imagens")
@Slf4j
public class imageController {

    @PostMapping("/save")
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tag") List<String> tag)
    {
        log.info("iamgem recebida: nome: {}, tamanho: {}  ", file.getOriginalFilename(), file.getSize());
        log.info("nome definido para a imagem: nome{}", name);
        log.info("Nome definido das Tags: Tags{}", tag);

        return ResponseEntity.ok().build();
    }

}
