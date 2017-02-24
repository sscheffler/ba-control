package de.moving.turtle.parse;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.Matcher;
import org.springframework.stereotype.Component;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class PurchaserStrategy implements CategoryIdentificationStrategy{
    @Override
    public boolean belongsToCategory(KnownRecord record, Matcher matcher) {
        return record.purchaser().equals(matcher.regex);
    }
}
