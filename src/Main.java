package Phase1;

public class Main {

	    public static void main(String[] args) {
	        ProductList products = new ProductList();
	        CustomerList customers = new CustomerList();
	        OrderList orders = new OrderList();
	        ReviewList reviews = new ReviewList();
//read
	        CSVReader.readProducts("data/products.csv", products);
	        CSVReader.readCustomers("data/customers.csv", customers);
	        CSVReader.readOrders("data/orders.csv", orders, customers, products);
	        CSVReader.readReviews("data/reviews.csv", reviews, products, customers);
//test
	        System.out.println("\n--- Summary ---");
	        System.out.println("Products: " + products.getSize());
	        System.out.println("Customers: " + customers.getSize());
	        System.out.println("Orders: " + orders.getSize());
	        System.out.println("Reviews: " + reviews.getSize());



	    }
	}
