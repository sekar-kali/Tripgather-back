package org.wcs.tripgather.model;

public enum Gender {
    HOMME("Homme"),
    FEMME("Femme"),
    MIXTE("Mixte");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
