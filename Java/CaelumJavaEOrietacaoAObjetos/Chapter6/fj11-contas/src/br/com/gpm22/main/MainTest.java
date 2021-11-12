package br.com.gpm22.main;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Data;
import br.com.gpm22.banco.contas.Conta;
import br.com.gpm22.banco.contas.ContaCorrente;
import br.com.gpm22.banco.contas.ContaPoupanca;
import br.com.gpm22.interfaces.Tributavel;

class MainTest {
	public static void main(String[] args) {
		try {
			Data data1 = new Data(1, 10, 2020);
			Data data2 = new Data(30, 4, 2001);

			Data dataDeNascimento1 = new Data(1, 10, 1978);
			Data dataDeNascimento2 = new Data(30, 4, 2000);

			Cliente cliente1 = new Cliente("Alberto", "Alves", "10", dataDeNascimento1);
			Cliente cliente2 = new Cliente("Alexandro", "Santos", "20", dataDeNascimento2);

			Conta conta1 = new ContaCorrente(cliente1, "A", data1, 1000.5);
			Conta conta2 = new ContaPoupanca(cliente2, "B", data2, 2000);

			conta1.depositar(1000);

			System.out.println(conta1.recuperarDadosParaImpressão());

			conta1.transferirPara(conta2, 1001);

			System.out.println(conta1.recuperarDadosParaImpressão());
			System.out.println(conta2.recuperarDadosParaImpressão());

			conta1.transferirPara(conta2, 500);

			System.out.println(conta1.recuperarDadosParaImpressão());
			System.out.println(conta2.recuperarDadosParaImpressão());

			System.out.println("\n\n\nTestando Tributavel:\n");

			ContaCorrente cc = new ContaCorrente(cliente1, "A", data1, 6000);

			cc.depositar(10000);

			System.out.println("Imposto Conta Corrente: " + cc.getValorImposto());

			// testando polimorfismo:
			Tributavel t = cc;
			System.out.println("Imposto Tributavel: " + t.getValorImposto());

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
