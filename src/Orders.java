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

	    public void addProduct(Products p) {
	        products.add(p);
	        totalPrice += p.getPrice();
	    }

	    public double getTotalPrice() {
	        return totalPrice;
	    }

	    public int getOrderId() {
	        return orderId;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	}


