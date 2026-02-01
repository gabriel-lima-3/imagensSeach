package gabriel_lima_3.imagelitapi.repository.specs;

import org.springframework.data.jpa.domain.Specification;

public class GerenicSpecs {

    private GerenicSpecs(){}

    public static <T> Specification<T> conjunctions (){
        return (root, q, cb) -> cb.conjunction();
    }
}
