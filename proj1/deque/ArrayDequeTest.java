package deque;

import jh61b.junit.In;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addEmptySizeTest() {

        ArrayDeque<String> arrayDeque1 = new ArrayDeque<>();
        assertTrue(arrayDeque1.isEmpty());
        arrayDeque1.addFirst("first");
        assertFalse(arrayDeque1.isEmpty());
        assertEquals(1, arrayDeque1.size());

        arrayDeque1.addLast("last");
        assertFalse(arrayDeque1.isEmpty());
        assertEquals(2, arrayDeque1.size());

        ArrayDeque<Double> arrayDeque2 = new ArrayDeque<>();
        assertTrue(arrayDeque2.isEmpty());
        arrayDeque2.addFirst(6.824);
        assertFalse(arrayDeque2.isEmpty());
        assertEquals(1, arrayDeque2.size());
        arrayDeque2.printDeque();

        arrayDeque2.addLast(6.888);
        assertFalse(arrayDeque2.isEmpty());
        assertEquals(2, arrayDeque2.size());
        arrayDeque2.printDeque();
    }

    @Test
    public void removeTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        assertEquals(8, a.getCapacity());

        int count = 1000000;

        for (int i = 0; i < count; ++i) {
            a.addLast(i);
            assertEquals(Optional.of(i), Optional.of(a.get(i)));
        }

        while(!a.isEmpty()) {
            int size = a.size();
            a.removeFirst();
            assertEquals(size-1, a.size());
            if (!a.isEmpty()) {
                a.removeLast();
                assertEquals(size - 2, a.size());
            }
            if (a.getCapacity() > 16) {
                assertTrue(a.getUsage() >= 0.25);
            }
        }

    }

    @Test
    public void randomTest() {
        Random random = new Random();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int count = 10000000;
        for (int i = 0; i < count; ++i) {
            int op = random.nextInt(4);
            int item = random.nextInt(Integer.MAX_VALUE);
            int size = arrayDeque.size();
            int fist = arrayDeque.get(0) == null ? 114 : arrayDeque.get(0);
            int last = arrayDeque.get(size-1) == null ? 514 : arrayDeque.get(size-1);
            int cap = arrayDeque.getCapacity();
            switch (op) {
                case 0:
                    arrayDeque.addFirst(item);
                    assertTrue(arrayDeque.size() == size+1);
                    assertTrue(arrayDeque.get(0) == item);
                    break;
                case 1:
                    arrayDeque.addLast(item);
                    assertTrue(arrayDeque.size() == size+1);
                    assertTrue(arrayDeque.get(size) == item);
                    break;
                case 2:
                    Integer it = arrayDeque.removeFirst();
                    int num = it == null ? 114 : it;

                    if (size > 0) {
                        assertTrue("error" + arrayDeque.size() + " " + size, arrayDeque.size() == size - 1);
                    }
                    else {
                        assertTrue(arrayDeque.size() == size);
                    }
                    assertTrue(num == fist);
                    break;
                case 3:
                    Integer it1 = arrayDeque.removeLast();
                    int num1 = it1 == null ? 514 : it1;
                    if (size > 0) {
                        assertTrue(arrayDeque.size() == size - 1);
                    }
                    else {
                        assertTrue(arrayDeque.size() == size);
                    }
                    assertTrue("num1 = " + num1 + " last = "+ last , num1 == last);
                    break;
            }
        }
    }

    @Test
    public void iteratorTest() {
        Random random = new Random();

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 100000; ++i) {
            int item = random.nextInt(Integer.MAX_VALUE);
            arrayDeque.addLast(item);
        }

        Iterator it = arrayDeque.iterator();
        int index = 0;
        while (it.hasNext()) {
            int num = (int) it.next();
            assertTrue(num == arrayDeque.get(index));
            ++index;
        }
    }
}
