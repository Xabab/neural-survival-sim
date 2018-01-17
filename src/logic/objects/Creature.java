package logic.objects;

import Jama.Matrix;
import logic.GameConstants;
import logic.Network;

import static java.lang.Math.*;
import static logic.GameConstants.ACCELERATION;
import static logic.GameConstants.BRAIN_INIT_RANGE;

public class Creature {
    public final Network brain = new Network(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{4, 4},
            new String[]{"Accelerate", "Turn", "Birth"});;

    private boolean readyToBirth = false;

    private double fitness;

    private Matrix xy= new Matrix(1, 2, 0);
    private Matrix speed = new Matrix(1, 2, 0);
    private double direction;

    //private String name;
    //private String type = "Herbivore";

    public Creature(){
        fitness = GameConstants.STARTING_FITNESS;
        direction = ((Math.random()*2)-1)*Math.PI*2;
    }

    public Creature(double x, double y){
        fitness = GameConstants.STARTING_FITNESS;
        xy.set(0, 0, x);
        xy.set(0, 1, y);
        direction = ((Math.random()*2)-1)*Math.PI*2;
    }

    public Creature(Creature c, boolean child){
        xy = c.xy.copy();

        if(child) {
            brain.mutate();
            fitness = GameConstants.STARTING_FITNESS;
            direction = ((Math.random()*2)-1)*Math.PI*2;
        }
        else {
            fitness = c.fitness;
            direction = c.direction;
            brain.initRandom(-BRAIN_INIT_RANGE, BRAIN_INIT_RANGE);
        }
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

    public void updateInputs(double [] foodDistDirection) {
        brain.updateInputs(new double[]{foodDistDirection[0], foodDistDirection[1], fitness});
    }

    private void updateBrain(){
        brain.calculate();
    }

    private void updateMoving(){
        direction += GameConstants.CREATURE_TURNING_SPEED*brain.getNeuronLayers()[3].get(0,1);

        while(direction > PI) direction -= 2*PI;
        while(direction < PI) direction += 2*PI;

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

        xy.plusEquals(this.speed);
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

    public void feed(double f){
        fitness += f;
    }

    public double getSpeedDouble(){
        double x = speed.get(0, 0) - speed.get(0, 0);
        double y = speed.get(0, 1) - speed.get(0, 1);

        return sqrt(x*x + y*y);
    }

    //public abstract void interactCreature(Creature c){}
}
