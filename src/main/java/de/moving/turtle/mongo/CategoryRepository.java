package de.moving.turtle.mongo;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "category", collectionResourceRel = "category")
public interface CategoryRepository extends MongoRepository<Category,String> {
    Category findByCategoryId(String categoryId);
}
