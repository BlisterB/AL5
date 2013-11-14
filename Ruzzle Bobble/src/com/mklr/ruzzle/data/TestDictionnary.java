package com.mklr.ruzzle.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Locale;
import java.util.Date;
import java.util.Scanner;

import com.mklr.ruzzle.engine.Board;

public class TestDictionnary {
    public static void main(String[] args) {
        long beg = new Date().getTime();
/*
        RuzzleDictionary d = new RuzzleDictionary();
        Thread t = new Thread(d);
        t.start();
        while (t.isAlive());
        System.out.println("DONE in " + ((new Date().getTime()) - beg) + "s.");
  */

        /*
        ArrayList<Integer[]> t = new ArrayList<Integer[]>();
        Integer[] a = {3,4};
        t.add(a);
        Integer[] b = {3,4};

        System.out.println("A : " + a[0] + " " + a[1]);
        System.out.println("B : " + b[0] + " " + b[1]);
        System.out.println("equals : " + a.equals(b));

        for (Integer[] gg : t) {
            if (Arrays.equals(a, b)) {
                System.out.println("a = b");
            } else {
                System.out.println("a != b");
            }
        }
*/
        Board b = new Board();
        b.init();
    }
}
