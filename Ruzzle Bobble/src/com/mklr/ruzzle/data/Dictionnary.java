package com.mklr.ruzzle.data;

import java.util.Locale;
import com.mklr.collection.Tree;

public class Dictionnary {
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

    }
}
