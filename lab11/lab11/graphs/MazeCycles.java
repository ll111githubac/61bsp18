package lab11.graphs;

import java.util.Stack;
import java.util.TreeSet;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Stack<Integer> cycle;
    private int[] parentTo;
    private boolean hasCycle;
    private TreeSet<Integer> notMarked;

    public MazeCycles(Maze m) {
        super(m);
        cycle = new Stack<>();
        parentTo = new int[maze.V()];
        hasCycle = false;
        notMarked = new TreeSet<>();
        for (int i = 0; i < maze.V(); i++) {
            notMarked.add(i);
        }
    }

    @Override
    public void solve() {
        while (!notMarked.isEmpty() && !hasCycle) {
            int start = notMarked.first();
            notMarked.remove(start);
            parentTo[start] = start;
            cycleFinder(start);
            if (hasCycle) {
                int end = cycle.pop();
                int current = end;
                int prev = cycle.pop();
                edgeTo[current] = prev;
                while (prev != end) {
                    current = prev;
                    prev = cycle.pop();
                    edgeTo[current] = prev;
                }
                announce();
            }
        }
    }

    // Helper methods go here
    private void cycleFinder(int v) {
        marked[v] = true;
        notMarked.remove(v);
        announce();
        cycle.push(v);
        for (int w : maze.adj(v)) {
            if (hasCycle) {
                return;
            }
            if (w == parentTo[v]) {
                continue;
            } else if (marked[w]) {
                hasCycle = true;
                cycle.push(w);
                return;
            } else {
                parentTo[w] = v;
                cycleFinder(w);
            }
        }
        if (!hasCycle) {
            int itemRemove = cycle.pop();
            if (itemRemove != v) {
                throw new RuntimeException("pop the wrong item");
            }
        }
    }
}

