package Phase1;

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
   
    public Products[] getTop3ByAverageRating() {
        Products[] top = new Products[3];
        double[] best = { 0, 0, 0 };

        Node cur = head; 
        while (cur != null) {
            Products p = cur.data;
            double avg = p.getAverageRating(); 

            for (int i = 0; i < 3; i++) {
                if (avg > best[i]) {
               
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
    
    public void addProduct(int id, String name, double price, int stock) {
      
        if (searchById(id) != null) {
            System.out.println(" Product with ID " + id + " already exists!");
            return;
        }

        
        Products newProduct = new Products(id, name, price, stock);

     
        add(newProduct);

        System.out.println(" Product added successfully: " + name);
    }
  
 public void printCommonReviewedProducts(int customerId1, int customerId2, double minAvg) {
     boolean found = false;
     Node cur = head; 

     System.out.println("\nCommon products reviewed by customers "
             + customerId1 + " and " + customerId2 + " with average rating > " + minAvg + ":");

     while (cur != null) {
         Products p = cur.data;
         if (p != null
                 && p.hasReviewByCustomer(customerId1)
                 && p.hasReviewByCustomer(customerId2)) {

             double avg = p.getAverageRating();
             if (avg > minAvg) {
                 System.out.printf("- %s (ID %d) — Avg: %.2f%n",
                         p.getName(), p.getProductId(), avg);
                 found = true;
             }
         }
         cur = cur.next;
     }

     if (!found) {
         System.out.println("None.");
     }
     
 }

}

