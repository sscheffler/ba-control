package de.moving.turtle.parse;

import de.moving.turtle.api.Identity;
import de.moving.turtle.api.KnownRecord;
import de.moving.turtle.api.RawRecord;
import de.moving.turtle.api.UnknownRecord;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class RecordIdentifier {

    public class IdentificationResult {
        public List<KnownRecord> known= new ArrayList();
        public List<UnknownRecord> unknown= new ArrayList();
    }

    public IdentificationResult identify(Collection<RawRecord> records){
        final IdentificationResult result = new IdentificationResult();
        for (RawRecord rawRecord : records) {
            final Optional<KnownRecord> knownRecord = Arrays.stream(Identity.values())
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
