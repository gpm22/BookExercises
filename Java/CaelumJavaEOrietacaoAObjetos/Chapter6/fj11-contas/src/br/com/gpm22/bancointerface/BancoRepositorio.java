package br.com.gpm22.bancointerface;

import java.util.ArrayList;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Conta;

public class BancoRepositorio {
	private static ArrayList<Conta> contas = new ArrayList<Conta>();
	private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public static void adicionarConta(Conta conta) {
		contas.add(conta);
	}
	
	public static void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public static Cliente retornarClientePeloNomeCompleto(String nomeCLiente) {
		String nomeCompleto;
		for(Cliente cliente: clientes) {
			nomeCompleto = cliente.getSobrenome() + " " + cliente.getSobrenome();
			if(nomeCompleto.equals(nomeCLiente)) {
				return cliente;
			}
		}
		
		return null;
	}
	
	public static Conta retornarContaPeloNumeroEAgencia(String agencia, int numeroDaConta) {

		for(Conta conta: contas) {
			if(conta.getAgencia().equals(agencia) && conta.getNumero() == numeroDaConta) {
				return conta;
			}
		}
		
		return null;
	}
}
