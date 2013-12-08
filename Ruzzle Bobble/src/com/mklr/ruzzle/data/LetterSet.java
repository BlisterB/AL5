package com.mklr.ruzzle.data;

import java.util.HashMap;

/**
 * Cette classe représente un ensemble d'objet Lettre.
 *
 * Pour que cette création se fasse de manière optimale, selon la langue
 * choisi, un fichier dans ${RUZZLE_BOBBLE}/config/lang/${LANG}.set
 *
 * Il doit être écrit de la manière suivante :
 *     lettre:score:pourcentage
 *
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class LetterSet extends HashMap<Character, Letter>
{
	private static final long serialVersionUID = -7359698600848463258L;

	private String lang;

	/**
	 * Create a new LetterSet.
	 * The lang is set to the default value. (ie. english)
	 */
    public LetterSet() {
        this("enEN");
    }

    /**
     * Create a new LetterSet with the given langage.
     * @param lang
     */
    public LetterSet(String lang) {
        this.lang = lang;
    }

    /**
     * @return the locale
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the locale to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        String result = "{\n";
        for(Letter l : values()) {
            result += l;
        }
        result += "}";
        return result;
    }

    /**
     * Retourne la lettre selon la valeur donnée.
     * @param percentage
     * @return
     */
    public Letter getLetterByPercentage(double percentage) {
        for (Letter l : values()) {
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
        return get(c);
    }
}
