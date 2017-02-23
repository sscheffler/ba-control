package de.moving.turtle.parse;

import de.moving.turtle.api.category.Category;
import de.moving.turtle.api.KnownRecord;

import java.util.Optional;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public interface CategoryIdentificationStrategy<T extends Category> {
    Optional<T> evaluate(KnownRecord record);
}
