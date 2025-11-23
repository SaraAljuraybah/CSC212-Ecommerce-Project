package Phase1;

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


public boolean hasReviewByCustomer(int customerId) {
  Node cur = head;                
  while (cur != null) {
      Reviews r = cur.data;
      if (r != null && r.getReviewer() != null
              && r.getReviewer().getCustomerId() == customerId) {
          return true;
      }
      cur = cur.next;
  }
  return false;
}

 
    

	@Override
	public Reviews searchById(int id) {
		
		return null;
	}

	@Override
	public int getSize() {
		
		return size ;
	}
	

	public void addReview(int customerId, int productId, int rating, String comment,
            CustomerList customers, ProductList products) {

Customers customer = customers.searchById(customerId);
if (customer == null) {
System.out.println(" Customer not found with ID " + customerId);
return;
}


Products product = products.searchById(productId);
if (product == null) {
System.out.println(" Product not found with ID " + productId);
return;
}


Reviews review = new Reviews(rating, comment, customer,product );


product.addReview(review);


add(review);

System.out.println(" Review added successfully by " + customer.getName() +
             " for product: " + product.getName());
}

}


