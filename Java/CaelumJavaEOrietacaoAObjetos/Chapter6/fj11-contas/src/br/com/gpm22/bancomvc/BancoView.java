package br.com.gpm22.bancomvc;

import java.util.Scanner;
import java.util.stream.Stream;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Conta;
import br.com.gpm22.banco.Data;

public class BancoView {

	private Scanner entrada = new Scanner(System.in);

	public void menuInicial() throws Exception {
		String resposta = "";
		while (true) {
			System.out.println("Seja bem-vindo(a) ao banco Girassol!");
			System.out.println("Digite uma das seguintes opções:");
			System.out.println("1 - Acessar Conta");
			System.out.println("2 - Criar Conta");
			System.out.println("X - Sair");
			resposta = entrada.next();

			if (resposta.equals("1")) {
				this.menuAcessarConta();

			} else if (resposta.equals("2")) {
				this.menuCriarConta();

			} else if (resposta.equals("x") || resposta.equals("X")) {
				this.sair();
				break;

			} else {
				System.out.println("Entrada inválida!");
			}
		}
	}

	private void menuAcessarConta() {
		Conta conta = this.acessarConta();

		this.menuOpcoesConta(conta);

	}

	private void menuOpcoesConta(Conta conta) {

		String opcao = "";

		while (true) {

			System.out.println("O que deseja fazer?");
			System.out.println("1 - Ver informações da conta");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Transferir");
			System.out.println("X - Sair");

			opcao = entrada.next();

			if (opcao.equals("1")) {
				this.mostrarInformacoesConta(conta);

			} else if (opcao.equals("2")) {
				this.menuDepositar(conta);

			} else if (opcao.equals("3")) {
				this.menuSacar(conta);

			} else if (opcao.equals("4")) {
				this.menuTransferir(conta);

			} else if (opcao.equals("x") || opcao.equals("X")) {
				this.sair();
				break;

			} else {
				System.out.println("Entrada inválida!");
			}

		}

	}

	private void menuTransferir(Conta conta) {
		Conta contaAReceber;
		Double valorTransferencia;

		System.out.println("Informe os dados sobre a conta que irá receber a transferência:");
		contaAReceber = this.acessarConta();

		System.out.println("Digite o valor a ser transferido");
		valorTransferencia = entrada.nextDouble();

		conta.transferirPara(contaAReceber, valorTransferencia);

	}

	private void menuSacar(Conta conta) {
		Double valorSaque;

		System.out.println("Digite o valor a ser sacado:");
		valorSaque = entrada.nextDouble();

		conta.sacar(valorSaque);

	}

	private void menuDepositar(Conta conta) {
		Double valorDeposito;

		System.out.println("Digite o valor a ser depositado:");
		valorDeposito = entrada.nextDouble();

		conta.depositar(valorDeposito);

	}

	private void mostrarInformacoesConta(Conta conta) {
		try {
			System.out.println(conta.recuperarDadosParaImpressão());
		} catch (Exception e) {
			System.out.println("Impossível imprimir dados");
			System.out.println(e);
		}

	}

	private Conta acessarConta() {
		String nomeAgencia;
		int numeroConta;

		System.out.println("Digite o nome da Agência:");
		nomeAgencia = entrada.next();

		System.out.println("Digite o número da conta:");
		numeroConta = entrada.nextInt();
		return BancoRepositorio.retornarContaPeloNumeroEAgencia(nomeAgencia, numeroConta);

	}

	public void menuCriarConta() throws Exception {
		try {
			BancoRepositorio.adicionarConta(this.criarConta());
		} catch (Exception e) {
			System.out.println("Impossível criar a conta.");
			throw e;
		}
	}

	private Conta criarConta() throws Exception {

		Data dataDeCriacao;
		Conta conta;
		Cliente cliente = null;
		String nomeDaAgencia;
		String opcao;
		double limiteDaConta;

		try {
	
			while (true) {
				System.out.println("Já é cliente?\n1-Sim\n2-Não");
				opcao = entrada.next();
				if (opcao.equals("1")) {
					cliente = this.menuRetornarCliente();
					System.out.println("Cliente é o: " + cliente.retornarInformacoesDoCliente());
					break;
				} else if (opcao.equals("2")) {
					cliente = this.criarCliente();
					BancoRepositorio.adicionarCliente(cliente);
					break;
				} else {
					System.out.println("Entrada Inválida");
				}
			}
			
			dataDeCriacao = this.criarDataDeCriacao();

			System.out.println("Digite o nome da Agência: ");
			nomeDaAgencia = entrada.next();
			System.out.println("Digite o limite da conta: ");
			limiteDaConta = entrada.nextDouble();

			conta = new Conta(cliente, nomeDaAgencia, dataDeCriacao, limiteDaConta);

			cliente.addConta(conta);

			return conta;

		} catch (Exception e) {
			throw e;
		}
	}

	private Cliente menuRetornarCliente() {
		String opcao;

		System.out.println("Informe o nome completo do cliente:");
		opcao = entrada.next();

		return BancoRepositorio.retornarClientePeloNomeCompleto(opcao);
	}

	private Data criarDataDeCriacao() throws Exception {
		int[] dataDeCriacaoEntrada;

		System.out.println("Digite a data de hoje (dd/mm/aaaa):");
		dataDeCriacaoEntrada = Stream.of(entrada.next().split("/")).mapToInt(Integer::parseInt).toArray();

		try {
			return new Data(dataDeCriacaoEntrada[0], dataDeCriacaoEntrada[1], dataDeCriacaoEntrada[2]);
		} catch (Exception e) {
			System.out.println("Impossível criar a data.");
			throw e;
		}

	}

	private Cliente criarCliente() throws Exception {
		String nomeDoCliente;
		String sobrenomeDoCliente;
		String cpfDoCliente;
		String[] entradaDataDeNascimento;
		Data dataDeNascimentoDoCliente;
		Cliente cliente;

		System.out.println("Digite o nome do Cliente:");
		nomeDoCliente = entrada.next();
		System.out.println("Digite o sobrenome do Cliente:");
		sobrenomeDoCliente = entrada.next();
		System.out.println("Digite o cpf do Cliente:");
		cpfDoCliente = entrada.next();
		System.out.println("Digite a data de nascimento do Cliente (dd/mm/aaaa):");
		entradaDataDeNascimento = entrada.next().split("/");
		try {
			dataDeNascimentoDoCliente = new Data(Integer.parseInt(entradaDataDeNascimento[0]),
					Integer.parseInt(entradaDataDeNascimento[1]), Integer.parseInt(entradaDataDeNascimento[2]));
		} catch (Exception e) {
			System.out.println("Impossível criar a data");
			throw e;
		}

		cliente = new Cliente(nomeDoCliente, sobrenomeDoCliente, cpfDoCliente, dataDeNascimentoDoCliente);

		System.out.println("Criado o cliente: " + cliente.retornarInformacoesDoCliente());

		return cliente;
	}

	private void sair() {
		System.out.println("Tenha um ótimo dia e volte sempre!");
	}

}
