package io.vermau2k01.spring_with_mongodb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    @Size(max = 255)
    @ProductIdValid
    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String description;

    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "75.08")
    private BigDecimal price;

    private Integer quantity;

    private Double rating;

    private List<@Size(max = 255) String> tags;

    @NotNull
    @Size(max = 255)
    @ProductCategoryUnique
    private String category;

}
