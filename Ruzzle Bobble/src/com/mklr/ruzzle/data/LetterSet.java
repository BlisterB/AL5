package com.mklr.ruzzle.data;

import java.util.HashSet;
import java.util.Locale;

/**
 * This class represent a Set of letters.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class LetterSet extends HashSet<Letter>
{
	private static final long serialVersionUID = -7359698600848463258L;

	private Locale locale;

	/**
	 * Create a new LetterSet.
	 * The lang is set to the default value. (ie. english)
	 */
    public LetterSet() {
        this(Locale.ENGLISH);
    }

    /**
     * Create a new LetterSet with the given langage.
     * @param locale 
     */
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

    public Letter getLetterByPercentage(double percentage) {
        for (Letter l : this) {
            System.out.println(l);
            double[] letterPercentages = l.getPercentage();
            if (percentage >= letterPercentages[0] 
                    && percentage < letterPercentages[1])
                return l;
        }

        return null;
    }

    /**
     * Return the letter needed in the Set.
     * It'll be search with the given Character.
     * @param c the needed character
     * @return the letter if it exists
     */
    public Letter getLetter(Character c) {
        for (Letter l : this) {
            if (c.equals(l.getLetter()))
                return l;
        }
        return null;
    }
}
