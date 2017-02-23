package de.moving.turtle.parse;

import de.moving.turtle.api.*;
import de.moving.turtle.input.IdentityReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class RecordIdentifier {

    private final IdentityReader identityReader;

    @Autowired
    public RecordIdentifier(@Qualifier("jsonIdentityReader") IdentityReader identityReader) {
        this.identityReader = identityReader;
    }

    public class IdentificationResult {
        public List<KnownRecord> known= new ArrayList();
        public List<UnknownRecord> unknown= new ArrayList();
    }

    public IdentificationResult identify(Collection<RawRecord> records){
        final Collection<Identity> identities = identityReader.read();
        final IdentificationResult result = new IdentificationResult();
        for (RawRecord rawRecord : records) {
            final Optional<KnownRecord> knownRecord = identities.stream()
                    .filter(i -> rawRecord.purchaser().matches(i.identificationRegex))
                    .findFirst()
                    .map(i -> KnownRecord.from(rawRecord, i));
            if(knownRecord.isPresent()){
                result.known.add(knownRecord.get());
            } else {
                result.unknown.add(UnknownRecord.from(rawRecord));
            }
        }
        return result;
    }

}
