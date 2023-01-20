package org.example;

public class StreetExtraordinaire extends Cat {
    private final Double decibelOfMeows;

    public StreetExtraordinaire(String name, Double decibelOfMeows) {
        super(name);
        this.decibelOfMeows = decibelOfMeows;
    }

    public Double getDecibelOfMeows() {
        return decibelOfMeows;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %.2f", decibelOfMeows);
    }
}
