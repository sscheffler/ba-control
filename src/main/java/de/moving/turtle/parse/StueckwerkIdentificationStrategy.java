package de.moving.turtle.parse;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.SteamCategory;
import de.moving.turtle.api.category.StueckwerkCategory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class StueckwerkIdentificationStrategy implements CategoryIdentificationStrategy<StueckwerkCategory> {
    @Override
    public Optional<StueckwerkCategory> evaluate(KnownRecord record) {
        if (record.usage() != null && record.usage().matches(".*STUECKWERK.*")) {
            return Optional.of(new StueckwerkCategory());
        }
        return Optional.empty();
    }
}
