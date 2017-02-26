package de.moving.turtle.input;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.moving.turtle.api.category.Category;
import de.moving.turtle.mongo.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class MongoCategoryReader implements CategoryReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoCategoryReader.class);
    private final CategoryRepository categoryRepository;

    public MongoCategoryReader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Collection<Category> read() {
        return categoryRepository.findAll();
    }
}
