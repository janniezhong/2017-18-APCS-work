import java.util.Arrays;

public class Rating implements Comparable <Rating> {

	private int movie;
	private int user;
	private long timeStamp;
	private double rating;
	
	
	public Rating (int movieId, int userId) {
		movie = movieId;
		user = userId;
	}
	
	public Rating(int movieId, int userId, long timeStamp, double rating) {
		
		movie = movieId;
		user = userId;
		this.timeStamp = timeStamp;
		this.rating = rating;
		
	}
	
	public String toString() {
	
		String out = "MOVIE ID: " + movie;
		out += "\nUSER ID: " + user;
		out += "\nRATING: " + rating;
		out += "\nTIMESTAMP: " + timeStamp;
		
		return out;
		
		
	}
	
	public int getMovie() {
		return movie;
	}
	
	public int getUser() {
		return user;
	}
	
	public double getRating() {
		return rating;
	}

	@Override
	public int compareTo(Rating o) {
		// TODO Auto-generated method stub
		if (movie > o.getMovie()) {
			return 1;
		
		} else if (movie < o.getMovie()) {
			return -1;
		} else {
			
			if (user > o.getUser()) {
				return 1;
			} else if (user < o.getUser()) {
				return -1;
			} else {
				return 0;
			}
			
			
		}
	}
	
	
}
