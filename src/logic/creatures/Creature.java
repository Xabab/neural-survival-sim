package logic.creatures;

import logic.GameConstants;
import logic.Network;

public class Creature {
    public final Network brain;

    private double fitness;
    //private String name;
    //private String type = "Herbivore";

    public Creature(){
        brain = new Network(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{4, 4},
                new String[]{"Acceleration", "Turning", "Birth"});
        fitness = GameConstants.STARTING_FITNESS;
    }

    public Network getBrain() {
        return brain;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void changeFitness(double change){
        fitness += change;
    }


}
