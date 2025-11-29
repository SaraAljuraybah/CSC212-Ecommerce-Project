package Phase2;

public class ReviewList {

    private class Node {
        Reviews data;
        Node next;
        Node(Reviews data) { this.data = data; }
    }

    private Node head;
    private int size;

 
    public void add(Reviews review) {
        Node newNode = new Node(review);

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

 
    public double getAverage() {
        if (size == 0) return 0;

        Node current = head;
        double total = 0;

        while (current != null) {
            total += current.data.getRating();
            current = current.next;
        }

        return total / size;
    }

  
    public void getReviewsByCustomer(int customerId) {
        if (head == null) {
            System.out.println("No reviews available.");
            return;
        }

        boolean found = false;
        Node current = head;

        System.out.println("Reviews by Customer ID: " + customerId);

        while (current != null) {
            Reviews r = current.data;

            if (r.getReviewer() != null &&
                r.getReviewer().getCustomerId() == customerId) {

                System.out.println("- Product ID: " + r.getProduct().getProductId()
                        + " | Rating: " + r.getRating()
                        + " | Comment: " + r.getComment());

                found = true;
            }

            current = current.next;
        }

        if (!found)
            System.out.println("This customer has not submitted any reviews yet.");
    }

  
    public boolean hasReviewByCustomer(int customerId) {
        Node current = head;

        while (current != null) {
            Reviews r = current.data;

            if (r != null && r.getReviewer() != null &&
                    r.getReviewer().getCustomerId() == customerId) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    
    public int getSize() {
        return size;
    }

 
    public void addReview(
            int customerId, int productId, int rating, String comment,
            CustomerTree customers, ProductTree products) {

        // search customers in the AVL tree
        Customers customer = customers.search(customerId);
        if (customer == null) {
            System.out.println(" Customer not found with ID " + customerId);
            return;
        }

        // search products in AVL tree
        Products product = products.search(productId);
        if (product == null) {
            System.out.println(" Product not found with ID " + productId);
            return;
        }

      
        Reviews review = new Reviews(rating, comment, customer, product);

        
        product.addReview(review);
        add(review);

        System.out.println("✅ Review added successfully by " +
                customer.getName() + " for product: " + product.getName());
    }
    

    
    
   
    public Reviews[] toArray() {
        Reviews[] arr = new Reviews[size];
        Node  current = head;
        int i = 0;

        while (current != null) {
            arr[i] = current.data;
            current = current.next;
            i++;
        }

        return arr;
    }
  
    
   
    
    private void sortByRating(Reviews[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].getRating() < arr[j + 1].getRating()) {
                    Reviews temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    
    
    
    public void printCustomersSortedByRating() {
        if (size == 0) {
            System.out.println("No reviews for this product.");
            return;
        }

        Reviews[] arr = toArray();
        sortByRating(arr);

        System.out.println("\nCustomers Who Reviewed This Product :\n");

        for (int i = 0; i < arr.length; i++) {
            System.out.println((i + 1) + ". "
                + arr[i].getReviewer().getName()
                + " | Rating: " + arr[i].getRating()
                + " | Comment: " + arr[i].getComment());
        }
    }

    
    
    
    
}
