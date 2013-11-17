package com.mklr.ruzzle.solver;

public abstract class Solver {
    public static final byte BREADTH_FIRST_SEARCH = 0;
    public static final byte DEPTH_FIRST_SEARCH = 1;

    public static final byte SORT_BY_NAME = 0;
    public static final byte SORT_BY_SCORE = 1;
    public static final byte SORT_BY_WORD_LENGTH = 2;

    
    protected byte sortType;

    protected Solver() {

    }

    protected Solver(byte sortType) {
        this.sortType = sortType;
    }

    /**
     * @return the sortType
     */
    public byte getSortType() {
        return sortType;
    }

    /**
     * @param sortType the sortType to set
     */
    public void setSortType(byte sortType) {
        this.sortType = sortType;
    }
}
