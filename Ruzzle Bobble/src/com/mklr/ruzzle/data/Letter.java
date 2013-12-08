package com.mklr.ruzzle.data;

/**
 * Cette classe représente une lettre.
 * A la création, on peut lui associé un score, et un pourcentage d'utilisation
 * dans la langue du dictionnaire.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Letter {
    private int value;
    private Character letter;
    private double[] percentage;

    /**
     * Créer la lettre selon la lettre donnée, et son score.
     * @param letter the letter given as a character
     * @param value the score given by the current letter
     */
    public Letter (char letter, int value) {
        this(new Character(letter), value, new double[]{0.0, 0.0});
    }
    
    /**
     * Créer la lettre selont la lettre donnée et le score.
     * Par défaut, le pourcentage donné est de 0.
     * @param letter the letter
     * @param value the score given by the current letter
     */
    public Letter(Character letter, int value) {
        this(letter, value, new double[]{0.0, 0.0});
    }

    /**
     * Créer la lettre selon la lettre donnée, le score, et le pourcentage.
     * @param letter the letter
     * @param value the score given by the current letter
     * @param percentage the percentage
     */
    public Letter(char letter, int value, double[] percentage) {
        this(new Character(letter), value, percentage);
    }

    /**
     * Créer la lettre selon la lettre donnée, le score, et le pourcentage
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
