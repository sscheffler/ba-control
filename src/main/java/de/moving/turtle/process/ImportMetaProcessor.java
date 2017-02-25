package de.moving.turtle.process;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.RawRecord;
import de.moving.turtle.in.MetaPersistenceManager;
import de.moving.turtle.in.ResolvedRecordsPersistenceManager;
import de.moving.turtle.input.CategoryReader;
import de.moving.turtle.input.IdentityReader;
import de.moving.turtle.parse.CategoryIdentifier;
import de.moving.turtle.parse.RecordIdentifier;
import de.moving.turtle.parse.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportMetaProcessor {
    private MetaPersistenceManager persistenceManager;
    private IdentityReader identityReader;
    private CategoryReader categoryReader;

    public ImportMetaProcessor withPersistenceManager(MetaPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
        return this;
    }

    public ImportMetaProcessor withIdentityReader(IdentityReader identityReader) {
        this.identityReader = identityReader;
        return this;
    }

    public ImportMetaProcessor withCategoryReader(CategoryReader categoryReader) {
        this.categoryReader = categoryReader;
        return this;
    }

    public void perform(){
        identityReader.read().stream()
                .filter((identity) -> !persistenceManager.containsIdentity(identity))
                .forEach(persistenceManager::persistIdentity);

        categoryReader.read().stream()
                .filter((category) -> !persistenceManager.containsCategory(category))
                .forEach(persistenceManager::persistCategory);
    }
}
