public class Link<T> {
    // data is the data and next is the pointer to the next node
    public T data; 
    public Link<T> next;

    // O(1) 
    public Link(T data) {
        this.data = data;
        this.next = null;
    }
}
