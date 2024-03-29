package br.com.gpm22.entidades.contas;

import br.com.gpm22.Util.Data;
import br.com.gpm22.entidades.Cliente;
import br.com.gpm22.exceptions.DataInvalidaException;
import br.com.gpm22.exceptions.SaldoInsuficienteException;
import br.com.gpm22.exceptions.TipoDiferenteException;
import br.com.gpm22.exceptions.ValorNegativoException;
import br.com.gpm22.interfaces.Tipavel;

public abstract class Conta implements Tipavel, Comparable<Conta> {
	private static int numeros = 1;
	private int numero;
	private Cliente titular;
	private String agencia;
	private Data dataDeAbertura;
	private double saldo;
	private double limite;

	public Conta(int numero, Cliente titular, String agencia, Data dataDeAbertura, double saldo, double limite) {
		this.numero = numero;
		this.titular = titular;
		this.agencia = agencia;
		this.dataDeAbertura = dataDeAbertura;
		this.saldo = saldo;
		this.limite = limite;
	}

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

	public double sacar(double valor) throws ValorNegativoException, SaldoInsuficienteException {

		if (valor < 0) {
			throw new ValorNegativoException("sacar");
		}

		if (this.saldo < valor) {
			throw new SaldoInsuficienteException(this.saldo);
		}

		this.saldo -= valor;
		return this.saldo;
	}

	public double transferirPara(Conta destino, double valor)
			throws TipoDiferenteException, ValorNegativoException, SaldoInsuficienteException {

		if (!destino.getTipo().equals(this.getTipo())) {
			throw new TipoDiferenteException(this, destino);
		}

		if (valor < 0) {
			throw new ValorNegativoException("transferir");
		}

		this.sacar(valor);
		return destino.depositar(valor);

	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public double depositar(double valor) throws ValorNegativoException {

		if (valor > 0) {
			this.saldo += valor;
			return this.saldo;
		} else {
			throw new ValorNegativoException("depositar");
		}
	}

	public double calcularRendimento() {
		return this.saldo * 0.1;
	}

	public String recuperarDadosParaImpressao() throws DataInvalidaException {

		return "\n\nInformações da Conta:\n\n" + "\nTipo da conta: " + this.getTipo() + "\nNúmero da conta: "
				+ this.numero + "\nAgencia: " + this.agencia + "\nSaldo: " + this.saldo + "\nLimite: " + this.limite
				+ "\nData de Abertura: " + this.dataDeAbertura + "\n\nInformações do titular\n\n"
				+ this.titular.retornarInformacoesDoCliente() + "\n";
	}

	@Override
	public String toString() {
		return "Conta: [ tipo: " + this.getTipo() + ", agencia: " + this.agencia + ", numero: " + this.numero
				+ " titular: " + this.titular.getCpf() + ", dataDeAbertura: " + this.dataDeAbertura + ", saldo: "
				+ this.saldo
				+ ", limite: " + this.limite + " ]";
	}

	@Override
	public boolean equals(Object object) {

		if (object == null) {
			return false;
		}

		if (!(object instanceof Conta)) {
			return false;
		}

		Conta conta = (Conta) object;

		return this.getTipo().equals(conta.getTipo()) && this.agencia.equals(conta.getAgencia())
				&& this.numero == conta.getNumero();
	}

	@Override
	public int hashCode() {
		return this.getTipo().hashCode() + this.getAgencia().hashCode() + this.getNumero();
	}

	@Override
	public int compareTo(Conta conta) {
		return this.titular.compareTo(conta.titular);
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
