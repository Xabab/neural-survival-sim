package logic.creatures;

import Jama.Matrix;
import logic.GameConstants;
import logic.Network;
import logic.creatures.Creatures;

public class Creature {
    public final Network brain;
    Creatures list;

    private double fitness;

    private Matrix xy= new Matrix(1, 2, 0);
    private Matrix speed = new Matrix(1, 2, 0);
    private Matrix direction = new Matrix(1, 2, 0);

    //private String name;
    //private String type = "Herbivore";

    public Creature(Creatures list){
        brain = new Network(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{4, 4},
                new String[]{"Acceleration", "Turning", "Birth"});
        fitness = GameConstants.STARTING_FITNESS;
        this.list = list;
    }

    public Creature(Creature c, boolean child, Creatures list){
        brain = new Network(c.brain);
        if(child) {
            brain.mutate();
            fitness = GameConstants.STARTING_FITNESS;
        }

        fitness = GameConstants.STARTING_FITNESS;
        this.list = list;
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

    public Matrix getXy() {
        return xy;
    }

    public Matrix getSpeed() {
        return speed;
    }

    public Matrix getDirection() {
        return direction;
    }

    private void giveBirth(){
        list.getBirth(this);
    }

}
