package de.moving.turtle.in;

import de.moving.turtle.api.KnownRecord;

import java.util.Collection;

/**
 * @author N3ophyn33
 */
public interface KnownRecordPersistenceManager {

    void persist(Collection<KnownRecord>records);

}
