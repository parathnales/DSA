public class SortList {
    
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

    public Node getMiddle(Node head) {
        if(head == null)
            return null;

        Node slow = head;
        Node fast = head;

        while(fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    public Node mergeList(Node list1, Node list2) {
        
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }

        Node head = null;
        if(list1.getVal() <= list2.getVal()) {
            head = list1;
            list1 = list1.getNext();
        } else {
            head = list2;
            list2 = list2.getNext();
        }

        Node temp = head;
        while(list1 != null && list2 != null) {

            if(list1.getVal() <= list2.getVal()) {
                temp.setNext(list1);
                list1 = list1.getNext();
            } else {
                temp.setNext(list2);
                list2 = list2.getNext();
            }
            temp = temp.getNext();
        }

        if(list1 != null) {
            temp.setNext(list1);
        } else if(list2 != null) {
            temp.setNext(list2);
        }

        return head;
    }

    public Node mergeSort(Node head) {
        if(head == null || head.getNext() == null) {
            return head;
        }

        Node mid = this.getMiddle(head);
        Node h2 = mid.getNext();
        mid.setNext(null);

        Node t1 = this.mergeSort(head);
        Node t2 = this.mergeSort(h2);

        return this.mergeList(t1, t2);
    }

    public static void main(String[] args) {
        SortList sl = new SortList();
        Node list = sl.createList(new int[]{1,4,3,6,5,2});

        sl.printList(list);

        Node sortedList = sl.mergeSort(list);
        sl.printList(sortedList);
    }
}
