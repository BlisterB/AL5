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

    public boolean contains(Letter letter) {
        for (Letter l : this) {
            if (l.equals(letter))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "{\n";
        for(Letter l : this) {
            result += l;
        }
        result += "}";
        return result;
    }

    public Letter getLetter(Character c) {
        for (Letter l : this) {
            if (c.equals(l.getLetter()))
                return l;
        }

        return  null;
    }
}
