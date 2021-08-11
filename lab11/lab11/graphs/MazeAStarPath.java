package lab11.graphs;

import edu.princeton.cs.algs4.IndexMinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private IndexMinPQ<Integer> estimates;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        estimates = new IndexMinPQ<>(maze.V());
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int x = maze.toX(t) - maze.toX(v);
        int y = maze.toY(t) - maze.toY(v);
        return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        estimates.insert(s, 0);
        while (!estimates.isEmpty() && !targetFound) {
            int v = estimates.delMin();
            marked[v] = true;
            announce();
            if (v == t) {
                targetFound = true;
                return;
            }
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    if (!estimates.contains(w)) {
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        estimates.insert(w, h(w));
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}


