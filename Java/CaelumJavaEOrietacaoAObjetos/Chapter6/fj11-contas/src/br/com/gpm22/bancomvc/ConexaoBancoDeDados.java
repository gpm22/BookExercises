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

    public static List<Cliente> retornarClientes() {
        try (Scanner s = new Scanner(new FileReader(CLIENTES_DB, Charset.forName("UTF8")))) {

            List<Cliente> ret = new ArrayList<>();
            s.nextLine();

            while (s.hasNextLine()) {
                String[] valores = s.nextLine().split(",");
            }

            return ret;

        } catch (IOException e) {
            System.out.println("Erro ao retornar a lista clientes!");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean alterarCliente(Cliente cliente) {

        if (!deletarCliente(cliente)) {
            return false;
        }

        if (!persistirCliente(cliente)) {
            return false;
        }

        if (cliente.getContas().size() > 0) {
            for (Conta conta : cliente.getContas()) {
                if (!persistirConta(conta)) {
                    return false;
                }
            }
        }

        if (cliente.getSeguroDeVida() != null) {
            if (!persistirSeguroDeVida(cliente.getSeguroDeVida())) {
                return false;
            }
        }

        return true;
    }

    public static boolean persistirCliente(Cliente cliente) {

        try (FileWriter fw = new FileWriter(CLIENTES_DB, true)) {
            fw.append("\n" + cliente.getNome() + "," + cliente.getSobrenome() + "," + cliente.getCpf() + ","
                    + cliente.getDataDeNascimento() + ","
                    + (cliente.getSeguroDeVida() != null ? cliente.getSeguroDeVida().getNumeroApolice() : ""));
            if (cliente.getContas().size() > 0) {
                for (Conta conta : cliente.getContas()) {
                    if (!persistirConta(conta)) {
                        return false;
                    }
                }
            }

            if (cliente.getSeguroDeVida() != null) {
                if (!persistirSeguroDeVida(cliente.getSeguroDeVida())) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("Problema ao adicionar cliente ao banco de dados");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean deleteFile(String file) {
        File myObj = new File(file + ".tmp");
        if (!myObj.delete()) {
            System.out.println("Arquivo temporário " + file + ".tmp não removido");
            return false;
        }

        return true;
    }

    public static boolean deletarCliente(Cliente cliente) {

        try {
            criarDBTemp(cliente);
            transferirDBTempCliente();
            return (deleteFile(CLIENTES_DB) && deleteFile(CONTAS_DB) && deleteFile(SEGUROS_DB));
        } catch (IOException e) {
            System.out.println("Error ao deletar cliente");
            e.printStackTrace();
            return false;
        }
    }

    private static void transferirDBTempCliente() throws IOException {
        try (FileChannel src = new FileInputStream(CLIENTES_DB + ".tmp").getChannel();
                FileChannel dest = new FileOutputStream(CLIENTES_DB).getChannel()) {
            dest.transferFrom(src, 0, src.size());
            transferirDBTempConta();
            transferirDBTempSeguroDeVida();
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

            criarDBTempContaCpf(cliente);
            criarDBTempSeguroDeVidaCpf(cliente);

        } catch (IOException e) {
            System.out.println("Error ao criar DB temporário para clientes");
            throw e;
        }
    }

    private static void criarDBTempContaCpf(Cliente cliente) throws IOException {

        try (FileWriter dest = new FileWriter(new File(CONTAS_DB + ".tmp"));
                Scanner src = new Scanner(new FileInputStream(CONTAS_DB))) {

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
            System.out.println("Error ao criar DB temporário para contas");
            throw e;
        }
    }

    private static void criarDBTempSeguroDeVidaCpf(Cliente cliente) throws IOException {

        try (FileWriter dest = new FileWriter(new File(SEGUROS_DB + ".tmp"));
                Scanner src = new Scanner(new FileInputStream(SEGUROS_DB))) {

            while (src.hasNextLine()) {
                String line = src.nextLine();
                if (line.contains(cliente.getCpf() + ",")) {
                    continue;
                }
                dest.append(line);
                if (src.hasNextLine()) {
                    dest.append("\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Error ao criar DB temporário para seguroDeVidas");
            throw e;
        }
    }

    public List<Conta> retornarContas() {
        List<Conta> ret = new ArrayList<>();
        return ret;
    }

    public static boolean alterarConta(Conta conta) {
        if (!deletarConta(conta)) {
            return false;
        }

        if (!persistirConta(conta)) {
            return false;
        }

        return true;
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
            transferirDBTempConta();
            return deleteFile(CONTAS_DB);
        } catch (IOException e) {
            System.out.println("Error ao deletar conta");
            e.printStackTrace();
            return false;
        }
    }

    private static void transferirDBTempConta() throws IOException {
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
                if (line.contains(conta.getNumero() + "," + conta.getAgencia())) {
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

    public static List<SeguroDeVida> retornarSegurosDeVida() {
        List<SeguroDeVida> ret = new ArrayList<>();
        return ret;
    }

    public static boolean alterarSeguroDeVida(SeguroDeVida seguroDeVida) {
        if (!deletarSeguroDeVida(seguroDeVida)) {
            return false;
        }

        if (!persistirSeguroDeVida(seguroDeVida)) {
            return false;
        }

        return true;
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

    public static boolean deletarSeguroDeVida(SeguroDeVida seguroDeVida) {

        try {
            criarDBTemp(seguroDeVida);
            transferirDBTempSeguroDeVida();
            return deleteFile(SEGUROS_DB);
        } catch (IOException e) {
            System.out.println("Error ao deletar seguroDeVida");
            e.printStackTrace();
            return false;
        }
    }

    private static void transferirDBTempSeguroDeVida() throws IOException {
        try (FileChannel src = new FileInputStream(SEGUROS_DB + ".tmp").getChannel();
                FileChannel dest = new FileOutputStream(SEGUROS_DB).getChannel()) {
            dest.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            System.out.println("Error ao transferir bancos de seguroDeVida");
            throw e;
        }
    }

    private static void criarDBTemp(SeguroDeVida seguroDeVida) throws IOException {

        try (FileWriter dest = new FileWriter(new File(SEGUROS_DB + ".tmp"));
                Scanner src = new Scanner(new FileInputStream(SEGUROS_DB))) {

            while (src.hasNextLine()) {
                String line = src.nextLine();
                if (line.contains(seguroDeVida.getNumeroApolice() + ",")) {
                    continue;
                }
                dest.append(line);
                if (src.hasNextLine()) {
                    dest.append("\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Error ao criar DB temporário para seguroDeVidas");
            throw e;
        }
    }
}
