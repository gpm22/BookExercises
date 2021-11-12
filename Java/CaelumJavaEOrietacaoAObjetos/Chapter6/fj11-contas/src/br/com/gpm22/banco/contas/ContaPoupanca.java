package br.com.gpm22.banco.contas;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Data;

public class ContaPoupanca extends Conta {

    private String tipo = "Poupanca";

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
