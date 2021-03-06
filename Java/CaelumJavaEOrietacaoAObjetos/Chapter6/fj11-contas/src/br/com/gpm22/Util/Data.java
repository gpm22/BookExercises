package br.com.gpm22.Util;

import br.com.gpm22.exceptions.DataInvalidaException;

public class Data {
	int dia;
	int mes;
	int ano;

	public Data(int dia, int mes, int ano) throws DataInvalidaException {
		if (this.validarData(dia, mes, ano)) {
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
		} else {
			throw new DataInvalidaException(this.formatarDiaOuMes(dia) + "/" + this.formatarDiaOuMes(mes) + "/" + ano);
		}
	}

	public Data(String data) throws DataInvalidaException {
		String[] dataSplited = data.split("/");

		int dia = Integer.parseInt(dataSplited[0]);
		int mes = Integer.parseInt(dataSplited[1]);
		int ano = Integer.parseInt(dataSplited[2]);

		if (this.validarData(dia, mes, ano)) {
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
		} else {
			throw new DataInvalidaException(this.formatarDiaOuMes(dia) + "/" + this.formatarDiaOuMes(mes) + "/" + ano);
		}

	}

	@Override
	public String toString() {

		return this.formatarDiaOuMes(this.dia) + "/" + this.formatarDiaOuMes(this.mes) + "/" + this.ano;

	}

	private String formatarDiaOuMes(int valor) {
		return (valor < 10) ? "0" + valor : "" + valor;
	}

	private boolean validarData(int dia, int mes, int ano) {

		if (dia < 1 || mes < 1 || ano < 0) {
			return false;
		}

		int diasEmFevereiro = this.isAnoBissexto(ano) ? 29 : 28;
		int[] meses = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		int[] diasDoMes = { 31, diasEmFevereiro, 31, 30, 31, 30, 31, 31, 30, 30, 30, 31 };

		for (int i = 0; i < 12; i++) {
			if (mes == meses[i]) {
				return (dia <= diasDoMes[i]) ? true : false;
			}
		}

		return false;
	}

	private boolean isAnoBissexto(int ano) {
		return (ano % 4 == 0 && !(ano % 100 == 0 && ano % 400 != 0)) ? true : false;
	}

	public int calcularIdade(Data diaAtual) {
		int idade = diaAtual.ano - this.ano;

		if (diaAtual.mes > this.mes) {
			return idade;
		}

		if (diaAtual.mes == this.mes && diaAtual.dia >= this.dia) {
			return idade;
		}

		return idade - 1;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (!(object instanceof Data)) {
			return false;
		}

		Data data = (Data) object;

		return this.toString().equals(data.toString());
	}
}
