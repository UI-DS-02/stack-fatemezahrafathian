public class LinkedStack<E> implements Stack<E>{

    //an empty linked list
    private LinkedList<E> linkedList = new LinkedList<>();

    public LinkedStack() {
    }
    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E element) {
        linkedList.addFirst(element);
    }

    @Override
    public E top() {
        return linkedList.first();
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }
}