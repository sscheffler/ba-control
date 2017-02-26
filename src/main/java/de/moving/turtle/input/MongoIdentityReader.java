package de.moving.turtle.input;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.moving.turtle.api.Identity;
import de.moving.turtle.mongo.IdentityRepository;
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
public class MongoIdentityReader implements IdentityReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoIdentityReader.class);
    private final IdentityRepository identityRepository;

    public MongoIdentityReader(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    @Override
    public Collection<Identity> read() {
        return identityRepository.findAll();
    }
}
