package com.mklr.ruzzle.data;

public class Letter {
    private int value;
    private Character letter;
    private double percentage;

    public Letter (char letter, int value) {
        this(new Character(letter), value, 0.0);
    }
    
    public Letter(Character letter, int value) {
        this(letter, value, 0.0);
    }

    public Letter(char letter, int value, double percentage) {
        this(new Character(letter), value, percentage);
    }

    public Letter(Character letter, int value, double percentage) {
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
    public double getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(double percentage) {
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
        return "\t" + letter + " : " + value + " : " + percentage + "%\n";
    }
}
