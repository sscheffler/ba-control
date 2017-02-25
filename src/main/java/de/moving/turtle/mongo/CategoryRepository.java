package de.moving.turtle.mongo;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,String> {
}
