package render;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author xabab
 */
public class Shapes {

    static GL2 gl = null;

    public static void init(GLAutoDrawable d) {
        gl = d.getGL().getGL2();
    }

    public static void drawCircle(int center_x, int center_y, int radius, int count_of_polygons, float cR, float cG, float cB){
        if(gl == null) return;

        gl.glColor3f(cR, cG, cB);
        gl.glLineWidth(1);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i < count_of_polygons; i++) {
            gl.glVertex2d((center_x + sin(((2 * PI) / count_of_polygons) * i) * radius),
                    (center_y + cos(((2 * PI) / count_of_polygons) * i) * radius));
        }
        gl.glEnd();
    }

    public static void drawBox(int x, int y, int size_x, int size_y, float cR, float cG, float cB){
        if(gl == null) return;

        gl.glColor3f(cR, cG, cB);
        gl.glLineWidth(1);
        gl.glBegin(GL2.GL_POLYGON);
        {
            gl.glVertex2i(x, y);
            gl.glVertex2i(x, y + size_y);
            gl.glVertex2i(x + size_x, y + size_y);
            gl.glVertex2i(x + size_x, y);
        }
        gl.glEnd();
    }

    public static void drawLine(int x1, int y1, int x2, int y2, float width,  float cR, float cG, float cB){
        if(gl == null) return;

        gl.glColor3f(cR, cG, cB);
        gl.glLineWidth(width);

        gl.glBegin(GL2.GL_LINES);
        {
            gl.glVertex2i(x1, y1);
            gl.glVertex2i(x2, y2);
        }
        gl.glEnd();
    }



}
