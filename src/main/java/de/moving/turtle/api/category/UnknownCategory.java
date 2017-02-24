package de.moving.turtle.api.category;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class UnknownCategory extends Category{
    public UnknownCategory() {
        super("category.unknown", "Unknown");
    }
}
