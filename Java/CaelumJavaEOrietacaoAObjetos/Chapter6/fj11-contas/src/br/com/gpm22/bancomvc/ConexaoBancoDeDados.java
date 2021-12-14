package br.com.gpm22.bancomvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.gpm22.entidades.Cliente;
import br.com.gpm22.entidades.contas.Conta;
import br.com.gpm22.entidades.contas.SeguroDeVida;

public class ConexaoBancoDeDados {

    private static String CLIENTES_DB = "src/br/com/data/clientes.txt";
    private static String CONTAS_DB = "src/br/com/data/contas.txt";
    private static String SEGUROS_DB = "src/br/com/data/seguros-de-vida.txt";

    public static List<Cliente> popularClientes() {
        try (Scanner s = new Scanner(new FileReader(CLIENTES_DB, Charset.forName("UTF8")))) {

            List<Cliente> ret = new ArrayList<>();
            s.nextLine();

            while (s.hasNextLine()) {
                String[] valores = s.nextLine().split(",");
            }

            return ret;

        } catch (IOException e) {
            System.out.println("Erro ao popular a lista clientes!");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean persistirCliente(Cliente cliente) {

        try (FileWriter fw = new FileWriter(CLIENTES_DB, true)) {
            fw.append("\n" + cliente.getNome() + "," + cliente.getSobrenome() + "," + cliente.getCpf() + ","
                    + cliente.getDataDeNascimento() + ","
                    + (cliente.getSeguroDeVida() != null ? cliente.getSeguroDeVida().getNumeroApolice() : ""));
        } catch (IOException e) {
            System.out.println("Problema ao adicionar cliente ao banco de dados");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deletarCliente(Cliente cliente) {

        try {
            criarDBTemp(cliente);
            transferirDBTemp(cliente);
            File myObj = new File(CLIENTES_DB + ".tmp");
            if (!myObj.delete()) {
                System.out.println("Error ao deletar cliente:\nArquivo temporário não removido");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error ao deletar cliente");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static void transferirDBTemp(Cliente cliente) throws IOException {
        try (FileChannel src = new FileInputStream(CLIENTES_DB + ".tmp").getChannel();
                FileChannel dest = new FileOutputStream(CLIENTES_DB).getChannel()) {
            dest.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            System.out.println("Error ao transferir bancos de cliente");
            throw e;
        }
    }

    private static void criarDBTemp(Cliente cliente) throws IOException {
        try (FileWriter dest = new FileWriter(new File(CLIENTES_DB + ".tmp"));
                Scanner src = new Scanner(new FileInputStream(CLIENTES_DB))) {

            while (src.hasNextLine()) {
                String line = src.nextLine();
                if (line.contains(cliente.getCpf())) {
                    continue;
                }
                dest.append(line);
                if (src.hasNextLine()) {
                    dest.append("\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Error ao criar DB temporário para clientes");
            throw e;
        }
    }

    public List<Conta> popularContas() {
        List<Conta> ret = new ArrayList<>();
        return ret;
    }

    public static boolean persistirConta(Conta conta) {
        try (FileWriter fw = new FileWriter(CONTAS_DB, true)) {
            fw.append("\n" + conta.getNumero() + "," + conta.getAgencia() + "," + conta.getTitular().getCpf() + ","
                    + conta.getDataDeAbertura() + "," + conta.getSaldo() + "," + conta.getLimite() + ","
                    + conta.getTipo());
        } catch (IOException e) {
            System.out.println("Problema ao adicionar conta ao banco de dados");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deletarConta(Conta conta) {

        try {
            criarDBTemp(conta);
            transferirDBTemp(conta);
            File myObj = new File(CONTAS_DB + ".tmp");
            if (!myObj.delete()) {
                System.out.println("Error ao deletar conta:\nArquivo temporário não removido");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error ao deletar conta");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static void transferirDBTemp(Conta conta) throws IOException {
        try (FileChannel src = new FileInputStream(CONTAS_DB + ".tmp").getChannel();
                FileChannel dest = new FileOutputStream(CONTAS_DB).getChannel()) {
            dest.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            System.out.println("Error ao transferir bancos de conta");
            throw e;
        }
    }

    private static void criarDBTemp(Conta conta) throws IOException {
        try (FileWriter dest = new FileWriter(new File(CONTAS_DB + ".tmp"));
                Scanner src = new Scanner(new FileInputStream(CONTAS_DB))) {

            while (src.hasNextLine()) {
                String line = src.nextLine();
                if (line.contains(conta.getTitular().getCpf())) {
                    continue;
                }
                dest.append(line);
                if (src.hasNextLine()) {
                    dest.append("\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Error ao criar DB temporário para contas");
            throw e;
        }
    }

    public static List<SeguroDeVida> popularSegurosDeVida() {
        List<SeguroDeVida> ret = new ArrayList<>();
        return ret;
    }

    public static boolean persistirSeguroDeVida(SeguroDeVida seguroDeVida) {
        try (FileWriter fw = new FileWriter(SEGUROS_DB, true)) {
            fw.append("\n" + seguroDeVida.getNumeroApolice() + "," + seguroDeVida.getTitular().getCpf() + ","
                    + seguroDeVida.getDataDeAbertura() + "," + seguroDeVida.getValor());
        } catch (IOException e) {
            System.out.println("Problema ao adicionar o seguro de vida ao banco de dados");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
