package org.example;

public class Siamese extends Cat {
    private final Double earSize;

    public Siamese(String name, Double earSize) {
        super(name);
        this.earSize = earSize;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %.2f", earSize);
    }
}
