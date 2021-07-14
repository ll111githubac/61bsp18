import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String s1 = "";
        assertTrue(palindrome.isPalindrome(s1));

        String s2 = "a";
        assertTrue(palindrome.isPalindrome(s2));

        String s3 = "fF";
        assertFalse(palindrome.isPalindrome(s3));

        String s4 = "++";
        assertTrue(palindrome.isPalindrome(s4));

        String s5 = "xrggrx";
        assertTrue(palindrome.isPalindrome(s5));

        String s6 = "plmjmlp";
        assertTrue(palindrome.isPalindrome(s6));

        String s7 = "mgfm";
        assertFalse(palindrome.isPalindrome(s7));
    }

    @Test
    public void testGeneralizedPalindrome() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue((palindrome.isPalindrome("k8l", offByOne)));
        assertFalse(palindrome.isPalindrome("aba", offByOne));
        assertFalse(palindrome.isPalindrome("deg", offByOne));
    }
}
