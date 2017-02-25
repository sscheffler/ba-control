package de.moving.turtle.parse;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.Category;
import de.moving.turtle.api.category.UnknownCategory;
import de.moving.turtle.input.CategoryReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class CategoryIdentifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryIdentifier.class);
    private final CategoryReader categoryReader;
    private final Map<String, CategoryIdentificationStrategy> strategyMap;

    @Autowired
    public CategoryIdentifier(@Qualifier("jsonCategoryReader") CategoryReader categoryReader,
                              Map<String, CategoryIdentificationStrategy> strategyMap) {
        this.categoryReader = categoryReader;
        this.strategyMap = strategyMap;
    }

    public KnownRecord identify(KnownRecord record) {
        final Collection<Category> categories = categoryReader.read();
        Category finalCategory = new UnknownCategory();
        for (Category category : categories) {
            final Optional<Category> resolvedCategory = category.matchers().stream()
                    .map(m -> {
                        final CategoryIdentificationStrategy matcherStrategy = strategyMap.get(m.strategy);
                        if (matcherStrategy != null) {
                            return (matcherStrategy.belongsToCategory(record, m)) ? category : null;
                        } else {
                            LOGGER.warn("Strategy '{}' not found for '{}'. Skipping!", m.strategy, category.categoryId);
                        }
                        return null;
                    })
                    .filter(c -> c != null)
                    .findFirst();
            if(resolvedCategory.isPresent()){
                finalCategory = resolvedCategory.get();
                break;
            }
        }
        LOGGER.debug("Resolved category: '{}'", finalCategory.categoryId);
        return record
                .withCategory(finalCategory);
    }


}
