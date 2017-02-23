package de.moving.turtle.api;

import org.apache.commons.csv.CSVRecord;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class RawRecord implements Record {
    final String date;
    final BigDecimal value;

    final String purchaser;
    final String usage;

    public static RawRecord fromCsvRecord(CSVRecord record){
        return new RawRecord(record);
    }

    private RawRecord(CSVRecord record) {
        date = record.get("Buchungstag");
        value = toValue(record.get("Haben"), record.get("Soll"));
        purchaser = record.get("Begünstigter / Auftraggeber");
        usage = record.get("Verwendungszweck");
    }

    private BigDecimal toValue(String haben, String soll) {
        if(isEmpty(haben) && isEmpty(soll)) {
            return ZERO;
        } else if(isEmpty(soll)){
            return new BigDecimal(haben.replace(".", "").replace(",","."));
        } else {
            return new BigDecimal(soll.replace(".", "").replace(",","."));
        }
    }

    @Override
    public String date() {
        return date;
    }

    @Override
    public BigDecimal value() {
        return value;
    }

    @Override
    public String purchaser() {
        return purchaser;
    }

    @Override
    public String usage() {
        return usage;
    }

    @Override
    public String toString() {
        return "RawRecord{" +
                "date='" + date + '\'' +
                ", value=" + value +
                ", purchaser='" + purchaser + '\'' +
                ", usage='" + usage + '\'' +
                '}';
    }
}
