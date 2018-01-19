package logic;

import Jama.Matrix;
import logic.objects.Creature;
import logic.objects.Creatures;
import logic.objects.Food;
import logic.objects.FoodPiece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.Math.*;
import static logic.GameConstants.*;

public class GameField {
    Food food = new Food();
    Creatures creatures = new Creatures();
}
