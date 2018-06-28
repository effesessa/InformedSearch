package it.unical.simulatedannealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tour {
	
	private ArrayList<City> cities;
	
	public Tour(ArrayList<City> cities) {
		this.cities = cities;
	}
	
	public Tour() {
		cities = new ArrayList<>();
	}
	
	public void add(City city) {
		cities.add(city);
	}
	
	public int size() {
		return cities.size();
	}
	
	public City get(int i) {
		return cities.get(i);
	}

	@Override
	public String toString() {
		return "Tour " + cities;
	}
	
	public static double distance(City fromCity, City toCity) {
		double x = Math.abs(fromCity.getX() - toCity.getX());
		double y = Math.abs(fromCity.getY() - toCity.getY());
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void shuffle() {
		Collections.shuffle(cities);
	}
	
	@SuppressWarnings("unchecked")
	public Tour clone() {
		return new Tour((ArrayList<City>) cities.clone());
	}
	
	public void neighboar() {
		Random random = new Random();
		int i, j;
		do {
			i = random.nextInt(size());
			j = random.nextInt(size());
		}
		while(i == j);
		City iCity = cities.get(i);
		City jCity = cities.get(j);
		cities.set(i,jCity);
		cities.set(j, iCity);
	}
	
}
