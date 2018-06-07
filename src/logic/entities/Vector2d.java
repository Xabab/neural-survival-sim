package logic.entities;

import static java.lang.Math.sqrt;

public class Vector2d {

    private double x;
    private double y;

    public void setVector(double x, double y){
        this.x = x;
        this.y = y;
    }

// --Commented out by Inspection START (24.04.18 12:24):
//    public void add(double x, double y){
//        this.x += x;
//        this.y += y;
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

    public void add(Vector2d v){
        x += v.x;
        y += v.y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2d(){ }

    public Vector2d(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d c){
        this.x = c.x;
        this.y = c.y;
    }

    public Vector2d(double d){
        this.x = d;
        this.y = d;
    }

    public double length(){
        return sqrt(x*x + y*y);
    }
}
