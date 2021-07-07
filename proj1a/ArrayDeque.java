public class ArrayDeque<T> {
    private T[] array;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        nextFirst = array.length - 1;
        nextLast = 0;
        size = 0;
    }

    private int indexOperator(int index, int delta) {
        index += delta;
        if (index < 0) {
            return array.length + index % array.length;
        } else {
            return index % array.length;
        }
    }

    private void resize(int capacity) {
        if (size > capacity) {
            throw new ArrayIndexOutOfBoundsException("need larger capacity");
        } else {
            T[] newArray = (T[]) new Object[capacity];
            int index = indexOperator(nextFirst, 1);
            for (int i = 0; i < size(); i++) {
                newArray[i] = array[index];
                index = indexOperator(index, 1);
            }
            array = newArray;
            nextFirst = capacity - 1;
            nextLast = size;
        }
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[nextFirst] = item;
        nextFirst = indexOperator(nextFirst, -1);
        size += 1;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[nextLast] = item;
        nextLast = indexOperator(nextLast, 1);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = indexOperator(nextFirst, 1);
        for (int i = 0; i < size(); i++) {
            System.out.print(array[index]);
            index = indexOperator(index, 1);
        }
        System.out.println("");

    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            int index = indexOperator(nextFirst, 1);
            T item = array[index];
            array[index] = null;
            nextFirst = index;
            size -= 1;
            if (size * 4 < array.length && array.length > 8) {
                resize(array.length / 2);
            }
            return item;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            int index = indexOperator(nextLast, -1);
            T item = array[index];
            array[index] = null;
            nextLast = index;
            size -= 1;
            if (size * 4 < array.length && array.length > 8) {
                resize(array.length / 2);
            }
            return item;
        }
    }

    public T get(int index) {
        return array[indexOperator(nextFirst, index + 1)];
    }
}
