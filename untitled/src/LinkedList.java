public class LinkedList<E>{
    public static class Node<E>{
        //value of node
        private E element;
        //Pointer to next node.
        private Node<E> next;
        //constructor
        public Node(E data, Node<E> next) {
            this.element = data;
            this.next = next;
        }
        //return element field
        public E getElement() {
            return element;
        }
        //return next pointer field
        public Node<E> getNext() {
            return next;
        }
        //give new element and replace white previous element
        public void setElement(E element) {
            this.element = element;
        }
        //give a new pointer and replace white previous next pointer
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    //number of elements in the list
    private int size=0;
    //pointer to head of list
    private Node<E> head=null;
    //pointer to tail of list
    private Node<E> tail=null;
    //constructor
    public LinkedList() {
    }
    //return number of elements in the list
    public int getSize() {
        return size;
    }
    //return header sentinel
    public Node<E> getHead() {
        return head;
    }
    //return trailer sentinel
    public Node<E> getTail() {
        return tail;
    }
    public int size(){
        return size;
    }
    //Returns true if this list contains no elements
    public boolean isEmpty(){
        return size==0;
    }
    public E first(){
        //check list is not null
        if (isEmpty()) return null;
        return head.getElement();
    }
    //Inserts the specified element at the beginning of this list
    public void addFirst(E element){

        //make node white value that is equal to element ,previous: header , next : last first element
        Node<E> myNode = new Node<>(element,head);
        if (isEmpty()){
            head=myNode;
            head.setNext(tail);
            tail=myNode;
            tail.setNext(null);
        }else {
            Node<E> temp = head;
            head=myNode;
            myNode.setNext(temp);
        }

        //increase number of elements in the list
        size++;
    }
    //Removes and returns the first element from this list.
    public E removeFirst(){
        Node<E> temp =null;
        //check list is not null
        if (!isEmpty()){
            temp = head;
            head=head.getNext();
            if (size==0)
                tail=null;
            //decrease number of elements in the list
            size--;
        }
        return temp.getElement();
    }
}
