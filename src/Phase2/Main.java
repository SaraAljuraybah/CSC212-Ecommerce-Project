package Phase2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ============================
        //   TREES for Phase 2
        // ============================
        ProductTree products = new ProductTree();
        CustomerTree customers = new CustomerTree();
        OrderTree orders = new OrderTree();
        ReviewList reviews = new ReviewList();

        // ============================
        //   READ CSV FILES
        // ============================
        CSVReader.readProducts("data/products.csv", products);
        CSVReader.readCustomers("data/customers.csv", customers);
        CSVReader.readOrders("data/orders.csv", orders, customers, products);
        CSVReader.readReviews("data/reviews.csv", reviews, products, customers);

        // ============================
        //   INTERACTIVE MENU
        // ============================
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Add a new product");
            System.out.println("2. Add a new customer");
            System.out.println("3. Place a new order");
            System.out.println("4. Add a review to a product");
            System.out.println("5. Extract reviews from a specific customer");
            System.out.println("6. Suggest top 3 products by average rating");
            System.out.println("7. Show all orders between two dates");
            System.out.println("8. Show common products reviewed by two customers (avg > 4)");
            System.out.println("9. Exit");
            System.out.println("========================");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ================================================
                // 1) Add new product
                // ================================================
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

                    Products newProd = new Products(pid, pname, price, stock);
                    products.insert(newProd);
                    System.out.println("✅ Product added.");
                    break;

                // ================================================
                // 2) Add new customer
                // ================================================
                case 2:
                    System.out.print("Enter customer ID: ");
                    int cid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter customer name: ");
                    String cname = sc.nextLine();

                    System.out.print("Enter customer email: ");
                    String email = sc.nextLine();

                    Customers newCustomer = new Customers(cid, cname, email);
                    customers.insert(newCustomer);

                    System.out.println("✅ Customer added.");
                    break;

                // ================================================
                // 3) Place new order
                // ================================================
                case 3:
                    System.out.print("Enter order ID: ");
                    int oid = sc.nextInt();

                    System.out.print("Enter customer ID: ");
                    int custId = sc.nextInt();
                    sc.nextLine();

                    Customers orderCust = customers.search(custId);
                    if (orderCust == null) {
                        System.out.println("❌ Customer not found.");
                        break;
                    }

                    System.out.print("Enter product IDs (101;102;103): ");
                    String idsStr = sc.nextLine();

                    // Create order
                    Orders ord = new Orders(oid, orderCust, new java.util.Date());

                    String[] idList = idsStr.split(";");
                    for (String s : idList) {
                        int pId = Integer.parseInt(s.trim());
                        Products prod = products.search(pId);
                        if (prod != null) {
                            ord.addProduct(prod);
                        }
                    }

                    // Add to Orders Tree
                    orders.insert(ord);
                    orderCust.placeOrder(ord);

                    System.out.println("✅ Order placed.");
                    break;

                // ================================================
                // 4) Add review
                // ================================================
                case 4:
                    System.out.print("Enter customer ID: ");
                    int rcid = sc.nextInt();

                    System.out.print("Enter product ID: ");
                    int rpid = sc.nextInt();

                    System.out.print("Enter rating (1-5): ");
                    int rating = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter comment: ");
                    String comment = sc.nextLine();

                    reviews.addReview(rcid, rpid, rating, comment, customers, products);
                    break;

                // ================================================
                // 5) Show reviews by customer
                // ================================================
                case 5:
                    System.out.print("Enter customer ID: ");
                    int revCID = sc.nextInt();
                    reviews.getReviewsByCustomer(revCID);
                    break;

                // ================================================
                // 6) Top 3 products (You must implement getTop3)
                // ================================================
                case 6:
                    System.out.println("\nTop 3 Products by Rating:");
                    products.printTop3ByRating();
                    break;

                // ================================================
                // 7) Orders between dates
                // ================================================
                case 7:
                    System.out.println("Feature pending (implement in OrderTree)");
                    break;

                // ================================================
                // 8) Common reviewed products
                // ================================================
                case 8:
                    System.out.println("Feature pending (Phase 2 bonus)");
                    break;

                case 9:
                    System.out.println("Exiting program …");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);

        sc.close();
    }
}
