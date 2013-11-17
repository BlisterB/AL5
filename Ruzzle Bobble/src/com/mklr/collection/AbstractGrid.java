package com.mklr.collection;

/**
 * This class is an abstraction of a grid.
 * It could be use for everything which could be seen as a grid.
 *
 * NOTE :   the grid IS NOT initialized !
 *          it must be initialized in the init() function before use.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public abstract class AbstractGrid<T> {
    protected T[][] tGrid;
    protected int row;

    /**
     * Create the grid with the row given.
     * @param row
     */
    protected AbstractGrid(int row) {
        this.row = row;
    }

    /**
     * @return the grid
     */
    public T[][] getGrid() {
        return tGrid;
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

    public abstract void init();
}
