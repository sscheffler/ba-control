package de.moving.turtle.in;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.app.KnownRecordRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author N3ophyn33
 */
@Component
public class MongoKnownRecordPersistenceManager implements KnownRecordPersistenceManager {
    private final KnownRecordRepository repository;

    public MongoKnownRecordPersistenceManager(KnownRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public void persist(Collection<KnownRecord> records) {
        repository.save(records);
    }
}
