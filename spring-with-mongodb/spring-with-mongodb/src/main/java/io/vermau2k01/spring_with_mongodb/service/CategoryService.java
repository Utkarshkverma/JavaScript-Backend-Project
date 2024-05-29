package io.vermau2k01.spring_with_mongodb.service;

import io.vermau2k01.spring_with_mongodb.domain.Category;
import io.vermau2k01.spring_with_mongodb.domain.Product;
import io.vermau2k01.spring_with_mongodb.model.CategoryDTO;
import io.vermau2k01.spring_with_mongodb.repos.CategoryRepository;
import io.vermau2k01.spring_with_mongodb.repos.ProductRepository;
import io.vermau2k01.spring_with_mongodb.util.NotFoundException;
import io.vermau2k01.spring_with_mongodb.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(final CategoryRepository categoryRepository,
            final ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<CategoryDTO> findAll() {
        final List<Category> categories = categoryRepository.findAll(Sort.by("id"));
        return categories.stream()
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .toList();
    }

    public CategoryDTO get(final String id) {
        return categoryRepository.findById(id)
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final CategoryDTO categoryDTO) {
        final Category category = new Category();
        mapToEntity(categoryDTO, category);
        category.setId(categoryDTO.getId());
        return categoryRepository.save(category).getId();
    }

    public void update(final String id, final CategoryDTO categoryDTO) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(categoryDTO, category);
        categoryRepository.save(category);
    }

    public void delete(final String id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDTO mapToDTO(final Category category, final CategoryDTO categoryDTO) {
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    private Category mapToEntity(final CategoryDTO categoryDTO, final Category category) {
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public boolean idExists(final String id) {
        return categoryRepository.existsByIdIgnoreCase(id);
    }

    public ReferencedWarning getReferencedWarning(final String id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Category category = categoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Product categoryProduct = productRepository.findFirstByCategory(category);
        if (categoryProduct != null) {
            referencedWarning.setKey("category.product.category.referenced");
            referencedWarning.addParam(categoryProduct.getId());
            return referencedWarning;
        }
        return null;
    }

}
