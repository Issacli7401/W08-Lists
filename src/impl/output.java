package impl;

import common.ListNode;

import java.util.Comparator;

public class output {
    private static final Comparator int_comparator = new Comparator() {

        @Override
        public int compare(Object object1, Object object2) {

            return (Integer) object1 - (Integer) object2;
        }
    };

    public static void main(String args[]) {
        int element1 = 1;
        int element2 = 2;
        int element3 = 3;
        ListNode list1 = new ListNode(element1);
        ListNode list2 = new ListNode(element2, list1);
        ListNode list3 = new ListNode(element1, list2);
        ListNode list4 = new ListNode(element3, list3);

        ListNode list_a = new ListNode(3);
        ListNode list_b = new ListNode(3, new ListNode(-2));
        ListNode list_c = new ListNode(3, new ListNode(-2, new ListNode(-14)));

        RecursiveListManipulator jeff = new RecursiveListManipulator();
        //jeff.printList(list4);
        jeff.printList(jeff.sort(list_b, int_comparator));
    }
}
