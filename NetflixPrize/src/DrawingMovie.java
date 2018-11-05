

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingMovie {

	private Movie movie;
	private PImage coverArt;
	
	public DrawingMovie(Movie m) {
		this.movie = m;
		coverArt = null;
	}
	
	public void draw(PApplet drawer, float x, float y, float width, float height) {
		if (movie != null) {
			if (coverArt != null) {
				drawer.image(coverArt, x, y,width,height);
			}
			
			drawer.text(movie.getTitle(), x, y);
		}
		drawer.stroke(0);
		drawer.noFill();
		drawer.rect(x, y, width, height);
	}
	

	public void downloadArt(PApplet drawer) {
		
		Thread downloader = new Thread(new Runnable() {

			@Override
			public void run() {
				
				
				Scanner scan = null;
				try {
				// Find the cover art using IMDB links
				// Initialize coverArt
				
				String imdb = movie.getIMDB();
				String pageURLString = "https://imdb.com/title/tt"+imdb + "/";
				
				URL pageURL = new URL(pageURLString);
				InputStream is = pageURL.openStream();
				scan = new Scanner(is);
				
				String fileData = "";
				
				while(scan.hasNextLine()) {
					
					String line = scan.nextLine();
					fileData += line + FileIO.lineSeparator;		
					
				}
				
				String imageURL = fileData.substring(fileData.indexOf("title-overview-widget"));
				imageURL = imageURL.substring(imageURL.indexOf("https://ia.media-imdb.com"));
				int endQuote = imageURL.indexOf('\"');
				imageURL = imageURL.substring(0, endQuote);
				//System.out.print(fileData + "\n ****************************************************************************************************************************");


				
				coverArt = drawer.loadImage(imageURL);
				
				} catch(IOException e) {
					e.printStackTrace();
				} finally {
					if (scan != null) {
					scan.close();
				}
				}
			}
			
		});
		
		downloader.start();

	}

	
}
