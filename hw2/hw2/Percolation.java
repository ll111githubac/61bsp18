package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private boolean[][] grid;
    private int numOfOpenSites;

    private WeightedQuickUnionUF UF;
    private int start;
    private int end;

    /**
     * create N-by-N grid, with all sites initially blocked
     */
    public Percolation(int N) {
        this.N = N;
        grid = new boolean[N][N];
        UF = new WeightedQuickUnionUF(N * N + 2);
        start = N * N;
        end = N * N + 1;
        numOfOpenSites = 0;
    }

    /**
     * convert (row, col) pairs to nodes in UF
     */
    private int toOneD(int row, int col) {
        return row * N + col;
    }

    private void connectToNeighbors(int row, int col) {
        if (isInBound(row + 1, col) && isOpen(row + 1, col)) {
            UF.union(toOneD(row, col), toOneD(row + 1, col));
        }
        if (isInBound(row - 1, col) && isOpen(row - 1, col)) {
            UF.union(toOneD(row, col), toOneD(row - 1, col));
        }
        if (isInBound(row, col + 1) && isOpen(row, col + 1)) {
            UF.union(toOneD(row, col), toOneD(row, col + 1));
        }
        if (isInBound(row, col - 1) && isOpen(row, col - 1)) {
            UF.union(toOneD(row, col), toOneD(row, col - 1));
        }

        if (row == 0) {
            UF.union(toOneD(row, col), start);
        }
        if (row == N - 1) {
            UF.union(toOneD(row, col), end);
        }
    }

    /**
     * return true if the (row, col) pair is in bound
     */
    private boolean isInBound(int row, int col) {
        return (row >= 0) && (row < N) && (col >= 0) && (col < N);
    }

    /**
     * open the site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        if (!isInBound(row, col)) {
            String errorMessage = "(" + row + ", " + col + ") out of bound";
            throw new java.lang.IndexOutOfBoundsException(errorMessage);
        } else {
            grid[row][col] = true;
            numOfOpenSites += 1;
            connectToNeighbors(row, col);
        }
    }

    /**
     * is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        if (!isInBound(row, col)) {
            String errorMessage = "(" + row + ", " + col + ") out of bound";
            throw new java.lang.IndexOutOfBoundsException(errorMessage);
        } else {
            return grid[row][col];
        }
    }

    /**
     * is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        return UF.connected(toOneD(row, col), start);
    }

    /**
     * number of open sites
     */
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        return UF.connected(start, end);
    }
}

