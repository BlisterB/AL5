package com.mklr.ruzzle.data;

import java.io.*;
import java.util.*;

import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;
import com.mklr.ruzzle.solver.SolutionWord;
import com.mklr.ruzzle.solver.SolveByDictionary;
import com.mklr.ruzzle.solver.SolveByMarbleGrid;
import com.mklr.ruzzle.solver.Solver;

public class TestDictionnary {
    public static void main(String[] args) throws IOException {
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

        /*
        Board b = new Board();
        b.init();
        */

        /*
        ArrayList<SolutionWord> al = new ArrayList<SolutionWord>();
        al.add(new SolutionWord("abcdefgh", 10));
        al.add(new SolutionWord("xyz", 100));
        al.add(new SolutionWord("jklazemlazeml", 23));
        al.add(new SolutionWord("xyqsd", 50));
        SolutionWord.changeSortType(Solver.SORT_BY_SCORE);
        Collections.sort(al, new SolutionWord());

        System.out.println(al);
        */
        
        //RuzzleDictionary d = new RuzzleDictionary("French", "dict/French.dict");
        RuzzleDictionary d = new RuzzleDictionary("English", "/usr/share/dict/words");
        d.init();

        Board b = new Board("English", d);
        //Board b = new Board("French", d);
        b.init();

        System.out.println("====== BOARD ======");
        System.out.println(b);
        System.out.println("====== BOARD ======");

        System.out.println("\n\n");

       /*
        Random r = new Random();
        for (int i = 0; i < 10000000; i++) {
            int random = r.nextInt(10000);
            double next = ((double)random)/100.0;
            try {
                new Marble(d.getLetterSet().getLetterByPercentage(next));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed with value " + next);
                break;
            }
        }
         */


        System.out.println("====== SOLVER : MARBLE GRID DFS ======");
        SolveByMarbleGrid solver = new SolveByMarbleGrid(b);
        long beg = new Date().getTime();
        solver.solve(Solver.SORT_BY_WORD_LENGTH);
        long end = new Date().getTime();
        //System.out.println(solver.getWordsList());
        System.out.println("====== SOLVER : MARBLE GRID DFS ======");
        System.out.println("\nAlg done in " + ((double)(end-beg)/(1000.0)) + "s.");
        System.out.println(solver.getWordsList().size() + " words found...");

        System.out.println("\n\n");

        System.out.println("====== SOLVER : DICTIONARY DFS ======");
        SolveByDictionary solver2 = new SolveByDictionary(b);
        long beg2 = new Date().getTime();
        solver2.solve(Solver.SORT_BY_WORD_LENGTH);
        long end2 = new Date().getTime();
        System.out.println("====== SOLVER : DICTIONARY DFS ======");
        System.out.println("\nAlg done in " + ((double)(end2-beg2)/(1000.0)) + "s.");
        System.out.println(solver2.getWordsList().size() + " words found...");

        System.out.println("\n\nTest des mots de l'algo 1 :");
        for (SolutionWord sw : solver.getWordsList()) {
            if (!d.searchWord(sw.getWord())) {
                System.out.println(sw.getWord());
            }
        }

        System.out.println("\n\nTest des mots de l'algo 2 :");
        for (SolutionWord sw : solver2.getWordsList()) {
            if (!d.searchWord(sw.getWord())) {
                System.out.println(sw.getWord());
            }
        }

        FileWriter fw = new FileWriter("/home/aaylor/.tmp/result_alg1");
        BufferedWriter bf = new BufferedWriter(fw);
        for (SolutionWord sw : solver.getWordsList()) {
            try {
                bf.write(sw.toString());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        FileWriter fw2 = new FileWriter("/home/aaylor/.tmp/result_alg2");
        BufferedWriter bf2 = new BufferedWriter(fw2);
        for (SolutionWord sw : solver2.getWordsList()) {
            try {
                bf2.write(sw.toString());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
