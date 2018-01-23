package logic.objects;

import Jama.Matrix;
import logic.GameConstants;
import logic.GameField;
import logic.Network;

import static java.lang.Math.*;
import static logic.GameConstants.BRAIN_INIT_RANGE;

public class Creature extends Network{
    private boolean readyToBirth = false;

    private double fitness;
    private double age = 0;

    private Matrix xy= new Matrix(1, 2, 0);
    private Matrix speed = new Matrix(1, 2, 0);
    private double direction;

    //private String name;
    //private String type = "Herbivore";

    public void addAge(double aging){
        age += abs(aging);
    }

    public double getAge() {
        return age;
    }


    public Creature(){
        super(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{3, 3},
                new String[]{"Accelerate", "Turn", "Birth"});
        fitness = GameConstants.STARTING_FITNESS;
        direction = ((Math.random()*2)-1)*Math.PI*2;
    }

    public Creature(double x, double y){
        super(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{3, 3},
                new String[]{"Accelerate", "Turn", "Birth"});
        fitness = GameConstants.STARTING_FITNESS;
        xy.set(0, 0, x);
        xy.set(0, 1, y);
        direction = ((Math.random()*2)-1)*Math.PI*2;
    }

    public Creature(Creature c, boolean child){
        super(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{3, 3},
                new String[]{"Accelerate", "Turn", "Birth"});
        xy = c.xy.copy();

        if(child) {
            mutate();
            fitness = GameConstants.STARTING_FITNESS;
            direction = ((Math.random()*2)-1)*Math.PI*2;
        }
        else {
            fitness = c.fitness;
            direction = c.direction;
            initRandom(-BRAIN_INIT_RANGE, BRAIN_INIT_RANGE);
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

        Creature c = new Creature(this, true);
        c.direction = (Math.random() * 2 * PI);
        return c;
    }

    public void updateInputs(double [] foodDistDirection) {
        super.updateInputs(new double[]{foodDistDirection[0], foodDistDirection[1], fitness});
    }

    private void updateMoving(){
        direction += GameConstants.CREATURE_TURNING_SPEED*
                getNeuronLayers()[getNeuronLayers().length - 1].get(0,1);

        fitness -= abs(GameConstants.CREATURE_TURNING_SPEED*
                getNeuronLayers()[getNeuronLayers().length - 1].get(0,1) )*
                GameConstants.FOOD_PER_RAD;

        while(direction > PI) direction -= 2*PI;
        while(direction < PI) direction += 2*PI;

        double x = speed.get(0, 0) * (1 - GameConstants.SURFACE_ROUGHNESS)
                + GameConstants.ACCELERATION*getNeuronLayers()[getNeuronLayers().length - 1].get(0,0)*cos(direction);
        double y = speed.get(0, 1) * (1 - GameConstants.SURFACE_ROUGHNESS)
                + GameConstants.ACCELERATION*getNeuronLayers()[getNeuronLayers().length - 1].get(0,0)*sin(direction);
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
        calculate();
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
        double x = speed.get(0, 0);
        double y = speed.get(0, 1);

        return sqrt(x*x + y*y);
    }

    //public abstract void interactCreature(Creature c){}
}
