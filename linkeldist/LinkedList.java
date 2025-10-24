
class MyLinkedList {
    MyLinkedList head;
    MyLinkedList tail;
    int data;
    MyLinkedList next;
    int N = 0;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    public int get(int index) {
        // System.out.println("index ="+index);
        if (index >= N) {
            return -1;
        } else {
            int i = 0;
            MyLinkedList cur = head;
            while (i < index) {
                cur = cur.next;
                i = i + 1;
            }
            return cur.data;
        }

    }

    private MyLinkedList getNode(int index) {
        // System.out.println("index ="+index);
        if (index >= N) {
            return null;
        } else {
            int i = 0;
            MyLinkedList cur = head;
            while (i < index) {
                cur = cur.next;
                i = i + 1;
            }
            return cur;
        }

    }

    public void addAtHead(int val) {
        MyLinkedList newNode = new MyLinkedList();
        newNode.data = val;
        if (head == null) {
            head = newNode;
            tail = newNode;

        } else {
            newNode.next = head;
            head = newNode;

        }
        N = N + 1;
        // print(head);

    }

    public void addAtTail(int val) {
        MyLinkedList newNode = new MyLinkedList();
        newNode.data = val;
        if (tail == null) {
            head = newNode;
            tail = newNode;

        } else {
            tail.next = newNode;
            tail = newNode;
        }
        N = N + 1;
        // System.out.println("addAtTail val= "+val+ "tail"+tail.data);
        // print(head);

    }

    public void addAtIndex(int index, int val) {
        // System.out.println("addAtInex "+index);
        if (index >= N) {
            return;
        } else if (index == N - 1) {
            addAtTail(val);
            return;
        } else if (index == 0) {
            addAtHead(val);
            return;
        } else {
            int i = 0;
            MyLinkedList cur = head;
            MyLinkedList prev = null;
            while (i < index) {
                prev = cur;
                cur = cur.next;
                i = i + 1;
            }
            // System.out.println("i "+i+" prev "+prev.data+" cur "+cur.data);
            MyLinkedList newNode = new MyLinkedList();
            newNode.data = val;
            prev.next = newNode;
            newNode.next = cur;
            N = N + 1;
        }
    }

    public void deleteAtIndex(int index) {
        // System.out.print("before delate index="+index);
        // print(head);
        if (index >= N) {
            return;
        } else if (index == 0) {
            if (head == null) {
                return;
            } else {
                head = head.next;
                N = N - 1;
            }
        } else if (index == N - 1) {
            if (tail == null) {
                return;
            } else {
                //get prev
                MyLinkedList prev = getNode(N - 2);
                //tail = prev
                tail = prev;
                //prev.next=null;
                prev.next = null;
                N = N - 1;
            }
        } else {
            MyLinkedList prev = getNode(index - 1);
            MyLinkedList cur = getNode(index);
            prev.next = cur.next;
            cur.next = null;
            N = N - 1;
            // System.out.print("after delate index="+index);
            // print(head);
        }
    }
}
