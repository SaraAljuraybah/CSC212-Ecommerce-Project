package phase1;

public class Main {

	    public static void main(String[] args) {
	        ProductList products = new ProductList();
	        CustomerList customers = new CustomerList();
	        OrderList orders = new OrderList();
	        ReviewList reviews = new ReviewList();
//read     ///****************filenames
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

	        //**********************
	        reviews.getReviewsByCustomer(220);

	        Products[] top = products.getTop3ByAverageRating();
	        System.out.println("\nTop 3 products by average rating:");
	        for (int i = 0; i < top.length; i++) {
	            if (top[i] != null) {
	                System.out.printf("%d) %s (ID %d) — Avg: %.2f%n",
	                    i + 1, top[i].getName(), top[i].getProductId(), top[i].getAverageRating());
	                //******************************
	                
	                
	            }
	        }


	    }
	}