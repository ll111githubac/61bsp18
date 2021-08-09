package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private int N;
    private boolean[][] grid;
    private int numOfOpenSites;

    private WeightedQuickUnionUF ufSingleEnded;
    private WeightedQuickUnionUF ufDoubleEnded;
    private int start;
    private int end;

    /**
     * create N-by-N grid, with all sites initially blocked
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should > 0");
        }

        this.N = N;
        grid = new boolean[N][N];
        ufSingleEnded = new WeightedQuickUnionUF(N * N + 1);
        ufDoubleEnded = new WeightedQuickUnionUF(N * N + 2);
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

    private void connectToHelper(int row, int col, int rowOffset, int colOffset) {
        if (isInBound(row + rowOffset, col + colOffset)
                && isOpen(row + rowOffset, col + colOffset)) {
            ufSingleEnded.union(toOneD(row, col), toOneD(row + rowOffset, col + colOffset));
            ufDoubleEnded.union(toOneD(row, col), toOneD(row + rowOffset, col + colOffset));
        }

    }

    private void connectToNeighbors(int row, int col) {
        connectToHelper(row, col, 1, 0);
        connectToHelper(row, col, -1, 0);
        connectToHelper(row, col, 0, 1);
        connectToHelper(row, col, 0, -1);

        if (row == 0) {
            ufSingleEnded.union(toOneD(row, col), start);
            ufDoubleEnded.union(toOneD(row, col), start);
        }

        if (row == N - 1) {
            ufDoubleEnded.union(toOneD(row, col), end);
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
        } else if (!grid[row][col]) {
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
        return isOpen(row, col) && ufSingleEnded.connected(toOneD(row, col), start);
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
        return ufDoubleEnded.connected(start, end);
    }

    public static void main(String[] args) {
        int[] freq = new int[5];
        for (int i = 0; i < 10000; i++) {
            Percolation P = new Percolation(2);
            while (!P.percolates()) {
                P.open(StdRandom.uniform(2), StdRandom.uniform(2));
            }
            freq[P.numberOfOpenSites()] += 1;
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(freq[i]);
        }
    }

}

