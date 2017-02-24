package de.moving.turtle;

import de.moving.turtle.analyze.CategoryTotalAnalyzer;
import de.moving.turtle.api.RawRecord;
import de.moving.turtle.parse.CategoryIdentifier;
import de.moving.turtle.parse.RecordIdentifier;
import de.moving.turtle.parse.RecordParser;
import de.moving.turtle.process.AnalyzeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.*;

import static java.util.Collections.*;

/**
 * This is an utility module. Application is just for testing purposes!
 */
@SpringBootApplication
public class MoneyControlApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyControlApplication.class);
    @Value("${path.data}")
    private String dataPath;

    private final RecordParser recordParser;
    private final RecordIdentifier recordIdentifier;
    private final CategoryIdentifier categoryIdentifier;
    private final CategoryTotalAnalyzer categoryTotalAnalyzer;

    @Autowired
    public MoneyControlApplication(@Qualifier("csvRecordParser") RecordParser recordParser,
                                   RecordIdentifier recordIdentifier,
                                   CategoryIdentifier categoryIdentifier,
                                   CategoryTotalAnalyzer categoryTotalAnalyzer) {
        this.recordParser = recordParser;
        this.recordIdentifier = recordIdentifier;
        this.categoryIdentifier = categoryIdentifier;
        this.categoryTotalAnalyzer = categoryTotalAnalyzer;
    }

    public static void main(String[] args) {
		SpringApplication.run(MoneyControlApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
            final Optional<CategoryTotalAnalyzer.CategoryTotalResult> categoryTotalResult = new AnalyzeProcessor()
                    .withAnalyzer(categoryTotalAnalyzer)
                    .withCategoryIdentifier(categoryIdentifier)
                    .withFilePath(dataPath)
                    .withRecordIdentifier(recordIdentifier)
                    .withRecordParser(recordParser)
                    .collect()
                    .get(CategoryTotalAnalyzer.CategoryTotalResult.class);

            categoryTotalResult.ifPresent(r ->
                        r.byCategory.forEach((key, value) -> LOGGER.info("{}: {}", key, value)
                    ));

            /*final Collection<RawRecord> rawRecords = recordParser.parseToRaw(dataPath).orElseGet(() -> emptyList());
            final RecordIdentifier.IdentificationResult result = recordIdentifier.identify(rawRecords);
            result.known
                    .forEach(categoryIdentifier::identify);

            final CategoryTotalAnalyzer.CategoryTotalResult analyze = categoryTotalAnalyzer.analyze(result.known);
            analyze.byCategory.forEach(
                    (key, value) -> LOGGER.info("{}: {}", key, value)
            );*/
		};
	}
}

