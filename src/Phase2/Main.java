package Phase2;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

      
        ProductTree products = new ProductTree();
        CustomerTree customers = new CustomerTree();
        OrderTree orders = new OrderTree();
        ReviewList reviews = new ReviewList();   

      
     
        CSVReader.readProducts("data/products.csv", products);      
        CSVReader.readCustomers("data/customers.csv", customers);
        CSVReader.readOrders("data/orders.csv", orders, customers, products);
        CSVReader.readReviews("data/reviews.csv", reviews, products, customers);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
        	System.out.println("\n");
            System.out.println("1- Find ALL orders between TWO dates");
            System.out.println("2- List ALL products within a PRICE range");
            System.out.println("3- Show TOP 3 most reviewed");
            System.out.println("4- List ALL customers sorted ALPHABETICALLY");
            System.out.println("5- Given a product ID, show ALL customers who reviewed it");
            System.out.println("0- Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {


                case 1:
                	System.out.print("Enter START date (M/d/yyyy): ");
                    String startStr = sc.nextLine();

                    System.out.print("Enter END date   (M/d/yyyy): ");
                    String endStr = sc.nextLine();

                    orders.printOrdersBetweenDates(startStr, endStr);
                    
                    break;

     
                case 2:
                	System.out.print("Enter MINIMUM price: ");
                    double minPrice = sc.nextDouble();

                    System.out.print("Enter MAXIMUM price: ");
                    double maxPrice = sc.nextDouble();
                    sc.nextLine(); 

                    products.printProductsInPriceRange(minPrice, maxPrice);
                    break;

                    

                case 3:
                   
                    products.printTop3MostReviewed();
                    break;

            
                case 4:
                    
                    customers.printCustomersAlphabetically();
                    break;

            
                case 5:
                    
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();
                    Products p = products.search(pid);

                    if (p == null) {
                        System.out.println("Product not found.");
                        break;
                    } 

                            p.getReviews().printCustomersSortedByRating();
                       
                    
                    break;

                case 0:
                    System.out.println("\nExiting program… ");
                    break;

                default:
                    System.out.println("\n Invalid choice, please try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
