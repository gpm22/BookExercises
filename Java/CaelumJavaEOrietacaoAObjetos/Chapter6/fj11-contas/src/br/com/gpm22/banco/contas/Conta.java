package br.com.gpm22.banco.contas;

import br.com.gpm22.Util.Data;
import br.com.gpm22.banco.Cliente;
import br.com.gpm22.interfaces.Tipavel;

public abstract class Conta implements Tipavel {
	private static int numeros = 1;
	private int numero;
	private Cliente titular;
	private String agencia;
	private Data dataDeAbertura;
	private double saldo;
	private double limite;

	public Conta(Cliente titular, String agencia, Data dataDeAbertura, double limite) {
		this.numero = Conta.numeros++;
		this.titular = titular;
		this.agencia = agencia;
		this.dataDeAbertura = dataDeAbertura;
		this.limite = limite;
	}

	public Conta(Conta conta) {
		this.titular = conta.getTitular();
		this.numero = conta.getNumero();
		this.agencia = conta.getAgencia();
		this.limite = conta.getLimite();
		this.dataDeAbertura = conta.getDataDeAbertura();
	}

	public boolean sacar(double valor) {

		if (valor < 0) {
			System.out.println("Impossível Sacar ou Transferir um Valor Negativo!");
			return false;
		}

		if (this.saldo < valor) {
			System.out.println("\nValor indisponível para Saque ou Transferência");
			System.out.println("Sua conta possui saldo de: " + this.saldo);
			return false;
		}

		this.saldo -= valor;
		return true;
	}

	public boolean transferirPara(Conta destino, double valor) {

		if (!destino.getTipo().equals(this.getTipo())) {
			System.out.println("Não é possível transferir de uma conta do tipo " + this.getTipo()
					+ " para uma conta do tipo " + destino.getTipo() + ".\n");
			return false;
		}

		if (this.sacar(valor)) {
			destino.depositar(valor);
			return true;
		}

		return false;
	}

	public void depositar(double valor) {

		if (valor > 0) {
			this.saldo += valor;
		} else {
			System.out.println("\nImpossível depositar valores negativos.");
		}
	}

	public double calcularRendimento() {
		return this.saldo * 0.1;
	}

	public String recuperarDadosParaImpressao() throws Exception {

		return "\n\nInformações da Conta:\n\n" + "\nTipo da conta: " + this.getTipo() + "\nNúmero da conta: "
				+ this.numero + "\nAgencia: " + this.agencia + "\nSaldo: " + this.saldo + "\nLimite: " + this.limite
				+ "\nData de Abertura: " + this.dataDeAbertura.retornarData() + "\n\nInformações do titular\n\n"
				+ this.titular.retornarInformacoesDoCliente() + "\n";
	}

	public String getAgencia() {
		return this.agencia;
	}

	public int getNumero() {
		return this.numero;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public Cliente getTitular() {
		return this.titular;
	}

	public double getLimite() {
		return this.limite;
	}

	public Data getDataDeAbertura() {
		return this.dataDeAbertura;
	}

}
