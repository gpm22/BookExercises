class Data {
	int dia;
	int mes;
	int ano;

	String formatarDiaOuMes(int valor) {
		if (valor < 10) {
			return "0" + valor;
		} else {
			return "" + valor;
		}
	}

	String retornarData() {

		return this.formatarDiaOuMes(this.dia) + "/" + this.formatarDiaOuMes(this.mes) + "/" + this.ano;

	}
}
