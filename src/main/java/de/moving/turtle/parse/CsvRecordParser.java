package de.moving.turtle.parse;

import de.moving.turtle.api.RawRecord;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static de.moving.turtle.api.RawRecord.fromCsvRecord;
import static java.util.Optional.*;
import static org.apache.commons.csv.CSVFormat.DEFAULT;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class CsvRecordParser implements RecordParser{
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvRecordParser.class);
    private static final String ENCODING_ISO = "ISO-8859-1";


    @Override
    public Optional<Collection<RawRecord>> parseToRaw(String filePath) {
        Reader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(filePath), ENCODING_ISO);
            final List<RawRecord> rawRecords = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = DEFAULT
                    .withFirstRecordAsHeader()
                    .withDelimiter(';')
                    .parse(in);
            csvRecords.forEach(r -> rawRecords.add(fromCsvRecord(r)));
            return of(rawRecords);
        } catch (Exception e) {
            LOGGER.error("Catched exception", e);
        }
        return empty();
    }
}
