package Phase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVReader {

    
    public static void readProducts(String filename, ProductList productList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                double price = Double.parseDouble(data[2].trim());
                int stock = Integer.parseInt(data[3].trim());
                Products p = new Products(id, name, price, stock);
                productList.add(p);
            }
            System.out.println("✅ Products loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error reading products file: " + e.getMessage());
        }
    }

    //  Read Customers file
    public static void readCustomers(String filename, CustomerList customerList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String email = data[2].trim();
                Customers c = new Customers(id, name, email);
                customerList.add(c);
            }
            System.out.println("✅ Customers loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error reading customers file: " + e.getMessage());
        }
    }

    //  Read Orders file
    public static void readOrders(String filename, OrderList orderList, CustomerList customers, ProductList products) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip header
            ////////////////////*************************yearFormat
            SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int orderId = Integer.parseInt(data[0].trim());
                int customerId = Integer.parseInt(data[1].trim());
                String productIdsStr = data[2].trim();
                //*******************************

             // FIX: remove surrounding quotes (e.g. "101;102")
                if (productIdsStr.startsWith("\"") && productIdsStr.endsWith("\"")) {
                    productIdsStr = productIdsStr.substring(1, productIdsStr.length() - 1);
                }
                productIdsStr = productIdsStr.replace("\"", ""); // safety for stray quotes
                ////************************
                
                double totalPrice = Double.parseDouble(data[3].trim());
                Date orderDate = formatter.parse(data[4].trim());
                String status = data[5].trim();

                Customers customer = customers.searchById(customerId);
                if (customer != null) {
                    Orders order = new Orders(orderId, customer, orderDate);
                    order.updateStatus(status);

                    // Add all products listed in the order
                    String[] productIds = productIdsStr.split(";");
                    for (String pid : productIds) {
                        pid = pid.trim();
                        if (!pid.isEmpty()) {
                            Products product = products.searchById(Integer.parseInt(pid));
                            if (product != null) {
                                order.addProduct(product);
                            }
                        }
                    }

                    orderList.add(order);
                }
            }
            System.out.println(" Orders loaded successfully from " + filename);
        } catch (Exception e) {
            System.out.println(" Error reading orders file: " + e.getMessage());
        }
    }

    // Read Reviews file
    public static void readReviews(String filename, ReviewList reviewList, ProductList products, CustomerList customers) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                
                int productId = Integer.parseInt(data[1].trim());
                int customerId = Integer.parseInt(data[2].trim());
                int rating = Integer.parseInt(data[3].trim());
                String comment = data[4].trim();

                Products product = products.searchById(productId);
                Customers reviewer = customers.searchById(customerId);

                if (product != null && reviewer != null) {
                	//*************
                    Reviews review = new Reviews(rating, comment, reviewer, product);
                    product.addReview(review);
                    reviewList.add(review);
                    System.out.println("DEBUG: added review for productId=" + productId + " | rating=" + rating + " | current size=" + reviewList.getSize());
                }
            }
            System.out.println("✅ Reviews loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error reading reviews file: " + e.getMessage());
        }
    }


}