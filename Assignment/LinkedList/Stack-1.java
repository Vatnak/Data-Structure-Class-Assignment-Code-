public class Stack<T> {
    private LinkedList<T> list;

    // O(1)
    public Stack(int arraySize) {
        // arraysize is not needed because the linked list grow by itself
        list = new LinkedList<>();
    }
    // O(1) 
    public void push(T newItem) {
        list.insertFirst(newItem);
    }

    // O(1)
    public T pop() {
        // peekFirst() check if it's empty and save the top stack value before deleting it
        T removed = list.peekFirst();
        list.deleteFirst();
        return removed;
    }

    // O(1)
    public T peek() {
        return list.peekFirst();
    }

    // O(n)
    @Override
    public String toString() {
        return list.toString();
    }
    // O(n)
    public void display() {
        System.out.println(toString());
    }
}