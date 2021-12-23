package broadcast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args)
            throws UnknownHostException, IOException {
        // dispara cliente
        new Cliente("127.0.0.1", 12345).executa();
    }

    private String host;
    private int porta;

    public Cliente(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    public void executa() throws UnknownHostException, IOException {
        Socket cliente = new Socket(this.host, this.porta);
        System.out.println("Cliente - O cliente se conectou ao servidor!");

        Recebedor r = new Recebedor(cliente.getInputStream());
        new Thread(r).start();

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        System.out.println("Digite o nickname: ");
        if (teclado.hasNext()) {
            saida.println(teclado.nextLine());
        }
        System.out.println("Digite suas mensagens (digite x ou X para sair): ");
        while (teclado.hasNextLine()) {
            String entrada = teclado.nextLine();
            if (entrada.equals("X") || entrada.equals("x")) {
                break;
            }
            saida.println(entrada);
        }

        saida.close();
        teclado.close();
        cliente.close();
    }
}