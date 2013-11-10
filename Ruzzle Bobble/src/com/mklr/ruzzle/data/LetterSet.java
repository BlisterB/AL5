package com.mklr.ruzzle.data;

import java.util.HashSet;

public class LetterSet extends HashSet<Letter>
{
    public static final String ENGLISH = "en.EN";
    public static final String FRENCH  = "fr.FR";

    private String locale;

    public LetterSet() {
        this(ENGLISH);
    }

    public LetterSet(String locale) {
        this.locale = locale;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void add(Character c, int value) {
        super.add(new Letter(c, value));
    }
}
