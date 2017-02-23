package de.moving.turtle.parse;

import de.moving.turtle.api.RawRecord;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */

public interface RecordParser {

    Optional<Collection<RawRecord>> parseToRaw(String filePath);
}
