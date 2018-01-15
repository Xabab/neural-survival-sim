package input;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xabab
 */
public class MouseInfo {                                                        //hack
    private static int _x = 0;
    private static int _y = 0;

    public static void setX(int x){
        _x = x;
    }

    public static void setY(int y){
        _y = y;
    }

    public static int getX(){
        return _x;
    }

    public static int getY(){
        return _y;
    }
}
