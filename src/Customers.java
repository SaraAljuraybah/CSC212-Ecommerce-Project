package Phase1;

public class Customers {
	    private int customerId;
	    private String name;
	    private String email;
	    private OrderList orders; // custom list

	    public Customers(int customerId, String name, String email) {
	        this.customerId = customerId;
	        this.name = name;
	        this.email = email;
	        this.orders = new OrderList();
	    }

	    // Getters
	    public int getCustomerId() { return customerId; }
	    public String getName() { return name; }

	    public void addOrder(Orders order) {
	        orders.add(order);
	    }
	}



