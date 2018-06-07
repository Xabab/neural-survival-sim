package logic;

import logic.entities.creature.Creature;
import java.util.ArrayList;
import java.util.List;


public class Creatures {
    private final List<Creature> creatures = new ArrayList<>();
    //private String type

    Creatures(/*String type ( = "Herbivore";)*/){
        //this.type = type;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

// --Commented out by Inspection START (24.04.18 12:24):
//    public void deleteCreature(Creature c){
//        creatures.removeIf(creature -> creature == c);
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

// --Commented out by Inspection START (24.04.18 12:24):
//    public void addCreature(){
//        Creature c = new Creature();
//
//        creatures.add(c);
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

// --Commented out by Inspection START (24.04.18 12:24):
//    public void addCreature(double x, double y){
//        Creature c = new Creature(x, y);
//
//        creatures.add(c);
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

    public void addCreature(Creature c){
        creatures.add(new Creature());
    }

// --Commented out by Inspection START (24.04.18 12:24):
//    public void getBirth(Creature c){
//        creatures.add(c.giveBirth());
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)
}
