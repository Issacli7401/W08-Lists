package impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import common.InvalidIndexException;
import common.ListNode;
import interfaces.IListManipulator;
import interfaces.IOperator;
import interfaces.ITransformation;

/**
 * This class represents the recursive implementation of the IListManipulator interface.
 *
 */
public class RecursiveListManipulator implements IListManipulator {

    static List<ListNode> circularHead = new ArrayList<>();
    @Override
    public int size(ListNode head) {
        if (head == null) {
            return 0;
        }
        else {
            if (head.next == null) {
                return 1;
            }
            else { return 1 + size(head.next); }
        }
    }

    @Override
    public boolean contains(ListNode head, Object element) {
        if (head == null) {
            return false;
        }
        else {
            if (head.element.equals(element)) {
                return true;
            }
            else { return contains(head.next, element); }
        }
    }

    @Override
    public int count(ListNode head, Object element) {
        if (head == null) {
            return 0;
        }
        else {
            if (head.element.equals(element)) {
                return 1 + count(head.next, element);
            }
            else {
                return count(head.next, element);
            }
        }

    }

    @Override
    public String convertToString(ListNode head) {
        String output = "";
        if (head == null) {
            return output;
        }
        else {
            if (head.next == null){
                return output.concat(String.valueOf(head.element));
            }
            else {
                return output.concat(String.valueOf(head.element)).concat(",").concat(convertToString(head.next));
            }
        }

    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
        if (head == null) throw new InvalidIndexException();
        if (n == 0) {
            return head.element;
        }
        else {
            return getFromFront(head.next, n-1);
        }
    }

    @Override
    public Object getFromBack(ListNode head, int n) throws InvalidIndexException {
        if (head == null) throw new InvalidIndexException();
        if (size(head) - 1 - n == 0) {
            return head.element;
        }
        else {
            return getFromBack(head.next, n);
        }
    }

    @Override
    public boolean deepEquals(ListNode head1, ListNode head2) {
        if (head1 == null ^ head2 == null){
            return false;
        }

        //This statement is needed to avoid a null pointer exception while comparing the elements
        if (head1 == null && head2 == null) {
            return true;
        }
        else {
            if (head1.element.equals(head2.element)) {
                if (head1.next == null && head2.next == null) {
                    return true;
                } else {
                    return deepEquals(head1.next, head2.next);
                }
            } else {
                return false;
            }
        }

    }

    @Override
    public ListNode deepCopy(ListNode head) {
        if (head == null) {
            return null;
        }
        else {
            return new ListNode(head.element, deepCopy(head.next));
        }

    }

    @Override
    public boolean containsDuplicates(ListNode head) {
        if(head == null){
            return false;
        }
        else {
            ListNode head2 = deepCopy(head.next);
            if (contains(head2, head.element)) {
                return true;
            } else {
                return containsDuplicates(head.next);
            }
        }

    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {
        //returns the second list if the first one is empty
        if(head1 == null) {
            return head2;
        }

        //returns the first list if the second one is empty
        if(head2 == null) {
            return head1;
        }

        //if the next element in the first list is empty, base case has been reached
        if(head1.next == null){
            head1.next = head2;
            return head1;
        }

        //else, we are still somewhere in the first list
        else {
            head1.next = append(head1.next,head2);
            return head1;
        }


    }

    @Override
    public ListNode flatten(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return (ListNode) head.element;
        }
        else {
            return append((ListNode) head.element,(ListNode) head.next.element);
        }
    }

    @Override
    public boolean isCircular(ListNode head) {
        RecursiveListManipulator.circularHead.add(head);

        if (head == null) {
            RecursiveListManipulator.circularHead.clear();
            return false;
        }

        if (head.next == null) {
            RecursiveListManipulator.circularHead.clear();
            return false;
        }
        else {
            if (RecursiveListManipulator.circularHead.get(0) == head.next) {
                RecursiveListManipulator.circularHead.clear();
                return true;
            }
            else {
                if (!RecursiveListManipulator.circularHead.contains(head.next)) {
                    return isCircular(head.next);
                }
                else {
                    RecursiveListManipulator.circularHead.clear();
                    return false;
                }
            }
        }
    }

    @Override
    public boolean containsCycles(ListNode head) {
        if(head == null) {
            return false;
        }

        if(head.next == null) {
            return false;
        }
        else {
            if(isCircular(head)){
                return true;
            }
            else {
                return containsCycles(head.next);
            }
        }
    }

    @Override
    public ListNode sort(ListNode head, Comparator comparator) {
        /*boolean sorted = false;
        boolean end = false;
        ListNode temp;
        ListNode find;
        if (head == null) {
            return null;
        }

        if (head.next == null){
            return head;
        }
        else {
            find = recurseCompare(head, head, comparator);
            if(find != null){
                temp = head;
                head = find.next;
                find.next = find.next.next;
                head.next = temp;
            }
            head.next = append(head.next,sort(head.next,comparator));
            return head;
        }*/

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }
        else {
            ListNode middle = findMiddle(head);
        }
        return null;
    }

    public ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode recurseCompare(ListNode head, ListNode node, Comparator comparator){
        if(head == null || node == null){
            return null;
        }

        if(comparator.compare(head.element, node.next.element)<=0){
            return recurseCompare(head, node.next, comparator);
        }
        else {
            return node;
        }
    }

    @Override
    public ListNode map(ListNode head, ITransformation transformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object reduce(ListNode head, IOperator operator, Object initial) {
        // TODO Auto-generated method stub
        return null;
    }

    public void printList(ListNode head){
        while (head.next != null) {
            System.out.println(head.element);
            head = head.next;
        }
    }



}
