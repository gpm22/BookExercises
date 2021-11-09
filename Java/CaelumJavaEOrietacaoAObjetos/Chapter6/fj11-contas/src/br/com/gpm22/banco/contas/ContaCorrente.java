package br.com.gpm22.banco.contas;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Data;

public class ContaCorrente extends Conta {

    double valorOperacao = 0.10;

    public ContaCorrente(Cliente titular, String agencia, Data dataDeAbertura, double limite) {
        super(titular, agencia, dataDeAbertura, limite);
    }

    @Override
    public String recuperarDadosParaImpressão() throws Exception {
        return "\nTipo da conta: Conta Corrente" + super.recuperarDadosParaImpressão();
    }

    @Override
    public boolean sacar(double valor) {
        return super.sacar(valor + this.valorOperacao);
    }

    @Override
    public boolean transferirPara(Conta destino, double valor) {
        if (destino.getClass().equals(ContaCorrente.class)) {
            return super.transferirPara(destino, valor);
        } else {
            System.out.println("Não é possível transferir de um conta corrente para uma conta poupança.\n");
            return false;
        }
    }

}
