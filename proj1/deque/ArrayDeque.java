package deque;

import java.util.Iterator;
import java.util.Random;

public class ArrayDeque<T> implements Deque<T> {
    private final double usageThreshold = 0.25;
    private final double expansionFactor = 2.0;
    private final double reductionFactor = 0.5;
    private T[] array;
    private int size = 0;
    private int capacity = 8;
    private int first = 0;
    private int last  = 0;
    public ArrayDeque() {
        array = (T[]) new Object[8];
    }

    public ArrayDeque(int cap) {
        capacity = cap <= 0 ? 8 : cap;
        array = (T[]) new Object[capacity];
    }
    @Override
    public void addFirst(T item) {
        if (size == capacity) {
            doubleArray();
        }
        if (isEmpty()) {
            array[first] = item;
        }
        else {
            first = first == 0 ? capacity - 1 : first - 1;
            array[first] = item;
        }
        ++size;
    }

    @Override
    public void addLast(T item) {
        if (size == capacity) {
            doubleArray();
        }
        if (isEmpty()) {
            array[first] = item;
        }
        else {
            last = (last + 1) % capacity;
            array[last] = item;
        }
        ++size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int cap) { capacity = cap; }

    @Override
    public void printDeque() {
        for (int i = first; i != last; ++i) {
            i %= capacity;
            System.out.print(array[i] + " ");
        }
        System.out.print(array[last] + "\n");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T t = array[first];
        if (size != 1) {
            first = (first + 1) % capacity;
        }
        --size;
        if (capacity >= 16 && getUsage() < usageThreshold) {
            halfArray();
        }
        return t;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T t = array[last];
        if (size != 1) {
            last = last == 0 ? capacity - 1 : last - 1;
        }
        if (capacity >= 16 && getUsage() < usageThreshold) {
            halfArray();
        }
        --size;
        return t;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[(index+first) % capacity];

    }

    public T set(int index, T t) {
        T old = get(index);
        if (old != null) {
            array[(index+first) % capacity] = t;
        }
        return old;
    }

    public double getUsage() {
        return (double) size / capacity;
    }

    private void doubleArray() {
        int sz = (int) (capacity * expansionFactor);
        T[] narray = (T[]) new Object[sz];
        int i, index;
        for (i = first, index = 1;; ++i, ++index) {
            i %= capacity;
            if (i == last) {
                break;
            }
            narray[index] = array[i];
        }
        narray[index] = array[last];
        array = narray;
        first = 1;
        last = index;
        capacity = sz;
    }

    private void halfArray() {
        int nsize = (int) (capacity * reductionFactor);
        T[] narray = (T[]) new Object[nsize];
        int i, index;
        for (i = first, index = 0;; ++i, ++index) {
            i %= capacity;
            if (i == last) {
                break;
            }
            narray[index] = array[i];
        }
        narray[index] = array[i];
        array = narray;
        first = 0;
        last = index;
        capacity = nsize;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deque)) {
            return false;
        }
        Deque<T> o = (Deque<T>) obj;
        if (o.size() != size) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if(!(get(i).equals(o.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new ArrayDeque.ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        int wizPos = 0;

        ArrayDequeIterator() {}
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T item = get(wizPos);
            ++wizPos;
            return item;
        }
    }

}
