public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    private boolean isPalindromeHelper(Deque wordDeque) {
        if (wordDeque.size() == 0 || wordDeque.size() == 1) {
            return true;
        } else if (wordDeque.removeFirst() != wordDeque.removeLast()){
            return false;
        } else {
            return isPalindromeHelper(wordDeque);
        }
    }
}
