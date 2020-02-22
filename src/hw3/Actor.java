package hw3;

import java.util.ArrayList;

public class Actor {
	//instance variables
	private String name;
	private ArrayList<Movie> movies;
	
	//constructor
	public Actor() {
		this.name = "";
		this.movies = new ArrayList<Movie>();
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	@Override
	public boolean equals(Object a) {
		Actor act = (Actor) a;
		if (name == act.name) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
