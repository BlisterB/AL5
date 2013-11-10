package com.mklr.ruzzle.data;

import java.util.HashSet;
import java.util.Locale;

public class LetterSet extends HashSet<Letter>
{
    private Locale locale;

    public LetterSet() {
        this(Locale.ENGLISH);
    }

    public LetterSet(Locale locale) {
        this.locale = locale;
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
}
