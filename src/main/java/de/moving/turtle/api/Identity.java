package de.moving.turtle.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public class Identity {

    public final String identificationRegex;
    public final String name;
    public final String type;

    public Identity(@JsonProperty("identificationRegex") String identificationRegex,
                    @JsonProperty("name") String name,
                    @JsonProperty("type") String type) {
        this.identificationRegex = identificationRegex;
        this.name = name;
        this.type = type;
    }
}
