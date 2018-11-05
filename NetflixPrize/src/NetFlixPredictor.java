import java.awt.image.PackedColorModel;
import java.util.ArrayList;
import java.util.Collections;


public class NetFlixPredictor {


	// Add fields to represent your database.

	private ArrayList<Movie> movieData;
	private ArrayList<User> userData;
	private ArrayList<Rating> ratingData;
	private ArrayList<Tag> tagData;
	private double allMovieAvg;




	/**
	 * 
	 * Use the file names to read all data into some local structures. 
	 * 
	 * @param movieFilePath The full path to the movies database.
	 * @param ratingFilePath The full path to the ratings database.
	 * @param tagFilePath The full path to the tags database.
	 * @param linkFilePath The full path to the links database.
	 */
	public NetFlixPredictor(String movieFilePath, String ratingFilePath, String tagFilePath, String linkFilePath) {
		//Should prob make methods for each of these to make my life easier


		//MOVIE DATA
		ArrayList<String> movieStringData = FileIO.readFile(movieFilePath);

		movieData = new ArrayList<Movie>();
		MovieLensCSVTranslator translator = new MovieLensCSVTranslator();
		for (int i = 1; i < movieStringData.size(); i++) {
			Movie m = translator.parseMovie(movieStringData.get(i));
			movieData.add(m);
		}

		Collections.sort(movieData);

		//LINK DATA
		ArrayList<String> linkStringData = FileIO.readFile(linkFilePath);

		for (int i = 1; i < linkStringData.size(); i++) {
			translator.parseLink(linkStringData.get(i), movieData);
		}

		//RATING DATA
		ArrayList<String> ratingStringData = FileIO.readFile(ratingFilePath);

		ratingData = new ArrayList<Rating>();
		for (int i = 1; i < ratingStringData.size(); i++) {
			Rating r = translator.parseRating(ratingStringData.get(i));
			ratingData.add(r);
		}
		Collections.sort(ratingData);
		//TAG DATA
		ArrayList<String> tagStringData = FileIO.readFile(tagFilePath);

		tagData = new ArrayList<Tag>();
		for (int i = 1; i < tagStringData.size(); i++) {
			Tag t = translator.parseTag(tagStringData.get(i));
			tagData.add(t);
		}


		//USER DATA
		userData = new ArrayList<User>();
		for (int i = 0; i < ratingData.size(); i++) {

			//cycle through the ratings
			//look at user ids for each
			//if the user id doesn't match any of the existing user ids, add it to the array of users

			boolean exists = false;
			for (int j = 0; j < userData.size(); j++) { // wait how does this work if there's nothing inside yet?
				if (ratingData.get(i).getUser() == userData.get(j).getID()) {
					exists = true;
				}
			}

			if (!exists) {
				userData.add(new User(ratingData.get(i).getUser()));
			}


		}
		Collections.sort(userData);

		//CLEAN UP
		//putting ratings into movies and users

		for (int i = 0; i < ratingData.size(); i++) {

			for (int j = 0; j < movieData.size(); j++) {
				if (movieData.get(j).getID() == ratingData.get(i).getMovie()) {
					movieData.get(j).addRating(ratingData.get(i));
				}
			}

			for (int j = 0; j < userData.size(); j++) {
				if (userData.get(j).getID() == ratingData.get(i).getUser()) {
					userData.get(j).addRating(ratingData.get(i));
				}
			}
		}
		//		
		//		for (int i = 0; i < users.get(0).getRatings().size(); i++) {
		//			System.out.println(users.get(0).getRatings().get(i));
		//			System.out.println("");
		//		}

		//putting tags into movies and users

		for (int i = 0; i < tagData.size(); i++) {

			for (int j = 0; j < movieData.size(); j++) {
				if (movieData.get(j).getID() == tagData.get(i).getMovie()) {
					movieData.get(j).addTag(tagData.get(i));
				}
			}

			for (int j = 0; j < userData.size(); j++) {
				if (userData.get(j).getID() == tagData.get(i).getUser()) {
					userData.get(j).addTag(tagData.get(i));
				}
			}



		}

		//		for (int i = 0; i < movies.get(0).getRatings().size(); i++) {
		//			System.out.println(movies.get(0).getRatings().get(i));
		//			System.out.println("");
		//		}

		for (User u: userData) {
			if (u.getRatings() != null) {
				u.setAvgRating();
		
				u.findTopGenres();
			
			}

		}

		for (Movie m: movieData) {
			if (m.getRatings() != null) {
				m.setAvgRating();
				//System.out.println(m.getAvgRating());
			}
		}

		findAvgGenreRatings();

		
		allMovieAvg = 0;
		int allMovieCount = 0;
		for (Movie m: movieData) {
			
			Double d = new Double(m.getAvgRating());
			
			
			if (!d.isNaN()){
				allMovieAvg += m.getAvgRating();
				allMovieCount++;
				//System.out.println(allMovieAvg + "    " + m2.getAvgRating() + "     " + allMovieCount);
			}
			
		}
		
		//System.out.println(allMovieAvg + "    " + allMovieCount);

		
		if (allMovieCount != 0) {
		    allMovieAvg = allMovieAvg/allMovieCount; 
		}


	}

