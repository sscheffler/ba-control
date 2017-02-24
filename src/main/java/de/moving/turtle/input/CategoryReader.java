package de.moving.turtle.input;

import de.moving.turtle.api.Identity;
import de.moving.turtle.api.category.Category;

import java.util.Collection;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public interface CategoryReader {

    Collection<Category> read();
}
