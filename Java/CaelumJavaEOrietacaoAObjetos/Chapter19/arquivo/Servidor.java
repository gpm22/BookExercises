package arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Servidor - Porta 12345 aberta!");

        while (true) {
            Socket cliente = servidor.accept();
            createThread(cliente);
        }

        // servidor.close();

    }

    private static int id = 0;

    private static void createThread(Socket cliente) {
        new Thread(() -> {
            try {
                System.out
                        .println("Servidor - Nova conexão com o cliente de id: " + id++ + " "
                                + cliente.getInetAddress().getHostAddress());

                Scanner scanner = new Scanner(cliente.getInputStream());

                File recebido = new File("recebido" + id + ".txt");
                System.out.println("Servidor - arquivo recebido" + id + " criado");

                FileWriter writer = new FileWriter(recebido);
                if (scanner.hasNextLine()) {
                    writer.append(scanner.nextLine());
                }

                while (scanner.hasNextLine()) {
                    writer.append("\n" + scanner.nextLine());
                }

                System.out.println("Servidor - arquivo recebido" + id + " escrito");

                scanner.close();
                writer.close();
                cliente.close();
            } catch (IOException e) {
                System.out.println("servidor erro - " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}