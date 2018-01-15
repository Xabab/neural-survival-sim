package logic;

public class Creature {
    Network brain;

    public Creature(){
        brain = new Network(new String[]{"FoodDist", "FoodDirection", "Fitness"}, new int[]{4, 4}, new String[]{"Acceleration", "Turning"});
    }
}
