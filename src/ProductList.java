package phase1;

public class ProductList implements ListInterface<Products> {
    private class Node {
        Products data;
        Node next;
        Node(Products data) { this.data = data; }
    }

    private Node head;
    private int size;

    @Override
    public void add(Products product) {
        Node newNode = new Node(product);
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
    //*******************************
    public Products[] getTop3ByAverageRating() {
        Products[] top = new Products[3];
        double[] best = { 0, 0, 0 };

        Node cur = head; // start at the first product node
        while (cur != null) {
            Products p = cur.data;
            double avg = p.getAverageRating(); // uses ReviewList.getAverage()

            for (int i = 0; i < 3; i++) {
                if (avg > best[i]) {
                    // shift lower-ranked items down one position
                    for (int j = 2; j > i; j--) {
                        best[j] = best[j - 1];
                        top[j]  = top[j - 1];
                    }
                    best[i] = avg;
                    top[i]  = p;
                    break;
                }
            }
            cur = cur.next;
        }
        return top;
    }

    
    
    ////remove product method ****************************
    public boolean removeProduct(int id) {
        if (head == null) return false;
        if (head.data.getProductId() == id) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getProductId() == id) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Products searchById(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.getProductId() == id)
                return current.data;
            current = current.next;
        }
        return null;
    }

    public Products searchByName(String name) {
        Node current = head;
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name))
                return current.data;
            current = current.next;
        }
        return null;
    }

    public void displayOutOfStock() {
        Node current = head;
        System.out.println("Out-of-stock products:");
        while (current != null) {
            if (current.data.getStock() == 0)
                System.out.println("- " + current.data.getName());
            current = current.next;
        }
    }

    @Override
    public int getSize() { return size; }
}
