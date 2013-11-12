package com.mklr.ruzzle.data;

import java.util.Locale;

import com.mklr.ruzzle.engine.Board;

public class TestDictionnary {
    public static void main(String[] args) {
        
        Dictionnary d = new Dictionnary();
        d.init();
/*        
        Thread t = new Thread(d);
        t.start();
        while (t.isAlive());
        System.out.println(d.getLetterSet()); */

        Board b = new Board(2, Locale.ENGLISH, d);
        b.init();
        System.out.println(b);
    }
}
