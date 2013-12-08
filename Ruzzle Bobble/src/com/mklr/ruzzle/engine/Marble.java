package com.mklr.ruzzle.engine;

import java.util.ArrayList;
import java.util.Arrays;

import com.mklr.ruzzle.data.Letter;

/**
 * This class represent a Marble of the ruzzle game.
 * It represent a letter for the game, with or without any
 * bonuses.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Marble {
    public static final byte NO_BONUS = 0;
    public static final byte LETTER_COUNT_DOUBLE    = 1;
    public static final byte LETTER_COUNT_TRIPLE    = 2;
    public static final byte WORD_COUNT_DOUBLE      = 4;
    public static final byte WORD_COUNT_TRIPLE      = 8;
    
    private Letter letter;
    private byte bonus;
    private ArrayList<Integer[]> neighbours;

    public Marble() {
        this(null, NO_BONUS);
    }

    public Marble(Letter letter) {
        this(letter, NO_BONUS);
    }

    public Marble(Letter letter, byte bonus) {
        this(letter, bonus, new ArrayList<Integer[]>());
    }

    public Marble(Letter letter, byte bonus, ArrayList<Integer[]> neighbours) {
        this.letter     = letter;
        this.bonus      = bonus;
        this.neighbours = neighbours;
    }

    /**
     * @return the l
     */
    public Letter getLetter() {
        return letter;
    }

    /**
     * @param letter the l to set
     */
    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    /**
     * @return the bonus
     */
    public byte getBonus() {
        return bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(byte bonus) {
        this.bonus = bonus;
    }

    /**
     * @return the neighbours
     */
    public ArrayList<Integer[]> getNeighbours() {
        return neighbours;
    }

    /**
     * @param neighbours the neighbours to set
     */
    public void setNeighbours(ArrayList<Integer[]> neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * Test if it's a neighbour
     * @param place array of marble's coordinate to test
     * @return true if it's a neighbour
     */
    public boolean isNeighbour(Integer[] place) {
        for (Integer[] i : neighbours) {
            if (Arrays.equals(place, i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + letter.getLetter() + "] ";
    }

}
