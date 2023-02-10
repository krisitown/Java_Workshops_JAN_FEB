package org.example.factory;

import org.example.AbstractWalker;

public class AnimalWalker extends AbstractWalker {
    public AnimalWalker(double speed) {
        super(speed);
    }

    @Override
    public void walk() {
        System.out.println("Animal is walking!");
        System.out.println("Speed is " + speed);
    }

    @Override
    protected double getSpeed() {
        return super.getSpeed();
    }

    double dSpeed() {
        return speed * 2;
    }

}
