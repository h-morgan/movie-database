package hw3;

import java.util.ArrayList;

public class Actor {
	//instance variables
	private String name;
	private ArrayList<Movie> movies;
	
	public Actor(String name) {
		this.name = name;
		movies = new ArrayList<>();
	}

}
