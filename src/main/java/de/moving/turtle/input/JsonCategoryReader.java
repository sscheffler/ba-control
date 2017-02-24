package de.moving.turtle.input;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.moving.turtle.api.category.Category;
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
 * todo -i this should be handled over a configuration
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class JsonCategoryReader implements CategoryReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonCategoryReader.class);
    private static ObjectMapper MAPPER = new ObjectMapper();

    @Value("${path.categories}")
    private String categoryPath;

    @Override
    public Collection<Category> read() {
        try {
            final File file = new File(categoryPath);
            return MAPPER.readValue(file, new TypeReference<List<Category>>(){});
        } catch (IOException e) {
            LOGGER.error("Error", e);
        }

        return Collections.emptyList();
    }
}
