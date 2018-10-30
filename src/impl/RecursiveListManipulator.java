package impl;

import java.util.Comparator;

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
        // TODO Can't get my head around this one yet
        return null;
    }

    @Override
    public boolean containsDuplicates(ListNode head) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListNode flatten(ListNode head) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isCircular(ListNode head) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsCycles(ListNode head) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ListNode sort(ListNode head, Comparator comparator) {
        // TODO Auto-generated method stub
        return null;
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

}
