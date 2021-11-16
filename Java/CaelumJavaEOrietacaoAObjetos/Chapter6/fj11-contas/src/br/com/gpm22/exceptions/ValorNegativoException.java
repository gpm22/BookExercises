package br.com.gpm22.exceptions;

public class ValorNegativoException extends Exception {

    public ValorNegativoException(String operacao) {
        super("Impossível " + operacao + " um valor negativo!");
    }
}
