package de.moving.turtle.in;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.UnknownRecord;
import de.moving.turtle.mongo.KnownRecordRepository;
import de.moving.turtle.mongo.UnKnownRecordRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author N3ophyn33
 */
@Component
public class MongoResolvedRecordsPersistenceManager implements ResolvedRecordsPersistenceManager {
    private final KnownRecordRepository knownRecordRepository;
    private final UnKnownRecordRepository unKnownRecordRepository;

    public MongoResolvedRecordsPersistenceManager(
            KnownRecordRepository knownRecordRepository,
            UnKnownRecordRepository unKnownRecordRepository) {
        this.knownRecordRepository = knownRecordRepository;
        this.unKnownRecordRepository = unKnownRecordRepository;
    }

    @Override
    public void persistKnownRecords(Collection<KnownRecord> records) {
        knownRecordRepository.save(records);
    }

    @Override
    public void persistUnknownRecords(Collection<UnknownRecord> records) {
        unKnownRecordRepository.save(records);
    }
}
