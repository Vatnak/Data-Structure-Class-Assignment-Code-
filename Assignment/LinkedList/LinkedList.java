import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    // head and tail pointers
    private Link<T> head;
    private Link<T> tail;

    // number of links in the list
    private int size;
    private int modCount;

    // O(1)
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // O(1)
    public boolean isEmpty() {
        //return true if the list has no link
        return head == null;
    }
    // O(1)
    public void insertFirst(T newItem) {
        Link<T> newLink = new Link<>(newItem);
        // if the list is empty point the tail to the added new link
        if (isEmpty()) {
            tail = newLink;
        }
        // then point the new link to the current head
        newLink.next = head;
        // now the new link becomes the head 
        head = newLink;
        size++;
        modCount++;
    }

    // O(1)
    public void insertLast(T newItem) {
        Link<T> newLink = new Link<>(newItem);
        // if the list is empty point both the head and tail to the added new link
        if (isEmpty()) {
            head = newLink;
            tail = newLink;
        }
        // if no, point the tail to the newly added link then the newly added link becomes the tail
        else {
            tail.next = newLink;
            tail = newLink;
        }
        size++;
        modCount++;
    }

    // O(1)
    public boolean deleteFirst() {
        if (isEmpty()) {
            return false;
        }
        // the second node now becomes the head
        head = head.next;
        // after remove it's empty
        if (isEmpty()) {
            tail = null;
        }
        size--;
        modCount++;
        return true;
    }

    // O(n)
    public int find(T key) {
        Link<T> current = head;
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
        if (isEmpty()) {
            return false;
        }
        // if the head matches delete it and return true
        if (head.data.equals(key)) {
            return deleteFirst();
        }

        Link<T> previous = head;
        Link<T> current = head.next;

        while (current != null) {
            if (current.data.equals(key)) {
                // skip over the matching node
                previous.next = current.next;

                // if remove the last node tail must move back
                if (current == tail) {
                    tail = previous;
                }
                size--;
                modCount++;
                return true;

            }
            // move the marker forward by one node if no match
            previous = previous.next;
            current = current.next;
        }

        return false;
    }

    // O(n)
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        // check if there's only one node
        if (head.next == null) {
            return deleteFirst();
        }

        Link<T> current = head;
        // if the next node is the tail
        while (current.next != tail) {
            current = current.next;
        }
        // remove the pointer from the last node and the move the tail back
        current.next = null;
        tail = current;
        size--;
        modCount++;
        return true;
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

        Link<T> previous = head;

        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }
        // make the previous skip over the node after it
        previous.next = previous.next.next;
        size--;
        modCount++;
        return true;
    }

    // O(n)
    public void insertAt(int index, T newItem) {
        // if can't find the index or out of bound
        if (index < 0 || index > size) {
            System.out.println("Error, Index out of bound!");
            return;
        }
        // if index is 0 insert at the first
        if (index == 0) {
            insertFirst(newItem);
            return;
        }
        // if index is equal to the size insert at the last
        if (index == size) {
            insertLast(newItem);
            return;
        }

        Link<T> previous = head;

        // walk forward until previous is the node just before the position
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }

        Link<T> newLink = new Link<>(newItem);
        // point the new node to the what the previous node was pointing to
        newLink.next = previous.next;
        // previous node points to the new node
        previous.next = newLink;
        size++;
        modCount++;
    }

    // O(1)
    public T peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }
    // O(1)
    public T peekLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.data;
    }
    
    // O(n)
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // store the head in current
        Link<T> current = head;

        // check if current is empty
        while (current != null) {
            // if no append the data of the current node
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
        return new SinglyLinkedListIterator();
    }

    // Each step takes O(1) time and the iterator uses O(1) space.
    private class SinglyLinkedListIterator implements Iterator<T> {
        private Link<T> nextNode = head;
        private final int expectedModCount = modCount;

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

        /** Returns the next element and advances; throws when exhausted. */
        @Override
        public T next() {
            checkForModification();
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            T item = nextNode.data;
            nextNode = nextNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Iterator removal is not supported");
        }
    }

    // O(n)
    public void display() {
        System.out.println(toString());
    }

}
