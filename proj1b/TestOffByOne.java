import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('s', 't'));
        assertTrue(offByOne.equalChars('h', 'g'));
        assertTrue(offByOne.equalChars('W', 'X'));
        assertTrue(offByOne.equalChars('N', 'M'));
        assertTrue(offByOne.equalChars('@', 'A'));
        assertTrue(offByOne.equalChars('Z', '['));
        assertTrue(offByOne.equalChars('a', '`'));
        assertTrue(offByOne.equalChars('z', '{'));

        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('F', 'F'));
        assertFalse(offByOne.equalChars('7', '7'));
        assertFalse(offByOne.equalChars('|', '|'));
        assertFalse(offByOne.equalChars('c', 'o'));
        assertFalse(offByOne.equalChars('k', 'b'));
        assertFalse(offByOne.equalChars('D', 'L'));
        assertFalse(offByOne.equalChars('Q', 'M'));
        assertFalse(offByOne.equalChars('a', 'A'));
        assertFalse(offByOne.equalChars('K', 'l'));
        assertFalse(offByOne.equalChars('a', 'Z'));
    }
}
