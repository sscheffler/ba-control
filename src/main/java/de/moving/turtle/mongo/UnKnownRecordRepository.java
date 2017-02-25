package de.moving.turtle.mongo;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.UnknownRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnKnownRecordRepository extends MongoRepository<UnknownRecord,String> {
}
