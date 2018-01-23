package engine;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import logic.Game;
import render.gamefield.GameFieldDrawer;
import render.gui.Gui;
import windows.Window;


/**
 *
 * @author xabab
 */
public class GameLoop {
    private static boolean running = false;


    static Game g = new Game();

    public static void start(){

        Thread thread = new Thread(() -> {
            running = true;
            Gui.transferGameField(g);

            while(running){
                Window.render();
            }
        });
        thread.setName("GameLoop");
        thread.start();
    }

    public static void stop(){
        running = false;
    }

    public static void loopIteration(){
        g.update();
        GameFieldDrawer.draw(g);
        Gui.draw();
    }
}
