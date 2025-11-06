package phase1;

public class ReviewList implements ListInterface<Reviews>{
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
    
 // ------------------------------------------------------------
 // Query #1: Get all reviews by a specific customer (reviewer)
 // Time Complexity: O(n)  -- linear scan through the linked list
 // Space Complexity: O(1)
 // ------------------------------------------------------------
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
         if (r.getReviewer() != null && r.getReviewer().getCustomerId() == customerId) {
             System.out.println("- Product ID: " + r.getProduct().getProductId()
                 + " | Rating: " + r.getRating()
                 + " | Comment: " + r.getComment());
             found = true;
         }
         current = current.next;
     }

     if (!found) {
         System.out.println("This customer has not submitted any reviews yet.");
     }
 }

    
    
    

	@Override
	public Reviews searchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size ;
	}
}

