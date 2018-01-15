package input;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import render.gui.Gui;
import render.gui.Button;



/**
 *
 * @author xabab
 */
public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        for(Button b: Gui.getMenu()){
            b.checkForClick();
        }

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
