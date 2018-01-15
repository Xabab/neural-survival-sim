package render;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;

import windows.Window;

/**
 *
 * @author xabab
 */
public class Text {
    static TextRenderer textRenderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 10));


    public static void text(int x, int y, String text, float cR, float cG, float cB){
        textRenderer.setColor(cR, cG, cB, 1f);
        textRenderer.beginRendering(Window.X, Window.Y);
        textRenderer.draw(text, x, Window.Y - y);
        textRenderer.endRendering();
    }

}
