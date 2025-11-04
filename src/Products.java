package Phase1;

public class Products {
public int productId ;
public String name ;
public double price;
private int stock;
private ReviewList reviews; // custom list (not ArrayList)




public Products(int productId, String name, double price, int stock) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.stock = stock;
   // this.reviews = new ReviewList();
}
//set get
public int getProductId() {
	return productId; 
	}

public String getName() {
	return name; 
	}

public double getPrice() { 
	return price;
	}

public int getStock() {
	return stock; 
	}

public void setPrice(double price) { 
	this.price = price;
	}

public void setStock(int stock) {
	this.stock = stock; 
	}

public void addReview(Reviews r) {
    reviews.add(r);
}

public double getAverageRating() {
    return reviews.getAverage();
}



}//end class