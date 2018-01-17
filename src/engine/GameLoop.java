package engine;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import logic.GameField;
import render.Shapes;
import render.gamefield.GameFieldDrawer;
import windows.Window;
import static logic.GameConstants.*;


/**
 *
 * @author xabab
 */
public class GameLoop {
    private static boolean running = false;


    static GameField g = new GameField();

    public static void start(){

        Thread thread = new Thread(){
            @Override
            public void run(){
                running = true;
//                GLContext.makeCurrent();

                while(running){
                    Window.render();
                }
            }
        };
        thread.setName("GameLoop");
        thread.start();
    }

    public static void loopIteration(){

        Shapes.drawBox(Window.X - FIELD_SIZE_X, 0, FIELD_SIZE_X, FIELD_SIZE_Y, 0.16f, 0.16f, 0.16f);

        GameFieldDrawer.draw(g);
        g.iteration();
    }
}
