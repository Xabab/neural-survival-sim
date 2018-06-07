package logic.entities;

import Jama.Matrix;
import logic.GameConstants;

import static java.lang.System.arraycopy;
import static logic.GameConstants.defaultHiddenLayersNeuronCount;

public class Brain {
    private final Matrix []weights;
    private final Matrix []neuronLayers;
    private final String []inputsNames;
    private final String []outputsNames;


    protected Brain(String inputLayerNeurons[], int []hiddenLayersNeuronCount, String[] outputLayerNeurons){
        outputsNames = outputLayerNeurons;

        neuronLayers = new Matrix[2 + hiddenLayersNeuronCount.length];
        for (int i = 0; i < hiddenLayersNeuronCount.length; i++) {
            neuronLayers[i + 1] = new Matrix(1, hiddenLayersNeuronCount[i] + 1 /*bias*/, 0d);
        }
        neuronLayers[0] = new Matrix(1, inputLayerNeurons.length + 1 /*bias*/, 0d);                             //input layer
        neuronLayers[neuronLayers.length - 1] = new Matrix(1, outputLayerNeurons.length, 0d);                       //output layer
        for (int i = 0; i < neuronLayers.length - 1; i++) {
            neuronLayers[i].set(0, neuronLayers[i].getColumnDimension() - 1, 1);
        }

        weights = new Matrix[neuronLayers.length - 1];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = new Matrix(neuronLayers[i].getColumnDimension(), neuronLayers[i+1].getColumnDimension());
        }

        String []temp = new String[inputLayerNeurons.length + 1];
        arraycopy(inputLayerNeurons, 0, temp, 0, inputLayerNeurons.length);
        temp[temp.length - 1] = "Bias (1)";
        inputsNames = temp;
    }

    protected Brain(String inputLayerNeurons[], String[] outputLayerNeurons){
        outputsNames = outputLayerNeurons;

        neuronLayers = new Matrix[2 + defaultHiddenLayersNeuronCount.length];
        for (int i = 0; i < defaultHiddenLayersNeuronCount.length; i++) {
            neuronLayers[i + 1] = new Matrix(1, defaultHiddenLayersNeuronCount[i] + 1 /*bias*/, 0d);
        }
        neuronLayers[0] = new Matrix(1, inputLayerNeurons.length + 1 /*bias*/, 0d);                             //input layer
        neuronLayers[neuronLayers.length - 1] = new Matrix(1, outputLayerNeurons.length, 0d);                       //output layer
        for (int i = 0; i < neuronLayers.length - 1; i++) {
            neuronLayers[i].set(0, neuronLayers[i].getColumnDimension() - 1, 1);
        }

        weights = new Matrix[neuronLayers.length - 1];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = new Matrix(neuronLayers[i].getColumnDimension(), neuronLayers[i+1].getColumnDimension());
        }

        String []temp = new String[inputLayerNeurons.length + 1];
        arraycopy(inputLayerNeurons, 0, temp, 0, inputLayerNeurons.length);
        temp[temp.length - 1] = "Bias (1)";
        inputsNames = temp;
    }

    public Brain(Brain n){
        weights = new Matrix[n.weights.length];
        for(int i = 0; i < weights.length; i++){
            weights[i] = n.weights[i].copy();
        }

        neuronLayers = new Matrix[n.neuronLayers.length];
        for(int i = 0; i < neuronLayers.length; i++){
            neuronLayers[i] = n.neuronLayers[i].copy();
        }

        inputsNames = new String[n.inputsNames.length];
        arraycopy(n.inputsNames, 0, inputsNames, 0, n.inputsNames.length);

        outputsNames = new String[n.outputsNames.length];
        arraycopy(n.outputsNames, 0, outputsNames, 0, n.outputsNames.length);
    }

    public void initRandom(double min, double max){
        for(Matrix m: weights){
            for (int i = 0; i < m.getRowDimension(); i++) {
                for (int j = 0; j < m.getColumnDimension(); j++) {
                    m.set(i, j, Math.random()*(max-min) + min);
                }
            }
        }
    }

    protected void calculate(){
        for (int i = 0; i < weights.length; i++) {
            neuronLayers[i + 1] = neuronLayers[i].copy().times(weights[i]);
            for (int j = 0; j < neuronLayers[i + 1].getColumnDimension(); j++) {
                neuronLayers[i + 1].set(0, j, Math.tanh(neuronLayers[i + 1].get(0, j)));
                if(i != weights.length - 1)
                    neuronLayers[i + 1].set(0, neuronLayers[i + 1].getColumnDimension() - 1, 1); //setting bias back to 1 (hack)

            }
        }

        for (int i = 1; i < neuronLayers.length - 1; i++) {
            neuronLayers[i].set(0, neuronLayers[i].getColumnDimension() - 1, 1);
        }
    }

    public void updateInputs(double[] input){
        if(input.length != neuronLayers[0].getColumnDimension() - 1 /* bias */) {
            return;
        }
        for (int i = 0; i < input.length; i++) {
            neuronLayers[0].set(0, i, input[i]);
        }
    }

    protected void mutate(){
        for(Matrix m: weights){
            for(int i = 0; i < m.getRowDimension(); i++){
                for(int j = 0; j < m.getColumnDimension(); j++){
                    m.set(i, j, (m.get(i, j) + (Math.random()*2 - 1) * GameConstants.MUTAGEN_MULTIPLIER));
                }
            }
        }
    }

// --Commented out by Inspection START (24.04.18 12:24):
//    public String[] getInputsList(){
//        return inputsNames;
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

// --Commented out by Inspection START (24.04.18 12:24):
//    public String[] getOutputsList(){
//        return outputsNames;
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

// --Commented out by Inspection START (24.04.18 12:24):
//    public Matrix[] getWeights(){
//        return weights;
//    }
// --Commented out by Inspection STOP (24.04.18 12:24)

    public Matrix[] getNeuronLayers(){
        return neuronLayers;
    }
}
