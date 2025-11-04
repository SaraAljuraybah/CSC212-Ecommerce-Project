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

	    public int getRating() { return rating; }
	    public String getComment() { return comment; }
	    public Customers getReviewer() { return reviewer; }
	}


