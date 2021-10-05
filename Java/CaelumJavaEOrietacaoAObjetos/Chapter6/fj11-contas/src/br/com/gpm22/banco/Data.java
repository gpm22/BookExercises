package br.com.gpm22.banco;

public class Data {
	int dia;
	int mes;
	int ano;

	public Data(int dia, int mes, int ano) throws Exception {
		if (this.validarData(dia, mes, ano)) {
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
		} else {
			throw new Exception("\nData inválida!");
		}
	}

	public String retornarData() {

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
}
