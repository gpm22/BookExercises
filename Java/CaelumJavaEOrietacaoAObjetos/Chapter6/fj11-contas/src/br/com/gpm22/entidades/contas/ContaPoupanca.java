package br.com.gpm22.entidades.contas;

import br.com.gpm22.Util.Data;
import br.com.gpm22.entidades.Cliente;

public class ContaPoupanca extends Conta {

    private String tipo = "Poupanca";

    public ContaPoupanca(int numero, Cliente titular, String agencia, Data dataDeAbertura, double saldo,
            double limite) {
        super(numero, titular, agencia, dataDeAbertura, saldo, limite);
    }

    public ContaPoupanca(Cliente titular, String agencia, Data dataDeAbertura, double limite) {
        super(titular, agencia, dataDeAbertura, limite);
    }

    public ContaPoupanca(Conta conta) {
        super(conta);
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

}
