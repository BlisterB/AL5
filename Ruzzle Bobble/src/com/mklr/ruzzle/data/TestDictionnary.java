package com.mklr.ruzzle.data;

import java.util.Date;

public class TestDictionnary {
    public static void main(String[] args) {
        long beg, beg2;
        
        beg = (new Date()).getTime();
        Dictionnary d = new Dictionnary();
        d.init();
        System.out.printf("TIME (s) : %d\n", (new Date()).getTime() - beg);

        beg2 = (new Date()).getTime();
        if (d.searchWord("corsepresent")) {
            System.out.printf("EXIST\n");
        } else {
            System.out.printf("DOESN'T EXIST\n");
        }
        System.out.printf("TIME to find \"corsepresent\" (s) : %d\n",
                (new Date()).getTime() - beg2);
    }
}
