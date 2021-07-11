public class LinkedListDeque<T> implements Deque<T>{
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node currentNode = sentinel.next;
        while (currentNode != sentinel) {
            System.out.print(currentNode.item + " ");
            currentNode = currentNode.next;
        }
        System.out.println("");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T item = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return item;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T item = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return item;
        }
    }

    @Override
    public T get(int index) {
        if (size <= index) {
            return null;
        } else {
            Node current = sentinel.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.item;
        }
    }

    private T getHelper(Node n, int idx) {
        if (idx == 0) {
            return n.item;
        } else {
            return getHelper(n.next, idx - 1);
        }
    }

    public T getRecursive(int index) {
        if (size <= index) {
            return null;
        } else {
            return getHelper(sentinel.next, index);
        }
    }
}
