package logic.creatures;

import logic.GameConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Creatures {
    private List<Creature> creatures = new ArrayList<Creature>();
    //private String type

    public Creatures(/*String type ( = "Herbivore";)*/){
        //this.type = type;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    //public abstract void interactCreature(Creature c){}

    public void deleteCreature(Creature c){
        Iterator<Creature> iterator = creatures.iterator();
        Creature i;
        while(iterator.hasNext()){
            i = iterator.next();
            if(i == c) {
                iterator.remove();
                break;
            }
        }
    }

    public void addCreature(){
        Creature c = new Creature(this);
        c.brain.initRandom(-GameConstants.BRAIN_INIT_RANGE, GameConstants.BRAIN_INIT_RANGE);

        creatures.add(c);
    }

    public void addCreature(Creature c){
        creatures.add(new Creature(c, false, this));
    }

    public void getBirth(Creature c){
        creatures.add(new Creature(c, true, this));
    }








}
