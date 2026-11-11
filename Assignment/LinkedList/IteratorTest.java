import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class IteratorTest {
    public static void main(String[] args) {
        LinkedList<Integer> singly = new LinkedList<>();
        DoublyLinkedList<Integer> doubly = new DoublyLinkedList<>();
        checkEmpty(singly.iterator());
        checkEmpty(doubly.iterator());
        checkEmpty(doubly.descendingIterator());

        singly.insertLast(1);
        doubly.insertLast(1);
        checkOrder(singly.iterator(), 1);
        checkOrder(doubly.descendingIterator(), 1);
        singly.insertLast(null);
        singly.insertLast(3);
        doubly.insertLast(null);
        doubly.insertLast(3);
        checkOrder(singly.iterator(), 1, null, 3);
        checkOrder(doubly.iterator(), 1, null, 3);
        checkOrder(doubly.descendingIterator(), 3, null, 1);
        checkForEach(singly);
        checkForEach(doubly);
        checkIndependent(singly);
        checkIndependent(doubly);

        checkSinglyChange(list -> list.insertFirst(0));
        checkSinglyChange(list -> list.insertLast(4));
        checkSinglyChange(list -> list.insertAt(1, 0));
        checkSinglyChange(list -> list.deleteFirst());
        checkSinglyChange(list -> list.deleteLast());
        checkSinglyChange(list -> list.deleteAt(1));
        checkSinglyChange(list -> list.deleteKey(2));
        checkDoublyChange(list -> list.insertFirst(0));
        checkDoublyChange(list -> list.insertLast(4));
        checkDoublyChange(list -> list.insertAt(1, 0));
        checkDoublyChange(list -> list.deleteFirst());
        checkDoublyChange(list -> list.deleteLast());
        checkDoublyChange(list -> list.deleteAt(1));
        checkDoublyChange(list -> list.deleteKey(2));

        LinkedList<Integer> unchangedSingly = new LinkedList<>();
        unchangedSingly.insertLast(1);
        Iterator<Integer> first = unchangedSingly.iterator();
        unchangedSingly.deleteKey(99);
        unchangedSingly.deleteAt(-1);
        checkOrder(first, 1);
        DoublyLinkedList<Integer> unchangedDoubly = new DoublyLinkedList<>();
        unchangedDoubly.insertLast(1);
        Iterator<Integer> second = unchangedDoubly.iterator();
        unchangedDoubly.deleteKey(99);
        unchangedDoubly.deleteAt(-1);
        checkOrder(second, 1);
        System.out.println("All iterator tests passed.");
    }

    private static void checkEmpty(Iterator<Integer> iterator) {
        if (iterator.hasNext() || iterator.hasNext()) {
            throw new AssertionError("Expected exhausted iterator");
        }
        expect(NoSuchElementException.class, () -> iterator.next());
        expect(UnsupportedOperationException.class, () -> iterator.remove());
    }

    private static void checkOrder(Iterator<Integer> iterator, Integer... expected) {
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            if (!iterator.hasNext()) {
                throw new AssertionError("hasNext changed the position");
            }
            actual.add(iterator.next());
        }
        if (!actual.equals(Arrays.asList(expected))) {
            throw new AssertionError("Unexpected traversal: " + actual);
        }
        checkEmpty(iterator);
    }

    private static void checkForEach(Iterable<Integer> list) {
        List<Integer> actual = new ArrayList<>();
        for (Integer item : list) {
            actual.add(item);
        }
        if (!actual.equals(Arrays.asList(1, null, 3))) {
            throw new AssertionError("Enhanced for loop failed");
        }
    }

    private static void checkIndependent(Iterable<Integer> list) {
        Iterator<Integer> first = list.iterator();
        Iterator<Integer> second = list.iterator();
        first.next();
        checkOrder(second, 1, null, 3);
        checkOrder(first, null, 3);
    }

    private static void checkSinglyChange(Consumer<LinkedList<Integer>> change) {
        LinkedList<Integer> list = new LinkedList<>();
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        Iterator<Integer> iterator = list.iterator();
        change.accept(list);
        checkInvalidated(iterator);
    }

    private static void checkDoublyChange(Consumer<DoublyLinkedList<Integer>> change) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        Iterator<Integer> forward = list.iterator();
        Iterator<Integer> backward = list.descendingIterator();
        change.accept(list);
        checkInvalidated(forward);
        checkInvalidated(backward);
    }

    private static void checkInvalidated(Iterator<Integer> iterator) {
        expect(ConcurrentModificationException.class, () -> iterator.hasNext());
        expect(ConcurrentModificationException.class, () -> iterator.next());
    }

    private static void expect(Class<? extends RuntimeException> type, Runnable action) {
        try {
            action.run();
        } catch (RuntimeException exception) {
            if (type.isInstance(exception)) {
                return;
            }
            throw exception;
        }
        throw new AssertionError("Expected " + type.getSimpleName());
    }
}
