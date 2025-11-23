package Phase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVReader {

    // =======================================================
    // READ PRODUCTS → Insert into ProductTree
    // =======================================================
    public static void readProducts(String filename, ProductTree productTree) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                double price = Double.parseDouble(data[2].trim());
                int stock = Integer.parseInt(data[3].trim());

                Products p = new Products(id, name, price, stock);
                productTree.insert(p);
            }

            System.out.println("✅ Products loaded (AVL) successfully from " + filename);

        } catch (IOException e) {
            System.out.println("❌ Error reading products file: " + e.getMessage());
        }
    }

    // =======================================================
    // READ CUSTOMERS → Insert into CustomerTree
    // =======================================================
    public static void readCustomers(String filename, CustomerTree customerTree) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String email = data[2].trim();

                Customers c = new Customers(id, name, email);
                customerTree.insert(c);
            }

            System.out.println("✅ Customers loaded (AVL) successfully from " + filename);

        } catch (IOException e) {
            System.out.println("❌ Error reading customers file: " + e.getMessage());
        }
    }

    // =======================================================
    // READ ORDERS → Insert into OrderTree
    // =======================================================
    public static void readOrders(String filename, OrderTree orderTree,
                                  CustomerTree customers, ProductTree products) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line = br.readLine(); // Skip header
            SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int orderId = Integer.parseInt(data[0].trim());
                int customerId = Integer.parseInt(data[1].trim());
                String productIdsStr = data[2].trim();

                // handle quotes "101;102"
                if (productIdsStr.startsWith("\"") && productIdsStr.endsWith("\"")) {
                    productIdsStr = productIdsStr.substring(1, productIdsStr.length() - 1);
                }
                productIdsStr = productIdsStr.replace("\"", "");

                double totalPrice = Double.parseDouble(data[3].trim());
                Date orderDate = formatter.parse(data[4].trim());
                String status = data[5].trim();

                // search in AVL trees
                Customers customer = customers.search(customerId);

                if (customer != null) {

                    Orders order = new Orders(orderId, customer, orderDate);
                    order.updateStatus(status);
                    order.setTotalPrice(totalPrice);

                    // add products to order
                    String[] productIds = productIdsStr.split(";");

                    for (String pid : productIds) {
                        pid = pid.trim();
                        if (!pid.isEmpty()) {

                            int productId = Integer.parseInt(pid);
                            Products product = products.search(productId);

                            if (product != null) {
                                order.addProduct(product);
                            }
                        }
                    }

                    // finally insert order into AVL tree
                    orderTree.insert(order);
                }
            }

            System.out.println("✅ Orders loaded (AVL) successfully from " + filename);

        } catch (Exception e) {
            System.out.println("❌ Error reading orders file: " + e.getMessage());
        }
    }

    // =======================================================
    // READ REVIEWS → still list-based (or use a tree if you want bonus++)
    // =======================================================
    public static void readReviews(String filename, ReviewList reviewList,
                                   ProductTree products, CustomerTree customers) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line = br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                int productId = Integer.parseInt(data[1].trim());
                int customerId = Integer.parseInt(data[2].trim());
                int rating = Integer.parseInt(data[3].trim());
                String comment = data[4].trim();

                Products product = products.search(productId);
                Customers reviewer = customers.search(customerId);

                if (product != null && reviewer != null) {

                    Reviews review = new Reviews(rating, comment, reviewer, product);

                    product.addReview(review);
                    reviewList.add(review);
                }
            }

            System.out.println("✅ Reviews loaded successfully from " + filename);

        } catch (IOException e) {
            System.out.println("❌ Error reading reviews file: " + e.getMessage());
        }
    }
}
