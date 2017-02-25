package de.moving.turtle.in;

import de.moving.turtle.api.Identity;
import de.moving.turtle.api.category.Category;
import de.moving.turtle.mongo.CategoryRepository;
import de.moving.turtle.mongo.IdentityRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author N3ophyn33
 */
@Component
public class MongoMetaPersistenceManager implements MetaPersistenceManager {

    private final IdentityRepository identityRepository;
    private final CategoryRepository categoryRepository;

    public MongoMetaPersistenceManager(
            IdentityRepository identityRepository,
            CategoryRepository categoryRepository) {
        this.identityRepository = identityRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void persistCategories(Collection<Category> categories) {
        categoryRepository.save(categories);
    }

    @Override
    public void persistCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Collection<Category> loadCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean containsCategory(Category category) {
        return categoryRepository.findByCategoryId(category.categoryId) != null;
    }

    @Override
    public void persistIdentities(Collection<Identity> identities) {
        identityRepository.save(identities);
    }

    @Override
    public void persistIdentity(Identity identity) {
        identityRepository.save(identity);
    }

    @Override
    public Collection<Identity> loadIdentities() {
        return identityRepository.findAll();
    }

    @Override
    public boolean containsIdentity(Identity identity) {
        return identityRepository.findByName(identity.name) != null;
    }
}