	/**
	 * If userNumber has rated movieNumber, return the rating. Otherwise, return -1.
	 * 
	 * @param userNumber The ID of the user.
	 * @param movieNumber The ID of the movie.
	 * @return The rating that userNumber gave movieNumber, or -1 if the user does not exist in the database, the movie does not exist, or the movie has not been rated by this user.
	 */
	public double getRating(int userID, int movieID) {

		int thisMovie = Collections.binarySearch(movieData, new Movie(movieID));

		if (thisMovie < 0) {
			return -1;		
		}

		int thisPerson = Collections.binarySearch(userData, new User(userID));

		if (thisPerson < 0) {
			return -1;
		}

		int thisRating = Collections.binarySearch(ratingData, new Rating(movieID, userID));

		if (thisRating < 0) {
			return -1;
		}

		System.out.print(ratingData.get(thisRating).getRating());
		return ratingData.get(thisRating).getRating();



	}

	/**
	 * If userNumber has rated movieNumber, return the rating. Otherwise, use other available data to guess what this user would rate the movie.
	 * 
	 * @param userNumber The ID of the user.
	 * @param movieNumber The ID of the movie.
	 * @return The rating that userNumber gave movieNumber, or the best guess if the movie has not been rated by this user.
	 * @pre A user with id userID and a movie with id movieID exist in the database.
	 */
	public double guessRating(int userID, int movieID) {
		
		int thisMovie = Collections.binarySearch(movieData, new Movie(movieID));

		if (thisMovie < 0) {
			return -1;		
		}

		int thisPerson = Collections.binarySearch(userData, new User(userID));

		if (thisPerson < 0) {
			return -1;
		}

		int thisRating = Collections.binarySearch(ratingData, new Rating(movieID, userID));

		if (thisRating >= 0) {
			return ratingData.get(thisRating).getRating();
		}

		User person = userData.get(thisPerson);

		Movie m = movieData.get(thisMovie);
		
		double allDifference = 0;
		double diffCount = 0;
		for (Rating r: person.getRatings()) {
			
			int movie = Collections.binarySearch(movieData, new Movie(r.getMovie()));
			
			
			Double difference = new Double (r.getRating() - movieData.get(movie).getAvgRating());
			if (!difference.isNaN()) {
				allDifference+=difference;
				diffCount++;
			}
			
			
		}
		
		allDifference = allDifference/diffCount;
		

		double avgGenreRatingSum = 0;
		int genreCount = 0;

		if (m.isPartOfGenre("Action") && person.getAvgGenreRatings()[0] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[0];
			genreCount++;

		}
		if (m.isPartOfGenre("Adventure") && person.getAvgGenreRatings()[1] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[1];
			genreCount++;

		}
		if (m.isPartOfGenre("Animation") && person.getAvgGenreRatings()[2] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[2];
			genreCount++;

		}
		if (m.isPartOfGenre("Children's") && person.getAvgGenreRatings()[3] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[3];
			genreCount++;

		}
		if (m.isPartOfGenre("Comedy") && person.getAvgGenreRatings()[4] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[4];
			genreCount++;

		}
		if (m.isPartOfGenre("Crime") && person.getAvgGenreRatings()[5] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[5];
			genreCount++;

		}
		if (m.isPartOfGenre("Documentary") && person.getAvgGenreRatings()[6] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[6];
			genreCount++;

		}
		if (m.isPartOfGenre("Drama") && person.getAvgGenreRatings()[7] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[7];
			genreCount++;

		}
		if (m.isPartOfGenre("Fantasy") && person.getAvgGenreRatings()[8] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[8];
			genreCount++;

		}
		if (m.isPartOfGenre("Film-Noir") && person.getAvgGenreRatings()[9] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[9];
			genreCount++;

		}
		if (m.isPartOfGenre("Horror") && person.getAvgGenreRatings()[10] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[10];
			genreCount++;

		}
		if (m.isPartOfGenre("Musical") && person.getAvgGenreRatings()[11] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[11];
			genreCount++;

		}
		if (m.isPartOfGenre("Mystery") && person.getAvgGenreRatings()[12] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[12];
			genreCount++;

		}
		if (m.isPartOfGenre("Romance") && person.getAvgGenreRatings()[13] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[13];
			genreCount++;
		}
		if (m.isPartOfGenre("Sci-Fi") && person.getAvgGenreRatings()[14] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[14];
			genreCount++;
		}
		if (m.isPartOfGenre("Thriller") && person.getAvgGenreRatings()[15] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[15];
			genreCount++;
		}
		if (m.isPartOfGenre("War") && person.getAvgGenreRatings()[16] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[16];
			genreCount++;
		}
		if (m.isPartOfGenre("Western") && person.getAvgGenreRatings()[17] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[17];
			genreCount++;
		}
		if (m.isPartOfGenre("(no genres listed)") && person.getAvgGenreRatings()[18] >=0) {
			avgGenreRatingSum += person.getAvgGenreRatings()[18];
			genreCount++;
		}

		double avgGenreRating = 0;

		if (genreCount !=0) {
			avgGenreRating = avgGenreRatingSum/genreCount;
		}
	
		
		
		
		

		
		double movieAvg = m.getAvgRating();
		double personAvg = person.getAvgRating();
		
		
		
		if (m.getRatings().size() == 0) {
			movieAvg = allMovieAvg;
		}
		if (person.getRatings().size() == 0) {
			personAvg = allMovieAvg;
		}
		
		if (avgGenreRating == 0) {
			avgGenreRating = allMovieAvg;
		}
		
		//return movieAvg*0.4 + personAvg*0.5 + avgGenreRating * 0.1;
		
		return (movieAvg + allDifference)*0.6 + personAvg * 0.3 + avgGenreRating * 0.1;
		
//		if (person.getRatings().size() == 0) {
//
//			if (m.getRatings().size() == 0) {
//				return 3;
//			} else {
//				return m.getAvgRating();
//			}
//
//		} else {
//			if (m.getRatings().size() == 0) {
//				if (avgGenreRating != 0) {
//					return person.getAvgRating()*0.8 + avgGenreRating *0.2;
//
//				}
//				return person.getAvgRating();
//			} else {
//
//				if (avgGenreRating !=0) {
//					return person.getAvgRating()*0.44 +m.getAvgRating()*0.44 + avgGenreRating*0.12;
//
//				}
//
//				return person.getAvgRating()*0.35 + m.getAvgRating()*0.65;
//			}
//		}

	}

