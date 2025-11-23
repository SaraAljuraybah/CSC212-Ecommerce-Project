package Phase1;

import java.util.Date;

public class Orders {
    private int orderId;
    private Customers customer;
    private ProductList products;
    private double totalPrice;
    private Date orderDate;
    private String status; // pending, shipped, delivered, canceled

    public Orders(int orderId, Customers customer, Date orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.products = new ProductList();
        this.totalPrice = 0.0;
        this.status = "pending";
    }

    
    public int getOrderId() { return orderId; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public Customers getCustomer() { return customer; }
    //^^^^^^^^^^^^
    public Date getOrderDate() {
        return orderDate;
    }
 
    //*********************
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    //^^^^^^^
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

 

    // Add product to order
    public void addProduct(Products p) {
        products.add(p);
        totalPrice += p.getPrice();
    }

    // Cancel order
    public void cancelOrder() {
        if (!status.equalsIgnoreCase("delivered")) {
            status = "canceled";
            System.out.println("Order #" + orderId + " has been canceled.");
        } else {
            System.out.println("Delivered orders cannot be canceled.");
        }
    }

    // Update order status
    public void updateStatus(String newStatus) {
        status = newStatus;
        System.out.println("Order #" + orderId + " status updated to " + newStatus);
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Total: " + totalPrice +
               ", Status: " + status + ", Customer: " + customer.getName();
    }
}
