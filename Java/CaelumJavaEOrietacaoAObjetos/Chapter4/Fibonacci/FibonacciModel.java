package Fibonacci;

import java.math.BigInteger;

public class FibonacciModel {
    private int posicao;
    private BigInteger valor;

    public FibonacciModel(int posicao, BigInteger valor) {
        this.posicao = posicao;
        this.valor = valor;
    }

    /**
     * @return int return the posicao
     */
    public int getPosicao() {
        return posicao;
    }

    /**
     * @return int return the valor
     */
    public BigInteger getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Posicao: " + this.posicao + " - Valor: " + this.valor;
    }
}