	/**
	 * Recommend a movie that you think this user would enjoy (but they have not currently rated it). 
	 * 
	 * @param userNumber The ID of the user.
	 * @return The ID of a movie that data suggests this user would rate highly (but they haven't rated it currently).
	 * @pre A user with id userID exists in the database.
	 */
	public int recommendMovie(int userID) {

		int thisPerson = Collections.binarySearch(userData, new User(userID));

		if (thisPerson < 0) {
			return -1;
		}

		User person = userData.get(thisPerson);
		String favGenre = person.favGenreTitle();
		
		ArrayList<Integer> goodMovies = new ArrayList<Integer>();
		
		for (int i = 0; i < movieData.size(); i++) {
			if (person.hasWatched(i) && movieData.get(i).isPartOfGenre(favGenre)) {
				goodMovies.add(i);
		
			}
		}
		
		if (!goodMovies.isEmpty()) {
			int randomMovie = goodMovies.get((int)(Math.random()*goodMovies.size()));
	
			int movieID = movieData.get(randomMovie).getID();
		
			return movieID;
		
		} else {
			return movieData.get((int)(Math.random()*movieData.size())).getID();
		}
		
	}

	public ArrayList<Movie> getMovies(){
		return movieData;
	}

	private void findAvgGenreRatings() { //needs access to allllll of the movies, should be private in NetflixPredictor

		for (User u: userData) {

			double actionAvg = 0;
			double adventureAvg = 0;
			double animationAvg = 0;
			double childrensAvg = 0;
			double comedyAvg = 0;
			double crimeAvg = 0;
			double documentaryAvg = 0;
			double dramaAvg = 0;
			double fantasyAvg = 0;
			double filmNoirAvg = 0;
			double horrorAvg = 0;
			double musicalAvg = 0;
			double mysteryAvg = 0;
			double romanceAvg = 0;
			double sciFiAvg = 0;
			double thrillerAvg = 0;
			double warAvg = 0;
			double westernAvg = 0;
			double noGenreAvg = 0;


			int actionAvgCount = 0;
			int adventureAvgCount = 0;
			int animationAvgCount = 0;
			int childrensAvgCount = 0;
			int comedyAvgCount = 0;
			int crimeAvgCount = 0;
			int documentaryAvgCount = 0;
			int dramaAvgCount = 0;
			int fantasyAvgCount = 0;
			int filmNoirAvgCount = 0;
			int horrorAvgCount = 0;
			int musicalAvgCount = 0;
			int mysteryAvgCount = 0;
			int romanceAvgCount = 0;
			int sciFiAvgCount = 0;
			int thrillerAvgCount = 0;
			int warAvgCount = 0;
			int westernAvgCount = 0;
			int noGenreAvgCount = 0;



			User thisPerson = u;
			for (Rating r: thisPerson.getRatings()) {

				int thisMovieLoc = Collections.binarySearch(movieData, new Movie(r.getMovie()));

				if (thisMovieLoc >= 0) {

					Movie thisMovie = movieData.get(thisMovieLoc);

					if (thisMovie.isPartOfGenre("Action")) {
						actionAvg += r.getRating();
						actionAvgCount++;
					}
					if (thisMovie.isPartOfGenre("Adventure")) {
						adventureAvg += r.getRating();
						adventureAvgCount++;

					}
					if (thisMovie.isPartOfGenre("Animation")) {
						animationAvg += r.getRating();
						animationAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Children's")) {
						childrensAvg += r.getRating();
						childrensAvgCount++;

					}
					if (thisMovie.isPartOfGenre("Comedy")) {
						comedyAvg += r.getRating();
						comedyAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Crime")) {
						crimeAvg += r.getRating();
						crimeAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Documentary")) {
						documentaryAvg += r.getRating();
						documentaryAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Drama")) {
						dramaAvg += r.getRating();
						dramaAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Fantasy")) {
						fantasyAvg += r.getRating();
						fantasyAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Film-Noir")) {
						filmNoirAvg += r.getRating();
						filmNoirAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Horror")) {
						horrorAvg += r.getRating();
						horrorAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Musical")) {
						musicalAvg += r.getRating();
						musicalAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Mystery")) {
						mysteryAvg += r.getRating();
						mysteryAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Romance")) {
						romanceAvg += r.getRating();
						romanceAvgCount++;

					}
					if (thisMovie.isPartOfGenre("Sci-Fi")) {
						sciFiAvg += r.getRating();
						sciFiAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Thriller")) {
						thrillerAvg += r.getRating();
						thrillerAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("War")) {
						warAvg += r.getRating();
						warAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("Western")) {
						westernAvg += r.getRating();
						westernAvgCount++;

					} 
					if (thisMovie.isPartOfGenre("(no genres listed)")){
						noGenreAvg += r.getRating();
						noGenreAvgCount++;

					}
				}

			}
			if (actionAvgCount != 0) {
				actionAvg /= actionAvgCount;
				actionAvg /= actionAvgCount;

			} else {
				actionAvg = 3.7;
			}

			if (adventureAvgCount != 0) {
				adventureAvg /= adventureAvgCount;		
			} else {
				adventureAvg = 3.7;
			}

			if (animationAvgCount != 0) {
				animationAvg /= animationAvgCount;
			} else {
				animationAvg = 3.7;
			}

			if (childrensAvgCount != 0) {
				childrensAvg /= childrensAvgCount;
			} else {
				childrensAvg = 3.7;
			}

			if (comedyAvgCount != 0) {
				comedyAvg /= comedyAvgCount;
			} else {
				comedyAvg = 3.7;
			}

			if (crimeAvgCount != 0) {
				crimeAvg /= crimeAvgCount;
			} else {
				crimeAvg = 3.7;
			}

			if (documentaryAvgCount != 0) {
				documentaryAvg /= documentaryAvgCount;
			} else {
				documentaryAvg = 3.7;
			}

			if (dramaAvgCount != 0) {
				dramaAvg /= dramaAvgCount;
			} else {
				dramaAvg = 3.7;
			}
			if (fantasyAvgCount != 0) {
				fantasyAvg /= fantasyAvgCount;
			} else {
				fantasyAvg = 3.7;
			}
			if (filmNoirAvgCount != 0) {
				filmNoirAvg /= filmNoirAvgCount;
			} else {
				filmNoirAvg = 3.7;
			}

			if (horrorAvgCount != 0) {
				horrorAvg /= horrorAvgCount;
			} else {
				horrorAvg = 3.7;
			}	if (musicalAvgCount != 0) {
				musicalAvg /= musicalAvgCount;
			} else {
				musicalAvg = 3.7;
			}

			if (mysteryAvgCount != 0) {
				mysteryAvg /= mysteryAvgCount;
			} else {
				mysteryAvg = 3.7;
			}	if (romanceAvgCount != 0) {
				romanceAvg /= romanceAvgCount;
			} else {
				romanceAvg = 3.7;
			}

			if (sciFiAvgCount != 0) {
				sciFiAvg /= sciFiAvgCount;
			} else {
				sciFiAvg = 3.7;
			}	
			if (thrillerAvgCount != 0) {
				thrillerAvg /= thrillerAvgCount;
			} else {
				thrillerAvg = 3.7;
			}
			if (warAvgCount != 0) {
				warAvg /= warAvgCount;
			} else {
				warAvg = 3.7;
			}	
			if (westernAvgCount != 0) {
				westernAvg /= westernAvgCount;
			} else {
				westernAvg = 3.7;
			}

			if (noGenreAvgCount != 0) {
				noGenreAvg /= noGenreAvgCount;
			} else {
				noGenreAvg = 3.7;
			}

			Double[] genreAvgs = {actionAvg, adventureAvg, animationAvg, childrensAvg, comedyAvg, crimeAvg, documentaryAvg, dramaAvg, fantasyAvg, filmNoirAvg, horrorAvg, musicalAvg, mysteryAvg, romanceAvg, sciFiAvg, thrillerAvg, warAvg, westernAvg, noGenreAvg};

			thisPerson.setAvgGenreRatings(genreAvgs);

		}
	}

}
