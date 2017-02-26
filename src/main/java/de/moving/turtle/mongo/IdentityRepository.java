package de.moving.turtle.mongo;

import de.moving.turtle.api.Identity;
import de.moving.turtle.api.KnownRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "identity", collectionResourceRel = "identity")
public interface IdentityRepository extends MongoRepository<Identity,String> {
    Identity findByName(String name);
}
