package com.mklr.ruzzle.engine;

import java.util.ArrayList;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.data.Letter;

/**
 * Create the board for the ruzzle game.
 * The board is an hexagon. The default value is 2.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Board {
    private int row;
    private int score;
    private Marble[][] board;
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
        this.row = row;
        this.dico = dico;
        score = 0;
        this.locale = locale;

        board = new Marble[2 * row][];
        for(int i = 0; i <= row/2; i++) {
            board[i] = new Marble[(2 * row) + (2 * i) + 1];
            board[(2 * row) - i - 1] = new Marble[(2 * row) + (2 * i) + 1];
        }
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
     * @return the board
     */
    public Marble[][] getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Marble[][] board) {
        this.board = board;
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

    /**
     * Initialize the board.
     * MUST BE CALLED BEFORE PLAYING THE GAME !!
     */
    public void init() {
        // TODO
        Random r = new Random();
        int cpt_star = 2;
        int cpt_word_count_double = 2;
        int cpt_word_count_triple = 1;
        int cpt_letter_count_double = 2;
        int cpt_letter_count_triple = 1;

        long beg = (new Date().getTime());

        if (dico != null) {
            //TODO temporary, change condition after
        }
        else {
            System.err.println("No Dictionary found... Board will be created"
                    + " with random letters...");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    int random = r.nextInt(26) + 97;
                    board[i][j] = new Marble(new Letter((char)random, 1));

                    //TODO Gérer les bonus
                    addNeighbours(i, j);
                }   
            }
        }
        board[board.length-1][board[board.length-1].length-1] = new Marble(new Letter('*', 1));

        board[0][1].setBonus(Marble.LETTER_COUNT_DOUBLE);
        board[1][2].setBonus(Marble.LETTER_COUNT_TRIPLE);
        board[3][2].setBonus(Marble.WORD_COUNT_DOUBLE);
        board[2][1].setBonus(Marble.WORD_COUNT_TRIPLE);
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
            if (currentLine < 0 || currentLine >= board.length)
                continue;

            for (int currentMarble = j-(2*row); 
                    currentMarble <= j+(2*row); currentMarble++) {
                if (currentMarble < 0 
                        || currentMarble >= board[currentLine].length
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

        board[i][j].setNeighbours(newNeighbours);
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
        for(Marble[] m1 : board) {
            for (Marble m2 : m1) {
                System.out.print(m2);
            }
            System.out.println();
        }

        result += "\n";
        return result;
    }
}
