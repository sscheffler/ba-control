package de.moving.turtle;

import de.moving.turtle.analyze.CategoryTotalAnalyzer;
import de.moving.turtle.process.AnalyzeProcessor;
import de.moving.turtle.process.ImportMetaProcessor;
import de.moving.turtle.process.ImportRecordsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * This is an utility module. Application is just for testing purposes!
 */
@SpringBootApplication
public class BaApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaApplication.class);
    @Value("${path.data}")
    private String dataPath;

    private final AnalyzeProcessor categoryTotalProcessor;
    private final ImportRecordsProcessor knownRecordImportRecordsProcessor;
    private final ImportMetaProcessor importMetaProcessor;

    @Autowired
    public BaApplication(AnalyzeProcessor categoryTotalProcessor,
                         ImportRecordsProcessor knownRecordImportRecordsProcessor,
                         ImportMetaProcessor importMetaProcessor) {
        this.categoryTotalProcessor = categoryTotalProcessor;
        this.knownRecordImportRecordsProcessor = knownRecordImportRecordsProcessor;
        this.importMetaProcessor = importMetaProcessor;
    }

    public static void main(String[] args) {
		SpringApplication.run(BaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
            importMetaProcessor.perform();

            knownRecordImportRecordsProcessor
                    .withFilePath(dataPath)
                    .importKnownRecords()
                    .importUnknownRecords()
                    .perform();
            categoryTotalProcessor
                    .withFilePath(dataPath)
                    .collect()
                    .get(CategoryTotalAnalyzer.CategoryTotalResult.class)
                    .ifPresent(r ->
                            r.byCategory.forEach((key, value) -> LOGGER.info("{}: {}", key, value))
                    );
		};
	}
}

