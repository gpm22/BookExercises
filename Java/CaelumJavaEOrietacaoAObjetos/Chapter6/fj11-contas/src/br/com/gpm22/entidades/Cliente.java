package br.com.gpm22.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gpm22.Util.Data;
import br.com.gpm22.entidades.contas.Conta;
import br.com.gpm22.entidades.contas.ContaCorrente;
import br.com.gpm22.entidades.contas.SeguroDeVida;
import br.com.gpm22.exceptions.CpfInvalidoException;
import br.com.gpm22.exceptions.DataInvalidaException;
import br.com.gpm22.interfaces.Tributavel;
import br.com.gpm22.services.ClienteServico;
import br.com.gpm22.services.TributavelServico;

public class Cliente implements Comparable<Cliente> {
	private String nome;
	private String sobrenome;
	private String cpf;
	private Data dataDeNascimento;
	private List<Conta> contas;
	private SeguroDeVida seguroDeVida;

	public Cliente(String nome, String sobrenome, String cpf, Data dataDeNascimento1) throws CpfInvalidoException {
		if (!ClienteServico.validarCPF(cpf)) {
			throw new CpfInvalidoException(cpf);
		}

		if (!ClienteServico.validarCPFUnico(cpf)) {
			throw new CpfInvalidoException();
		}

		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento1;
		this.contas = new ArrayList<>();
	}

	public Cliente(String nome, String sobrenome, String cpf, Data dataDeNascimento1, SeguroDeVida seguroDeVida,
			List<Conta> contas) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento1;
		this.seguroDeVida = seguroDeVida;
		this.contas = contas;
	}

	public void mudarCPF(String cpf) {
		if (ClienteServico.validarCPF(cpf)) {
			this.cpf = cpf;
		}
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

		List<Tributavel> tributaveis;

		if (contas.size() > 0) {
			List<ContaCorrente> contasCorrentes = contas.stream()
					.filter((conta) -> conta.getTipo().equals("Corrente"))
					.map((conta) -> (ContaCorrente) conta)
					.collect(Collectors.toList());
			tributaveis = new ArrayList<Tributavel>(contasCorrentes);
		} else {
			tributaveis = new ArrayList<Tributavel>();
		}

		if (seguroDeVida != null) {
			tributaveis.add(seguroDeVida);
		}

		return tributaveis;
	}

	public void setSeguroDeVida(SeguroDeVida seguroDeVida) {
		this.seguroDeVida = seguroDeVida;
	}

	public SeguroDeVida getSeguroDeVida() {
		return this.seguroDeVida;
	}

	@Override
	public String toString() {
		return "Cliente: [ nome: " + this.getNome() + ", sobrenome: " + this.sobrenome + ", cpf: " + this.cpf
				+ ", dataDeNascimento: " + this.getDataDeNascimento() + ", seguroDeVida: " + this.seguroDeVida
				+ ", contas: " + this.contas + ", tributaveis: " + this.getTributaveis() + "]";
	}

	@Override
	public boolean equals(Object object) {

		if (object == null) {
			return false;
		}

		if (!(object instanceof Cliente)) {
			return false;
		}

		Cliente cliente = (Cliente) object;

		return this.cpf.equals(cliente.getCpf());
	}

	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}

	public String retornarInformacoesDoCliente() throws DataInvalidaException {

		Data diaAtual;
		try {
			diaAtual = new Data(30, 4, 2030);
		} catch (DataInvalidaException e) {
			throw e;
		}

		return "Nome completo: " + this.getNome() + " " + this.getSobrenome() + "\nCPF: " + this.getCpf() + "\nIdade: "
				+ this.getDataDeNascimento().calcularIdade(diaAtual) + "\nQuantidade de Contas: " + this.contas.size()
				+ "\nPossui Seguro de vida? " + (this.seguroDeVida == null ? "Não" : "Sim")
				+ "\nNúmero de Tributáveis: " + this.getTributaveis().size() + "\nImposto total: "
				+ TributavelServico.calculaImpostos(this.getTributaveis());

	}

	public int compareTo(Cliente cliente) {
		return (this.nome + " " + this.sobrenome).compareTo((cliente.nome + " " + cliente.sobrenome));
	}

}
