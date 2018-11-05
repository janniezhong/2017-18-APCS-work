
public class Tag {

	private int movie;
	private int user;
	private long timeStamp;
	private String tag;

	public Tag(int movieId, int userId, long timeStamp, String tag) {

		movie = movieId;
		user = userId;
		this.timeStamp = timeStamp;
		this.tag = tag;

	}
	
	public String toString() {
		
		String out = "MOVIE ID: " + movie;
		out += "\nUSER ID: " + user;
		out += "\nTAG: " + tag;
		out += "\nTIMESTAMP: " + timeStamp;
		
		return out;
		
		
	}


	public int getMovie() {
		return movie;
	}
	
	public int getUser() {
		return user;
	}
	


}
