package com.mklr.ruzzle.data;

import java.io.File;
import java.text.Normalizer;
import java.util.Locale;
import java.util.Scanner;

import com.mklr.collection.Tree;

public class Dictionnary implements Runnable {
    private final String dictionnaryPath;
    private Locale locale;
    private Tree<Character> dictionnaryTree;

    public Dictionnary() {
        this(Locale.ENGLISH);
    }

    public Dictionnary(Locale locale) {
        this(Locale.ENGLISH, "/usr/share/dict/words");
    }

    public Dictionnary(Locale locale, String dictionnaryPath) {
        this(Locale.ENGLISH, dictionnaryPath, false);
    }

    public Dictionnary(Locale locale, String dictionnaryPath, boolean init) {
        this.locale = locale;
        this.dictionnaryPath = dictionnaryPath;
        dictionnaryTree = new Tree<Character>();

        if (init)
            init();
    }

    /**
     * @return the dictionnaryPath
     */
    public String getDictionnaryPath() {
        return dictionnaryPath;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the dictionnaryTree
     */
    public Tree<Character> getDictionnaryTree() {
        return dictionnaryTree;
    }

    /**
     * @param dictionnaryTree the dictionnaryTree to set
     */
    public void setDictionnaryTree(Tree<Character> dictionnaryTree) {
        this.dictionnaryTree = dictionnaryTree;
    }

    public void init() {
        try {
            File f = new File(dictionnaryPath);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                Tree<Character> cur_pos = dictionnaryTree;
                String line = sc.nextLine();
                int length = line.length();

                line = normalizeWord(line);
                for (int i = 0; i <length; i++) {
                    Character c = line.charAt(i);

                    if (!cur_pos.childExist(c))
                        cur_pos.add(c, new Tree<Character>(c));

                    cur_pos = cur_pos.getChild(c);
                }
                
                cur_pos.setStatus(Tree.TERMINAL);
            }

            sc.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean searchWord(String word) {
        Tree<Character> cur_pos = dictionnaryTree;
        int length = word.length();

        for (int i = 0; i < length; i++) {
            Character c = word.charAt(i);

            if (cur_pos.childExist(c))
                cur_pos = cur_pos.getChild(c);
            else
                return false;
        }

        return cur_pos.isTerminal();
    }

    private String normalizeWord(String s) {
        s = s.toLowerCase();
        return Normalizer.normalize(s, Normalizer.Form.NFD)
            .replaceAll("[\u0300-\u036F]", "");
    }

    @Override
    public void run() {
        init();
    }
}
