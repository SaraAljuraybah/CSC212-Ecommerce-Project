package Phase2;

import java.util.Date;

public class Orders {

    private int orderId;
    private Customers customer;
    private ProductTree orderProducts;   // Phase 2: tree instead of list
    private double totalPrice;
    private Date orderDate;
    private String status; // pending, shipped, delivered, canceled

    // ============================================
    //  CONSTRUCTOR
    // ============================================
    public Orders(int orderId, Customers customer, Date orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderProducts = new ProductTree(); // Tree of products inside this order
        this.totalPrice = 0.0;
        this.status = "pending";
    }

    // ============================================
    //  GETTERS
    // ============================================
    public int getOrderId() { return orderId; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public Customers getCustomer() { return customer; }
    public Date getOrderDate() { return orderDate; }

    public ProductTree getOrderProducts() {
        return orderProducts;
    }

    // ============================================
    //  SETTERS
    // ============================================
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    // ============================================
    //  ADD PRODUCT TO ORDER
    // ============================================
    public void addProduct(Products p) {
        orderProducts.insert(p);
        totalPrice += p.getPrice();
    }

    // ============================================
    //  CANCEL ORDER
    // ============================================
    public void cancelOrder() {
        if (!status.equalsIgnoreCase("delivered")) {
            status = "canceled";
            System.out.println("Order #" + orderId + " has been canceled.");
        } else {
            System.out.println("Delivered orders cannot be canceled.");
        }
    }

    // ============================================
    //  UPDATE STATUS
    // ============================================
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        // System.out.println("Order #" + orderId + " status updated to " + newStatus);
    }

    // ============================================
    //  TO STRING
    // ============================================
    @Override
    public String toString() {
        return "Order ID: " + orderId +
                " | Total: " + totalPrice +
                " | Status: " + status +
                " | Customer: " + customer.getName() +
                " | Date: " + orderDate;
    }
}
