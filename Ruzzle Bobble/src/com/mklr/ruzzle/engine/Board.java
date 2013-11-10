package com.mklr.ruzzle.engine;

public class Board {
    public static final int WHITE = 0;
    public static final int GREY  = 1;
    public static final int BLACK = 2;

    private int row;
    private int state;
    private Marble[][] board;

    public Board() {
        this(2);
    }

    public Board(int row) {
        this(row, false);
    }

    public Board(int row, boolean init) {
        this.row = row;
        state = WHITE;
        
        board = new Marble[2 * row][];
        for(int i = 0; i < row/2; i++) {
            board[i] = new Marble[(2 * row) + (2 * i) + 1];
            board[row - i - 1] = new Marble[(2 * row) + (2 * i) + 1];
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
    
    private void init() {
        // TODO
    }
}
