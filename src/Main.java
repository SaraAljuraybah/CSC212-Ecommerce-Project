package phase1;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	      

	        //**********************
	        // ===== Interactive Menu =====
	        Scanner sc = new Scanner(System.in);
	        int choice = 0;

	        do {
	            System.out.println("========= MENU =========");
	            System.out.println("1. Add a new product");
	            System.out.println("2. Add a new customer");
	            System.out.println("3. Place a new order");
	            System.out.println("4. Add a review to a product");
	            System.out.println("5. Extract reviews from a specific customer");
	            System.out.println("6. Suggest top 3 products by average rating");
	            System.out.println("7. Show all orders between two dates");
	            System.out.println("8. Show common products reviewed by two customers with avg rating > 4");
	            System.out.println("9. Exit");
	            System.out.println("========================");
	            System.out.print("Enter your choice: ");
	            choice = sc.nextInt();
	            sc.nextLine(); // consume newline

	            switch (choice) {

	                // 1️⃣ Add new product
	                case 1:
	                    System.out.print("Enter product ID: ");
	                    int pid = sc.nextInt();
	                    sc.nextLine();
	                    System.out.print("Enter product name: ");
	                    String pname = sc.nextLine();
	                    System.out.print("Enter product price: ");
	                    double price = sc.nextDouble();
	                    System.out.print("Enter stock quantity: ");
	                    int stock = sc.nextInt();

	                    // use instance: products (NOT ProductList)
	                    products.addProduct(pid, pname, price, stock);
	                  //  System.out.println("✅ Product added successfully!\n");
	                    break;

	                // 2️⃣ Add new customer
	                case 2:
	                    System.out.print("Enter customer ID: ");
	                    int cid = sc.nextInt();
	                    sc.nextLine();
	                    System.out.print("Enter customer name: ");
	                    String cname = sc.nextLine();
	                    System.out.print("Enter customer email: ");
	                    String email = sc.nextLine();

	                    // use customers.addCustomer (helper in CustomerList)
	                    customers.addCustomer(cid, cname, email);
	                   //+++++++++++++++++++++++++
	                 //   System.out.println("✅ Customer added successfully!\n");
	                    break;

	                // 3️⃣ Place a new order
	                case 3:
	                    System.out.print("Enter order ID: ");
	                    int oid = sc.nextInt();
	                    System.out.print("Enter customer ID: ");
	                    int custId = sc.nextInt();
	                    sc.nextLine();

	                    // use instance customers, not CustomerList
	                    Customers orderCustomer = customers.searchById(custId);
	                    if (orderCustomer == null) {
	                        System.out.println("❌ Customer not found!\n");
	                        break;
	                    }

	                    System.out.print("Enter product IDs (separated by ';', e.g. 101;102;103): ");
	                    String productIdsStr = sc.nextLine();

	                    // we already have helper in OrderList: addOrder(int, int, String, CustomerList, ProductList)
	                    orders.addOrder(oid, custId, productIdsStr, customers, products);
	                    System.out.println();
	                    break;

	                // 4️⃣ Add a review
	                case 4:
	                    System.out.print("Enter customer ID: ");
	                    int rcid = sc.nextInt();
	                    System.out.print("Enter product ID: ");
	                    int rpid = sc.nextInt();
	                    System.out.print("Enter rating (1-5): ");
	                    int rate = sc.nextInt();
	                    //+++++++++++++++++++
	                    if(rate<0 || rate >5 ) {
	                    	System.out.print("Rating muste be a value from 0-5 \n");
	                    	break;}
	                    //+++++++++++++++++
	                    sc.nextLine();
	                    System.out.print("Enter comment: ");
	                    String comment = sc.nextLine();

	                    // use ReviewList.addReview(...) helper
	                    reviews.addReview(rcid, rpid, rate, comment, customers, products);
	                    System.out.println();
	                    break;

	                // 5️⃣ Extract reviews by a specific customer
	                case 5:
	                    System.out.print("Enter customer ID: ");
	                    int custToSearch = sc.nextInt();
	                    sc.nextLine();
	                    reviews.getReviewsByCustomer(custToSearch);
	                    System.out.println();
	                    break;

	                // 6️⃣ Suggest top 3 products by average rating
	                case 6:
	                    Products[] top = products.getTop3ByAverageRating();
	                    System.out.println("\nTop 3 products by average rating:");
	                    for (int i = 0; i < top.length; i++) {
	                        if (top[i] != null) {
	                            System.out.printf("%d) %s (ID %d) — Avg: %.2f%n",
	                                    i + 1,
	                                    top[i].getName(),
	                                    top[i].getProductId(),
	                                    top[i].getAverageRating());
	                        }
	                    }
	                    System.out.println();
	                    break;

	                // 7️⃣ Show all orders between two dates
	                case 7:
	                    System.out.println("Enter dates in format M/d/yyyy (e.g. 1/20/2025)");
	                    System.out.print("Enter start date: ");
	                    String startDateStr = sc.nextLine();
	                    System.out.print("Enter end date: ");
	                    String endDateStr = sc.nextLine();

	                    // use orders.printOrdersBetweenDates(String, String)
	                    orders.printOrdersBetweenDates(startDateStr, endDateStr);
	                    System.out.println();
	                    break;

	                // 8️⃣ Common products reviewed by two customers (>4 avg)
	                case 8:
	                    System.out.print("Enter first customer ID: ");
	                    int c1 = sc.nextInt();
	                    System.out.print("Enter second customer ID: ");
	                    int c2 = sc.nextInt();
	                    sc.nextLine();

	                    // function is in ProductList: printCommonReviewedProducts
	                    products.printCommonReviewedProducts(c1, c2, 4.0);
	                    System.out.println();
	                    break;

	                // 9️⃣ Exit
	                case 9:
	                    System.out.println("👋 Exiting program...");
	                    break;

	                default:
	                    System.out.println("Invalid choice, please try again.\n");
	                    break;
	            }

	        } while (choice != 9);

	        sc.close();


	    }
	}