package br.com.gpm22.banco;

public class Cliente {
	private String nome;
	private String sobrenome;
	private String cpf;
	private int idade;

	public Cliente(String nome, String sobrenome, String cpf, int idade) {
		if (this.validarCPF(cpf)) {
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.cpf = cpf;
			this.idade = idade;
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

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public int getIdade() {
		return this.idade;
	}

	public String getCpf() {
		return this.cpf;
	}

}
