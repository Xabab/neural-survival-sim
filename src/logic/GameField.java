package logic;

import Jama.Matrix;
import logic.objects.Creature;
import logic.objects.Creatures;
import logic.objects.Food;
import logic.objects.FoodPiece;

import java.util.Iterator;
import java.util.function.Consumer;

import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.sqrt;
import static logic.GameConstants.*;

public class GameField {
    Food food = new Food();

    Creatures creatures = new Creatures();


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
            temp.brain.initRandom(-BRAIN_INIT_RANGE, BRAIN_INIT_RANGE);
            creatures.addCreature(temp);
        }



        //spawn food if needed
        while(food.getFood().size() < FOOD_COUNT) {
            food.addFood(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y));
        }

        for (Creature o1 : creatures.getCreatures()) {
            //update inputs
            o1.updateInputs(findClosestFoodDistanceAndDirection(o1));

            //update creatures
            o1.updateCreature();

            //check collisions with walls
            if (o1.getXY().get(0, 0) < 0) {
                o1.getXY().set(0, 0, 0);
            } else if (o1.getXY().get(0, 0) > FIELD_SIZE_X) {
                o1.getXY().set(0, 0, FIELD_SIZE_X);
            }

            if (o1.getXY().get(0, 1) < 0) {
                o1.getXY().set(0, 1, 0);
            } else if (o1.getXY().get(0, 1) > FIELD_SIZE_Y) {
                o1.getXY().set(0, 1, FIELD_SIZE_Y);
            }

            //creature fitness degradation
            o1.feed(-(o1.getSpeedDouble() * FOOD_PER_PX + FOOD_PER_ITERATION));

            /*//give birth
            if (o1.brain.getNeuronLayers()[3].get(0, 2) > BIRTH_NEURON_ACTIVATION) {
                if (o1.getFitness() > BIRTH_FITNESS_COST) creatures.addCreature(o1.giveBirth());
                else o1.feed(- BIRTH_FITNESS_COST);
            }*/
        }

        //give birth
        creatures.getCreatures().iterator().forEachRemaining(creature -> {
            if (creature.brain.getNeuronLayers()[3].get(0, 2) > BIRTH_NEURON_ACTIVATION) {
                if (creature.getFitness() > BIRTH_FITNESS_COST) creatures.addCreature(creature.giveBirth());
                else creature.feed(- BIRTH_FITNESS_COST);
            }
        });

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

        /*
        for(FoodPiece p: food.getFood()){
            out = findDistanceAndDirection(c.getXY(), p.getXY());
            if(out[0] < dist){
                dist = out[0];
            }
            //check for food collisions and eat
            if(out[0] < CREATURE_SIZE) {
                c.feed(FOOD_COST);
                food.deleteFood(p);
            }
        }
        */

        return out;
    }

    private double[] findDistanceAndDirection(Matrix m1, Matrix m2){
        double x = m2.get(0, 0) - m1.get(0, 0);
        double y = m2.get(0, 1) - m1.get(0, 1);

        return new double[]{sqrt(x*x + y*y), atan(y/x)};

    }

}
