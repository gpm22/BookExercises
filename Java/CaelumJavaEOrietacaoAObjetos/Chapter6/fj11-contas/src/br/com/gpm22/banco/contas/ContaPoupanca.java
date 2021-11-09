package br.com.gpm22.banco.contas;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Data;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente titular, String agencia, Data dataDeAbertura, double limite) {
        super(titular, agencia, dataDeAbertura, limite);
    }

    @Override
    public String recuperarDadosParaImpressão() throws Exception {
        return "\nTipo da conta: Conta Poupança" + super.recuperarDadosParaImpressão();
    }

    @Override
    public boolean transferirPara(Conta destino, double valor) {
        if (destino.getClass().equals(ContaPoupanca.class)) {
            return super.transferirPara(destino, valor);
        } else {
            System.out.println("Não é possível transferir de uma conta poupanca para uma conta corrente.\n");
            return false;
        }
    }

}
