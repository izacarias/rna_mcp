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
public class RedesNeuraisMCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Neuronio neuroAND = new Neuronio();
        Neuronio neuroOR = new Neuronio();
        Neuronio neuroXOR = new Neuronio();

        // { IN1, IN2, OUT }
        int[][] andPort = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 0, 0},
            {1, 1, 1}
        };

        int[][] orPort = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        int[][] xorPort = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0}
        };

        for (int i = 0; i < 5000; i++) {
            RedesNeuraisMCP.treinaRNA(andPort, neuroAND, "AND");
            RedesNeuraisMCP.treinaRNA(orPort, neuroOR, "OR");
            RedesNeuraisMCP.treinaRNA(xorPort, neuroXOR, "XOR");
        }

        RedesNeuraisMCP.testaRNA(andPort, neuroAND, "AND");
        RedesNeuraisMCP.testaRNA(orPort, neuroOR, "OR");
        RedesNeuraisMCP.testaRNA(xorPort, neuroXOR, "XOR");

    }

    private static void testaRNA(int[][] tabelaVerdade, Neuronio n,
            String porta) {

        System.out.println(" -------- Teste RNA (" + porta + ") -----------");
        for (int[] linha : tabelaVerdade) {
            System.out.printf("|%1d", linha[0]);
            System.out.print(" " + porta + " ");
            System.out.printf("%1d", linha[1]);
            System.out.printf(" = %1d|", n.run(linha[0], linha[1]));
            System.out.println();
        }
    }

    private static void treinaRNA(int[][] tabelaVerdade, Neuronio n,
            String porta) {

        System.out.println(" Treinando a RNA (" + porta + ")...");
        for (int[] linha : tabelaVerdade) {
            int[] entrada = new int[2];
            entrada[0] = linha[0];
            entrada[1] = linha[1];
            int result = linha[2];
            n.aprendizagem(entrada, result);
        }
    }

}
