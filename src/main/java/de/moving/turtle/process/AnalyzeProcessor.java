package de.moving.turtle.process;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import de.moving.turtle.analyze.Analyzer;
import de.moving.turtle.api.AnalyzeResult;
import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.RawRecord;
import de.moving.turtle.parse.CategoryIdentifier;
import de.moving.turtle.parse.RecordIdentifier;
import de.moving.turtle.parse.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class AnalyzeProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzeProcessor.class);
    private RecordParser recordParser;
    private String filePath;
    private CategoryIdentifier categoryIdentifier;
    private RecordIdentifier recordIdentifier;
    private final List<Analyzer<?>> analyzers = new ArrayList<>();

    public AnalyzeProcessor withRecordParser(RecordParser recordParser) {
        this.recordParser = recordParser;
        return this;
    }

    public AnalyzeProcessor withCategoryIdentifier(CategoryIdentifier categoryIdentifier) {
        this.categoryIdentifier = categoryIdentifier;
        return this;
    }

    public AnalyzeProcessor withAnalyzer(Analyzer<?> analyzer) {
        this.analyzers.add(analyzer);
        return this;
    }

    public AnalyzeProcessor withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public AnalyzeProcessor withRecordIdentifier(RecordIdentifier recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
        return this;
    }

    public ResultProcessor collect(){
        checkNotNull(recordParser, "Recordparser must not be null");
        checkNotNull(categoryIdentifier, "CategoryIdentifier must not be null");
        checkNotNull(recordIdentifier, "RecordIdentifier must not be null");
        if(Strings.isNullOrEmpty(filePath)){
            throw new IllegalArgumentException("FilePath not set");
        }
        final Collection<RawRecord> rawRecords = recordParser.parseToRaw(filePath);
        LOGGER.info("'{}' rawrecords collected.", rawRecords.size());
        final RecordIdentifier.IdentificationResult identify = recordIdentifier.identify(rawRecords);
        LOGGER.info("'{}'/'{}' known / unknown records collected.", identify.known.size(), identify.unknown.size());
        final List<KnownRecord> enrichedByCategory = identify.known.stream()
                .map(categoryIdentifier::identify)
                .collect(Collectors.toList());
        final List<AnalyzeResult> results = analyzers.stream()
                .map(a -> a.analyze(enrichedByCategory))
                .collect(Collectors.toList());
        return new ResultProcessor(results);
    }

    public class ResultProcessor{
        private final List<AnalyzeResult> results;

        private ResultProcessor(List<AnalyzeResult> results){
            this.results = results;
        }

        public <T extends AnalyzeResult> Optional<T> get(Class<T> clazz){
            return results.stream()
                    .filter(r -> r.getClass().equals(clazz))
                    .map(r -> (T)r)
                    .findFirst();
        }
    }
}
