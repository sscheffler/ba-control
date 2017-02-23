package de.moving.turtle.api;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
public enum  Identity {
    PAYPAL("^PayPal.*","Paypal", Constants.ONLINE_PAYMENT_PROVIDER),
    NETTO("^NETTO.*","Netto", Constants.FOOD),
    REWE("^REWE.*","Rewe", Constants.FOOD),
    AMAZON("^AMAZON.*","Amazon", Constants.ONLINE_STORE)
    ;

    public final String identificationRegex;
    public final String name;
    public final String type;

    Identity(String identificationRegex, String name, String type) {
        this.identificationRegex = identificationRegex;
        this.name = name;
        this.type = type;
    }

    private static class Constants {
        static final String ONLINE_PAYMENT_PROVIDER = "Online Payment Provider";
        static final String ONLINE_STORE = "Online Store";
        static final String FOOD = "Food";
    }
}
