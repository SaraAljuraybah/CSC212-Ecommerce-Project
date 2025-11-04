package Phase1;

public class Reviews {
    private int rating; // from 1 to 5
    private String comment;
    private Customers reviewer;

    public Reviews(int rating, String comment, Customers reviewer) {
        this.rating = rating;
        this.comment = comment;
        this.reviewer = reviewer;
    }

    // ✅ Getters & Setters
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public Customers getReviewer() { return reviewer; }

    public void editReview(String newComment, int newRating) {
        this.comment = newComment;
        this.rating = newRating;
        System.out.println("Review updated successfully!");
    }

    @Override
    public String toString() {
        return "Rating: " + rating + "/5 - Comment: " + comment;
    }
}
