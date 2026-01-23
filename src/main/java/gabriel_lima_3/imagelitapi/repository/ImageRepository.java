package gabriel_lima_3.imagelitapi.repository;

import gabriel_lima_3.imagelitapi.domain.entity.Image;
import gabriel_lima_3.imagelitapi.domain.enums.ImageExtensions;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID>, JpaSpecificationExecutor<Image> {

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtensions extensions, String query ){

        //SELECET * FROM WHERE 1 = 1
        Specification<Image> conjunction = (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
        Specification<Image> spec = Specification.where(conjunction);

        if (extensions != null){
             //AND EXTENSION = 'PNG'
            Specification<Image> extensionEqual = (root, q, cb) ->cb.equal(root.get("extensions"), extensions);
            spec = spec.and(extensionEqual);
        }
        if (StringUtils.hasText(query)){

            // AND ( NAME LIKE "QUERY" OR TAGS LIKE "QUERY"
            // RIVER = %RI% - % faz pesquisa com que pode vir antes ou depois
            //

            Specification<Image> nameLike = (root, q, cb) -> cb.like(cb.upper(root.get("name")),"%" +  query.toUpperCase() + "%");
            Specification<Image> tagsLike = (root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + query.toUpperCase() + "%");

            Specification<Image> namesOrTagsLike =  Specification.anyOf(nameLike, tagsLike);

            spec = spec.and(namesOrTagsLike);

        }

        return findAll(spec);
    }
}
