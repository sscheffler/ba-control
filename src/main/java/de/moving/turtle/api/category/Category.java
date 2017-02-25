package de.moving.turtle.api.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class Category {

    @Id
    public String id;
    public String categoryId;
    public String label;
    public List<Matcher> matchers = new ArrayList<>();

    public Category(@JsonProperty("categoryId") String categoryId,
                    @JsonProperty("label") String label) {
        this.categoryId = categoryId;
        this.label = label;
    }

    public String categoryId() {
        return categoryId;
    }

    public String label() {
        return label;
    }

    public List<Matcher> matchers() {
        return matchers;
    }
}
