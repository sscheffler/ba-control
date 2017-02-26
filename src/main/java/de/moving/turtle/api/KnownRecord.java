package de.moving.turtle.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.moving.turtle.api.category.Category;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class KnownRecord extends ResolvedRecord {

    private final String type;
    private Category category;

    public static KnownRecord from(RawRecord rawRecord, Identity identity){
        return new KnownRecord(rawRecord.date(),
                rawRecord.value(),
                identity.name,
                rawRecord.usage(),
                identity.type);
    }

    public KnownRecord(
            @JsonProperty("date") String date,
            @JsonProperty("value") BigDecimal value,
            @JsonProperty("purchaser") String purchaser,
            @JsonProperty("usage") String usage,
            @JsonProperty("type") String type) {
        super(date, value, purchaser, usage);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Category getCategory() {
        return category;
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
                ", category.categoryId='" + category.categoryId() + '\'' +
                ", " + super.toString() +
                '}';
    }
}
