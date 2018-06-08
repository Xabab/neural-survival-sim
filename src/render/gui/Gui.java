/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.gui;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import logic.Game;
import render.Shapes;
import render.Text;


public class Gui {
    public static final int MENU_WIDTH = 140;

    private static GL2 gl = null;
    private static Game _g;

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }
    public static void transferGameField(Game g) { _g = g; }

    private static final Button []menu = {
        new Button(10, 10 ,30, 30, 0, 0.3f, 0, "<<") {
            @Override
            public void onClick() {
                _g.iterationCount_mm();
            }
        },
        new Button(50, 10 ,30, 30, 0, 0.3f, 0, ">>") {
            @Override
            public void onClick() {
                _g.iterationCount_pp();
            }
        }
    };

    public static void draw(){
        if (gl == null) return;
        for(Button b: menu){
            gl.glColor3f(b.get_cR(), b.get_cG(), b.get_cB());
            Shapes.drawBox(b.get_posX(), b.get_posY(), b.get_sizeX(), b.get_sizeY(), b._cR, b._cG, b._cB);
            Text.text(b.get_posX() + 3, b.get_posY() + 8, b.get_title(), 1f, 1f, 1f);
        }
    }

    public static Button[] getMenu(){
        return menu;
    }
}
