import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class User implements Comparable<User>{
	private int id;
	private ArrayList<Rating> ratings = new ArrayList<Rating>();
	private ArrayList<Tag> tags = new ArrayList<Tag>();
	//private ArrayList<Integer> moviesWatched = new ArrayList<Integer>(); //unnecessary?
	private double avgRating;
	private ArrayList<String> favGenres;
	private Double [] avgGenreRatings;
	private double favGenreRating;
	private int favGenre;

	// CONSTRUCTOR
	public User(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	// TOSTRING
	public String toString() {
		String out = "ID: " + id;

		return out;
	}

	public void addRating(Rating r) {
		ratings.add(r);
	}
	public void addTag(Tag t) {
		tags.add(t);
	}
	public boolean hasWatched(int movieID) {



		int thisRating = Collections.binarySearch(ratings, new Rating(movieID, id));

		if (thisRating < 0) {
			return false;
		} else {
			return true;
		}

		//		for (int j = 0; j < ratings.size(); j++) {
		//			if (ratings.get(j).getMovie() == movieID) {
		//				b = true;
		//			}
		//		}


	}

	public ArrayList<Rating> getRatings(){
		return ratings;
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

	public double getRating(int movieID) {
		boolean b = false;
		double rating = -1;
		for (int j = 0; j < ratings.size(); j++) {
			if (ratings.get(j).getMovie() == movieID) {
				b = true;
				rating = ratings.get(j).getRating();
			}

		}

		if (b) {
			return rating;
		} else {
			return -1;
		}

	}

	public void setAvgGenreRatings(Double[] avgs) { //is this necessary
		avgGenreRatings = avgs;
	}
	
	public Double[] getAvgGenreRatings() {
		return avgGenreRatings;
	}
	
	public void findTopGenres() {
		if (avgGenreRatings != null) {
		double max = avgGenreRatings[0];
		int maxSpot = 0;
		for (int i = 0; i < 19; i++) {
			if (avgGenreRatings[i] > max) {
				
				max = avgGenreRatings[i];
				maxSpot = i;
				
			}
		}
		
		favGenre = maxSpot;
		favGenreRating = max;
		}
		favGenre = 0;
		favGenreRating = 3.7;
		
	}

		//Possible genres: 
//* Action
//* Adventure
//* Animation
//* Children's
//* Comedy
//* Crime
//* Documentary
//* Drama
//* Fantasy
//* Film-Noir
//* Horror
//* Musical
//* Mystery
//* Romance
//* Sci-Fi
//* Thriller
//* War
//* Western
//* (no genres listed)

//
//
//		//store arraylist of favorite genres (+avg ratings for the genre) in user
//		//in calculations, take the averages and weight those
//		//weight that number with other stuff
//
//
//		return 0;
//	}
//
	public int favGenreNum() {
		return favGenre;
	}

	public String favGenreTitle() {
		if (favGenre == 0) {
			return "Action";
		} else if (favGenre == 1) {
			return "Adventure";
		} else if (favGenre == 2) {
			return "Animation";
		} else if (favGenre == 3) {
			return "Children's";
		} else if (favGenre == 4) {
			return "Comedy";
		} else if (favGenre == 5) {
			return "Crime";
		} else if (favGenre == 6) {
			return "Documentary";
		} else if (favGenre == 7) {
			return "Drama";
		} else if (favGenre == 8) {
			return "Fantasy";
		} else if (favGenre == 9) {
			return "Film-Noir";
		} else if (favGenre == 10) {
			return "Horror";
		} else if (favGenre == 11) {
			return "Musical";
		}  else if (favGenre == 12) {
			return "Mystery";
		} else if (favGenre == 13) {
			return "Romance";
		} else if (favGenre == 14) {
			return "Sci-Fi";
		} else if (favGenre == 15) {
			return "Thriller";
		}  else if (favGenre == 16) {
			return "War";
		} else if (favGenre == 17) {
			return "Western";
		} else {
			return "(no genres listed)";
		} 
	}

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub;

		return id - o.getID();
		
		
	}

}
