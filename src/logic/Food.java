package logic;

import logic.entities.FoodPiece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Food {
    private final List<FoodPiece> food = new ArrayList<>();

    public List<FoodPiece> getFood() {
        return food;
    }

// --Commented out by Inspection START (24.04.18 12:24):
//    public void deleteFood(FoodPiece c){
//        Iterator<FoodPiece> iterator = food.iterator();
//        FoodPiece i;
//        while(iterator.hasNext()){
//            i = iterator.next();
//            if(i == c) {
//                iterator.remove();
//                break;
//            }
//        }
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

    public void addFood(double x, double y){
        food.add(new FoodPiece(x, y));
    }
}
