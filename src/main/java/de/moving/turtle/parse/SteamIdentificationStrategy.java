package de.moving.turtle.parse;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.SteamCategory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class SteamIdentificationStrategy implements CategoryIdentificationStrategy<SteamCategory> {
    @Override
    public Optional<SteamCategory> evaluate(KnownRecord record) {
        if (record.usage() != null && record.usage().matches(".*STEAM GAMES.*")) {
            return Optional.of(new SteamCategory());
        }
        return Optional.empty();
    }
}
