package de.moving.turtle.api;

import java.math.BigDecimal;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public abstract class ResolvedRecord implements Record {

    final String date;
    final BigDecimal value;
    final String purchaser;
    final String usage;

    protected ResolvedRecord(String date, BigDecimal value, String purchaser, String usage) {
        this.date = date;
        this.value = value;
        this.purchaser = purchaser;
        this.usage = usage;
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
        return "ResolvedRecord{" +
                "date='" + date + '\'' +
                ", value=" + value +
                ", purchaser='" + purchaser + '\'' +
                ", usage='" + usage + '\'' +
                '}';
    }
}
