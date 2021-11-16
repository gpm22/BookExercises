package br.com.gpm22.exceptions;

import br.com.gpm22.interfaces.Tipavel;

public class TipoDiferenteException extends Exception {

    public TipoDiferenteException(Tipavel conta1, Tipavel conta2) {
        super("Não é possível transferir de uma conta do tipo " + conta1.getTipo() + " para uma conta do tipo "
                + conta2.getTipo() + ".\n");
    }

}
