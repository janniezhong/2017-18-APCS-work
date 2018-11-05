import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {

		// Testing movies
		String movieFilePath = "ml-small-dataset" + FileIO.fileSeparator + "movies.csv";

		ArrayList<String> movieStringData = FileIO.readFile(movieFilePath);

		ArrayList<Movie> movieData = new ArrayList<Movie>();
		MovieLensCSVTranslator translator = new MovieLensCSVTranslator();
		for (int i = 1; i < movieStringData.size(); i++) {
			Movie m = translator.parseMovie(movieStringData.get(i));
			movieData.add(m);
		}

		for (Movie m : movieData) {
			//System.out.println(m);
		}


		//reading links

		String linksFilePath = "ml-small-dataset" + FileIO.fileSeparator + "links.csv";

		ArrayList<String> linkStringData = FileIO.readFile(linksFilePath);

		//MovieLensCSVTranslator translator2 = new MovieLensCSVTranslator();
		for (int i = 1; i < linkStringData.size(); i++) {
			translator.parseLink(linkStringData.get(i), movieData);
		}


		//reading ratings
		String ratingsFilePath = "ml-small-dataset" + FileIO.fileSeparator + "ratings.csv";

		ArrayList<String> ratingStringData = FileIO.readFile(ratingsFilePath);

		ArrayList<Rating> ratingData = new ArrayList<Rating>();
		//MovieLensCSVTranslator translator3 = new MovieLensCSVTranslator();
		for (int i = 1; i < ratingStringData.size(); i++) {
			Rating r = translator.parseRating(ratingStringData.get(i));
			ratingData.add(r);
		}

		for (Rating r : ratingData) {
			System.out.println(r);
		}

		//reading tags

		String tagsFilePath = "ml-small-dataset" + FileIO.fileSeparator + "tags.csv";

		ArrayList<String> tagStringData = FileIO.readFile(tagsFilePath);

		ArrayList<Tag> tagData = new ArrayList<Tag>();
		//MovieLensCSVTranslator translator4 = new MovieLensCSVTranslator();
		for (int i = 1; i < tagStringData.size(); i++) {
			Tag t = translator.parseTag(tagStringData.get(i));
			tagData.add(t);
		}

		for (Tag t : tagData) {
			//System.out.println(t);
		}
		
		//sorting stuff out but idk what stuff help
		
		
		//USER DATA
		ArrayList<User> userData = new ArrayList<User>();
//		
		for (int i = 0; i < ratingData.size();i++) {
			
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
		
//		for (int i = 0; i < userData.get(0).getRatings().size(); i++) {
//			System.out.println(userData.get(0).getRatings().get(i));
//			System.out.println("");
//		}
//		
		
//		//putting movies into user
//		
//		ArrayList<Integer> alreadyWatched = new ArrayList<Integer>();
//		
//		for (int i = 0; i < tagData.size(); i++) {
//			for (int j = 0; j < userData.size(); j++) {
//				
//				boolean b = false;
//				for (int k = 0; k < alreadyWatched.size(); k++){
//					if (alreadyWatched.get(k) == tagData.get(i).getMovie()) {
//						b = true;
//					}
//				}
//				
//				if (tagData.get(i).getUser() == userData.get(j).getID() && b) {
//					alreadyWatched.add(tagData.get(i).getMovie());
//
//				}
//				
//			}
//		}
//		
//		for (int i = 0; i < ratingData.size(); i++) {
//			for (int j = 0; j < userData.size(); j++) {
//				
//				boolean b = false;
//				for (int k = 0; k < alreadyWatched.size(); k++){
//					if (alreadyWatched.get(k) == ratingData.get(i).getMovie()) {
//						b = true;
//					}
//				}
//				
//				if (ratingData.get(i).getUser() == userData.get(j).getID() && b) {
//					alreadyWatched.add(ratingData.get(i).getMovie());
//
//				}
//				
//			}
//		}
		
	
		
		





	}


}

