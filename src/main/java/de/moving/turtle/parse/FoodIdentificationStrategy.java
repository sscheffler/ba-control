package de.moving.turtle.parse;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.FoodCategory;
import de.moving.turtle.api.category.SteamCategory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class FoodIdentificationStrategy implements CategoryIdentificationStrategy<FoodCategory> {
    @Override
    public Optional<FoodCategory> evaluate(KnownRecord record) {
        if (record.usage() != null && (
                record.purchaser().equals("Rewe"))||
                record.purchaser().equals("Netto")) {
            return Optional.of(new FoodCategory());
        }
        return Optional.empty();
    }
}
