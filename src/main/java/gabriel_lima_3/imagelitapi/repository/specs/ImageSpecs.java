package gabriel_lima_3.imagelitapi.repository.specs;

import gabriel_lima_3.imagelitapi.domain.entity.Image;
import gabriel_lima_3.imagelitapi.domain.enums.ImageExtensions;
import org.springframework.data.jpa.domain.Specification;

public class ImageSpecs {

    private ImageSpecs(){}

    public static Specification<Image> extensionEqual (ImageExtensions extensions){
        return (root, q, cb) ->cb.equal(root.get("extensions"), extensions);
    }

    public static Specification<Image> nameLike(String name){

        return (root, q, cb) -> cb.like(cb.upper(root.get("name")),"%" +  name.toUpperCase() + "%");

    }

    public static Specification<Image> tagsLike(String tag){
        return (root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + tag.toUpperCase() + "%");

    }



}


