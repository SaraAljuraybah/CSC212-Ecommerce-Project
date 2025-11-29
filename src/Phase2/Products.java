package Phase2;

public class Products {

    private int productId;
    private String name;
    private double price;
    private int stock;
    private ReviewList reviews;   // list of reviews for this product

 
    public Products(int productId, String name, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reviews = new ReviewList();
    }

   
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public ReviewList getReviews() { return reviews; }


    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

   
    public void addReview(Reviews review) {
        reviews.add(review);
    }


    public double getAverageRating() {
        return reviews.getAverage();
    }

 
    public void updateProduct(double newPrice, int newStock) {
        this.price = newPrice;
        this.stock = newStock;
        System.out.println("Product updated successfully!");
    }

 
    public boolean hasReviewByCustomer(int customerId) {
        return reviews != null && reviews.hasReviewByCustomer(customerId);
    }


    public boolean isOutOfStock() {
        return stock <= 0;
    }

   
    @Override
    public String toString() {
        return "Product ID: " + productId +
                    " | Name: " + name +
          " | Price: " + price +
              " | Stock: " + stock +
                   " | Avg Rating: " + getAverageRating();
    }
}
