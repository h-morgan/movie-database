package hw3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MovieDatabase {
	//instance variables
	ArrayList<Movie> movieList;
	ArrayList<Actor> actorList;
	
	//constructor
	public MovieDatabase() {
		this.actorList = new ArrayList<>();
		this.movieList = new ArrayList<>();
	}
	
	
	//getters and setters
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}

	public ArrayList<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(ArrayList<Actor> actorList) {
		this.actorList = actorList;
	}
	
	//add movie to movieList
	public void addMovie(String name, String[] actors) {
		//first check if move is in movie database, do nothing if it is already
		Movie newMovie = new Movie(name);
		if (!movieList.contains(newMovie)) {
			movieList.add(newMovie);
			System.out.println("Adding " + name + " to movie database.");
			for (String actorName : actors) {
				Actor newActor = new Actor();
				if (!actorList.contains(newActor)) {
					this.actorList.add(newActor);
					newActor.setName(actorName);
					//System.out.println("Adding new actor " + actorName + " to database.");
				} else {
					//System.out.println("Actor already exists in databse.");
					newActor = actorList.get(actorList.indexOf(newActor));
				}
				
				//update the objects themselves
				newMovie.getActors().add(newActor);
				newActor.getMovies().add(newMovie);
			}
		} else {
			System.out.println("Movie already exists in database.");
		}
	}
	
	//add rating for movie specified
	public void addRating(String name, double rating) {
		// first get the specified movie (name)
		for (int k = 0; k < movieList.size(); k++) {
			String thisName = movieList.get(k).getName();
			if (thisName == name) {
				movieList.get(k).setRating(rating);
			}	
		}
	}
	
	//update rating for movie specified
	public void updateRating(String name, double newRating) {
		// first get the specified movie (name)
		for (int k = 0; k < movieList.size(); k++) {
			String thisName = movieList.get(k).getName();
			if (thisName == name) {
				movieList.get(k).setRating(newRating);
			}	
		}
	}
	
	//get best actor average movie rating
	public String getBestActor() {
		//initialize bestActor and avgRating 
		String bestActor = "";
		double avgRating = 0.0;
		for (int x = 0; x < actorList.size(); x++) {
			double thisAverage = 0.0;
			double numMovies = 0.0;
			double addedRatings = 0.0;
			for (int j = 0; j < actorList.get(x).getMovies().size(); j++) {
				double thisRating = actorList.get(x).getMovies().get(j).getRating();
				addedRatings = addedRatings + thisRating;
				numMovies ++;
			}
			thisAverage = addedRatings / numMovies;
			if (thisAverage > avgRating) {
				avgRating = thisAverage;
				bestActor = actorList.get(x).getName();
			}
		}
		return bestActor;
	}
	
	//get best movie
	public String getBestMovie() {
		//initialize bestMovie and bestRating, to be updated as you loop
		String bestMovie = "";
		double bestRating = 0.0;
		for (int n = 0; n < movieList.size(); n++) {
			double thisRating = movieList.get(n).getRating();
			String thisName = movieList.get(n).getName();
			if (thisRating > bestRating) {
				bestMovie = thisName;
				bestRating = thisRating;
			}
		}
		return bestMovie;
	}



	public static void main(String[] args) {
		// 1. Create a new instance of movieDatabase
		//MovieDatabase haleyDB = new MovieDatabase();
		//String actors[] = new String[] {"Chris Pratt", "Zac Efron", "Zendaya"};
		//String[] actors2 = new String[] {"Miley Cyrus", "Zac Efron", "Zendaya"};
		
		//haleyDB.addMovie("Jurassic Park", actors);
		//haleyDB.addRating("Jurassic Park", 8.7);
		
		//haleyDB.addMovie("High School Musical", actors2);
		//haleyDB.addRating("High School Musical", 9.9);
		
		//System.out.println(haleyDB.getBestMovie());
		//System.out.println(haleyDB.getBestActor());
		
		MovieDatabase movieDB = new MovieDatabase();
		
		//Read in the movies - pass the path to the file as a parameter
		try {
			File file = new File("movies.txt");
			Scanner sc = new Scanner(file);
		
			int h = 0;
			Map<String, List<String>> movies = new HashMap<>();
			while (sc.hasNextLine()) {
				//System.out.println(sc.nextLine());
				String[] actorMovies = sc.nextLine().split(", ");
				//reshape format of data - movie at front of list with actors following
				for (int i = 1; i < actorMovies.length; i++) {
					if (!movies.containsKey(actorMovies[i])) {
						movies.put(actorMovies[i], new ArrayList<String>());
					}
					movies.get(actorMovies[i]).add(actorMovies[0]);
				}
				h++;
				if (h > 20) {
					break;
				}	
			}
			sc.close();
			for (String movie : movies.keySet()) {
				List<String> actors = movies.get(movie);
				movieDB.addMovie(movie, actors.toArray(new String[actors.size()]));
			}
	
		
		} catch (Exception e) {
			System.out.println("Movies file not found");
		}
		
		
		//read in the ratings
		try {
			Scanner sc = new Scanner(new File("ratings.txt"));
			sc.nextLine();
			while(sc.hasNextLine()) {
				String[] ratings = sc.nextLine().split("\t");
				movieDB.addRating(ratings[0], Double.parseDouble(ratings[1]));
			}
			sc.close();
			
		} catch (Exception e) {
			System.out.println("Ratings file not found");
		}

		
		System.out.println("Best movie: " + movieDB.getBestMovie());
		System.out.println("Best actor: " + movieDB.getBestActor());
		
			
		}

	}

