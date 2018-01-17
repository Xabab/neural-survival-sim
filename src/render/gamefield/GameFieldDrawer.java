package render.gamefield;

import logic.objects.Creature;
import logic.objects.FoodPiece;
import render.Shapes;
import windows.Window;

import static java.lang.Math.*;
import static logic.GameConstants.*;

public class GameFieldDrawer {
    static public void draw(logic.GameField g){
        Shapes.drawBox(Window.X - FIELD_SIZE_X, 0, FIELD_SIZE_X, FIELD_SIZE_Y, 0.16f, 0.16f, 0.16f);

        for(Creature c: g.getCreatures().getCreatures()){
            Shapes.drawCircle(FIELD_X_OFFSET + (int)c.getXY().get(0, 0), (int)c.getXY().get(0, 1), (int)CREATURE_SIZE,
                    10, 0, (float) (1 * c.getFitness() / 100), 0);
            Shapes.drawLine(FIELD_X_OFFSET + (int)c.getXY().get(0, 0), (int)c.getXY().get(0, 1),
                    FIELD_X_OFFSET + (int)(c.getXY().get(0, 0) + cos(c.getDirection())),
                    (int)(c.getXY().get(0, 1) + sin(c.getDirection())),
                    1, 0.3f, 0.3f, 1f);
        }

        for(FoodPiece c: g.getFood().getFood()){
            Shapes.drawCircle(FIELD_X_OFFSET + (int)c.getXY().get(0, 0), (int)c.getXY().get(0, 1), 1,
                    6, 1, 1, 0.3f);
        }


    }
}
