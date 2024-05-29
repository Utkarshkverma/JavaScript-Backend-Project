package io.vermau2k01.spring_with_mongodb.repos;

import io.vermau2k01.spring_with_mongodb.domain.Category;
import io.vermau2k01.spring_with_mongodb.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {

    Product findFirstByCategory(Category category);

    boolean existsByIdIgnoreCase(String id);

    boolean existsByCategoryIdIgnoreCase(String id);

}
