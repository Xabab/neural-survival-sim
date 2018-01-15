package gui;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import render.Shapes;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;


public class Gui {

    private static Shapes sh;
    private static GL2 gl = null;
    private Gui(){}

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    static final Button []menu = {

    };

    public static void draw(){

    }

    public static Button[] getMenu(){
        return menu;
    }
}
