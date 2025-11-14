package phase1;

public class CustomerList implements ListInterface<Customers> {
    private class Node {
        Customers data;
        Node next;
        Node(Customers data) { this.data = data; }
    }

    private Node head;
    private int size;

    @Override
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

    @Override
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

    @Override
    public int getSize() { return size; }
    
    public void addCustomer(int id, String name, String email) {
     
        if (searchById(id) != null) {
            System.out.println(" Customer with ID " + id + " already exists!");
            return;
        }

   
        Customers newCustomer = new Customers(id, name, email);

     
        add(newCustomer);

        System.out.println(" Customer added successfully: " + name);
    }

}

