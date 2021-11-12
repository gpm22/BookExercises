package br.com.gpm22.banco;

import java.util.ArrayList;
import java.util.List;

import br.com.gpm22.banco.contas.Conta;
import br.com.gpm22.banco.contas.SeguroDeVida;
import br.com.gpm22.interfaces.Tributavel;
import br.servicos.TributavelServico;

public class Cliente {
	private String nome;
	private String sobrenome;
	private String cpf;
	private Data dataDeNascimento;
	private List<Conta> contas;
	private SeguroDeVida seguroDeVida;
	private List<Tributavel> tributaveis;

	public Cliente(String nome, String sobrenome, String cpf, Data dataDeNascimento) {
		if (this.validarCPF(cpf)) {
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.cpf = cpf;
			this.dataDeNascimento = dataDeNascimento;
			this.contas = new ArrayList<>();
			this.tributaveis = new ArrayList<>();
		}
	}

	public void mudarCPF(String cpf) {
		if (validarCPF(cpf)) {
			this.cpf = cpf;
		}
	}

	private boolean validarCPF(String cpf) {
		return true;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setDataDeNascimento(Data dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public int getIdade(Data diaAtual) {
		return this.dataDeNascimento.calcularIdade(diaAtual);
	}

	public Data getDataDeNascimento() {
		return this.dataDeNascimento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void addConta(Conta conta) {
		this.contas.add(conta);
	}

	public List<Tributavel> getTributaveis() {
		return this.tributaveis;
	}

	public void setSeguroDeVida(SeguroDeVida seguroDeVida) {
		this.seguroDeVida = seguroDeVida;
	}

	public SeguroDeVida getSeguroDeVida() {
		return this.seguroDeVida;
	}

	public String retornarInformacoesDoCliente() throws Exception {

		Data diaAtual;
		try {
			diaAtual = new Data(30, 4, 2030);
		} catch (Exception e) {
			throw e;
		}

		return "Nome completo: " + this.getNome() + " " + this.getSobrenome() + "\nCPF: " + this.getCpf() + "\nIdade: "
				+ this.getDataDeNascimento().calcularIdade(diaAtual) + "\nQuantidade de Contas: " + this.contas.size()
				+ "\nPossui Seguro de vida? " + (this.seguroDeVida == null ? "Não" : "Sim")
				+ "\nNúmero de Tributáveis: " + this.tributaveis.size() + "\nImposto total: "
				+ TributavelServico.calculaImpostos(this.tributaveis);

	}

}
