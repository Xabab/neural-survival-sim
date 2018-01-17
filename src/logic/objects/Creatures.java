package logic.objects;

import logic.GameConstants;

import java.util.ArrayList;
import java.util.List;


public class Creatures {
    private List<Creature> creatures = new ArrayList<>();
    //private String type

    public Creatures(/*String type ( = "Herbivore";)*/){
        //this.type = type;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void deleteCreature(Creature c){
        creatures.removeIf(creature -> creature == c);
    }

    public void addCreature(){
        Creature c = new Creature();
        c.brain.initRandom(-GameConstants.BRAIN_INIT_RANGE, GameConstants.BRAIN_INIT_RANGE);

        creatures.add(c);
    }

    public void addCreature(double x, double y){
        Creature c = new Creature(x, y);
        c.brain.initRandom(-GameConstants.BRAIN_INIT_RANGE, GameConstants.BRAIN_INIT_RANGE);

        creatures.add(c);
    }

    public void addCreature(Creature c){
        creatures.add(new Creature(c, false));
    }

    public void getBirth(Creature c){
        creatures.add(c.giveBirth());
    }
}
