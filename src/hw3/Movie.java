package hw3;

import java.util.ArrayList;

public class Movie {
	//instance variables
	private String name;
	private ArrayList<Actor> actors;
	private double rating;
	
	//Constructor
	public Movie(String name) {
		this.name = name;
		this.actors = new ArrayList<Actor>();
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	@Override
	public boolean equals(Object m) {
		//type-cast
		Movie mov = (Movie) m;
		if (name == mov.name) {
			return true;
		} else {
			return false;
		}
	}
	

	

}

