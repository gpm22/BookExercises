public class Teste {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int resultado;
        for (int i = 1; i <= 6; i++) {
            resultado = fibonacci.calcularFibonacci(i);
            System.out.println(resultado);
        }
    }
}
