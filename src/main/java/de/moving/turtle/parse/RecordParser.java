package de.moving.turtle.parse;

import de.moving.turtle.api.RawRecord;

import java.util.Collection;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */

public interface RecordParser {

    Collection<RawRecord> parseToRaw(String filePath);
}
