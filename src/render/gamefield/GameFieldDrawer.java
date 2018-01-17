package render.gamefield;

import logic.objects.Creature;
import logic.objects.FoodPiece;
import render.Shapes;
import windows.Window;

import static logic.GameConstants.*;

public class GameFieldDrawer {
    static public void draw(logic.GameField g){
        Shapes.drawBox(Window.X - FIELD_SIZE_X, 0, FIELD_SIZE_X, FIELD_SIZE_Y, 0.16f, 0.16f, 0.16f);

        for(Creature c: g.getCreatures().getCreatures()){
            Shapes.drawCircle(FIELD_X_OFFSET + (int)c.getXY().get(0, 0), (int)c.getXY().get(0, 1), (int)CREATURE_SIZE,
                    10, 0, (float) (0.4 * c.getFitness() / 100), 0);
        }

        for(FoodPiece c: g.getFood().getFood()){
            Shapes.drawCircle(FIELD_X_OFFSET + (int)c.getXY().get(0, 0), (int)c.getXY().get(0, 1), (int)3,
                    6, 0.3f, 1, 1);
        }
    }
}
