package Fibonacci;

import java.math.BigInteger;

public class Fibonacci {

    FibonacciModel[] numerosJaCalculados = new FibonacciModel[0];

    public BigInteger calcularFibonacci(int posicao) {

        int arrayPosicao = this.contem(posicao);
        if (arrayPosicao > -1) {
            return this.numerosJaCalculados[arrayPosicao].getValor();
        }

        BigInteger resultado = new BigInteger("0");
        BigInteger numero_1 = new BigInteger("1");
        BigInteger numero_2 = new BigInteger("0");

        if (posicao == 1 || posicao == 2) {
            resultado = new BigInteger("1");
        } else {
            numero_1 = calcularFibonacci(posicao - 1);

            numero_2 = calcularFibonacci(posicao - 2);

            resultado = numero_1.add(numero_2);
        }

        this.add(posicao, resultado);
        return resultado;
    }

    private void add(int posicao, BigInteger valor) {

        if (this.contem(posicao) > -1) {
            return;
        }

        this.numerosJaCalculados = this.gerarArrayMaior(this.numerosJaCalculados);
        this.numerosJaCalculados[this.numerosJaCalculados.length - 1] = new FibonacciModel(posicao, valor);
    }

    private FibonacciModel[] gerarArrayMaior(FibonacciModel[] arrayAntigo) {
        FibonacciModel[] arrayNovo = new FibonacciModel[arrayAntigo.length + 1];

        for (int i = 0; i < arrayAntigo.length; i++) {
            arrayNovo[i] = arrayAntigo[i];
        }

        return arrayNovo;
    }

    private int contem(int posicao) {

        for (int i = 0; i < this.numerosJaCalculados.length; i++) {
            if (this.numerosJaCalculados[i].getPosicao() == posicao) {
                return i;
            }
        }

        return -1;
    }
}
