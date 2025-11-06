package phase1;

public class Customers {
    private int customerId;
    private String name;
    private String email;
    private OrderList orders;

    public Customers(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.orders = new OrderList();
    }

    
    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }



    // Register new customer
    public static Customers registerCustomer(int id, String name, String email) {
        System.out.println("Customer registered successfully!");
        return new Customers(id, name, email);
    }

    // Place a new order for this customer
    public void placeOrder(Orders order) {
        orders.add(order);
        System.out.println("Order placed successfully for customer: " + name);
    }

    // View order history
    public void viewOrderHistory() {
        System.out.println("Order history for " + name + ":");
        orders.displayOrders();
    }
}
