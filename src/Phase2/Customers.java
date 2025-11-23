package Phase2;

public class Customers {

    private int customerId;
    private String name;
    private String email;

    // Each customer now has their own ORDER TREE (Phase 2)
    private OrderTree customerOrders;

    // ============================================
    //  CONSTRUCTOR
    // ============================================
    public Customers(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.customerOrders = new OrderTree();  // AVL Tree for this customer's orders
    }

    // ============================================
    //  GETTERS
    // ============================================
    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public OrderTree getCustomerOrders() {
        return customerOrders;
    }

    // ============================================
    //  REGISTER NEW CUSTOMER
    // ============================================
    public static Customers registerCustomer(int id, String name, String email) {
        System.out.println("Customer registered successfully!");
        return new Customers(id, name, email);
    }

    // ============================================
    //  PLACE ORDER → Insert into this customer's tree
    // ============================================
    public void placeOrder(Orders order) {
        customerOrders.insert(order);
        System.out.println("Order placed successfully for customer: " + name);
    }

    // ============================================
    //  VIEW ORDER HISTORY (Inorder Traversal)
    // ============================================
    public void viewOrderHistory() {
        System.out.println("\n📦 Order history for " + name + ":");

        if (customerOrders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        customerOrders.inOrder();  // Use traversal to display orders
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId +
               " | Name: " + name +
               " | Email: " + email;
    }
}
