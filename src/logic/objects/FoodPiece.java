package logic.objects;

import Jama.Matrix;

public class FoodPiece {
    public Matrix getXY() {
        return xy;
    }

    Matrix xy = new Matrix(1, 2, 0);

    public FoodPiece(){
        xy.set(0, 0, 0);
        xy.set(0, 1, 0);
    }

    public FoodPiece(double x, double y){
        xy.set(0, 0, x);
        xy.set(0, 1, y);
    }
}
