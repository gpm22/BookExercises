package br.com.gpm22.banco;

import java.util.ArrayList;
import java.util.List;

import br.com.gpm22.banco.contas.Conta;

public class Cliente {
	private String nome;
	private String sobrenome;
	private String cpf;
	private Data dataDeNascimento;
	private List<Conta> contas;

	public Cliente(String nome, String sobrenome, String cpf, Data dataDeNascimento) {
		if (this.validarCPF(cpf)) {
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.cpf = cpf;
			this.dataDeNascimento = dataDeNascimento;
			this.contas = new ArrayList<>();
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

	public String retornarInformacoesDoCliente() {
		return "Nome completo:" + this.getNome() + " " + this.getSobrenome() + "\nCPF: " + this.getCpf()
				+ "\nData de Nascimento: " + this.getDataDeNascimento().retornarData();
	}

}
