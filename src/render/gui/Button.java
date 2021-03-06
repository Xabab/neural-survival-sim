/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.gui;

import input.MouseInfo;

/**
 *
 * @author xabab
 */
public abstract class Button{
    float _cR;
    float _cG;
    float _cB;

    int _sizeX;
    int _sizeY;

    int _posX;
    int _posY;

    String _title = null;

// --Commented out by Inspection START (24.04.18 12:24):
//    public Button(int posX, int posY, int sizeX, int sizeY, float cR ,float cG, float cB){
//    _cR = cR;
//    _cG = cG;
//    _cB = cB;
//    _sizeX = sizeX;
//    _sizeY = sizeY;
//    _posX = posX;
//    _posY = posY;
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

    public Button(int posX, int posY, int sizeX, int sizeY, float cR ,float cG, float cB, String title){
    _cR = cR;
    _cG = cG;
    _cB = cB;
    _sizeX = sizeX;
    _sizeY = sizeY;
    _posX = posX;
    _posY = posY;
    _title = title;

    }

    public void checkForClick(){
        if((MouseInfo.getX() > _posX) && (MouseInfo.getX() < (_posX + _sizeX)) &&
           (MouseInfo.getY() > _posY) && (MouseInfo.getY() < (_posY + _sizeY))
                ){
            this.onClick();
        }
    }



    public abstract void onClick();

    public float get_cR(){
        return _cR;
    }
    public float get_cG(){
        return _cG;
    }
    public float get_cB(){
        return _cB;
    }
    public int get_sizeX(){
        return _sizeX;
    }
    public int get_sizeY(){
        return _sizeY;
    }
    public int get_posX(){
        return _posX;
    }
    public int get_posY(){
        return _posY;
    }
    public String get_title(){
        return _title;
    }


}
