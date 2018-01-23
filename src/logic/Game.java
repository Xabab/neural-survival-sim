package logic;

import Jama.Matrix;
import logic.objects.Creature;
import logic.objects.Creatures;
import logic.objects.Food;
import logic.objects.FoodPiece;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.atan;
import static java.lang.Math.sqrt;
import static java.lang.Math.tanh;
import static logic.GameConstants.*;

public class Game extends GameField {


    //todo game speed
    private int desiredIterationCount = 1;

    public void iterationCount_pp(){
        if(desiredIterationCount > 10) return;
        desiredIterationCount++;
    }

    public void iterationCount_mm(){
        if(desiredIterationCount <= 0) return;
        desiredIterationCount--;
    }


    public void update(){
        for (int i = 0; i < desiredIterationCount; i++) {
            iteration();
        }
    }

    private void iteration(){
        //spawn new if needed
        Creature temp;
        while(creatures.getCreatures().size() < MIN_CREATURES_COUNT) {
            temp = new Creature(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y));
            temp.initRandom(-BRAIN_INIT_RANGE, BRAIN_INIT_RANGE);
            creatures.addCreature(temp);
        }

        //spawn food if needed
        while(food.getFood().size() < FOOD_COUNT) {
            food.addFood(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y));
        }

        for (Creature c : creatures.getCreatures()) {
            //update inputs
            c.updateInputs(findClosestFoodDistanceAndDirection(c));

            //update creatures
            c.updateCreature();

            //check collisions with walls
            if (c.getXY().get(0, 0) < 0) {
                c.getXY().set(0, 0, 0);
            } else if (c.getXY().get(0, 0) > FIELD_SIZE_X) {
                c.getXY().set(0, 0, FIELD_SIZE_X);
            }

            if (c.getXY().get(0, 1) < 0) {
                c.getXY().set(0, 1, 0);
            } else if (c.getXY().get(0, 1) > FIELD_SIZE_Y) {
                c.getXY().set(0, 1, FIELD_SIZE_Y);
            }

            //creature fitness degradation
            c.feed(-(c.getSpeedDouble() * FOOD_PER_PX + FOOD_PER_ITERATION));
        }

        //give birth
        for(Creature creature: new ArrayList<Creature>(creatures.getCreatures())){
            if (creature.getNeuronLayers()[creature.getNeuronLayers().length - 1].get(0, 2) > BIRTH_NEURON_ACTIVATION) {
                if (creature.getFitness() > BIRTH_FITNESS_COST) creatures.getCreatures().add(creature.giveBirth());
                //else creature.feed(- BIRTH_FITNESS_COST);
            }
        }

        //death from starvation
        creatures.getCreatures().removeIf(o -> (o).getFitness() < 0);
    }

    public Food getFood() {
        return food;
    }

    public Creatures getCreatures() {
        return creatures;
    }

    private double[] findClosestFoodDistanceAndDirection(Creature c){
        double dist = 1000000; //"inf"
        double out[] = new double[]{999999, 0};

        Iterator i = food.getFood().listIterator();

        FoodPiece p;
        while(i.hasNext()){
            p = (FoodPiece)i.next();
            out = findDistanceAndDirection(c.getXY(), p.getXY());
            if(out[0] < dist){
                dist = out[0];
            }
            //check for food collisions and eat
            if(out[0] < CREATURE_SIZE) {
                c.feed(FOOD_COST);
                i.remove();
            }
        }

        return out;
    }

    private double[] findDistanceAndDirection(Matrix m1, Matrix m2){
        double x = m2.get(0, 0) - m1.get(0, 0);
        double y = m2.get(0, 1) - m1.get(0, 1);

        return new double[]{sqrt(x*x + y*y), tanh(atan(y/x))};
    }

}
