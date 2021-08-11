package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private boolean targetFound;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        targetFound = false;
        edgeTo[s] = s;
        distTo[s] = 0;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> waiting = new LinkedList<>();
        marked[s] = true;
        announce();
        waiting.add(s);
        while (!waiting.isEmpty() && !targetFound) {
            int v = waiting.remove();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();
                    targetFound = (w == t);
                    if (targetFound) {
                        return;
                    } else {
                        waiting.add(w);
                    }
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

