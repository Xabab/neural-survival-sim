package logic;

import Jama.Matrix;

public class Network {
    private Matrix []weights;
    private Matrix []neuronLayers;
    String []inputs;
    String []outputs;


    public Network(String inputLayerNeurons[], int [] countOfHiddenLayersNeurons, String []outputLayerNeurons){
        inputs = inputLayerNeurons;
        outputs = outputLayerNeurons;

        neuronLayers = new Matrix[2 + countOfHiddenLayersNeurons.length];
        for (int i = 0; i < countOfHiddenLayersNeurons.length; i++) {
            neuronLayers[i + 1] = new Matrix(1, countOfHiddenLayersNeurons[i] + 1 /*bias*/, 0d);
        }
        neuronLayers[0] = new Matrix(1, inputLayerNeurons.length + 1 /*bias*/, 0d);                             //input layer
        neuronLayers[1 + countOfHiddenLayersNeurons.length] = new Matrix(1, outputLayerNeurons.length, 0d);         //output layer
        for (int i = 0; i < neuronLayers.length - 1; i++) {
            neuronLayers[i].set(0, neuronLayers[i].getColumnDimension() - 1, 1);
        }

        weights = new Matrix[neuronLayers.length - 1];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = new Matrix(neuronLayers[i].getColumnDimension(), neuronLayers[i+1].getColumnDimension());
        }

        String []temp = new String[outputs.length + 1];
        System.arraycopy(inputs, 0, temp, 0, outputs.length);
        temp[temp.length - 1] = "Bias (const)";
        inputs = temp;
    }

    public Network(Network n){
        weights = new Matrix[n.weights.length];
        for(int i = 0; i < weights.length; i++){
            weights[i] = n.weights[i].copy();
        }

        neuronLayers = new Matrix[n.neuronLayers.length];;
        for(int i = 0; i < neuronLayers.length; i++){
            neuronLayers[i] = n.neuronLayers[i].copy();
        }

        inputs = new String[n.inputs.length];
        System.arraycopy(n.inputs, 0, inputs, 0, n.inputs.length);

        outputs = new String[n.outputs.length];
        System.arraycopy(n.outputs, 0, outputs, 0, n.outputs.length);
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

    public void calculate(){
        for (int i = 0; i < weights.length; i++) {
            neuronLayers[i + 1] = neuronLayers[i].copy().times(weights[i]);
            for (int j = 0; j < neuronLayers[i + 1].getColumnDimension(); j++) {
                neuronLayers[i + 1].set(0, j, Math.tanh(neuronLayers[i + 1].get(0, j)));
                neuronLayers[i].set(0, neuronLayers[i].getColumnDimension() - 1, 1); //setting bias back to 1 (hack)

            }
        }

        for (int i = 1; i < neuronLayers.length - 1; i++) {
            neuronLayers[i].set(0, neuronLayers[i].getColumnDimension() - 1, 1);
        }
    }

    public void updateInputs(double []input){
        if(input.length != neuronLayers[0].getColumnDimension() - 1 /* bias */) {
            return;
        }
        for (int i = 0; i < input.length; i++) {
            neuronLayers[0].set(0, i, input[i]);
        }
    }

    public void mutate(){
        for(Matrix m: weights){
            for(int i = 0; i < m.getRowDimension(); i++){
                for(int j = 0; j < m.getColumnDimension(); j++){
                    m.set(i, j, (m.get(i, j)*(1 + (Math.random()*2 - 1) * GameConstants.MUTAGEN_MULTIPLIER)));
                }
            }
        }
    }

    public String[] getInputsList(){
        return inputs;
    }

    public String[] getOutputsList(){
        return outputs;
    }

    public Matrix[] getWeights(){
        return weights;
    }

    public Matrix[] getNeuronLayers(){
        return neuronLayers;
    }
}
