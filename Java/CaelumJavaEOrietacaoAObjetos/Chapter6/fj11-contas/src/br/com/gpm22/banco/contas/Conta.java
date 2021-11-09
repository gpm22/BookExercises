package br.com.gpm22.banco.contas;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.banco.Data;

public class Conta {
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

	public String recuperarDadosParaImpressão() throws Exception {

		Data diaAtual;
		try {
			diaAtual = new Data(30, 4, 2030);
		} catch (Exception e) {
			throw e;
		}

		return "\nNome completo do titular: " + this.titular.getNome() + " " + this.titular.getSobrenome()
				+ "\nCPF do titular: " + this.titular.getCpf() + "\nIdade: " + this.titular.getIdade(diaAtual)
				+ "\nNúmero da conta: " + this.numero + "\nAgencia: " + this.agencia + "\nSaldo: " + this.saldo
				+ "\nLimite: " + this.limite + "\nData de Abertura: " + this.dataDeAbertura.retornarData() + "\n";

	}

	public String getAgencia() {
		return this.agencia;
	}

	public int getNumero() {
		return this.numero;
	}

}
