public class Queue<T> {
    private LinkedList<T> list;

    // O(1)
    public Queue(int arraySize) {
        // arraysize is not needed because the linked list grow by itself
        list = new LinkedList<>();
    }

    // O(1)
    public void insert(T newItem) {
        list.insertLast(newItem);
    }

    // O(1)
    public T remove() {
        // peekFirst() check if it's empty and save the first value before deleting it
        T removed = list.peekFirst();
        list.deleteFirst();
        return removed;
    }

    // O(1)
    public T peekFront() {
        return list.peekFirst();
    }

    // O(1)
    public T peekRear() {
        return list.peekLast();
    }

    // O(n) - has to go through every item to build the string
    // Format is [front, ..., rear] so the left side is the next one to leave
    @Override
    public String toString() {
        return list.toString();
    }

    // O(n) 
    public void display() {
        System.out.println(toString());
    }
}
