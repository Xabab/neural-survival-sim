package logic.objects;

import logic.GameConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Food {
    private List<FoodPiece> food = new ArrayList<FoodPiece>();

    public List<FoodPiece> getFood() {
        return food;
    }

    public void deleteFood(FoodPiece c){
        Iterator<FoodPiece> iterator = food.iterator();
        FoodPiece i;
        while(iterator.hasNext()){
            i = iterator.next();
            if(i == c) {
                iterator.remove();
                break;
            }
        }
    }

    public void addFood(double x, double y){
        food.add(new FoodPiece(x, y));
    }
}
