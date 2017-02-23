package de.moving.turtle.parse;

import de.moving.turtle.api.category.Category;
import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.UnknownCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class CategoryIdentifier {

    @Autowired
    private List<CategoryIdentificationStrategy> strategies;

    public KnownRecord identify(KnownRecord record){
        final Category category = (Category)strategies.stream()
                .map(s -> s.evaluate(record))
                .map(o -> o.orElseGet(UnknownCategory::new))
                .filter(c -> !(c instanceof UnknownCategory))
                .findFirst()
                .orElseGet(UnknownCategory::new);
        return record.withCategory(category);
    }
}
