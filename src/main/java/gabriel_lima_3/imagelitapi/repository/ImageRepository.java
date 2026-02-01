package gabriel_lima_3.imagelitapi.repository;

import gabriel_lima_3.imagelitapi.domain.entity.Image;
import gabriel_lima_3.imagelitapi.domain.enums.ImageExtensions;
import gabriel_lima_3.imagelitapi.repository.specs.GerenicSpecs;
import gabriel_lima_3.imagelitapi.repository.specs.ImageSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID>, JpaSpecificationExecutor<Image> {

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtensions extensions, String query ){

        Specification<Image> spec = Specification.where(GerenicSpecs.conjunctions());


        if (extensions != null){

            spec = spec.and(ImageSpecs.extensionEqual(extensions));
        }
        if (StringUtils.hasText(query)){

            spec = spec.and(Specification.anyOf(ImageSpecs.nameLike(query), ImageSpecs.tagsLike(query)));

        }

        return findAll(spec);
    }
}
