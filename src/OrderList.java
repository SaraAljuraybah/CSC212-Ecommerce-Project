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
    
    //********************************************
    public void addOrder(int orderId, int customerId, String productIdsStr, //O(n+m)
            CustomerList customers, ProductList products) {
	
	// 🧩 نتأكد أن الطلب ما هو موجود مسبقًا
	if (searchById(orderId) != null) {
	System.out.println("❌ Order with ID " + orderId + " already exists!");
	return;
	}
	
	// 🧩 نبحث عن العميل باستخدام الـ ID
	Customers customer = customers.searchById(customerId);
	if (customer == null) {
	System.out.println("❌ Customer not found with ID " + customerId);
	return;
	}
	
	// 🧩 نجهز الطلب الجديد
	Date today = new Date(); // التاريخ الحالي
	Orders newOrder = new Orders(orderId, customer, today);
	newOrder.updateStatus("Pending");
	
	double totalPrice = 0;
	
	// 🧩 نضيف المنتجات
	String[] productIds = productIdsStr.split(";");
	for (String pid : productIds) {
	pid = pid.trim();
	if (!pid.isEmpty()) {
	   Products product = products.searchById(Integer.parseInt(pid));
	   if (product != null) {
	       newOrder.addProduct(product);
	       totalPrice += product.getPrice();
	   } else {
	       System.out.println("⚠️ Product not found: " + pid);
	   }
	}
	}
	
	// 🧩 نحسب المجموع
	newOrder.setTotalPrice(totalPrice);
	
	// 🧩 نضيف الطلب إلى القائمة
	add(newOrder);
	System.out.println("✅ Order added successfully for " + customer.getName());
	}
    //********************************************
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

                // التحقق إن التاريخ بين التاريخين
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
                System.out.println("⚠️ No orders found in this date range.");

        } catch (Exception e) {
            System.out.println("❌ Error parsing date: " + e.getMessage());
        }
    }
    //***************************************************8
}
