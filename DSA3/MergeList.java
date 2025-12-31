public class MergeList {

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

        // 1 -> 3 -> 5
        // 2 -> 4 -> 6
        /* 
            head                            list1.         list 2
            1
            1 -> 2.                         3 -> 5.        4 -> 6
            1 -> 2 -> 3.                    5.             4 -> 6
            1 -> 2 -> 3 -> 4                5.             6
            1 -> 2 -> 3 -> 4 -> 5.          null.          6
            1 -> 2 -> 3 -> 4 -> 5 -> 6
        */

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

    public static void main(String[] args) {
        MergeList ml = new MergeList();
        Node list1 = ml.createList(new int[]{2,4,6,10,14});
        Node list2 = ml.createList(new int[]{1,3,5,7,8,9,11,12,13,15,16});

        ml.printList(list1);
        ml.printList(list2);

        Node mergedList = ml.mergeList(list1, list2);
        ml.printList(mergedList);
    }

    // Time Complexity = O(N + M)
    // Space Complexity = O(1)

}