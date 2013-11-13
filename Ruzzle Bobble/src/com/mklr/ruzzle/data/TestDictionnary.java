package com.mklr.ruzzle.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.util.Scanner;

import com.mklr.ruzzle.engine.Board;

public class TestDictionnary {
    public static void main(String[] args) {
        long beg = new Date().getTime();
/*
        Dictionnary d = new Dictionnary();
        Thread t = new Thread(d);
        t.start();
        while (t.isAlive());
        System.out.println("DONE in " + ((new Date().getTime()) - beg) + "s.");
  */

        Board b = new Board();
        b.init();
    }
}
