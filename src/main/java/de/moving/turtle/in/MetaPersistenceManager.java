package de.moving.turtle.in;

import de.moving.turtle.api.Identity;
import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.UnknownRecord;
import de.moving.turtle.api.category.Category;

import java.util.Collection;

/**
 * @author N3ophyn33
 */
public interface MetaPersistenceManager {

    void persistCategories(Collection<Category> categories);
    Collection<Category> loadCategories();
    void persistIdentities(Collection<Identity> identities);
    Collection<Identity> loadIdentities();

}
