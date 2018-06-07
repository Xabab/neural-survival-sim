package input;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import render.Shapes;


/**
 *
 * @author xabab
 */
public class KeyInput implements KeyListener{

    private static boolean _pressed;
    // --Commented out by Inspection (24.04.18 12:24):static String numberic_imput_temp = "";
    // --Commented out by Inspection (24.04.18 12:24):static final String ALOWED_NUMBERIC_INPUT = "-1234567890";

    @Override
    public void keyPressed(KeyEvent e) {
        Shapes.drawCircle(100, 100, 5, 8, 1, 1, 1);
        System.out.println("Code: " + e.getKeyCode());
        System.out.println("Char: " + e.getKeyChar());
        if(!_pressed){
            switch (e.getKeyCode()) {
                case 13: //if ENTER

                    break;
                case 8:  //if BACKSPACE

                    break;
                default:

                    break;
            }
            _pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        _pressed = false;
    }
}
