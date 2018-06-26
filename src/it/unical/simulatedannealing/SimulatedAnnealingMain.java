package it.unical.simulatedannealing;

import java.util.Random;

public class SimulatedAnnealingMain {
	
	public static double THRESHOLD = 0.001;
	
	public static void main(String[] args) {
		Tour bestTour = new Tour();
		bestTour.add(new City("Milano", 180, 90));
		bestTour.add(new City("Firenze", 160, 110));
		bestTour.add(new City("Roma", 110, 98));
		bestTour.add(new City("Bologna", 150, 114));
		bestTour.add(new City("Crotone", 30, 87));
		bestTour.add(new City("Cosenza", 25, 99));
		bestTour.add(new City("Palermo", 11, 30));
		algorithm(bestTour);
	}
	
	public static void algorithm(Tour bestTour) {
		double temperature = 1.0;
		double alpha = 0.9;
		int iterations = 0;
		bestTour.shuffle();
		double bestTotalDistance = getTotalDistance(bestTour);
		double newTotalDistance;
		while(temperature > THRESHOLD) {
			Tour newTour = bestTour.clone();
			newTour.neighboar();
			newTotalDistance = getTotalDistance(newTour);
			if(newTotalDistance < bestTotalDistance || evaluateProbability(bestTotalDistance, newTotalDistance, temperature)) {
				bestTotalDistance = newTotalDistance;
				bestTour = newTour;
			}
			temperature*=alpha;
			iterations++;
		}
		System.out.println(bestTour.toString());
		System.out.println("Distance [" + bestTotalDistance + "]");
		System.out.println("iterations [" + iterations + "]");
	}
	
	public static boolean evaluateProbability(double bestTotalDistance, double newTotalDistance , double temperature) {
		return (Math.exp((bestTotalDistance - newTotalDistance) / temperature) > (double) new Random().nextInt(1000) / 1000);
	}
	
	public static double getTotalDistance(Tour tour) {
		double totalDistance = 0.0;
		for (int i = 0; i < tour.size(); i++) {
			if(i+1 < tour.size())
				totalDistance+=Tour.distance(tour.get(i), tour.get(i+1));
			else
				totalDistance+=Tour.distance(tour.get(i), tour.get(0));
		}
		return totalDistance;
	}
	
}
