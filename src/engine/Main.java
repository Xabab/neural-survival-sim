package engine;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import logic.GameConstants;
import logic.GameField;
import windows.Window;

import static java.lang.Math.sqrt;

/**
 *
 * @author xabab
 */
public class Main {
    public static void main(String []args){
        Window.init();
        GameLoop.start();
    }
}
