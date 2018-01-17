package logic;

import logic.objects.Creature;
import logic.objects.Creatures;
import logic.objects.Food;
import static logic.GameConstants.*;

public class GameField {
    Food food = new Food();

    Creatures creatures = new Creatures();


    //game speed

    public void iteration(){
        //spawn new if needed
        while(creatures.getCreatures().size() < MIN_CREATURES_COUNT) {
            creatures.addCreature(new Creature(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y)));
        } //todo repair creature spawn

        //spawn food if needed
        while(food.getFood().size() < FOOD_COUNT) {
            food.addFood(Math.random()*(FIELD_SIZE_X), Math.random()*(FIELD_SIZE_Y));
        }
        //update inputs
        //update creatures
        //check collisions with walls
        //check for food collisions
        //  eat
        //birth
    }

    public Food getFood() {
        return food;
    }

    public Creatures getCreatures() {
        return creatures;
    }

}
