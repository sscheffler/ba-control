package de.moving.turtle.in;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.UnknownRecord;

import java.util.Collection;

/**
 * @author N3ophyn33
 */
public interface ResolvedRecordsPersistenceManager {

    void persistKnownRecords(Collection<KnownRecord>records);
    void persistUnknownRecords(Collection<UnknownRecord>records);

}
