package com.mklr.ruzzle.data;

import java.util.Locale;
import java.util.Date;

import com.mklr.ruzzle.engine.Board;

public class TestDictionnary {
    public static void main(String[] args) {
        long beg = new Date().getTime();

        Dictionnary d = new Dictionnary();
        Thread t = new Thread(d);
        t.start();
        while (t.isAlive());
        System.out.println(d.getLetterSet());
        System.out.println("DONE in " + ((new Date().getTime()) - beg) + "s.");
/*
        Board b = new Board(2, Locale.ENGLISH, d);
        b.init();
        System.out.println(b);*/
    }
}
