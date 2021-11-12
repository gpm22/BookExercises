package br.com.gpm22.bancomvc;

import java.util.ArrayList;
import java.util.List;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Data;
import br.com.gpm22.banco.contas.Conta;
import br.com.gpm22.banco.contas.ContaCorrente;
import br.com.gpm22.banco.contas.SeguroDeVida;

public class BancoRepositorio {
	private static ArrayList<Conta> contas = new ArrayList<Conta>();
	private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private static ArrayList<SeguroDeVida> segurosDeVida = new ArrayList<SeguroDeVida>();

	public static void adicionarSeguroDeVida(SeguroDeVida seguroDeVida) {
		segurosDeVida.add(seguroDeVida);
	}

	public static void removerTributavel(SeguroDeVida seguroDeVida) {
		segurosDeVida.remove(seguroDeVida);
	}

	public static List<SeguroDeVida> retornarSegurosDeVida() {
		return segurosDeVida;
	}

	public static void adicionarConta(Conta conta) {
		contas.add(conta);
	}

	public static void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public static void removerConta(Conta conta) {
		contas.remove(conta);
	}

	public static void removerCliente(Cliente cliente) {
		clientes.remove(cliente);
	}

	public static Cliente retornarClientePeloNomeCompleto(String nomeCLiente) {
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

			Cliente usuario1 = new Cliente("Usuário1", "Teste1", "097969231", dataDeNascimentoUsuario1);
			Cliente usuario2 = new Cliente("Usuário2", "Teste2", "097969232", dataDeNascimentoUsuario2);

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
