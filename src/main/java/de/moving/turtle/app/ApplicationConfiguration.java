package de.moving.turtle.app;

import de.moving.turtle.analyze.CategoryTotalAnalyzer;
import de.moving.turtle.in.MetaPersistenceManager;
import de.moving.turtle.in.ResolvedRecordsPersistenceManager;
import de.moving.turtle.input.CategoryReader;
import de.moving.turtle.input.IdentityReader;
import de.moving.turtle.parse.CategoryIdentifier;
import de.moving.turtle.parse.RecordIdentifier;
import de.moving.turtle.parse.RecordParser;
import de.moving.turtle.process.AnalyzeProcessor;
import de.moving.turtle.process.ImportMetaProcessor;
import de.moving.turtle.process.ImportRecordsProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public AnalyzeProcessor categoryTotalProcessor(RecordParser csvRecordParser,
                                                   RecordIdentifier recordIdentifier,
                                                   CategoryIdentifier categoryIdentifier,
                                                   CategoryTotalAnalyzer categoryTotalAnalyzer){
        return new AnalyzeProcessor()
                .withAnalyzer(categoryTotalAnalyzer)
                .withCategoryIdentifier(categoryIdentifier)
                .withRecordIdentifier(recordIdentifier)
                .withRecordParser(csvRecordParser);
    }

    @Bean
    public ImportRecordsProcessor knownRecordImportProcessor(RecordParser csvRecordParser,
                                                             RecordIdentifier recordIdentifier,
                                                             CategoryIdentifier categoryIdentifier,
                                                             ResolvedRecordsPersistenceManager mongoResolvedRecordsPersistenceManager){
        return new ImportRecordsProcessor()
                .withPersistenceManager(mongoResolvedRecordsPersistenceManager)
                .withCategoryIdentifier(categoryIdentifier)
                .withRecordIdentifier(recordIdentifier)
                .withRecordParser(csvRecordParser);
    }

    @Bean
    public ImportMetaProcessor importMetaProcessor(IdentityReader mongoIdentityReader,
                                                   CategoryReader jsonCategoryReader,
                                                   MetaPersistenceManager mongoMetaPersistenceManager){
        return new ImportMetaProcessor()
                .withPersistenceManager(mongoMetaPersistenceManager)
                .withIdentityReader(mongoIdentityReader)
                .withCategoryReader(jsonCategoryReader);
    }

}
