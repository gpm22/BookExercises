import br.com.gpm22.banco.contas.ContaCorrente;
import br.com.gpm22.exceptions.CpfInvalidoException;
import br.com.gpm22.exceptions.DataInvalidaException;
import br.com.gpm22.exceptions.ValorNegativoException;
import br.com.gpm22.banco.contas.Conta;
import br.com.gpm22.banco.Cliente;
import br.com.gpm22.Util.Data;

public class TestaArrays {

    public static void main(String[] args) throws CpfInvalidoException, ValorNegativoException, DataInvalidaException {
        Conta[] contas = new Conta[10];

        Data data1 = new Data(1, 10, 2020);

        Data dataDeNascimento1 = new Data(1, 10, 1978);

        Cliente cliente1 = new Cliente("Alberto", "Alves", "529.982.247-25", dataDeNascimento1);

        for (int i = 0; i < contas.length; i++) {
            Conta conta = new ContaCorrente(cliente1, "A", data1, 1000.5);
            conta.depositar((i + 1) * 100.0);
            contas[i] = conta;
        }

        Double media;
        Double total = 0.0;

        for (int i = 0; i < contas.length; i++) {
            total += contas[i].getSaldo();
        }

        media = total / contas.length;

        System.out.println("media: " + media);
    }
}