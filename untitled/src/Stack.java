interface Stack<E> {
    //returns the number of elements in the stack
    int size();
    //returns a boolean indicating whether is empty
    boolean isEmpty();
    //adds element to the top of the stack
    void push(E element);
    //returns the top element of the stack
    E top();
    //removes and returns the top element of the stack
    E pop();
}
