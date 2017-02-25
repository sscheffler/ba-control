package de.moving.turtle.app;

import de.moving.turtle.analyze.CategoryTotalAnalyzer;
import de.moving.turtle.in.ResolvedRecordsPersistenceManager;
import de.moving.turtle.parse.CategoryIdentifier;
import de.moving.turtle.parse.RecordIdentifier;
import de.moving.turtle.parse.RecordParser;
import de.moving.turtle.process.AnalyzeProcessor;
import de.moving.turtle.process.ImportRecordsProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public AnalyzeProcessor categoryTotalProcessor(@Qualifier("csvRecordParser") RecordParser recordParser,
                                                   RecordIdentifier recordIdentifier,
                                                   CategoryIdentifier categoryIdentifier,
                                                   CategoryTotalAnalyzer categoryTotalAnalyzer){
        return new AnalyzeProcessor()
                .withAnalyzer(categoryTotalAnalyzer)
                .withCategoryIdentifier(categoryIdentifier)
                .withRecordIdentifier(recordIdentifier)
                .withRecordParser(recordParser);
    }

    @Bean
    public ImportRecordsProcessor knownRecordImportProcessor(@Qualifier("csvRecordParser") RecordParser recordParser,
                                                             RecordIdentifier recordIdentifier,
                                                             CategoryIdentifier categoryIdentifier,
                                                             ResolvedRecordsPersistenceManager mongoResolvedRecordsPersistenceManager){
        return new ImportRecordsProcessor()
                .withPersistenceManager(mongoResolvedRecordsPersistenceManager)
                .withCategoryIdentifier(categoryIdentifier)
                .withRecordIdentifier(recordIdentifier)
                .withRecordParser(recordParser);
    }
}
