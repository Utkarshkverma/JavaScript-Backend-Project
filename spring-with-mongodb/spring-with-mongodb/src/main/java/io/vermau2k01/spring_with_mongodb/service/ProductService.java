package io.vermau2k01.spring_with_mongodb.service;

import io.vermau2k01.spring_with_mongodb.domain.Category;
import io.vermau2k01.spring_with_mongodb.domain.Product;
import io.vermau2k01.spring_with_mongodb.model.ProductDTO;
import io.vermau2k01.spring_with_mongodb.repos.CategoryRepository;
import io.vermau2k01.spring_with_mongodb.repos.ProductRepository;
import io.vermau2k01.spring_with_mongodb.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(final ProductRepository productRepository,
            final CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> findAll() {
        final List<Product> products = productRepository.findAll(Sort.by("id"));
        return products.stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .toList();
    }

    public ProductDTO get(final String id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        product.setId(productDTO.getId());
        return productRepository.save(product).getId();
    }

    public void update(final String id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final String id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setRating(product.getRating());
        productDTO.setTags(product.getTags());
        productDTO.setCategory(product.getCategory() == null ? null : product.getCategory().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setRating(productDTO.getRating());
        product.setTags(productDTO.getTags());
        final Category category = productDTO.getCategory() == null ? null : categoryRepository.findById(productDTO.getCategory())
                .orElseThrow(() -> new NotFoundException("category not found"));
        product.setCategory(category);
        return product;
    }

    public boolean idExists(final String id) {
        return productRepository.existsByIdIgnoreCase(id);
    }

    public boolean categoryExists(final String id) {
        return productRepository.existsByCategoryIdIgnoreCase(id);
    }

}
