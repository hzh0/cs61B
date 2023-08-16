package deque;

import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {

    @Test
    public void maxTest() {
        Comparator<Integer> c = Comparator.naturalOrder();
        MaxArrayDeque<Integer> m = new MaxArrayDeque<>(c);

        assertTrue(m.max() == null);

        m.addFirst(1);
        m.addFirst(3);
        m.addFirst(-1);
        m.addFirst(-2);
        m.addFirst(5);
        m.addFirst(7);
        m.addFirst(5);
        m.addFirst(5);
        m.addFirst(-9);

        assertTrue(m.max() == 7);

        Comparator<Integer> c1 = Comparator.reverseOrder();
        assertTrue(m.max(c1) == -9);

        Comparator<Integer> absComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (abs(o1) - abs(o2));
            }
        };

        assertTrue(m.max(absComparator) == -9);

        Comparator<Integer> near5 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (abs(o2-5) - abs(o1-5));
            }
        };

        assertTrue(m.max(near5) == 5);

    }
}
