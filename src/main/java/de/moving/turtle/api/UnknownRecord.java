package de.moving.turtle.api;

import java.math.BigDecimal;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class UnknownRecord extends ResolvedRecord {

    public static UnknownRecord from(RawRecord rawRecord){
        return new UnknownRecord(rawRecord.date(),
                rawRecord.value(),
                rawRecord.purchaser(),
                rawRecord.usage());
    }

    private UnknownRecord(String date, BigDecimal value, String purchaser, String usage) {
        super(date, value, purchaser, usage);
    }

    @Override
    public String toString() {
        return "UnknownRecord{"+super.toString()+"}";
    }
}
