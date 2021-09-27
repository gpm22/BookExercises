public class Fibonacci {
    int numero_1 = 1;
    int numero_2 = 0;
    int contador = 0;

    int calcularFibonacci(int posicao) {
        if (posicao == 1) {
            int resultado = this.numero_1;
            this.numero_1 = 1;
            this.numero_2 = 0;
            return resultado;
        }

        this.numero_1 += this.numero_2;

        this.numero_2 = this.numero_1 - this.numero_2;

        return calcularFibonacci(posicao - 1);
    }
}
