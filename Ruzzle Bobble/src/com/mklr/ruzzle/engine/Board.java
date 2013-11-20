package com.mklr.ruzzle.engine;

import java.util.ArrayList;

import java.util.Locale;
import java.util.Random;

import com.mklr.collection.AbstractGrid;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.data.Letter;

/**
 * Create the board for the ruzzle game.
 * The board is an hexagon. The default value is 2.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Board extends AbstractGrid<Marble>{
    private int score;
    private Locale locale;
    private RuzzleDictionary dico;

    /**
     * Create the board with default value.
     */
    public Board() {
        this(2);
    }

    /**
     * Create the board with row size.
     * @param row
     */
    public Board(int row) {
        this(row, Locale.ENGLISH);
    }

    /**
     * Create the board with row size, and the langage.
     * @param row
     * @param locale
     */
    public Board(int row, Locale locale) {
        this(row, locale, null);
    }
    
    /**
     * Create the board with row size, the langage and the dictionary.
     * @param row
     * @param locale
     * @param dico
     */
    public Board(int row, Locale locale, RuzzleDictionary dico) {
        super(row);
        this.dico = dico;
        score = 0;
        this.locale = locale;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
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

    /**
     * @return the dico
     */
    public RuzzleDictionary getDico() {
        return dico;
    }

    /**
     * @param dico the dico to set
     */
    public void setDico(RuzzleDictionary dico) {
        this.dico = dico;
    }

    public Marble[][] getBoard() {
        return super.getGrid();
    }

    /**
     * Initialize the board.
     * MUST BE CALLED BEFORE PLAYING THE GAME !!
     */
    public void init() {
        tGrid = new Marble[2 * row][];
        for(int i = 0; i <= row/2; i++) {
            tGrid[i] = new Marble[(2 * row) + (2 * i) + 1];
            tGrid[(2 * row) - i - 1] = new Marble[(2 * row) + (2 * i) + 1];
        }

        fillGrid();
    }

    /**
     *  Fill the grid.
     */
    private void fillGrid() {
        // TODO
        Random r = new Random();
        int cpt_star = 2;
        int cpt_word_count_double = 2;
        int cpt_word_count_triple = 1;
        int cpt_letter_count_double = 2;
        int cpt_letter_count_triple = 1;
/*
        if (dico == null) {
            //TODO temporary, change condition after
        }
        else {*/
            for (int i = 0; i < tGrid.length; i++) {
                for (int j = 0; j < tGrid[i].length; j++) {
         //           int random = r.nextInt(10000);
         //           tGrid[i][j] = new Marble(dico.getLetterSet().getLetterByPercentage(((double)random)/100.0));
                    int random = r.nextInt(26) + 97;
                    tGrid[i][j] = new Marble(new Letter((char)random, 1,new double[]{0,  100./26.0}));
                        
                    //TODO Gérer les bonus
                    addNeighbours(i, j);
                }   
            }
       // }

        tGrid[0][0] = new Marble(new Letter('m', 1));
        addNeighbours(0, 0);
        tGrid[1][0] = new Marble(new Letter('o', 1));
        addNeighbours(1, 0);
        tGrid[2][0] = new Marble(new Letter('*', 0));
        addNeighbours(2, 0);
        tGrid[3][0] = new Marble(new Letter('e', 1));
        addNeighbours(3, 0);
        
        tGrid[0][1].setBonus(Marble.LETTER_COUNT_DOUBLE);
        tGrid[1][2].setBonus(Marble.LETTER_COUNT_TRIPLE);
        tGrid[3][2].setBonus(Marble.WORD_COUNT_DOUBLE);
        tGrid[2][1].setBonus(Marble.WORD_COUNT_TRIPLE);
    }

    /**
     * It adds every neighbours of the marble in the [i][j] pos.
     * @param i
     * @param j
     */
    private void addNeighbours(int i, int j) {
        ArrayList<Integer[]> newNeighbours = new ArrayList<Integer[]>(6);
        int lineOfTheTop = 
            ((i < row)  ? ((j&1) == 0 ? i-1 : i+1)
                        : ((j&1) == 0 ? i+1 : i-1));

        for (int currentLine = i-1; currentLine < i+2; currentLine++) {
            if (currentLine < 0 || currentLine >= tGrid.length)
                continue;

            for (int currentMarble = j-(2*row); 
                    currentMarble <= j+(2*row); currentMarble++) {
                if (currentMarble < 0 
                        || currentMarble >=tGrid[currentLine].length
                        || (currentLine == i && currentMarble == j)
                        || !toAdd(currentLine == lineOfTheTop,
                                    currentLine, currentMarble,
                                    i, j)) 
                {
                    continue;
                }

                newNeighbours.add(new Integer[]{currentLine, currentMarble});
            }
        }

        tGrid[i][j].setNeighbours(newNeighbours);
    }
    
    /**
     * It returns a boolean to say if the current case is a neighbour of
     * the current marble.
     * @param tline
     * @param line
     * @param cpt
     * @param i
     * @param j
     * @return true if it's a neighbour.
     */
    private boolean toAdd(boolean tline, int line, int cpt, int i, int j) {
        if (tline) {
            if (line == i-1) {
                return cpt >= (j-3+i) && cpt <= (j+i-1);
            } else {
                return cpt >= (j-i) && cpt <= (j+2-i);
            }
        } else {
            if (line == i) {
                return cpt >= (j-2) && cpt <= (j+2);
            } else {
                if (line == i-1) {
                    return cpt >= (j-4+i) && cpt <= (j+i);
                } else {
                    return cpt >= (j-i-1) && cpt <= (j+3-i);
                }
            }
        }
    }

    @Override
    public String toString() {
        String result = new String();

        result += "\n";
        for(Marble[] m1 : tGrid) {
            for (Marble m2 : m1) {
                System.out.print(m2);
            }
            System.out.println();
        }

        result += "\n";
        return result;
    }
}
