package linkeldist;

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
//1 2 3 --c1
//1 3 4 -- c2

/**
 * head= null; c=null; c1= 1, c2=1
 * head= 1* 1* is copy of 1; c1=1 c2= 3 c=1*
 * c=1*->1** c1=2 c2=3 head 1*->1** c= 1**
 * c->1**->2* c1=3 c2=3 head 1*->1**->2* c=2*
 * c: 2*->3* c1=3 c2=null head 1*->1**->2* ->3* c= 3*
 * c: 3*-> 3* c1=null c2=null head 1*->1**->2* -> 3* -> 3* c= null;
 */

class MergeTwoList {
    static ListNode copy(ListNode node) {
        ListNode copyNode = new ListNode(node.val, null);
        return copyNode;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode c = null;
        ListNode c1 = list1;
        ListNode c2 = list2;
        Boolean isLoop = false;
        while (c1 != null & c2 != null) {
            if (!isLoop) {
                isLoop = true;
            }
            if (head == null) {
                if (c1.val < c2.val) {
                    head = copy(c1);
                    c1 = c1.next;
                } else {
                    head = copy(c2);
                    c2 = c2.next;
                }
                c = head;
                continue;
            } else if (c1.val < c2.val) {
                c.next = copy(c1);
                c1 = c1.next;

            } else {
                c.next = copy(c2);
                c2 = c2.next;
            }
            c = c.next;
        }
        if (c1 == null) {
            while (c2 != null) {
                if (!isLoop) {
                    head = copy(c2);
                    c2 = c2.next;
                    c = head;
                    isLoop = true;
                } else {
                    c.next = copy(c2);
                    c2 = c2.next;
                    c = c.next;
                }
            }

        }
        if (c2 == null) {
            while (c1 != null && !isLoop) {
                if (!isLoop) {
                    head = copy(c1);
                    c1 = c1.next;
                    c = head;
                    isLoop = true;
                } else {
                    c.next = copy(c1);
                    c1 = c1.next;
                    c = c.next;
                }
            }

        }
        return head;

    }

    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("empty list");
        } else {
            while (head != null) {
                System.out.println(head.val + " ");
                head = head.next;
            }
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
        ListNode result = mergeTwoLists(l1, l2);
        printList(result);

    }
}

