package org.example.factory;

import org.example.AnimalWalker;
import org.example.InsectWalker;
import org.example.PersonWalker;
import org.example.Walker;

public class WalkerFactoryImpl {
    public Walker createWalker(String type, Double speed) {
        return new InsectWalker(speed);

//        if(type.equals("Animal")) {
//            return new AnimalWalker(speed);
//        }
//
//        if(type.equals("Person")) {
//            return new PersonWalker(speed);
//        }
//
//        throw new IllegalArgumentException("No such walker type exists!");
    }
}
