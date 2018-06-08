package input;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import engine.GameLoop;
import logic.GameConstants;
import render.gui.Gui;
import render.gui.Button;

import static logic.GameConstants.FIELD_X_OFFSET;


/**
 *
 * @author xabab
 */
public class MouseInput implements MouseListener {

    private boolean pressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        if(pressed) return;

        if(MouseInfo.getX() < Gui.MENU_WIDTH)
        for(Button b: Gui.getMenu()){
            b.checkForClick();
        }
        else{
            GameLoop.getGame().chooseCreature(e.getX() - FIELD_X_OFFSET, e.getY());
        }

        pressed = true;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseInfo.setX(e.getX());
        MouseInfo.setY(e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseEvent e) {
    }
}
