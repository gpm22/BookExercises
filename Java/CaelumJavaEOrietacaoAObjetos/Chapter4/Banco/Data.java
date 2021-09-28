class Data {
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
		if (valor < 10) {
			return "0" + valor;
		} else {
			return "" + valor;
		}
	}

	private boolean validarData(int dia, int mes, int ano) {

		if (dia < 0 || mes < 0 || ano < 0) {
			return false;
		}

		if (mes == 1) {
			if ((dia >= 1 && dia <= 31)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 2) {

			if (this.isAnoBissexto(ano)) {
				if ((dia >= 1 && dia <= 29)) {
					return true;
				} else {
					return false;
				}
			} else {
				if ((dia >= 1 && dia <= 28)) {
					return true;
				} else {
					return false;
				}
			}

		}

		if (mes == 3) {
			if ((dia >= 1 && dia <= 31)) {
				return true;
			} else {
				return false;
			}
		}

		if (mes == 4) {
			if ((dia >= 1 && dia <= 30)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 5) {
			if ((dia >= 1 && dia <= 31)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 6) {
			if ((dia >= 1 && dia <= 30)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 7) {
			if ((dia >= 1 && dia <= 31)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 8) {
			if ((dia >= 1 && dia <= 31)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 9) {
			if ((dia >= 1 && dia <= 30)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 10) {
			if ((dia >= 1 && dia <= 30)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 11) {
			if ((dia >= 1 && dia <= 30)) {
				return true;
			} else {
				return false;
			}

		}

		if (mes == 12) {
			if ((dia >= 1 && dia <= 31)) {
				return true;
			} else {
				return false;
			}

		}

		return false;
	}

	private boolean isAnoBissexto(int ano) {
		return (ano % 4 == 0 && !(ano % 100 == 0 && ano % 400 != 0)) ? true : false;
	}
}
