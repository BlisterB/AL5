package com.mklr.ruzzle.engine;

import java.util.ArrayList;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.mklr.ruzzle.data.Dictionnary;
import com.mklr.ruzzle.data.Letter;

public class Board {
    private int row;
    private int state;
    private int score;
    private Marble[][] board;
    private Locale locale;
    private Dictionnary dico;

    public Board() {
        this(2);
    }

    public Board(int row) {
        this(row, Locale.ENGLISH);
    }

    public Board(int row, Locale locale) {
        this(row, locale, null);
    }
    
    public Board(int row, Locale locale, Dictionnary dico) {
        this(row, locale, dico, false);
    }

    public Board(int row, Locale locale, Dictionnary dico, boolean init) {
        this.row = row;
        this.dico = dico;
        state = WHITE;
        score = 0;
        this.locale = locale;

        board = new Marble[2 * row][];
        for(int i = 0; i <= row/2; i++) {
            board[i] = new Marble[(2 * row) + (2 * i) + 1];
            board[(2 * row) - i - 1] = new Marble[(2 * row) + (2 * i) + 1];
        }

        if (init)
            init();
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
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
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
    public Dictionnary getDico() {
        return dico;
    }

    /**
     * @param dico the dico to set
     */
    public void setDico(Dictionnary dico) {
        this.dico = dico;
    }

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
            System.err.println("No dictionnary found... Board will be created"
                    + " with random letters...");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    int random = r.nextInt(26) + 97;
     /*               System.out.println((char)random + " " + random);
                    System.out.println("found : " + dico.getLetterSet().getLetter((char)random));
                    board[i][j] = new Marble(
                        dico.getLetterSet()
                        .getLetter((char)random));
                    */

                    board[i][j] = new Marble(new Letter((char)random, 1));

                    //TODO Gérer les bonus
                    //TODO Gérer les voisins (trouver formules...)
                }   
            }
        }

        board[0][1].setBonus(Marble.LETTER_COUNT_DOUBLE);
        board[1][2].setBonus(Marble.LETTER_COUNT_TRIPLE);
        board[3][2].setBonus(Marble.WORD_COUNT_DOUBLE);
        board[2][1].setBonus(Marble.WORD_COUNT_TRIPLE);

        addNeighbours();
        System.out.println("BOARD DONE IN " + ((new Date().getTime()) - beg) + "s.");
    }

    private void addNeighbours() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ArrayList<Marble> neighbours = new ArrayList<Marble>(6);

                int ligneATrois = 
                        ((i < row) 
                            ? ((j&1) == 0 ? i-1 : i+1)
                            : ((j&1) == 0 ? i+1 : i-1));

                for (int k = i-1; k < i+2; k++) {
                    if (k < 0 || k > (2 * row))
                        continue;

                    System.out.println("FOR board["+i+"]["+j+"]");
                    for (int cpt = j-2; cpt < j+3; cpt++) {
                       
                        if ( ((k == ligneATrois)
                                && ((i < (row-1) && cpt < j || cpt > (j+2))
                                    || ((i == row-1 && cpt < j-1 || cpt > j+1))
                                    || ((i >= row && cpt < j-2 || cpt > j))))
                                || (cpt < 0)
                                || (cpt >= board[i].length)
                                || (k == i && cpt == j))
                            continue;

                        System.out.println("\tadd board["+k+"]["+cpt+"] ~~"
                                + " i: " + i 
                                + " l: " + (board[i].length - 1));
                        neighbours.add(board[k][cpt]);
                    }
                }

                board[i][j].setNeighbours(neighbours);
            }
        }
    }

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
