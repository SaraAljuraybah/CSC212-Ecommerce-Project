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
        if (head == null) {
            head = newNode;
        } else {
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

	@Override
	public Reviews searchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	//********************************************************
	public void addReview(int customerId, int productId, int rating, String comment,
		            CustomerList customers, ProductList products) {
		// نبحث عن العميل
		Customers customer = customers.searchById(customerId);
		if (customer == null) {
		System.out.println("❌ Customer not found with ID " + customerId);
		return;
		}
		
		// نبحث عن المنتج
		Products product = products.searchById(productId);
		if (product == null) {
		System.out.println("❌ Product not found with ID " + productId);
		return;
		}
		
		// ننشئ التقييم الجديد
		Reviews review = new Reviews(rating, comment, customer);
		
		// نربط التقييم بالمنتج
		product.addReview(review);
		
		// نضيفه في القائمة العامة
		add(review);
		
		System.out.println("✅ Review added successfully by " + customer.getName() +
		             " for product: " + product.getName());
		}
	//*****************************************************

}

