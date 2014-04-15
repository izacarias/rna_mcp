/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesneuraismcp;

/**
 *
 * @author iulisloi
 */
public class Neuronio {

    private double inputs[];
    private double weights[];
    private double sum;
    private double limiar;
    private int activationLevel;

    private final int INPUT_COUNT = 2;

    public Neuronio() {
        limiar = 0.5;
        weights = new double[INPUT_COUNT];
        inputs = new double[INPUT_COUNT];

    }

    private void setInput(int index, int value) {
        inputs[index] = value;
    }

    private void transfer() {
        activationLevel = FuncaoAtivacao.getInstance().avalia(limiar, sum);
    }

    private void sum() {
        double total = 0;
        for (int i = 0; i < inputs.length; i++) {
            total = total + inputs[i] * weights[i];
        }
        this.sum = total;
    }

    private void doSynapse() {
        this.sum();
        this.transfer();
    }

    public void adjustWeight(int inputIndex, double correctionFactor) {
        weights[inputIndex] = weights[inputIndex] + correctionFactor;
    }

    public void aprendizagem(int[] inputs, int expectedResult) {
        this.inputs[0] = (double) inputs[0];
        this.inputs[1] = (double) inputs[1];

        doSynapse();
        if(this.activationLevel != expectedResult){
            for (int i = 0; i < this.inputs.length; i++) {
                double error = computeError(expectedResult, this.sum);
                double correctionFactor = computeCorrectionFactor(error, this.inputs[i]);
                this.adjustWeight(i, correctionFactor);
            }
        }
    }

    public int run(int input0, int input1) {
        this.setInput(0, input0);
        this.setInput(1, input1);
        doSynapse();
        return this.activationLevel;
    }

    private double computeCorrectionFactor(double error, double input) {
        double learningRate = 0.5;
        return learningRate * error * input;
    }

    private double computeError(double desiredOutput, double obtainedOutput) {
        return (desiredOutput - obtainedOutput);
    }
}
