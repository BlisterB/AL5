package com.mklr.ruzzle.engine;

public class Letter {
    private int value;
    private Character letter;

    Letter () {
        this(null, 0);
    }

    Letter (char letter) {
        this(new Character(letter), 1);
    }

    Letter (Character letter) {
        this(letter, 1);
    }

    Letter (char letter, int value) {
        this(new Character(letter), value);
    }

    Letter (Character letter, int value) {
        this.letter = letter;
        this.value = value;
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
}
