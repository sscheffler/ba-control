package de.moving.turtle.parse;

import de.moving.turtle.api.RawRecord;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static de.moving.turtle.api.RawRecord.fromCsvRecord;
import static org.apache.commons.csv.CSVFormat.DEFAULT;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class CsvRecordParser implements RecordParser{
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvRecordParser.class);
    private static final String ENCODING_ISO = "ISO-8859-1";


    @Override
    public Collection<RawRecord> parseToRaw(String filePath) {
        Reader in;
        final List<RawRecord> rawRecords = new ArrayList<>();
        try {
            in = new InputStreamReader(new FileInputStream(filePath), ENCODING_ISO);
            final Iterable<CSVRecord> csvRecords = DEFAULT
                    .withFirstRecordAsHeader()
                    .withDelimiter(';')
                    .parse(in);
            csvRecords.forEach(r -> rawRecords.add(fromCsvRecord(r)));
        } catch (Exception e) {
            LOGGER.error("Catched exception", e);
        }
        return rawRecords;
    }
}
