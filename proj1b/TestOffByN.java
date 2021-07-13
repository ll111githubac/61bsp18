import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    /**
    A class that test OffByN class
     */
    @Test
    public void testEqualChars() {
        CharacterComparator offBy4 = new OffByN(4);
        assertTrue(offBy4.equalChars('j', 'n'));
        assertTrue(offBy4.equalChars('^', 'Z'));
        assertFalse(offBy4.equalChars('a', 'a'));
        assertFalse(offBy4.equalChars('t', 'o'));
        assertFalse(offBy4.equalChars('D', 'h'));
    }
}
