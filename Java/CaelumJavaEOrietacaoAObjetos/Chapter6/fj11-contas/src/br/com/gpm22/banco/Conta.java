package br.com.gpm22.banco;

public class Conta {
	private static int numeros = 0;
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

	boolean sacar(double valor) {

		if (this.saldo < valor) {
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
		}
	}

	public double calcularRendimento() {
		return this.saldo * 0.1;
	}

	public String recuperarDadosParaImpressão() throws Exception{

		Data diaAtual;
		try {
			diaAtual = new Data(30, 4, 2030);
		} catch (Exception e){
			throw e;
		}


		return "\nNome completo do titular: " + this.titular.getNome() + " " + this.titular.getSobrenome()
				+ "\nCPF do titular: " + this.titular.getCpf() + "\nIdade: " + this.titular.getIdade(diaAtual) + "\nNúmero da conta: " + this.numero + "\nAgencia: "
				+ this.agencia + "\nSaldo: " + this.saldo + "\nLimite: " + this.limite + "\nData de Abertura: "
				+ this.dataDeAbertura.retornarData() + "\n";

	}
	
	public String getAgencia() {
		return this.agencia;
	}
	
	public int getNumero() {
		return this.numero;
	}

}
