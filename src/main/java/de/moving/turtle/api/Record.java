package de.moving.turtle.api;

import java.math.BigDecimal;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public interface Record {

    String date();
    BigDecimal value();
    String purchaser();
    String usage();



}
