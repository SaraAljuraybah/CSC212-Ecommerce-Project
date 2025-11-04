package Phase1;


public class CustomerList {
    private class Node {
        Customers data;
        Node next;
        Node(Customers data) { this.data = data; }
    }

    private Node head;
    private int size;

    public void add(Customers customer) {
        Node newNode = new Node(customer);
        if (head == null)
            head = newNode;
        else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        size++;
    }

    public Customers searchById(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.getCustomerId() == id)
                return current.data;
            current = current.next;
        }
        return null;
    }

    public Customers searchByName(String name) {
        Node current = head;
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name))
                return current.data;
            current = current.next;
        }
        return null;
    }

    public int getSize() { return size; }
}
