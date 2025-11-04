package Phase1;
public class OrderList implements ListInterface<Orders> {
	
    private class Node {
        Orders data;
        Node next;
        Node(Orders data) { this.data = data; }
    }

    private Node head;
    private int size;

    @Override
    public void add(Orders order) {
        Node newNode = new Node(order);
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
    public Orders searchById(int orderId) {
        Node current = head;
        while (current != null) {
            if (current.data.getOrderId() == orderId)
                return current.data;
            current = current.next;
        }
        return null;
    }

    @Override
    public int getSize() { return size; }

    public void displayOrders() {
        Node current = head;
        while (current != null) {
            System.out.println("Order ID: " + current.data.getOrderId() + 
                               " | Status: " + current.data.getStatus() + 
                               " | Total: " + current.data.getTotalPrice());
            current = current.next;
        }
    }
}
