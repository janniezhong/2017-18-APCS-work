import java.util.ArrayList;
import java.util.Arrays;

public class Movie implements Comparable<Movie>{

	private int ID;
	private String title;
	private int year;
	private String[] genres;
	private double avgRating;
	private String imdbId;
	private String tmdbId;
	private ArrayList<Tag> tags = new ArrayList<Tag>();
	private ArrayList<Rating> ratings = new ArrayList<Rating>();

	// Add other fields for handling ratings, links, and tags in some way
	
	
	
	// CONSTRUCTOR
	public Movie(int id) {
		ID = id;
	}
	
	public Movie(int id, int year, String title, String[] genres) {
		this.ID = id;
		this.year = year;
		this.title = title;
		this.genres = genres;
	}
	
	public int getID() {
		return ID;
	}
	
	// TOSTRING
	public String toString() {
		String out = "ID: " + ID;
		out += "\nYEAR: " + year;
		out += "\nTITLE: " + title;
		out += "\nGENRES: " + Arrays.toString(genres);
		
		return out;
	}
	
	public void setIMDB(String x) {
		imdbId = x;
	}
	public void setTMDB(String x) {
		tmdbId = x;
	}
	

	public String getIMDB() {
		return imdbId;
	}
	public String getTMDB() {
		return tmdbId;
	}
	
	
	public void addRating(Rating r) {
		ratings.add(r);
	}
	public void addTag(Tag t) {
		tags.add(t);
	}
	public void setAvgRating() {
		double sum = 0;
		
		for(Rating r: ratings) {
			sum += r.getRating();
		}
		
		avgRating = (sum/ratings.size());
		
	}
	
	public double getAvgRating() {
		return avgRating;
	}
	
	public ArrayList<Rating> getRatings(){
		return ratings;
	}

	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
//		if (ID > o.getID()) {
//			return 1;
//		}  else if (ID < o.getID()) {
//			return -1;
//		}  else {
//			return 0;
//		}
		
		return ID - o.getID();
		
		
	}

	public String[] getGenres() {
		return genres;
	}
	public boolean isPartOfGenre(String s) {
		for(String genre :genres) {
			if (genre.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	public String getTitle() {
		return title;
	}
	public double compareMovies() {
		
		
		return 0;
	}


	
}
