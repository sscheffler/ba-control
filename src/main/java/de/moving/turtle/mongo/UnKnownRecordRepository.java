package de.moving.turtle.mongo;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.UnknownRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unknown", collectionResourceRel = "unknown")
public interface UnKnownRecordRepository extends MongoRepository<UnknownRecord,String> {
}
