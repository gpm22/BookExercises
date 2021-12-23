package arquivo;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket cliente = new Socket("127.0.0.1", 12345);

        System.out.println("Cliente - O cliente se conectou ao servidor");

        Scanner scanner = new Scanner(new FileReader("arquivo.txt"));
        PrintStream saida = new PrintStream(cliente.getOutputStream());

        while (scanner.hasNextLine()) {
            saida.println(scanner.nextLine());
        }

        saida.close();
        scanner.close();
        cliente.close();
        System.out.println("Cliente - O cliente se desconectou ao servidor");
    }

}
