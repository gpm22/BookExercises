package br.com.gpm22.entidades.contas;

import br.com.gpm22.Util.Data;
import br.com.gpm22.entidades.Cliente;
import br.com.gpm22.exceptions.SaldoInsuficienteException;
import br.com.gpm22.exceptions.ValorNegativoException;
import br.com.gpm22.interfaces.Tributavel;

public class ContaCorrente extends Conta implements Tributavel {

    double valorOperacao = 0.10;
    private String tipo = "Corrente";

    public ContaCorrente(int numero, Cliente titular, String agencia, Data dataDeAbertura, double saldo,
            double limite) {
        super(numero, titular, agencia, dataDeAbertura, saldo, limite);
    }

    public ContaCorrente(Cliente titular, String agencia, Data dataDeAbertura, double limite) {
        super(titular, agencia, dataDeAbertura, limite);
    }

    public ContaCorrente(Conta conta) {
        super(conta);
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public double sacar(double valor) throws ValorNegativoException, SaldoInsuficienteException {
        return super.sacar(valor + this.valorOperacao);
    }

    @Override
    public double getValorImposto() {
        return this.getSaldo() * 0.01;
    }

}
