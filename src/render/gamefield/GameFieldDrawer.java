package render.gamefield;

import logic.objects.Creature;
import render.Shapes;
import static logic.GameConstants.*;

public class GameFieldDrawer {
    static public void draw(logic.GameField g){
        for(Creature c: g.getCreatures().getCreatures()){
            Shapes.drawCircle(FIELD_X_OFFSET + (int)c.getXY().get(0, 0), (int)c.getXY().get(0, 1), (int)CREATURE_SIZE,
                    10, 0, 0.4f, 0);
        }
    }
}
