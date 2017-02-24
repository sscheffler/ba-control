package de.moving.turtle;

import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.RawRecord;
import de.moving.turtle.parse.CategoryIdentifier;
import de.moving.turtle.parse.RecordIdentifier;
import de.moving.turtle.parse.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.*;

import static java.util.Collections.*;

@SpringBootApplication
public class MoneyControlApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyControlApplication.class);
    private static final String FILE_PATH = "/home/sscheffler/Downloads/data.csv";

    @Autowired
    @Qualifier("csvRecordParser")
    private RecordParser recordParser;

    @Autowired
    private RecordIdentifier identifier;

    @Autowired
    private CategoryIdentifier categoryIdentifier;


	public static void main(String[] args) {
		SpringApplication.run(MoneyControlApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
            final Collection<RawRecord> rawRecords = recordParser.parseToRaw(FILE_PATH).orElseGet(() -> emptyList());
            final RecordIdentifier.IdentificationResult result = identifier.identify(rawRecords);

            final Map<String, List<KnownRecord>> byCategory = new HashMap<>();
            result.known.stream()
                    .map(categoryIdentifier::identify)
                    .forEach(r -> {
                        if(!byCategory.containsKey(r.category().id())){
                            byCategory.put(r.category().id(), new ArrayList<>());
                        }
                        byCategory.get(r.category().id()).add(r);
                    });
            byCategory.entrySet().stream()
                    .map((stringListEntry) -> {
                        LOGGER.info("Key: '{}'", stringListEntry.getKey());
                        return stringListEntry.getValue();
                    })
                    .forEach(l -> l.stream()
                            .map(KnownRecord::value)
                            .reduce((a,b)-> a.add(b))
                            .ifPresent(r -> LOGGER.info("Result: {}", r.toString())));


            LOGGER.info("Done");
            //result.unknown.stream().forEach(r -> LOGGER.info(r.toString()));
		};
	}
}

