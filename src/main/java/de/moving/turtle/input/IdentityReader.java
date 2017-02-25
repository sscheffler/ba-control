package de.moving.turtle.input;

import de.moving.turtle.api.Identity;

import java.util.Collection;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public interface IdentityReader {
    Collection<Identity> read();
}
