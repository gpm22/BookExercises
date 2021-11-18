public class TestaSplit {
    public static void main(String[] args) {

        imprimirInverso("Uma mensagem qualquer");
        imprimirInverso("Socorram-me, subi no ônibus em Marrocos");
    }

    private static void imprimirInverso(String frase) {
        String[] palavras = frase.split(" ");

        String fraseInversa = "";

        for (int i = palavras.length - 1; i >= 0; i--) {
            fraseInversa += palavras[i] + " ";
        }

        System.out.println("Frase        : " + frase);
        System.out.println("Frase Inversa: " + fraseInversa);
    }
}
