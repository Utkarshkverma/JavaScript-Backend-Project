package io.vermau2k01.spring_with_mongodb.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.vermau2k01.spring_with_mongodb.model.CategoryDTO;
import io.vermau2k01.spring_with_mongodb.service.CategoryService;
import io.vermau2k01.spring_with_mongodb.util.ReferencedException;
import io.vermau2k01.spring_with_mongodb.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createCategory(
            @RequestBody @Valid final CategoryDTO categoryDTO) {
        final String createdId = categoryService.create(categoryDTO);
        return new ResponseEntity<>('"' + createdId + '"', HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final CategoryDTO categoryDTO) {
        categoryService.update(id, categoryDTO);
        return ResponseEntity.ok('"' + id + '"');
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") final String id) {
        final ReferencedWarning referencedWarning = categoryService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
