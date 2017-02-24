package de.moving.turtle.api.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class Category {
    public String id;
    public String label;
    public List<Matcher> matchers = new ArrayList<>();

    public Category(@JsonProperty("id") String id,
                    @JsonProperty("label") String label) {
        this.id = id;
        this.label = label;
    }

    public String id() {
        return id;
    }

    public String label() {
        return label;
    }

    public List<Matcher> matchers() {
        return matchers;
    }
}
