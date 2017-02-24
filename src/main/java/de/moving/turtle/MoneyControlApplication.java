package de.moving.turtle;

import de.moving.turtle.analyze.CategoryTotalAnalyzer;
import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.app.KnownRecordRepository;
import de.moving.turtle.process.AnalyzeProcessor;
import de.moving.turtle.process.ImportProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/**
 * This is an utility module. Application is just for testing purposes!
 */
@SpringBootApplication
public class MoneyControlApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyControlApplication.class);
    @Value("${path.data}")
    private String dataPath;

    private final AnalyzeProcessor categoryTotalProcessor;
    private final ImportProcessor knownRecordImportProcessor;

    @Autowired
    public MoneyControlApplication(AnalyzeProcessor categoryTotalProcessor, ImportProcessor knownRecordImportProcessor) {
        this.categoryTotalProcessor = categoryTotalProcessor;
        this.knownRecordImportProcessor = knownRecordImportProcessor;
    }

    public static void main(String[] args) {
		SpringApplication.run(MoneyControlApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
            knownRecordImportProcessor
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

