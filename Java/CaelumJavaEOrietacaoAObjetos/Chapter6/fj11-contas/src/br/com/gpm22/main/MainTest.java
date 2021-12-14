package br.com.gpm22.main;

import br.com.gpm22.Util.Data;
import br.com.gpm22.bancomvc.BancoRepositorio;
import br.com.gpm22.entidades.Banco;
import br.com.gpm22.entidades.Cliente;
import br.com.gpm22.entidades.contas.Conta;
import br.com.gpm22.entidades.contas.ContaCorrente;
import br.com.gpm22.entidades.contas.ContaPoupanca;
import br.com.gpm22.interfaces.Tributavel;

class MainTest {
	public static void main(String[] args) {
		try {
			Data data1 = new Data(1, 10, 2020);
			Data data2 = new Data(30, 4, 2001);

			Data dataDeNascimento1 = new Data(1, 10, 1978);
			Data dataDeNascimento2 = new Data(2, 4, 2000);

			Cliente cliente1 = new Cliente("Alberto", "Alves", "529.982.247-25", dataDeNascimento1);
			System.out.println("Adicionar cliente1 ao repositório");
			BancoRepositorio.adicionarCliente(cliente1);
			Cliente cliente2 = new Cliente("Alexandro", "Santos", "157.802.000-09", dataDeNascimento2);
			System.out.println("Adicionar cliente2 ao repositório");
			BancoRepositorio.adicionarCliente(cliente2);

			Conta conta1 = new ContaCorrente(cliente1, "A", data1, 1000.5);
			BancoRepositorio.adicionarConta(conta1);
			Conta conta2 = new ContaPoupanca(cliente2, "B", data2, 2000);
			BancoRepositorio.adicionarConta(conta2);

			conta1.depositar(2000);

			System.out.println(conta1.recuperarDadosParaImpressao());
			try {
				conta1.transferirPara(conta2, 1000);

				System.out.println(conta1.recuperarDadosParaImpressao());
				System.out.println(conta2.recuperarDadosParaImpressao());

				conta1.transferirPara(conta2, 500);

				System.out.println(conta1.recuperarDadosParaImpressao());
				System.out.println(conta2.recuperarDadosParaImpressao());
			} catch (Exception e) {

			}

			System.out.println("\n\n\nTestando Tributavel:\n");

			ContaCorrente cc = new ContaCorrente(cliente1, "A", data1, 6000);

			cc.depositar(10000);

			System.out.println("Imposto Conta Corrente: " + cc.getValorImposto());

			// testando polimorfismo:
			Tributavel t = cc;
			System.out.println("Imposto Tributavel: " + t.getValorImposto());

			// testando classe banco

			System.out.println("\n\n\nTestando Banco:\n");
			Banco banco = new Banco("CaelumBank", 999);

			banco.adiciona(conta1);

			for (int i = 0; i < 14; i++) {
				banco.adiciona(conta2);
			}

			System.out.println("\nQuantidade de contas do banco " + banco.getNome() + ":" + banco.getContas().length);
			// banco.mostraContas();

			System.out.println("O Banco:" + banco.getNome() + " contém a conta \n" + conta1 + "? \n"
					+ (banco.contem(conta1) > -1 ? "sim" : "não"));

			System.out.println("O Banco:" + banco.getNome() + " contém a conta \n" + conta2 + "? \n"
					+ (banco.contem(conta2) > -1 ? "sim" : "não"));

			System.out.println("Remover conta1");
			BancoRepositorio.removerConta(conta1);
			System.out.println("Remover cliente1");
			BancoRepositorio.removerCliente(cliente1);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
