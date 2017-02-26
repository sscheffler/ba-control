package de.moving.turtle.mongo;

import de.moving.turtle.api.KnownRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "known", collectionResourceRel = "known")
public interface KnownRecordRepository extends MongoRepository<KnownRecord,String> {
}
