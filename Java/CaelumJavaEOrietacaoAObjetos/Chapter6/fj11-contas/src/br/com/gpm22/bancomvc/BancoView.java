package br.com.gpm22.bancomvc;

import java.util.Scanner;
import java.util.stream.Stream;

import br.com.gpm22.Util.Data;
import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.contas.Conta;
import br.com.gpm22.banco.contas.ContaCorrente;
import br.com.gpm22.banco.contas.ContaPoupanca;
import br.com.gpm22.banco.contas.SeguroDeVida;

public class BancoView {

	private Scanner entrada = new Scanner(System.in).useDelimiter(";|\r?\n|\r");

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
		try {
			Conta conta = this.acessarConta();
			if (conta == null) {
				return;
			}
			this.menuOpcoesConta(conta);
		} catch (Exception e) {
			System.out.println("Cancelado.");
		}

	}

	private void menuOpcoesConta(Conta conta) {

		String opcao = "";

		while (true) {

			System.out.println("O que deseja fazer?");
			System.out.println("1 - Ver informações da conta");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Transferir");
			System.out.println("5 - Criar Seguro de Vida");
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

			} else if (opcao.equals("5")) {
				this.menuCriarSeguroDeVida(conta);

			} else if (opcao.equals("x") || opcao.equals("X")) {
				this.sair();
				break;

			} else {
				System.out.println("Entrada inválida!");
			}

		}

	}

	private void menuCriarSeguroDeVida(Conta conta) {

		try {
			System.out.println("Vamos criar o seu seguro De vida: ");

			Data dataDeAbertura = this.criarDataDeCriacao();

			System.out.println("Digite o valor do seguro: ");

			double valor = entrada.nextDouble();

			SeguroDeVida seguroDeVida = new SeguroDeVida(conta.getTitular(), dataDeAbertura, valor);

			BancoRepositorio.adicionarSeguroDeVida(seguroDeVida);
			conta.getTitular().setSeguroDeVida(seguroDeVida);
			conta.getTitular().getTributaveis().add(seguroDeVida);
		} catch (Exception e) {
			System.out.println("Impossível Criar Seguro de Vida");
			System.out.println(e);
		}
	}

	private void menuTransferir(Conta conta) {
		Conta contaAReceber;
		Double valorTransferencia;

		System.out.println("Informe os dados sobre a conta que irá receber a transferência:");
		contaAReceber = this.acessarConta();

		if (contaAReceber == null) {
			return;
		}

		System.out.println("Digite o valor a ser transferido (ou digite 0 para sair):");
		valorTransferencia = entrada.nextDouble();

		if (valorTransferencia.equals(0)) {
			return;
		}

		conta.transferirPara(contaAReceber, valorTransferencia);

	}

	private void menuSacar(Conta conta) {
		Double valorSaque;

		System.out.println("Digite o valor a ser sacado (ou digite 0 para sair):");
		valorSaque = entrada.nextDouble();

		if (valorSaque.equals(0)) {
			return;
		}

		conta.sacar(valorSaque);

	}

	private void menuDepositar(Conta conta) {
		Double valorDeposito;

		System.out.println("Digite o valor a ser depositado (ou digite 0 para sair):");
		valorDeposito = entrada.nextDouble();

		if (valorDeposito.equals(0)) {
			return;
		}
		conta.depositar(valorDeposito);

	}

	private void mostrarInformacoesConta(Conta conta) {
		try {
			System.out.println(conta.recuperarDadosParaImpressao());
		} catch (Exception e) {
			System.out.println("Impossível imprimir dados");
			System.out.println(e);
		}

	}

	private Conta acessarConta() {
		String nomeAgencia;
		int numeroConta;

		System.out.println("Digite o nome da Agência (ou digite x para sair):");
		nomeAgencia = entrada.next();
		if (nomeAgencia.equals("x") || nomeAgencia.equals("X")) {
			return null;
		}

		System.out.println("Digite o número da conta (ou digite 0 para sair):");
		numeroConta = entrada.nextInt();
		if (numeroConta == 0) {
			return null;
		}
		return BancoRepositorio.retornarContaPeloNumeroEAgencia(nomeAgencia, numeroConta);

	}

	public void menuCriarConta() throws Exception {
		try {
			Conta conta = this.criarConta();
			if (conta == null) {
				return;
			}

			System.out.println("Conta criada: " + conta.recuperarDadosParaImpressao());
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
					if (cliente == null) {
						return null;
					}
					System.out.println("Cliente é o:\n" + cliente.retornarInformacoesDoCliente());
					break;
				} else if (opcao.equals("2")) {
					cliente = this.criarCliente();
					if (cliente == null) {
						return null;
					}
					BancoRepositorio.adicionarCliente(cliente);
					break;
				} else {
					System.out.println("Entrada Inválida");
				}
			}

			dataDeCriacao = this.criarDataDeCriacao();

			if (dataDeCriacao == null) {
				return null;
			}

			System.out.println("Digite o nome da Agência (ou x para sair): ");
			nomeDaAgencia = entrada.next();
			if (nomeDaAgencia.equals("x") || nomeDaAgencia.equals("X")) {
				return null;
			}
			System.out.println("Digite o limite da conta (ou 0 apra sair): ");
			limiteDaConta = entrada.nextDouble();
			if (limiteDaConta == 0) {
				return null;
			}

			System.out.println("Escolha o tipo da conta (ou digite 0 para sair):");
			System.out.println("1 - Conta Corrente\n2 - Conta Poupança");

			if (entrada.next().equals("1")) {
				ContaCorrente contaCorrente = new ContaCorrente(cliente, nomeDaAgencia, dataDeCriacao, limiteDaConta);
				cliente.getTributaveis().add(contaCorrente);
				conta = contaCorrente;
			} else if (entrada.next().equals("2")) {
				conta = new ContaPoupanca(cliente, nomeDaAgencia, dataDeCriacao, limiteDaConta);
			} else {
				return null;
			}

			cliente.addConta(conta);

			BancoRepositorio.adicionarConta(conta);
			return conta;

		} catch (Exception e) {
			throw e;
		}
	}

	private Cliente menuRetornarCliente() {
		String opcao;

		System.out.println("Informe o nome completo do cliente (ou digite x para sair):");
		opcao = entrada.next();

		if (opcao.equals("x") || opcao.equals("X")) {
			return null;
		}
		return BancoRepositorio.retornarClientePeloNomeCompleto(opcao);
	}

	private Data criarDataDeCriacao() throws Exception {
		int[] dataDeCriacaoEntrada;

		System.out.println("Digite a data de hoje (dd/mm/aaaa) (ou 0 para sair) :");
		dataDeCriacaoEntrada = Stream.of(entrada.next().split("/")).mapToInt(Integer::parseInt).toArray();

		if (dataDeCriacaoEntrada[0] == 0) {
			return null;
		}
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

		System.out.println("Digite o nome do Cliente (ou x para sair):");
		nomeDoCliente = entrada.next();
		if (nomeDoCliente.equals("x") || nomeDoCliente.equals("X")) {
			return null;
		}

		System.out.println("Digite o sobrenome do Cliente (ou x para sair):");
		sobrenomeDoCliente = entrada.next();
		if (sobrenomeDoCliente.equals("x") || sobrenomeDoCliente.equals("X")) {
			return null;
		}

		System.out.println("Digite o cpf do Cliente (ou x para sair):");
		cpfDoCliente = entrada.next();
		if (cpfDoCliente.equals("x") || cpfDoCliente.equals("X")) {
			return null;
		}

		System.out.println("Digite a data de nascimento do Cliente (dd/mm/aaaa) (ou x para sair):");
		entradaDataDeNascimento = entrada.next().split("/");
		if (entradaDataDeNascimento[0].equals("x") || entradaDataDeNascimento[0].equals("X")) {
			return null;
		}
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
