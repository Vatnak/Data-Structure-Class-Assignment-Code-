import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
    private DoublyLink<T> head;
    private DoublyLink<T> tail;
    // number of links in the list
    private int size;
    private int modCount;

    // O(1)
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // O(1)
    public boolean isEmpty() {
        return head == null;
    }

    // O(1)
    public void insertFirst(T newItem) {
        DoublyLink<T> newLink = new DoublyLink<>(newItem);

        if (isEmpty()) {
            // point the tail to the new link
            tail = newLink;
        }
        else {
            // point the previous pointer backward to the new link
            head.previous = newLink;
        }
        // point new link to the current head
        newLink.next = head;
        // new link becomes the head
        head = newLink;
        size++;
        modCount++;
    }

    // O(1)
    public void insertLast(T newItem) {
        DoublyLink<T> newLink = new DoublyLink<>(newItem);

        if (isEmpty()) {
            head = newLink;
            tail = newLink;
        }
        else {
            // point the tail to new link
            tail.next = newLink;
            // point the new link back to the tail (previous pointer)
            newLink.previous = tail;
            // the new link now become the tail
            tail = newLink;
        }
        size++;
        modCount++;
    }

    // O(n)
    public void insertAt(int index, T newItem) {
        // index out of bound
        if (index < 0 || index > size) {
            System.out.println("Error, Index out of Bound!");
            return;
        }
        // if index is 0 insert at the first
        if (index == 0) {
            insertFirst(newItem);
            return;
        }
        // if index is at the end insert at the end
        if (index == size) {
            insertLast(newItem);
            return;
        }

        DoublyLink<T> previous = head;
        // walk forward until previous is the node just before the position
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }
        // store the next node in after
        DoublyLink<T> after = previous.next;
        DoublyLink<T> newLink = new DoublyLink<>(newItem);

        // the new node points back to the node before it
        newLink.previous = previous;
        // the new node points forward to the node after it
        newLink.next = after;
        // the node before the new node points to the new node
        previous.next = newLink;
        // the node after the new node points back to the new node
        after.previous = newLink;
        size++;
        modCount++;
    }

    // O(1)
    public boolean deleteFirst() {
        if (isEmpty()) {
            return false;
        }
        // move head to the next node
        head = head.next;
        // after moving is it empty
        if (isEmpty()) {
            tail = null;
        }
        // if no remove the pointer to the previous head
        else {
            head.previous = null;
        }
        size--;
        modCount++;
        return true;
    }

    // O(1)
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        // move the tail back a node
        tail = tail.previous;
        // after moving is it empty
        if (tail == null) {
            head = null;
        }
        // the new tail is the last node so the next one must be null
        else {
            tail.next = null;
        }
        size--;
        modCount++;
        return true;
    }

    // O(n)
    public int find(T key) {
        DoublyLink<T> current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(key)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    // O(n)
    public boolean deleteKey(T key) {
        DoublyLink<T> current = head;

        // as long as there's a node
        while (current != null) {
            // find the key
            if (current.data.equals(key)) {
                // the first node?
                if (current == head) {
                    return deleteFirst();
                }
                // the last node?
                if (current == tail) {
                    return deleteLast();
                }
                // skip over the deleted key (eg. a <-> b <-> c becomes a <-> c)
                current.previous.next = current.next;
                current.next.previous = current.previous;
                size--;
                modCount++;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // O(n)
    public boolean deleteAt(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size - 1) {
            return deleteLast();
        }

        DoublyLink<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        // skip over the current that is deleted
        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
        modCount++;
        return true;
    }

    // O(n)
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // store the head in the current
        DoublyLink<T> current = head;

        // keep going if there's a node
        while (current != null) {
            // append data
            sb.append(current.data);
            // check if there is a next node after the current
            if (current.next != null) {
                sb.append(", ");
            }
            // store the next node as the current node
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }

    /** Returns an independent iterator from the head to the tail. */
    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator(false);
    }

    /** Returns an independent iterator from the tail to the head. */
    public Iterator<T> descendingIterator() {
        return new DoublyLinkedListIterator(true);
    }

    // Each step takes O(1) time and the iterator uses O(1) space.
    private class DoublyLinkedListIterator implements Iterator<T> {
        private DoublyLink<T> nextNode;
        private final boolean backward;
        private final int expectedModCount = modCount;

        private DoublyLinkedListIterator(boolean backward) {
            this.backward = backward;
            nextNode = backward ? tail : head;
        }

        private void checkForModification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            checkForModification();
            return nextNode != null;
        }

        /** Returns the next element in the chosen direction and advances. */
        @Override
        public T next() {
            checkForModification();
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            T item = nextNode.data;
            nextNode = backward ? nextNode.previous : nextNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Iterator removal is not supported");
        }
    }

    // O(n)
    public void displayForward() {
        System.out.println(toString());
    }
    // O(n)
    public void displayBackward() {
        System.out.println(toStringBackward());
    }

    // helper method for displayBackward O(n)
    private String toStringBackward() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // store the tail in the current
        DoublyLink<T> current = tail;

        // keep going if there's a node
        while (current != null) {
            // append data
            sb.append(current.data);
            // check if there is a previous node after the current
            if (current.previous != null) {
                sb.append(", ");
            }
            // store the previous node as the current node
            current = current.previous;
            
        }
        sb.append("]");
        return sb.toString();
    }
}
