import java.util.Objects;
import java.util.Random;

public class DoublyLinkedList {
    
    // Represent each Node
    class Node {

        private int value; 
        private Node prev; 
        private Node next;

        public Node(final int value) {
            this.value = value;
        }

        public Node(final int value, final Node prev, final Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public void setNext(final Node next) {
            this.next = next;
        }

        public void setPrev(final Node prev) {
            this.prev = prev;
        }

        public int getValue() {
            return this.value;
        }

        public Node getPrev() {
            return this.prev;
        }

        public Node getNext() {
            return this.next;
        }

    }
       
    // Itertion order
    enum DisplayDirection {
        FORWARD,
        BACKWARD
    }

    // DLL states
    Node head = null;
    Node tail = null;
    int size = 0;

    public DoublyLinkedList() {
        head = new Node(-1, null, null);
        tail = new Node(-1, head, null);

        this.head.setNext(tail);
    }

    public Node insertNode(int value, int index) {

        final Node node = new Node(value);

        //iterate
        int i = 0;
        Node temp = this.head;
        while(i < index) {
            temp = temp.getNext();
            i++;
        }

        Node tempNext = temp.getNext();
        node.setNext(tempNext);
        temp.setNext(node);

        tempNext.setPrev(node);
        node.setPrev(temp);
        size++;

        return node;
    }

    public void deleteNode(int index) {

        if(this.size == 0) {
            System.out.println("The list is empty");
            return;
        }

        //iterate
        int i = 0;
        Node temp = this.head.getNext();
        while(i < index && temp != null) {
            temp = temp.getNext();
            i++;
        }

        if(temp == null) {
            System.out.println("No valid index to delete");
            return;
        }

        Node tempNext = temp.getNext();
        Node tempPrev = temp.getPrev();

        tempNext.setPrev(tempPrev);
        tempPrev.setNext(tempNext);
        size--;
    }

    public void display(DisplayDirection direction) {
        if(Objects.isNull(direction)) {
            direction = DisplayDirection.FORWARD;
        }

        if(DisplayDirection.FORWARD.equals(direction)){
            Node temp = this.head;
            while(temp.getNext() != this.tail) {
                temp = temp.getNext();
                System.out.print(temp.getValue()+ " -> ");
                
            }
            System.out.println("null");
        } else {
            Node temp = this.tail;
            while(temp.getPrev() != this.head) {
                temp = temp.getPrev();
                System.out.print(temp.getValue()+ " -> ");
                
            }
            System.out.println("null");
        }
        
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        for(int i = 1; i <= 5; i++) {
            dll.insertNode(i, 0);
        }
        dll.display(DisplayDirection.FORWARD);
        
        dll.insertNode(11, 1);
        dll.insertNode(10, 0);
        dll.insertNode(20, dll.size);
        System.out.println("Size of DLL : "+ dll.size);
        
        dll.display(DisplayDirection.FORWARD);
        //dll.display(DisplayDirection.BACKWARD);

        System.out.println();
        System.out.println("Deleting the node's");
        while(!dll.isEmpty()) {
            Random rand = new Random();
            int randInt = rand.nextInt(100);

            int index = randInt % dll.size;
            System.out.println("Deleting the node at index "+ index);
            dll.deleteNode(index);
            dll.display(DisplayDirection.FORWARD);
            System.out.println();
        }

        dll.display(DisplayDirection.FORWARD);

    }


}
