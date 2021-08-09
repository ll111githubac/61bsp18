package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double[] portions;

    /**
     * perform T independent experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should > 0");
        } else if (T <= 0) {
            throw new IllegalArgumentException("T should > 0");
        }

        this.T = T;
        portions = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation P = pf.make(N);
            while (!P.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                while (P.isOpen(row, col)) {
                    row = StdRandom.uniform(N);
                    col = StdRandom.uniform(N);
                }
                P.open(row, col);
            }
            portions[i] = (double) P.numberOfOpenSites() / (N * N);
        }

    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(portions);

    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(portions);
    }

    /**
     * low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /**
     * high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
