import java.util.ArrayList;
import java.util.Arrays;

public class MovieLensCSVTranslator {


	private ArrayList<String> getLinePieces(String line) {
		ArrayList<String> pieces = new ArrayList<String>();  // Holds comma separated pieces of the line
		boolean quoted = false;  // Keeps track of whether the current character is inside of quotes or not
		int start = 0;
		for (int i = 0; i < line.length(); i++) {  // For each character...
			char thisChar = line.charAt(i);
			if (thisChar == '"') {  // If we see a quote symbol
				quoted = !quoted; // Then we're inside of quotes
			} else if (thisChar == ',' && !quoted) {  // If we see a comma and we're not inside of quotes
				pieces.add(line.substring(start,i));  // Add this chunk of data to the pieces list
				start = i+1;
			}
		}
//		while (pieces.get(1).startsWith("\"")) {
//			if (pieces.get(1).startsWith("\"") && pieces.get(1).endsWith("\"")) {
//				pieces.set(1, pieces.get(1).substring(1,pieces.get(1).length()-1));
//			}
//		}
		pieces.add(line.substring(start));  // Add this chunk of data to the pieces list


		return pieces;
	}


	public Movie parseMovie(String line) {
		ArrayList<String> pieces = getLinePieces(line);  // Get all the sections of the line separated by commas (but not in quotes)

		int id = Integer.parseInt(pieces.get(0));  // ID is the first piece of data

		String title = pieces.get(1);  // title is the first piece of data

		int yearStart = title.lastIndexOf("(");
		int year = 0;
		if (yearStart > -1) {
			year = Integer.parseInt(title.substring(yearStart+1, yearStart+5));  // Extract the year from inside parenthesis
		}
		
		String[] genrePieces = pieces.get(2).split("\\|"); // Split up the genres by looking for |

		Movie m = new Movie(id, year, title, genrePieces);
		return m;
	}


	// HERE WE NEED
	// METHODS FOR TRANSLATING
	// RATINGS
	// TAGS
	// AND LINKS
	
	
	public void parseLink(String line, ArrayList<Movie> movies) {
		int firstComma = line.indexOf(',');
		int movieId = Integer.parseInt(line.substring(0, firstComma));
		
		//What about the zeros in front of the imdbId links?
		int secondComma = line.indexOf(',', firstComma+1);
		String imdbId = line.substring(firstComma+1, secondComma);
		
		String tmdbId = line.substring(secondComma + 1);
		
		for (int i = 0; i < movies.size(); i++) {
			if (movies.get(i).getID() == movieId) {

				movies.get(i).setIMDB(imdbId);
				movies.get(i).setTMDB(tmdbId);

			}
			
		}
		
		
	}
	
	public Rating parseRating(String line) {
		int firstComma = line.indexOf(',');
		int userId = Integer.parseInt(line.substring(0, firstComma));
		
		int secondComma = line.indexOf(',', firstComma+1);
		int movieId = Integer.parseInt(line.substring(firstComma+1, secondComma));
		
		int thirdComma = line.indexOf(',', secondComma + 1);
		double rating = Double.parseDouble(line.substring(secondComma+1, thirdComma));
		
		long timestamp = Long.parseLong(line.substring(thirdComma+1));
		
		Rating r = new Rating(movieId, userId, timestamp, rating);
		return r;
	}
	
	public Tag parseTag(String line) {
		
		int firstComma = line.indexOf(',');
		int userId = Integer.parseInt(line.substring(0, firstComma));

		int secondComma = line.indexOf(',', firstComma+1);
		int movieId = Integer.parseInt(line.substring(firstComma+1, secondComma));
		
//		int thirdComma = 0;
//		if (line.indexOf('"') != -1) {
//			thirdComma = line.lastIndexOf(',')
//		} else {
//			thirdComma = line.indexOf(',', secondComma + 1);
//		}
		
		int thirdComma = line.lastIndexOf(',');
		String tag = line.substring(secondComma+1, thirdComma);
		
		long timestamp = Long.parseLong(line.substring(thirdComma+1));
		
		Tag t = new Tag(movieId, userId, timestamp, tag);
		return t;
		
		
	}
	
	


}
