package de.moving.turtle.input;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.moving.turtle.api.Identity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class JsonIdentityReader implements IdentityReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonIdentityReader.class);
    private static ObjectMapper MAPPER = new ObjectMapper();

    @Value("${path.identities}")
    private String identityPath;

    @Override
    public Collection<Identity> read() {
        try {
            LOGGER.info("Read in identities file '{}'", identityPath);
            final File file = new File(identityPath);
            return MAPPER.readValue(file, new TypeReference<List<Identity>>(){});
        } catch (IOException e) {
            LOGGER.error("Error", e);
        }

        return Collections.emptyList();
    }
}
