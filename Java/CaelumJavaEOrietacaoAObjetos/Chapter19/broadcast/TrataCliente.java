package broadcast;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TrataCliente implements Runnable {

    private Socket cliente;
    private Servidor servidor;

    public TrataCliente(Socket cliente, Servidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public void run() {
        // quando chegar uma msg, distribui pra todos
        Scanner s;
        try {
            s = new Scanner(this.cliente.getInputStream());

            String nickname = "";
            if (s.hasNextLine()) {
                nickname = s.nextLine();
            }

            servidor.distribuiMensagem(nickname + " conectou-se ");

            while (s.hasNextLine()) {
                servidor.distribuiMensagem(nickname + " - " + s.nextLine());
            }

            servidor.distribuiMensagem(nickname + " desconectou-se ");
            s.close();
            servidor.removerCliente(this.cliente);
        } catch (IOException e) {
            System.out.println("Servidor erro - " + e.getMessage());
            e.printStackTrace();
        }

    }
}