package logic;

import render.gui.Gui;
import windows.Window;

public class GameConstants {
    public static final double FOOD_PER_PX = 0.5;
    public static final double FOOD_PER_ITERATION = 0.1;
    public static final int MIN_CREATURES_COUNT = 15;
    public static final int FOOD_COUNT = 30;
    public static final int ITERATIONS_PER_SECOND = 5;
    public static final double CREATURE_SPEED = 0.7;                                                                    //own sizes /s
    public static final double CREATURE_TURNING_SPEED = 1;                                                              //rads / s
    public static final double CREATURE_SIZE = 7;                                                                       //px
    public static final double ACCELERATION = 0.3;
    public static final double BRAIN_INIT_RANGE = 3;
    public static final double FOOD_COST = 5;
    public static final double STARTING_FITNESS = 30;
    public static final double STARTING_FITNESS_RANGE = 5;
    public static final double BIRTH_FITNESS_COST = 50;
    public static final double MUTAGEN_MULTIPLIER = 0.05;
    public static final int FIELD_X_OFFSET = Gui.MENU_WIDTH;
    public static final int FIELD_SIZE_X = Window.X - Gui.MENU_WIDTH;
    public static final int FIELD_SIZE_Y = Window.Y;
    public static final double BIRTH_NEURON_ACTIVATION = 0.8;
    public static final double SURFACE_ROUGHNESS = 0.05;

}
