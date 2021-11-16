package br.com.gpm22.exceptions;

public class DataInvalidaException extends Exception {

    public DataInvalidaException(String data) {
        super("\nA data " + data + " é inválida!");
    }
}
