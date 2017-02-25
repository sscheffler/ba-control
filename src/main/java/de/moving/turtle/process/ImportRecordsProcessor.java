package de.moving.turtle.process;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.RawRecord;
import de.moving.turtle.in.ResolvedRecordsPersistenceManager;
import de.moving.turtle.parse.CategoryIdentifier;
import de.moving.turtle.parse.RecordIdentifier;
import de.moving.turtle.parse.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class ImportRecordsProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportRecordsProcessor.class);
    private RecordParser recordParser;
    private String filePath;
    private CategoryIdentifier categoryIdentifier;
    private RecordIdentifier recordIdentifier;
    private ResolvedRecordsPersistenceManager persistenceManager;
    private boolean importKnown = false;
    private boolean importUnknown = false;

    public ImportRecordsProcessor withRecordParser(RecordParser recordParser) {
        this.recordParser = recordParser;
        return this;
    }

    public ImportRecordsProcessor withCategoryIdentifier(CategoryIdentifier categoryIdentifier) {
        this.categoryIdentifier = categoryIdentifier;
        return this;
    }

    public ImportRecordsProcessor withPersistenceManager(ResolvedRecordsPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
        return this;
    }

    public ImportRecordsProcessor withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public ImportRecordsProcessor withRecordIdentifier(RecordIdentifier recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
        return this;
    }

    public ImportRecordsProcessor importKnownRecords(){
        importKnown = true;
        return this;
    }
    public ImportRecordsProcessor importUnknownRecords(){
        importUnknown = true;
        return this;
    }

    public void perform(){
        final Collection<RawRecord> rawRecords = recordParser.parseToRaw(filePath);
        LOGGER.info("'{}' rawrecords collected.", rawRecords.size());
        final RecordIdentifier.IdentificationResult identify = recordIdentifier.identify(rawRecords);
        LOGGER.info("'{}'/'{}' known / unknown records collected.", identify.known.size(), identify.unknown.size());
        final List<KnownRecord> enrichedByCategory = identify.known.stream()
                .map(categoryIdentifier::identify)
                .collect(Collectors.toList());
        if(importKnown){
            LOGGER.info("Importing known records");
            persistenceManager.persistKnownRecords(enrichedByCategory);
        }

        if(importUnknown){
            LOGGER.info("Importing unknown records");
            persistenceManager.persistUnknownRecords(identify.unknown);
        }
    }
}
