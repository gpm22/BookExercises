package br.com.gpm22.bancomvc;

import java.util.List;

import br.com.gpm22.Util.Data;
import br.com.gpm22.entidades.Cliente;
import br.com.gpm22.entidades.contas.Conta;
import br.com.gpm22.entidades.contas.ContaCorrente;
import br.com.gpm22.entidades.contas.SeguroDeVida;

public class BancoRepositorio {

	public static List<Cliente> retornarClientes() {
		return ConexaoBancoDeDados.retornarClientes();
	}

	public static void adicionarSeguroDeVida(SeguroDeVida seguroDeVida) {
		ConexaoBancoDeDados.persistirSeguroDeVida(seguroDeVida);
	}

	public static List<SeguroDeVida> retornarSegurosDeVida() {
		return ConexaoBancoDeDados.retornarSegurosDeVidas();
	}

	public static List<Conta> retornarContas() {
		return ConexaoBancoDeDados.retornarContas();
	}

	public static void adicionarConta(Conta conta) {
		ConexaoBancoDeDados.persistirConta(conta);
	}

	public static void adicionarCliente(Cliente cliente) {
		ConexaoBancoDeDados.persistirCliente(cliente);
	}

	public static void atualizarCliente(Cliente cliente) {
		if (ConexaoBancoDeDados.alterarCliente(cliente)) {
			System.out.println(
					"Cliente " + cliente.getNome() + " " + cliente.getSobrenome() + " atualizado com sucesso!");
		}
	}

	public static void removerConta(Conta conta) {
		ConexaoBancoDeDados.deletarConta(conta);
	}

	public static void removerCliente(Cliente cliente) {
		ConexaoBancoDeDados.deletarCliente(cliente);
	}

	public static Cliente retornarClientePeloNomeCompleto(String nomeCLiente) {
		List<Cliente> clientes = retornarClientes();
		String nomeCompleto;
		for (Cliente cliente : clientes) {
			nomeCompleto = cliente.getNome() + " " + cliente.getSobrenome();
			if (nomeCompleto.equals(nomeCLiente)) {
				return cliente;
			}
		}

		return null;
	}

	public static Conta retornarContaPeloNumeroEAgencia(String agencia, int numeroDaConta) {
		List<Conta> contas = retornarContas();
		for (Conta conta : contas) {
			if (conta.getAgencia().equals(agencia) && conta.getNumero() == numeroDaConta) {
				return conta;
			}
		}

		return null;
	}

	public static void iniciarUsuariosDeTeste() throws Exception {

		try {
			Data dataDeNascimentoUsuario1 = new Data(10, 10, 2000);
			Data dataDeNascimentoUsuario2 = new Data(9, 9, 1998);

			Cliente usuario1 = new Cliente("Usuário1", "Teste1", "268.202.460-21", dataDeNascimentoUsuario1);
			Cliente usuario2 = new Cliente("Usuário2", "Teste2", "459.521.200-32", dataDeNascimentoUsuario2);

			adicionarCliente(usuario1);
			adicionarCliente(usuario2);

		} catch (Exception e) {
			throw e;
		}

	}

	public static void iniciarContasDeTeste() throws Exception {
		try {

			iniciarUsuariosDeTeste();

			Data dataDeInicioConta1 = new Data(9, 9, 2020);
			Data dataDeInicioConta2 = new Data(10, 10, 2018);

			// Conta(Cliente titular, String agencia, Data dataDeAbertura, double limite)

			ContaCorrente conta1 = new ContaCorrente(retornarClientePeloNomeCompleto("Usuário1 Teste1"), "Girassol 1",
					dataDeInicioConta1, 1000);
			ContaCorrente conta2 = new ContaCorrente(retornarClientePeloNomeCompleto("Usuário2 Teste2"), "Girassol 1",
					dataDeInicioConta2, 1000);

			retornarClientePeloNomeCompleto("Usuário1 Teste1").addConta(conta1);
			retornarClientePeloNomeCompleto("Usuário2 Teste2").addConta(conta2);

			retornarClientePeloNomeCompleto("Usuário1 Teste1").getTributaveis().add(conta1);
			retornarClientePeloNomeCompleto("Usuário2 Teste2").getTributaveis().add(conta2);

			adicionarConta(conta1);
			adicionarConta(conta2);

		} catch (Exception e) {
			throw e;
		}
	}

}
