package engine;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Jama.Matrix;
import logic.Network;
import logic.creatures.Creatures;
import windows.Window;

import java.util.List;

/**
 *
 * @author xabab
 */
public class Main {
    public static void main(String []args){
        Creatures c = new Creatures();

        List c1 = c.getCreatures();
        List c2 = c.getCreatures();

        System.out.println(c1 == c2);



        Window.init();
        GameLoop.start();

    }
}
