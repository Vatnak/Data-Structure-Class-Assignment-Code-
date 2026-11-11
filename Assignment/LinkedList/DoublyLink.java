public class DoublyLink<T> {
    // data is the data and next is the pointer to the next node and previous is the pointer to go back the node
    public T data;
    public DoublyLink<T> next;
    public DoublyLink<T> previous;

    // O(1)
    public DoublyLink(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}
