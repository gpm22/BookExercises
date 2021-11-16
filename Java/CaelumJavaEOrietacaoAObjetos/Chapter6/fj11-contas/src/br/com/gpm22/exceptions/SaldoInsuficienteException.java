package br.com.gpm22.exceptions;

public class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException(Double saldo) {
        super("\nValor indisponível para Saque ou Transferência" + "\nSua conta possui saldo de: " + saldo);
    }
}
