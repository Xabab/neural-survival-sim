package logic;

import Jama.Matrix;
import logic.entities.Vector2d;
import logic.entities.creature.Creature;
import logic.entities.FoodPiece;
import render.Text;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.*;
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

        while(creatures.getCreatures().size() < MIN_CREATURES_COUNT) {
            creatures.addCreature(new Creature(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y)));
        }

        //spawn food if needed
        while(food.getFood().size() < FOOD_COUNT) {
            food.addFood(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y));
        }

        Vector2d tmp;

        for (Creature c : creatures.getCreatures()) {
            tmp = findClosestFoodDistanceAndDirection(c);
            //update inputs
            c.updateInputs(new double[]{tanh(tmp.getX()/FIELD_SIZE_X), - tmp.getY(),
                                        tanh(c.getFitness()/BIRTH_FITNESS_COST), tanh(c.getSpeed().length() / (CREATURE_SIZE*CREATURE_SPEED))});
            c.updateInfo(new double[]{tmp.getX(), - tmp.getY(), c.getFitness(), c.getSpeed().length()});

            //update creatures
            c.updateCreature();

            //check collisions with walls
            if (c.getXY().getX() < 0) {
                c.getXY().setX(0);
            } else if (c.getXY().getX() > FIELD_SIZE_X) {
                c.getXY().setX(FIELD_SIZE_X);
            }

            if (c.getXY().getY() < 0) {
                c.getXY().setY(0);
            } else if (c.getXY().getY() > FIELD_SIZE_Y) {
                c.getXY().setY(FIELD_SIZE_Y);
            }
        }

        //give birth
        for(Creature creature: new ArrayList<>(creatures.getCreatures())){
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

    private Vector2d findClosestFoodDistanceAndDirection(Creature c){
        double dist = 100000000; //"inf"
        Vector2d out;

        double d;

        Iterator i = food.getFood().listIterator();

        FoodPiece p;
        FoodPiece temp = getFood().getFood().get(0);

        while(i.hasNext()){
            p = (FoodPiece)i.next();
            out = findDistanceAndDirection(c.getXY(), p.getXY());

            d = out.getX();

            if(d < dist){
                dist = d;
                temp = p;
            }
            //check for food collisions and eat //todo move from here
            if(out.getX() < CREATURE_SIZE) {
                c.feed(FOOD_COST);
                i.remove();
            }
        }

        out = findDistanceAndDirection(c.getXY(), temp.getXY());

        return out;
    }

    private Vector2d  findDistanceAndDirection(Vector2d m1, Vector2d m2){
        Vector2d temp = new Vector2d();
        temp.setX(-(m2.getX() - m1.getX()));
        temp.setY(m2.getY() - m1.getY());

        double dir;
        if(temp.getX() > 0 && temp.getY() >= 0) dir = atan(temp.getY()/temp.getX());
        else if(temp.getX() > 0 && temp.getY() < 0) dir = atan(temp.getY()/temp.getX()) + 2*PI;
        else if(temp.getX() < 0) dir = atan(temp.getY()/temp.getX()) + PI;
        else if(temp.getX() == 0 && temp.getY() > 0) dir = PI/2;
        else if(temp.getX() == 0 && temp.getY() < 0) dir = 3*PI/2;
        else dir = 0;

        dir += PI;

        return new Vector2d(temp.length(), dir);
    }

    public Creature getTheChosenOne() {
        return theChosenOne;
    }

    public void setTheChosenOne(Creature theChosenOne) {
        this.theChosenOne = theChosenOne;
    }

    private Creature theChosenOne = null;

    private Creature creatureClick(int x, int y){
        for(Creature c: creatures.getCreatures()){
            if (((pow(x - c.getXY().getX(), 2) +
                    pow((y - c.getXY().getY()), 2)) < pow(CREATURE_SIZE, 2)))
                return c;
        }
        return null;
    }

    public void chooseCreature(int x, int y){
        if (x < 0) return;
        theChosenOne = creatureClick(x, y);
    }



}
