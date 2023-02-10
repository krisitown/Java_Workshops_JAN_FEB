package org.example.factory;

import org.example.Walker;

public interface WalkerFactory {
    Walker createWalker(String type, Double speed);
}
