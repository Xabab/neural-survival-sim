package logic.objects;

import Jama.Matrix;
import logic.GameConstants;
import logic.Network;

import static java.lang.Math.*;
import static logic.GameConstants.ACCELERATION;

public class Creature {
    public final Network brain;



    private boolean readyToBirth = false;

    private double fitness;

    private Matrix xy= new Matrix(1, 2, 0);
    private Matrix speed = new Matrix(1, 2, 0);
    private double direction = 0;

    //private String name;
    //private String type = "Herbivore";

    public Creature(){
        brain = new Network(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{4, 4},
                new String[]{"Accelerate", "Turn", "Birth"});
        fitness = GameConstants.STARTING_FITNESS;
    }

    public Creature(double x, double y){
        brain = new Network(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{4, 4},
                new String[]{"Accelerate", "Turn", "Birth"});
        fitness = GameConstants.STARTING_FITNESS;
        xy.set(0, 0, x);
        xy.set(0, 1, y);
    }

    public Creature(Creature c, boolean child){
        brain = new Network(c.brain);

        if(child) {
            brain.mutate();
            fitness = GameConstants.STARTING_FITNESS;
        }
        else fitness = c.fitness;
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

    public Matrix getXY() {
        return xy;
    }

    public Matrix getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    public Creature giveBirth(){
        readyToBirth = false;
        fitness -= GameConstants.BIRTH_FITNESS_COST;

        return new Creature(this, true);
    }

    public void updateInputs(double foodDist, double foodDirection) {
        brain.updateInputs(new double[]{foodDist, foodDirection, fitness});
    }

    private void updateBrain(){
        brain.calculate();
    }

    private void updateMoving(){
        direction += GameConstants.CREATURE_TURNING_SPEED*brain.getNeuronLayers()[3].get(0,1);

        while(direction > 2*PI) direction -= 2*PI;
        while(direction < 0) direction += 2*PI;

        double x = speed.get(0, 0) * (1 - GameConstants.SURFACE_ROUGHNESS)
                + GameConstants.ACCELERATION*brain.getNeuronLayers()[3].get(0,0)*cos(direction);
        double y = speed.get(0, 1) * (1 - GameConstants.SURFACE_ROUGHNESS)
                + GameConstants.ACCELERATION*brain.getNeuronLayers()[3].get(0,0)*sin(direction);
        double speed = sqrt(x*x + y*y);

        if(speed > GameConstants.CREATURE_SPEED) {
            x *= GameConstants.CREATURE_SPEED/speed;
            y *= GameConstants.CREATURE_SPEED/speed;
        }

        this.speed.set(0, 0, x);
        this.speed.set(0, 1, y);
    }

    private void move(){
        xy.plusEquals(speed);
    }

    public void updateCreature(){
        updateBrain();
        updateMoving();
        move();
    }

    public boolean isReadyToBirth() {
        return readyToBirth;
    }

    //public abstract void interactCreature(Creature c){}
}
