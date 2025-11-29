package Phase2;

import java.util.Date;

public class Orders {

    private int orderId;
    private Customers customer;
    private ProductTree orderProducts;   
    private double totalPrice;
    private Date orderDate;
    private String status; 


    public Orders(int orderId, Customers customer, Date orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderProducts = new ProductTree(); // Tree of products inside this order
        this.totalPrice = 0.0;
        this.status = "pending";
    }

 
    public int getOrderId() { return orderId; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public Customers getCustomer() { return customer; }
    public Date getOrderDate() { return orderDate; }

    public ProductTree getOrderProducts() {
        return orderProducts;
    }

 
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

   
    public void addProduct(Products p) {
        orderProducts.insert(p);
        totalPrice += p.getPrice();
    }

 
    public void cancelOrder() {
        if (!status.equalsIgnoreCase("delivered")) {
            status = "canceled";
            System.out.println("Order #" + orderId + " has been canceled.");
        } else {
            System.out.println("Delivered orders cannot be canceled.");
        }
    }

 
    public void updateStatus(String newStatus) {
        this.status = newStatus;
       
    }

   
    @Override
    public String toString() {
        return "Order ID: " + orderId +
           " | Total: " + totalPrice +
       " | Status: " + status +
                     " | Customer: " + customer.getName() +
           " | Date: " + orderDate;
    }
}
