package br.com.gpm22.bancomvc;

import java.util.Scanner;
import java.util.stream.Stream;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Conta;
import br.com.gpm22.banco.Data;

public class BancoView {
	
	private Scanner entrada = new Scanner(System.in);
	
	
	public void menuInicial() throws Exception{
		String resposta;
		System.out.println("Seja bem-vindo ao banco Girassol!");
		System.out.println("Digite uma das seguintes opções:");
		System.out.println("1 - Criar Conta");
		System.out.println("2 - Acessar Conta");
		System.out.println("X - Sair");
		resposta = entrada.next();
		
		if(resposta.equals("1")) {
			this.menuCriarConta();	
		}
		if(resposta.equals("2")) {
			this.acessarConta();
		}
		if(resposta.equals("x") || resposta.equals("X")) {
			this.sair();
		} else {
			System.out.println("Entrada inválida!");
		}
	}
	
	
	public void menuCriarConta() throws Exception{
		try {
			BancoRepositorio.adicionarConta(this.criarConta());
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void sair() {
		System.out.println("Tenha um ótimo dia e volte sempre!");		
	}


	private void acessarConta() {
		// TODO Auto-generated method stub
		
	}


	private Conta criarConta() throws Exception{	
		
		Data dataDeCriacao;
		Conta conta;
		Cliente cliente;
		String nomeDaAgencia;
		double limiteDaConta;
		
		try {
			dataDeCriacao = this.criarDataDeCriacao();
			cliente = this.criarCliente();
			
			BancoRepositorio.adicionarCliente(cliente);
			
			System.out.println("Digite o nome da Agência: ");
			nomeDaAgencia = entrada.next();
			System.out.println("Digite o limite da conta: ");
			limiteDaConta = entrada.nextDouble();
			
			conta = new Conta(cliente, nomeDaAgencia, dataDeCriacao, limiteDaConta);
			
			cliente.addConta(conta);
			
			return conta;
			
		} catch(Exception e) {
			throw e;
		}	
	}
	
	private Data criarDataDeCriacao() throws Exception{
		int[] dataDeCriacaoEntrada;
		
		System.out.println("Digite a data de hoje, por favor no seguinte formato:\ndd/mm/aaaa");
		dataDeCriacaoEntrada = Stream.of(entrada.next().split("/")).mapToInt(Integer::parseInt).toArray();
		
		try {
			return new Data(dataDeCriacaoEntrada[0], dataDeCriacaoEntrada[1], dataDeCriacaoEntrada[2]);
		} catch(Exception e) {
			throw e;
		}
			
	}
	
	private Cliente criarCliente() throws Exception {
		String nomeDoCliente;
		String sobrenomeDoCliente;
		String cpfDoCliente;
		String[] entradaDataDeNascimento;
		Data dataDeNascimentoDoCliente;
				
		System.out.println("Digite o nome do Cliente:");
		nomeDoCliente = entrada.next();
		System.out.println("Digite o sobrenome do Cliente:");
		sobrenomeDoCliente = entrada.next();
		System.out.println("Digite o cpf do Cliente:");
		cpfDoCliente = entrada.next();
		System.out.println("Digite a data de nascimento do Cliente (dd/mm/aaaa):");
		entradaDataDeNascimento = entrada.next().split("/");
		try{
			dataDeNascimentoDoCliente = new Data(Integer.parseInt(entradaDataDeNascimento[0]), Integer.parseInt(entradaDataDeNascimento[1]), Integer.parseInt(entradaDataDeNascimento[2]));
		} catch (Exception e){
			throw e;
		}
		
		return new Cliente(nomeDoCliente, sobrenomeDoCliente, cpfDoCliente, dataDeNascimentoDoCliente);
	}
	
}
