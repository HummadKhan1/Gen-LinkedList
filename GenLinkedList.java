import java.lang.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

public class GenLinkedList<T> {
    
    private Node<T> head;
    
    private static class Node<T> {
        //storing value of Node
        public T data;
        //storing address of next Node
        public Node<T> next;
        //constructor
        public Node(T data) {
            this(data, null);
        }
        //parameterized constructor to assign value
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getValue() {
            return data;
        }

        public void setValue(T data) {
            this.data = data;
        }
    }

    public int size(){
        int size = 0;
        Node<T> temp= head;
        while(temp!=null){
            temp=temp.next;
            size++;
        }
        return size;
    }
    // addFront method
    public void addFront(T data) {
        head = new Node<T>(data, head);
    }

    // addEnd method
    public void addEnd(T data) {
        if (head == null) {
            head = new Node<T>(data);
            return;
        }

        Node<T> last = head;

        // While loop to insert nodes
        while (last.next != null)
            last = last.next;
        last.next = new Node<T>(data);
    }

    // removeFront() method
    public void removeFront() {
        if (head == null)
            return;
        head = head.next;
    }

    // removeEnd() method
    public void removeEnd() {
        if (head == null)
            return;
        Node<T> prev = null, last = head;
        // loop to remove elements
        while (last.next != null) {
            prev = last;
            last = last.next;
        }

        if (prev != null) {
            prev.next = null;
        }
        else {
            head = null;
        }
    }

    // method to set the elements
    public void set(int index, T data) {
        getNode(index).data = data;
    }

    // method returns the data at the given index
    public T get(int index) {
        return getNode(index).data;
    }

    // method to swap two elements
    void swap(int firstElement, int secondElement){
        //if not valid
        if(!(firstElement >=0 && firstElement < size() && secondElement >=0 && secondElement < size())){
            return;
        }
        Node<T> temp, temp1 = null, temp2 = null;
        temp = head;
        for (int i = 0; i < size(); i++) {
            if(i== firstElement){
                temp1 = temp;
            }if(i== secondElement){
                temp2 = temp;
            }
            temp = temp.next;
        }
        T value = temp1.getValue();
        temp1.setValue(temp2.getValue());
        temp2.setValue(value);
    }

    // method getNode to get Node
    private Node<T> getNode(int index) {
        return getNextNode(index, head);
    }

    // method to get next node
    private Node<T> getNextNode(int index, Node<T> first) {
        if (index < 0 || first == null);
        int pos = 0;

        while (index != pos) {
            first = first.next;
            pos++;
        }
        return first;
    }
    // method which erases elements in the list
    public void erase(int index, int integer) {
        if (integer <= 0)
            return;
        if (index == 0)
            try {
                head = getNode(integer);
            } catch (IndexOutOfBoundsException e) {
                head = null;
            }else {
            Node<T> prevNode = getNode(index - 1);
            prevNode.next = getNextNode(integer + 1,
                    prevNode);

        }

    }

    // method inserts another list into original list
    public void insertList(List<T> list, int index) {
        // Check if list is empty
        if (list.isEmpty())
            return;
        // Check is head is null
        if (head == null) {
            Node<T> node = new Node<T>(list.get(0));
            head = node;

            // traverse list
            for (int i = 1; i < list.size(); i++) {
                node.next = new Node<T>(list.get(i));
                node = node.next;
            }
        } else if (index == 0) {
            Node<T> node = new Node<T>(list.get(0));
            Node<T> prevHead = head;
            head = node;

            // traverse list
            for (int i = 1; i < list.size(); i++) {
                node.next = new Node<T>(list.get(i));
                node = node.next;
            }
            node.next = prevHead;
        } else {
            Node<T> start = getNode(index - 1);
            Node<T> oldNext = start.next;

            for (T data : list) {
                Node<T> node = new Node(data);
                start.next = node;
                start = node;
            }
            start.next = oldNext;
        }
    }


    // method to format results
    public String toString() {
        String S = "{ ";

        Node<T> X = head;

        if (X == null)
            return S + " }";

        while (X.next != null) {
            S += X.data + ", ";
            X = X.next;
        }

        S += String.valueOf(X.data);
        return S + " }";
    }

    // Start the main method
    public static void main(String[] args) {

        // Create object list
        GenLinkedList<Integer> list = new GenLinkedList<>();
        // loop to add elements to the end of the list
        for (int i = 0; i < 10; i++)
            list.addEnd(i);
        //print original list
        System.out.print("The original list is: \n");
        System.out.println(list);
        //add element to front of list
        System.out.println("\nList after adding integer -1 to the front of the list: ");
        //call addFront method
        list.addFront(-1);

        System.out.println(list);

        // add element to end of list
        System.out.println("\nList after adding integer 10 to the front of the list: ");

        //call addEnd method
        list.addEnd(10);
        System.out.println(list);

        // remove element at front of the list
        System.out.println("\nRemoving an integer from the front of the list: ");

        //call removeFront method
        list.removeFront();
        System.out.println(list);

        // remove integer from end of the list
        System.out.println("\nRemoving an integer from the end of the list: ");
        // Call removeEnd method
        list.removeEnd();
        System.out.println(list);

        try {
            // Call get method
            int get = list.get(0);

            // set position using set method
            System.out.println("\nSet position of index 0 to index 100: ");

            // Call set method
            list.set(0, 100);
            System.out.println(list);

            // set position using set method
            System.out.println("\nSet position of index 0 to initial value: ");

            // Call set method
            list.set(0, get);
            System.out.println(list);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to access index 0");
        }try {

            // swap elements at index 0 and 5
            System.out.println("\nSwap index 0 and index 5");

            // Call swap method
            list.swap(0, 5);
            System.out.println(list);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Cannot swap. Out of bounds");
        }

        //new list to add into original using insertList
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = -5; i < 0; i++)
            arrayList.add(i);

        // insert arraylist into current list
        System.out.println("\nAdd new list into current list");

        // call insertList method
        list.insertList(arrayList, 0);
        System.out.println(list);

        // erase first five elements
        System.out.println("\nErase the first five elements: ");

        // call erase method
        list.erase(0, 5);
        System.out.println(list);
    }
}