package logic;

import render.gui.Gui;
import windows.Window;

public class GameConstants {
    private static final double SPEED_HACK = 0.1;
    public static final double FOOD_PER_PX = 0.2 * SPEED_HACK;
    // --Commented out by Inspection (24.04.18 12:24):public static final double MAX_AGE = 3000;
    // --Commented out by Inspection (24.04.18 12:24):public static final double AGING = 1 * SPEED_HACK;
    public static final double FITNESS_DEGRADATION = 0.02 * SPEED_HACK;
    public static final double FOOD_PER_RAD = 2 * SPEED_HACK;
    public static final int MIN_CREATURES_COUNT = 20;
    public static final int FOOD_COUNT = 50;
    public static final double CREATURE_SPEED = 0.7 * SPEED_HACK;                                 //own sizes /s
    public static final double CREATURE_TURNING_SPEED = 0.7 * SPEED_HACK;                         //rads / s
    public static final double CREATURE_SIZE = 7;                                                 //px
    public static final double ACCELERATION = 0.3 * SPEED_HACK;
    public static final double BRAIN_INIT_RANGE = 0.5;
    public static final double FOOD_COST = 15;
    public static final double STARTING_FITNESS = 50;
    // --Commented out by Inspection (24.04.18 12:24):public static final double STARTING_FITNESS_RANGE = 5;
    public static final double BIRTH_FITNESS_COST = 60;
    public static final double MUTAGEN_MULTIPLIER = 0.002;
    public static final int FIELD_X_OFFSET = Gui.MENU_WIDTH;
    public static final int FIELD_SIZE_X = Window.X - Gui.MENU_WIDTH;
    public static final int FIELD_SIZE_Y = Window.Y;
    public static final double BIRTH_NEURON_ACTIVATION = 0;
    public static final double SURFACE_ROUGHNESS = 0.6 * SPEED_HACK;

    public static final int[] defaultHiddenLayersNeuronCount = {5, 4};

}
