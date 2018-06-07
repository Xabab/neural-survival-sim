package logic.entities;

public class FoodPiece {
    public Vector2d getXY() {
        return xy;
    }

    private final Vector2d xy = new Vector2d();

// --Commented out by Inspection START (24.04.18 12:24):
//    public FoodPiece(){
//        xy.setX(0);
//        xy.setY(0);
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

    public FoodPiece(double x, double y){
        xy.setX(x);
        xy.setY(y);
    }
}
