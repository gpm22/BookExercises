import java.io.PrintStream;

public class Test13 {

    public static void main(String[] args) {

        PrintStream saida = System.out;

        saida.println("ola");

        String x = new String("ola");
        String y = new String("ola");

        if (x == y) {
            saida.println("referência para o mesmo objeto");
        } else {
            saida.println("referências para objetos diferentes!");
        }

        if (x.equals(y)) {
            saida.println("consideramos iguais no critério de igualdade");
        } else {
            saida.println("consideramos diferentes no critério de igualdade");
        }

        x = "ola";
        y = "ola";

        if (x == y) {
            saida.println("referência para o mesmo objeto");
        } else {
            saida.println("referências para objetos diferentes!");
        }

        String palavra = "fj11";
        palavra.toUpperCase();
        saida.println(palavra);

        saida.println("Método imprimirChars");
        imprimirChars("O amor é lindo, não é?");
        saida.println("Método imprimirCharsInverso");
        imprimirCharsInverso("Socorram-me, subi no ônibus em Marrocos");
        saida.println("\n****");
        imprimirCharsInverso("anotaram a data da maratona");
        saida.println("\n****");
        StringBuilder j = new StringBuilder("Socorram-me, subi no ônibus em Marrocos");
        saida.println(j);
        j.reverse();

        saida.println(j);

        saida.println("Numero: 312102901*2 - stringToInt:" + stringToInt("312102901") * 2);
    }

    private static void imprimirChars(String string) {
        for (int i = 0; i < string.length(); i++) {
            System.out.println(string.charAt(i));
        }
    }

    private static void imprimirCharsInverso(String string) {
        for (int i = string.length() - 1; i >= 0; i--) {
            System.out.print(string.charAt(i));
        }
    }

    private static int stringToInt(String string) {
        int resultado = 0;
        int numero;
        int tamanho = string.length();
        for (int i = 0; i < tamanho; i++) {
            numero = string.charAt(tamanho - 1 - i) - '0';
            resultado += numero * Math.pow(10, i);
        }

        return resultado;
    }
}
