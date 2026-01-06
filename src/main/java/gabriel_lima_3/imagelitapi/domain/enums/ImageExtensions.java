package gabriel_lima_3.imagelitapi.domain.enums;

import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ImageExtensions {

    PNG(MediaType.IMAGE_PNG),
    JPEG(MediaType.IMAGE_JPEG),
    GIF(MediaType.IMAGE_GIF);

    private MediaType mediaType;

    ImageExtensions(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ImageExtensions fromMediaType(MediaType mediaType){

        return Arrays.stream(values())
                .filter(ie -> ie.mediaType.equals(mediaType))
                .findFirst().orElse(null);
    }

}
