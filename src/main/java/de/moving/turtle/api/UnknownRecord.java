package de.moving.turtle.api;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    private UnknownRecord(@JsonProperty("date") String date,
                          @JsonProperty("value") BigDecimal value,
                          @JsonProperty("purchaser") String purchaser,
                          @JsonProperty("usage") String usage) {
        super(date, value, purchaser, usage);
    }


    @Override
    public String toString() {
        return "UnknownRecord{"+super.toString()+"}";
    }
}
