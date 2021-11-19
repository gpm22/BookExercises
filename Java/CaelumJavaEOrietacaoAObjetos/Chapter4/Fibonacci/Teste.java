package Fibonacci;

public class Teste {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();

        System.out.println(fibonacci.calcularFibonacci(400));
        for (int i = 0; i < fibonacci.numerosJaCalculados.length; i++) {
            System.out.println(fibonacci.numerosJaCalculados[i]);
        }
    }
}
