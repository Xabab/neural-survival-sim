package logic;

import Jama.Matrix;
import logic.objects.Creature;
import logic.objects.Creatures;
import logic.objects.Food;
import logic.objects.FoodPiece;

import java.util.Iterator;

import static java.lang.Math.asin;
import static java.lang.Math.sqrt;
import static logic.GameConstants.*;

public class GameField {
    Food food = new Food();

    Creatures creatures = new Creatures();


    //todo game speed

    public void iteration(){
        //spawn new if needed
        Creature temp;
        while(creatures.getCreatures().size() < MIN_CREATURES_COUNT) {
            temp = new Creature(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y));
            temp.brain.initRandom(-BRAIN_INIT_RANGE, BRAIN_INIT_RANGE);
            temp.feed((Math.random()*2 - 1) * STARTING_FITNESS_RANGE);
            creatures.addCreature(temp);
        }

        //spawn food if needed
        while(food.getFood().size() < FOOD_COUNT) {
            food.addFood(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y));
        }

        /*for(Creature c: creatures.getCreatures()){

            //update inputs
            c.updateInputs(findClosestFood(c));

            //update creatures
            c.updateCreature();

            //check collisions with walls
            if(c.getXY().get(0, 0) < 0){
                c.getXY().set(0, 0, 0);
            }
            else if(c.getXY().get(0, 0) > FIELD_SIZE_X){
                c.getXY().set(0, 0, FIELD_SIZE_X);
            }

            if(c.getXY().get(0, 1) < 0){
                c.getXY().set(0, 1, 0);
            }
            else if(c.getXY().get(0, 1) > FIELD_SIZE_Y){
                c.getXY().set(0, 1, FIELD_SIZE_Y);
            }

            //creature fitness degradation
            c.feed(- (c.getSpeedDouble() * FOOD_PER_PX + FOOD_PER_ITERATION));

            //death
            if(c.getFitness() < 0) creatures.deleteCreature(c);

            if(c.brain.getNeuronLayers()[3].get(0, 2) > BIRTH_NEURON_ACTIVATION) creatures.addCreature(c.giveBirth());
        }*/

        Iterator i = creatures.getCreatures().listIterator();
        while(i.hasNext()){
            temp = (Creature)i.next();

            //update inputs
            temp.updateInputs(findClosestFood(temp));

            //update creatures
            temp.updateCreature();

            //check collisions with walls
            if(temp.getXY().get(0, 0) < 0){
                temp.getXY().set(0, 0, 0);
            }
            else if(temp.getXY().get(0, 0) > FIELD_SIZE_X){
                temp.getXY().set(0, 0, FIELD_SIZE_X);
            }

            if(temp.getXY().get(0, 1) < 0){
                temp.getXY().set(0, 1, 0);
            }
            else if(temp.getXY().get(0, 1) > FIELD_SIZE_Y){
                temp.getXY().set(0, 1, FIELD_SIZE_Y);
            }

            //creature fitness degradation
            temp.feed(- (temp.getSpeedDouble() * FOOD_PER_PX + FOOD_PER_ITERATION));

            //death
            if(temp.getFitness() < 0) i.remove();

            if(temp.brain.getNeuronLayers()[3].get(0, 2) > BIRTH_NEURON_ACTIVATION) creatures.addCreature(temp.giveBirth());
        }

        //debug
        System.out.println(creatures.getCreatures().get(0).getFitness());

    }

    public Food getFood() {
        return food;
    }

    public Creatures getCreatures() {
        return creatures;
    }

    private double[] findClosestFood(Creature c){
        FoodPiece f = food.getFood().get(0);
        double dist = 1000000; //"inf"
        double out[] = findDistanceAndDirection(c.getXY(), f.getXY());
        for(FoodPiece p: food.getFood()){
            out = findDistanceAndDirection(c.getXY(), p.getXY());
            if(out[0] < dist){
                dist = out[0];
                f = p;
            }
        }

        //check for food collisions and eat
        if(out[0] < CREATURE_SIZE) {
            c.feed(FOOD_COST);
            food.deleteFood(f);
        }

        return out;
    }

    private double[] findDistanceAndDirection(Matrix m1, Matrix m2){
        double x = m1.get(0, 0) - m2.get(0, 0);
        double y = m2.get(0, 1) - m2.get(0, 1);

        return new double[]{sqrt(x*x + y*y), asin(y/x)};
    }

}
