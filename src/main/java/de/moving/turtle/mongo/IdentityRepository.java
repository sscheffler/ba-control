package de.moving.turtle.mongo;

import de.moving.turtle.api.Identity;
import de.moving.turtle.api.KnownRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdentityRepository extends MongoRepository<Identity,String> {
}
