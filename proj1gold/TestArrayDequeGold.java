import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> buggy10 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution10 = new ArrayDequeSolution<>();
        testArrayDequeHelper(buggy10, solution10, 10);

        StudentArrayDeque<Integer> buggy100 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution100 = new ArrayDequeSolution<>();
        testArrayDequeHelper(buggy100, solution100, 100);

        StudentArrayDeque<Integer> buggy10000 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution10000 = new ArrayDequeSolution<>();
        testArrayDequeHelper(buggy10000, solution10000, 10000);
    }

    private void testArrayDequeHelper(StudentArrayDeque<Integer> buggy,
                                      ArrayDequeSolution<Integer> solution, int testScale) {
        String message = "";
        for (int i = 0; i < testScale; i++) {
            message += "size()\n";
            assertEquals(solution.size(), buggy.size());
            Integer item = StdRandom.uniform(testScale);
            boolean isEmpty = solution.isEmpty();
            boolean isAdd = StdRandom.bernoulli(0.7);
            boolean isFirst = StdRandom.bernoulli();
            message += newMessage(isEmpty, isAdd, isFirst, item);
            if (isEmpty) {
                dequesAddItem(buggy, solution, item, isFirst);
            } else {
                if (isAdd) {
                    dequesAddItem(buggy, solution, item, isFirst);
                } else {
                    dequesRemoveItem(buggy, solution, isFirst, message);
                }
            }
        }
    }

    private void dequesAddItem(StudentArrayDeque<Integer> buggy,
                               ArrayDequeSolution<Integer> solution,
                               Integer item, boolean isFirst) {
        if (isFirst) {
            buggy.addFirst(item);
            solution.addFirst(item);
        } else {
            buggy.addLast(item);
            solution.addLast(item);
        }
    }

    private void dequesRemoveItem(StudentArrayDeque<Integer> buggy,
                                  ArrayDequeSolution<Integer> solution,
                                  boolean isFirst, String message) {
        if (isFirst) {
            Integer bugItem = buggy.removeFirst();
            Integer solItem = solution.removeFirst();
            assertEquals(message, solItem, bugItem);
        } else {
            Integer bugItem = buggy.removeLast();
            Integer solItem = solution.removeLast();
            assertEquals(message, solItem, bugItem);
        }
    }

    private String newMessage(boolean isEmpty, boolean isAdd, boolean isFirst, Integer item) {
        if (isEmpty || isAdd) {
            if (isFirst) {
                return "addFirst(" + item + ")\n";
            } else {
                return "addLast(" + item + ")\n";
            }
        } else {
            if (isFirst) {
                return "removeFirst()\n";
            } else {
                return "removeLast()\n";
            }
        }
    }
}
