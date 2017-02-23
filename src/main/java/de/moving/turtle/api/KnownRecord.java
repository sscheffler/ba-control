package de.moving.turtle.api;

import de.moving.turtle.api.category.Category;

import java.math.BigDecimal;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class KnownRecord extends ResolvedRecord {

    private final String type;
    private Category category;

    public static KnownRecord from(RawRecord rawRecord, Identity identityOld){
        return new KnownRecord(rawRecord.date(),
                rawRecord.value(),
                identityOld.name,
                rawRecord.usage(),
                identityOld.type);
    }

    private KnownRecord(String date, BigDecimal value, String purchaser, String usage, String type) {
        super(date, value, purchaser, usage);
        this.type = type;
    }

    public KnownRecord withCategory(Category category) {
        this.category = category;
        return this;
    }

    public String type() {
        return type;
    }

    public Category category() {
        return category;
    }

    @Override
    public String toString() {
        return "KnownRecord{" +
                "type='" + type + '\'' +
                ", category.id='" + category.id() + '\'' +
                ", " + super.toString() +
                '}';
    }
}
