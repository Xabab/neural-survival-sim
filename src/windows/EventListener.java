package windows;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.awt.TextRenderer;
import gui.Gui;
import render.Shapes;


import engine.GameLoop;

public class EventListener implements GLEventListener {                         //window event listener

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();                                     //object with all gl f()

        gl.glClearColor(0.3f, 0.3f, 0.3f, 1);                                   //setting background color

        Shapes.init(drawable);
        Gui.init(drawable);
    }

    @Override
    public void display(GLAutoDrawable drawable) {                              //draw loop
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);                                    //cleaning up screen

        GameLoop.loopIteration();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(0, width, height, 0, -1, 1);                                 //min/max points by x/y  axises, last two idk
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

}
