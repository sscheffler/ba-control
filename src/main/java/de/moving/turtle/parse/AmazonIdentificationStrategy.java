package de.moving.turtle.parse;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.AmazonCategory;
import de.moving.turtle.api.category.SteamCategory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class AmazonIdentificationStrategy implements CategoryIdentificationStrategy<AmazonCategory> {
    @Override
    public Optional<AmazonCategory> evaluate(KnownRecord record) {
        if ("Amazon".equals(record.purchaser())) {
            return Optional.of(new AmazonCategory());
        }
        return Optional.empty();
    }
}
