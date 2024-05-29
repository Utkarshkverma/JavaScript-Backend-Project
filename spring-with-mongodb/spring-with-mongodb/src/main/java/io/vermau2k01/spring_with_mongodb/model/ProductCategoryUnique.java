package io.vermau2k01.spring_with_mongodb.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import io.vermau2k01.spring_with_mongodb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the id value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = ProductCategoryUnique.ProductCategoryUniqueValidator.class
)
public @interface ProductCategoryUnique {

    String message() default "{Exists.product.category}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ProductCategoryUniqueValidator implements ConstraintValidator<ProductCategoryUnique, String> {

        private final ProductService productService;
        private final HttpServletRequest request;

        public ProductCategoryUniqueValidator(final ProductService productService,
                final HttpServletRequest request) {
            this.productService = productService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(productService.get(currentId).getCategory())) {
                // value hasn't changed
                return true;
            }
            return !productService.categoryExists(value);
        }

    }

}
