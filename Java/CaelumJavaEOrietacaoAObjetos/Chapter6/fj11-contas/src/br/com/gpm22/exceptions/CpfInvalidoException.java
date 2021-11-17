package br.com.gpm22.exceptions;

public class CpfInvalidoException extends Exception {

    public CpfInvalidoException(String cpf) {
        super("o CPF: " + cpf + " é inválido!");
    }

    public CpfInvalidoException() {
        super("Esse CPF já foi cadastrado!");
    }
}
