package phase1;

public class Products {
    private int productId;
    private String name;
    private double price;
    private int stock;
    private ReviewList reviews;

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

    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

    // ✅ Operations

    // Add new review to the product ***************** 
   //method add product-remove 
    public void addReview(Reviews review) {
        reviews.add(review);
    }

    // Get average rating for this product
    public double getAverageRating() {
        return reviews.getAverage();
    }

    // Update product info (price and stock)
    public void updateProduct(double newPrice, int newStock) {
        this.price = newPrice;
        this.stock = newStock;
        System.out.println("Product updated successfully!");
    }

    // Check if out of stock
    public boolean isOutOfStock() {
        return stock <= 0;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name +
               ", Price: " + price + ", Stock: " + stock;
    }
}
