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
public class FuncaoAtivacao {

    private static final FuncaoAtivacao instance = new FuncaoAtivacao();

    static {

    }

    private FuncaoAtivacao() {

    }

    public static FuncaoAtivacao getInstance() {
        return instance;
    }

    public int avalia(double limiar, double soma) {
        return (soma <= limiar) ? 0 : 1;
    }

}
