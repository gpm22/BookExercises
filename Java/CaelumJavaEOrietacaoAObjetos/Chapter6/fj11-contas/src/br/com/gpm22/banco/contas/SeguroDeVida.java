package br.com.gpm22.banco.contas;

import br.com.gpm22.Util.Data;
import br.com.gpm22.banco.Cliente;
import br.com.gpm22.interfaces.Tipavel;
import br.com.gpm22.interfaces.Tributavel;

public class SeguroDeVida implements Tipavel, Tributavel {

    private Cliente titular;
    private Data dataDeAbertura;
    private String tipo = "Seguro de Vida";
    private double valor;
    private static int numeroApolices = 1;
    private int numeroApolice;

    public SeguroDeVida(Cliente titular, Data dataDeAbertura, double valor) {
        this.titular = titular;
        this.dataDeAbertura = dataDeAbertura;
        this.valor = valor;
        this.numeroApolice = SeguroDeVida.numeroApolices++;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    /**
     * @return double return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return int return the numeroApolice
     */
    public int getNumeroApolice() {
        return numeroApolice;
    }

    /**
     * @return Cliente return the titular
     */
    public Cliente getTitular() {
        return titular;
    }

    /**
     * @return Data return the dataDeAbertura
     */
    public Data getDataDeAbertura() {
        return dataDeAbertura;
    }

    @Override
    public double getValorImposto() {
        return this.valor * 0.02 + 42;
    }

}
