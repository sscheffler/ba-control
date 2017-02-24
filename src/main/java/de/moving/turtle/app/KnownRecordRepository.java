package de.moving.turtle.app;

import de.moving.turtle.api.KnownRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KnownRecordRepository extends MongoRepository<KnownRecord,String> {
}
