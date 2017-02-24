package de.moving.turtle.parse;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.Matcher;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public interface CategoryIdentificationStrategy{
    boolean belongsToCategory(KnownRecord record, Matcher matcher);
}
