package de.moving.turtle.analyze;

import de.moving.turtle.api.AnalyzeResult;
import de.moving.turtle.api.KnownRecord;

import java.util.Collection;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public interface Analyzer<T extends AnalyzeResult> {

    T analyze(Collection<KnownRecord> records);
}
