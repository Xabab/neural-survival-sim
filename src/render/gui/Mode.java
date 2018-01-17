package render.gui;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Mode {
    public enum MODE {NONE};

    private static MODE mode = MODE.NONE;

    public static void setMode(MODE m){
        mode = m;
    }

    public static MODE getMode(){
        return mode;
    }
}
