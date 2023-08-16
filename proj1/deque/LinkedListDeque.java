package deque;

import java.util.*;

public class LinkedListDeque<T> implements Deque<T> {
    Node<T> first;
    Node<T> last;
    int size = 0;

    public LinkedListDeque() {
    }

    private void add1(Node<T> n) {
        first = n;
        last = n;
    }
    @Override
    public void addFirst(T item) {
        Node<T> n = new Node<T>(null, item, null);
        if (isEmpty()) {
            add1(n);
        }
        else {
            first.prev = n;
            n.next = first;
            first = n;
        }
        ++size;
    }

    @Override
    public void addLast(T item) {
        Node<T> n = new Node<T>(null, item, null);
        if (isEmpty()) {
            add1(n);
        }
        else {
            last.next = n;
            n.prev = last;
            last = n;
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

    @Override
    public void printDeque() {
        Node tmp = first;
        while(tmp != null) {
            System.out.print(tmp.item + " ");
            tmp = tmp.next;
        }
        System.out.print("\n");
    }

    @Override
    public T removeFirst() {
        Node<T> f = first;
        if (f == null) {
            return null;
        }
        T item = f.item;
        Node<T> next = f.next;
        first = next;
        if (next == null) {
            last = null;
        }
        else {
            next.prev = null;
        }
        --size;
        return item;
    }

    @Override
    public T removeLast() {
        Node<T> f = last;
        if (f == null) {
            return null;
        }
        T item = f.item;
        Node<T> pre = f.prev;
        last = pre;
        if (pre == null) {
            first= null;
        }
        else {
            pre.next = null;
        }
        --size;
        return item;
    }

    @Override
    public T get(int index) {
        if (index > size-1) {
            return null;
        }
        int mid = size / 2;
        if (index >= mid) {
            int t = size - index - 1;
            Node<T> tmp = last;
            for (int i = 0; i < t; ++i) {
                tmp = tmp.prev;
            }
            return tmp.item;
        }
        else {
            Node<T> tmp = first;
            for (int i = 0; i < index; ++i) {
                tmp = tmp.next;
            }
            return tmp.item;
        }
    }

    private static class Node<T> {
        T item;
        LinkedListDeque.Node<T> next;
        LinkedListDeque.Node<T> prev;

        Node(LinkedListDeque.Node<T> prev, T element, LinkedListDeque.Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private T getRecursiveSup(int index, int curIndex, Node<T> curNode, boolean forward) {
        if (index == curIndex) {
            return curNode.item;
        }
        else {
            Node<T> next = forward ? curNode.next : curNode.prev;
            return getRecursiveSup(index, curIndex + 1, next, forward);
        }
    }
    public T getRecursive(int index) {
        if (index < 0 || index > size-1) {
            return null;
        }
        int mid = size / 2;
        if (index >= mid) {
            int t = size - index - 1;
            return getRecursiveSup(t, 0, last, false);
        }
        else {
            return getRecursiveSup(index, 0, first, true);
        }
    }

    @Override
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
        return new LinkdListDequeIterator();
    }

    private class LinkdListDequeIterator implements Iterator<T> {

        int wizPos = 0;

        LinkdListDequeIterator() {}
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
