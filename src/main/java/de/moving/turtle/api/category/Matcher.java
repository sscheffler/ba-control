package de.moving.turtle.api.category;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class Matcher {

    public final String strategy;
    public final String regex;

    public Matcher(@JsonProperty("strategy") String strategy,
                   @JsonProperty("regex") String regex) {
        this.strategy = strategy;
        this.regex = regex;
    }
}
