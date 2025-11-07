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
	                // sara's 
	                System.out.println("\n--- Adding New Product ---");
	    	        products.addProduct(151, "Smart Glasses X", 499.99, 25);
	    	        System.out.println("Total products after adding: " + products.getSize());

	    	        System.out.println("\n--- Adding New Customer ---");
	    	        customers.addCustomer(231, "Sara Aljuraybah", "sara.aljreba@example.com");
	    	        System.out.println("Total customers after adding: " + customers.getSize());
	    	        
	    	        System.out.println("\n--- Placing New Order ---");
	    	        orders.addOrder(401, 231, "101;102;103", customers, products);
	    	        System.out.println("Total orders after adding: " + orders.getSize());
	    	        
	    	        System.out.println("\n--- Adding New Review ---");
	    	         reviews.addReview(231, 101, 5, "Amazing product, super fast!", customers, products);
	    	        System.out.println("Total reviews after adding: " + reviews.getSize());
	                //&&&&&&&&&
	    	        int c1 = 201;  // pick two valid IDs from your CSV
	    	        int c2 = 211;
	    	        products.printCommonReviewedProducts(c1, c2, 4.0);

	    	        //&&&&&&&&&&&&&
	    	        //^^^^^^^^^
	    	        System.out.println("\n--- Orders Between Two Dates ---");
	    	        orders.printOrdersBetweenDates("1/20/2025", "1/25/2025");
//^^^^^^^^^^^^6
	                
	            }
	        }


	    }
	}