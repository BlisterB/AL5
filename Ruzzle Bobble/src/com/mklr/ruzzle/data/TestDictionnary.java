package com.mklr.ruzzle.data;

public class TestDictionnary {
    public static void main(String[] args) {
        
        Dictionnary d = new Dictionnary();
        Thread t = new Thread(d);
        
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("i : " + i);
        }
    }
}
