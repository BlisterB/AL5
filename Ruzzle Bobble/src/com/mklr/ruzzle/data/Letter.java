package com.mklr.ruzzle.data;

/**
 * This class represent a letter.
 * When created, it could be set the value (ie. the score given by the current
 * letter), and the percentage of use in the langage.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Letter {
    private int value;
    private Character letter;
    private double[] percentage;

    /**
     * Create the letter with the primitive type char, and the given
     * value.
     * @param letter the letter given as a character
     * @param value the score given by the current letter
     */
    public Letter (char letter, int value) {
        this(new Character(letter), value, new double[]{0.0, 0.0});
    }
    
    /**
     * Create the letter with the object and the given value.
     * @param letter the letter
     * @param value the score given by the current letter
     */
    public Letter(Character letter, int value) {
        this(letter, value, new double[]{0.0, 0.0});
    }

    /**
     * Create the letter with the primitive type, the given value, 
     * and the percentage.
     * @param letter the letter
     * @param value the score given by the current letter
     * @param percentage the percentage
     */
    public Letter(char letter, int value, double[] percentage) {
        this(new Character(letter), value, percentage);
    }

    /**
     * Create the letter with the object, the given value, and the percentage.
     * @param letter the letter
     * @param value the score given by the current letter
     * @param percentage the percentage
     */
    public Letter(Character letter, int value, double[] percentage) {
        this.letter = letter;
        this.value = value;
        this.percentage = percentage;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the letter
     */
    public Character getLetter() {
        return letter;
    }

    /**
     * @param letter the letter to set
     */
    public void setLetter(Character letter) {
        this.letter = letter;
    }

    /**
     * @return the percentage
     */
    public double[] getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(double[] percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Letter) {
            return (((Letter)o).letter == letter
                && ((Letter)o).value == value
                && ((Letter)o).percentage == percentage);
        }
        return false;
    }

    @Override
    public String toString() {
        return "\t" + letter + " : " + value + " : {" 
            + percentage[0] + "%, " + percentage[1] + "%}\n";
    }
}
