import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void test() {
        ArrayDeque<String> A = new ArrayDeque<>();

        for (int i = 0; i < 8; i += 2) {
            A.addFirst(Integer.toString(i));
            A.addLast(Integer.toString(i + 1));
        }
        //expected: {"6", "4", "2", "0", "1", "3", "5", "7"}
        assertEquals("6", A.get(0));
        assertEquals("4", A.get(1));
        assertEquals("2", A.get(2));
        assertEquals("0", A.get(3));
        assertEquals("1", A.get(4));
        assertEquals("3", A.get(5));
        assertEquals("5", A.get(6));
        assertEquals("7", A.get(7));

        assertEquals(8, A.size());

        assertEquals(false, A.isEmpty());

        assertEquals("6", A.removeFirst());
        A.removeFirst();
        assertEquals(6, A.size());
        assertEquals("2", A.get(0));
        assertEquals("7", A.get(5));

        assertEquals("7", A.removeLast());
        assertEquals(5, A.size());
        assertEquals("5", A.get(4));
        assertEquals("2", A.get(0));

        A.removeLast();
        A.removeLast();
        A.removeFirst();
        A.removeFirst();
        A.removeLast();
        assertEquals(true, A.isEmpty());
    }
}
