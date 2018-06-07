package render.gamefield;

import Jama.Matrix;
import logic.Game;
import logic.entities.creature.Creature;
import logic.entities.FoodPiece;
import render.Shapes;
import render.Text;
import windows.Window;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;
import static logic.GameConstants.*;

public class GameFieldDrawer {
    private static Creature c = null;

    static public void draw(Game g){
        Shapes.drawBox(Window.X - FIELD_SIZE_X, 0, FIELD_SIZE_X, FIELD_SIZE_Y, 0.16f, 0.16f, 0.16f);

        //draw The Chosen One
        drawTheChosenOne(g);

        //draw The Chosen One's mind
        drawMind(g.getTheChosenOne());

        if(g.getTheChosenOne() != null) {
            if(c == null) c = g.getTheChosenOne();

            Text.text(20, 80, "Dir", 1, 1, 1);
            Text.text(20, 90, Double.toString(c.getDirection()), 1, 1, 1);

            Text.text(20, 110, "Food dist", 1, 1, 1);
            Text.text(20, 120, Double.toString(c.getInfo()[0]), 1, 1, 1);

            Text.text(20, 140, "Food dir", 1, 1, 1);
            Text.text(20, 150, Double.toString(c.getInfo()[1]), 1, 1, 1);

            Text.text(20, 170, "Fit", 1, 1, 1);
            Text.text(20, 180, Double.toString(c.getFitness()), 1, 1, 1);
        }
        else c = null;


        drawCreatures(g);

        drawFood(g);

    }

    private static void drawTheChosenOne(Game g) {
        if(g.getTheChosenOne() != null){
            if(g.getTheChosenOne().getFitness() <= 0) g.setTheChosenOne(null);
            else Shapes.drawCircle(
                    (int)g.getTheChosenOne().getXY().getX() + FIELD_X_OFFSET,
                    (int)g.getTheChosenOne().getXY().getY(),
                    (int)(CREATURE_SIZE * 2),
                    10,
                    0, 0, 0
                    );
        }


    }

    private static void drawCreatures(Game g) {
        for(Creature c: g.getCreatures().getCreatures()){
            Shapes.drawCircle(FIELD_X_OFFSET + (int)c.getXY().getX(), (int)c.getXY().getY(), (int)CREATURE_SIZE,
                    10, 0, (float) (1 * c.getFitness() / 100), 0);
            Shapes.drawLine(
                    FIELD_X_OFFSET + (int)c.getXY().getX(),
                    (int)c.getXY().getY(),
                    FIELD_X_OFFSET + (int)(c.getXY().getX() + cos(c.getDirection())*CREATURE_SIZE),
                    (int)(c.getXY().getY() + sin(c.getDirection())*CREATURE_SIZE),
                    1, 0.3f, 0.3f, 1f);
            Shapes.drawLine(
                    FIELD_X_OFFSET + (int)c.getXY().getX(),
                    (int)c.getXY().getY(),
                    FIELD_X_OFFSET +
                            (int)(c.getXY().getX() + cos(c.getNeuronLayers()[0].get(0, 1))*c.getNeuronLayers()[0].get(0, 0)
                            + c.getDirection()),
                            (int)(c.getXY().getY() + sin(c.getNeuronLayers()[0].get(0, 1))*c.getNeuronLayers()[0].get(0, 0)
                            + c.getDirection()),
                            1, 1f, 0.3f, 0.3f);
        }
    }

    private static void drawFood(Game g) {
        for(FoodPiece c: g.getFood().getFood()){
            Shapes.drawCircle(FIELD_X_OFFSET + (int)c.getXY().getX(), (int)c.getXY().getY(), 1,
                    6, 1, 1, 0.3f);
        }
    }

    public static void drawMind(Creature c){
        if(c == null) return;
        int x = 5;
        int y;
        for(Matrix nl: c.getNeuronLayers()){
            y = 300;
            for(double n: nl.getArray()[0]){
                Text.text(x, y, new BigDecimal(n).setScale(2, RoundingMode.HALF_UP).toPlainString(), 1, 1, 1);
                y += 10;
            }
            x += 30;
        }
    }
}
