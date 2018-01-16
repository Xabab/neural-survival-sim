package engine;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import render.Shapes;
import windows.Window;


/**
 *
 * @author xabab
 */
public class GameLoop {
    private static boolean running = false;

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
        Shapes.drawBox(140, 0, 660, 600, 0.16f, 0.16f, 0.16f);
    }
}
