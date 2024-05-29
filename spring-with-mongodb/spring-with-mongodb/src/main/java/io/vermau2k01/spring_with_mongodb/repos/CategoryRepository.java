package io.vermau2k01.spring_with_mongodb.repos;

import io.vermau2k01.spring_with_mongodb.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CategoryRepository extends MongoRepository<Category, String> {

    boolean existsByIdIgnoreCase(String id);

}
