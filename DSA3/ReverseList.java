public class ReverseList {
    
    class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public void setNext(final Node next) {
            this.next = next;
        }

        public int getVal() {
            return this.val;
        }

        public Node getNext() {
            return this.next;
        }
    }

    public Node createList(final int[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }

        Node head = new Node(arr[0]);
        Node tempNode = head; 
        for(int i = 1; i < arr.length; i++) {
            Node tempNode1 = new Node(arr[i]);
            tempNode.setNext(tempNode1);
            tempNode = tempNode1;
        }

        return head;
    }

    public void printList( Node head) {

        while(head != null) {
            System.out.print(head.getVal() + " -> ");
            head = head.getNext();
        }
        System.out.println("null");
    }

    // 1 -> 2 -> 3 -> 4 -> 5
    /*
        head.                      curr                   prev.            next
        1 -> 2 -> 3 -> 4 -> 5.     1.                     null             null
        Iterations
                                   1 -> null                               2 -> 3 -> 4 -> 5
                                   2 -> 3 -> 4 -> 5.      1 -> null 

                                                                           3 -> 4 -> 5
                                   2 -> 1 ->null.         2 -> 1 ->null.
                                   3 -> 4 -> 5
                                   
                                                                           4 -> 5
                                   3 -> 2 -> 1 -> null.   3 -> 2 -> 1 -> null. 
                                   4 -> 5
                                  
    */
    public Node reverseList(Node head) {

        Node curr = head;
        Node prev = null;
        Node next = null;

        while(curr != null) {

            next = curr.getNext();
            curr.setNext(prev); 

            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ReverseList rl = new ReverseList();

        Node list = rl.createList(new int[]{1,2,3,4,5,6,7});

        rl.printList(list);

        Node reverseList = rl.reverseList(list);
        rl.printList(reverseList);
    }
}
