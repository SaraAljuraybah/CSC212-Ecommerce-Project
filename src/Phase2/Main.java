package Phase2;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // ============================
        //   PHASE 2 – AVL STRUCTURES
        // ============================
        ProductTree products = new ProductTree();
        CustomerTree customers = new CustomerTree();
        OrderTree orders = new OrderTree();
        ReviewList reviews = new ReviewList();   // later we’ll fix its package if needed

        // ============================
        //   LOAD DATA ONCE (CSV)
        // ============================
        // Make sure these CSV files are in the project root (same folder as src or as you configured)
        CSVReader.readProducts("data/products.csv", products);       // file name has the same typo as your file
        CSVReader.readCustomers("data/customers.csv", customers);
        CSVReader.readOrders("data/orders.csv", orders, customers, products);
        CSVReader.readReviews("data/reviews.csv", reviews, products, customers);

        Scanner sc = new Scanner(System.in);
       // SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy"); // same as in CSVReader
        int choice;

        do {
        	System.out.println("\n");
            System.out.println("1) Find ALL orders between TWO dates");
            System.out.println("2) List ALL products within a PRICE range");
            System.out.println("3) Show TOP 3 most reviewed");
            System.out.println("4) List ALL customers sorted ALPHABETICALLY");
            System.out.println("5) Given a product ID, show ALL customers who reviewed it");
            System.out.println("0) Exit");
            System.out.print("➡ Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                // =========================================
                // 1) ORDERS BETWEEN TWO DATES
                // =========================================
                case 1:
                	System.out.print("Enter START date (M/d/yyyy): ");
                    String startStr = sc.nextLine();

                    System.out.print("Enter END date   (M/d/yyyy): ");
                    String endStr = sc.nextLine();

                    // Send raw strings to OrderTree → it handles everything
                    orders.printOrdersBetweenDates(startStr, endStr);
                    
                    break;

                // =========================================
                // 2) PRODUCTS WITHIN PRICE RANGE
                // =========================================
                case 2:
                	System.out.print("Enter MINIMUM price: ");
                    double minPrice = sc.nextDouble();

                    System.out.print("Enter MAXIMUM price: ");
                    double maxPrice = sc.nextDouble();
                    sc.nextLine();  // clear buffer

                    products.printProductsInPriceRange(minPrice, maxPrice);
                    break;

                    

                // =========================================
                // 3) TOP 3 MOST REVIEWED / HIGHEST RATED
                // =========================================
                case 3:
                   
                    products.printTop3MostReviewed();
                    break;

                // =========================================
                // 4) CUSTOMERS SORTED ALPHABETICALLY
                // =========================================
                case 4:
                    
                    customers.printCustomersAlphabetically();
                    break;

                // =========================================
                // 5) ALL CUSTOMERS WHO REVIEWED A PRODUCT
                // =========================================
                case 5:
                    
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();
                    Products p = products.search(pid);

                    if (p == null) {
                        System.out.println("Product not found.");
                    } 

                            p.getReviews().printCustomersSortedByRating();
                       
                    
                    break;

                case 0:
                    System.out.println("\nExiting program… 👋");
                    break;

                default:
                    System.out.println("\n❌ Invalid choice, please try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
