package deque;

import java.util.Comparator;

import static java.lang.Math.abs;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) { comparator = c; }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (int i = 1; i < size(); ++i) {
            if (comparator.compare(get(i), max) > 0) {
                max = get(i);
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (int i = 1; i < size(); ++i) {
            if (c.compare(get(i), max) > 0) {
                max = get(i);
            }
        }
        return max;
    }

}
