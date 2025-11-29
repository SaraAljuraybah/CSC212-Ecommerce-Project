package Phase2;

public class Reviews {

    private int rating;          
    private String comment;
    private Customers reviewer;  
    private Products product;    

 
    public Reviews(int rating, String comment, Customers reviewer, Products product) {
        this.rating = rating;
        this.comment = comment;
        this.reviewer = reviewer;
        this.product = product;
    }

  
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public Customers getReviewer() { return reviewer; }
    public Products getProduct() { return product; }

  
    public void editReview(String newComment, int newRating) {
        this.comment = newComment;
        this.rating = newRating;
        System.out.println("Review updated successfully!");
    }

  
    @Override
    public String toString() {
        return "⭐ Rating: " + rating +
         " | Product: " + product.getName() +
                    " | By: " + reviewer.getName() +
        " | Comment: " + comment;
    }
}
