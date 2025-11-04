package Phase1;

public class CSVReader {
	public class Main {
	    public static void main(String[] args) {
	        ProductList products = new ProductList();
	        CustomerList customers = new CustomerList();

	        // Example data
	        Products p1 = new Products(1, "Laptop", 4000, 5);
	        Products p2 = new Products(2, "Phone", 2500, 0);

	        products.add(p1);
	        products.add(p2);

	        products.displayOutOfStock();

	        Customers c1 = new Customers(101, "Sara", "sara@email.com");
	        customers.add(c1);

	        System.out.println("Search by name: " + customers.searchByName("Sara").getName());
	    }
	}

}
