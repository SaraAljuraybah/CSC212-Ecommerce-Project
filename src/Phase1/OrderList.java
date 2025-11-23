package Phase1;
import java.text.SimpleDateFormat;
import java.util.Date;
public class OrderList implements ListInterface<Orders> {
	
    private class Node {
        Orders data;
        Node next;
        Node(Orders data) { this.data = data; }
    }

    private Node head;
    private int size;

    @Override
    public void add(Orders order) {
        Node newNode = new Node(order);
        if (head == null)
            head = newNode;
        else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public Orders searchById(int orderId) {
        Node current = head;
        while (current != null) {
            if (current.data.getOrderId() == orderId)
                return current.data;
            current = current.next;
        }
        return null;
    }

    @Override
    public int getSize() { return size; }

    public void displayOrders() {
        Node current = head;
        while (current != null) {
            System.out.println("Order ID: " + current.data.getOrderId() + 
                               " | Status: " + current.data.getStatus() + 
                               " | Total: " + current.data.getTotalPrice());
            current = current.next;
        }
    }
  
    public void addOrder(int orderId, int customerId, String productIdsStr, 
            CustomerList customers, ProductList products) {
    	

//   check if order ID already exists

Orders existing = searchById(orderId);
if (existing != null) {
   System.out.println(" Order ID " + orderId + " already exists. Please use a unique ID.");
   return; 
}

// Validate customer
Customers customer = customers.searchById(customerId);
if (customer == null) {
   System.out.println(" Customer not found with ID " + customerId);
   return;
}

// Create order object
Date today = new Date();
Orders newOrder = new Orders(orderId, customer, today);
newOrder.updateStatus("Pending");

double totalPrice = 0;
int productCount = 0;        
boolean missingProduct = false;

// Process product IDs
String[] productIds = productIdsStr.split(";");
for (String pid : productIds) {
   pid = pid.trim();
   if (!pid.isEmpty()) {
       Products product = products.searchById(Integer.parseInt(pid));
       if (product != null) {
           newOrder.addProduct(product);
           totalPrice += product.getPrice();
           productCount++;
       } else {
           System.out.println(" Product not found: " + pid);
           missingProduct = true;
       }
   }
}

//  Validation after product processing
if (productCount == 0) {
   System.out.println(" Order not added: no valid products were provided.");
   return;
}
if (missingProduct) {
   System.out.println("Order not added: one or more product IDs were invalid.");
   return;
}

// Add order if everything valid
newOrder.setTotalPrice(totalPrice);
add(newOrder);
System.out.println(" Order added successfully for " + customer.getName());




} 
    
    public void printOrdersBetweenDates(String startDateStr, String endDateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
            Date startDate = formatter.parse(startDateStr);
            Date endDate = formatter.parse(endDateStr);

            System.out.println("\n--- Orders between " + startDateStr + " and " + endDateStr + " ---");

            Node current = head;
            boolean found = false;

            while (current != null) {
                Orders order = current.data;
                Date orderDate = order.getOrderDate();

              
                if (orderDate.compareTo(startDate) >= 0 && orderDate.compareTo(endDate) <= 0) {
                    found = true;
                    System.out.println("Order ID: " + order.getOrderId() +
                                       " | Customer: " + order.getCustomer().getName() +
                                       " | Date: " + formatter.format(orderDate) +
                                       " | Status: " + order.getStatus() +
                                       " | Total: " + order.getTotalPrice());
                }

                current = current.next;
            }

            if (!found)
                System.out.println(" No orders found in this date range.");

        } catch (Exception e) {
            System.out.println(" Error parsing date: " + e.getMessage());
        }
    }
 

}




