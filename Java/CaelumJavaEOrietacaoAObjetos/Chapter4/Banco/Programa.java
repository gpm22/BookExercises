class Programa {
	public static void main(String[] args) {

		Conta conta1 = new Conta();
		Conta conta2 = new Conta();

		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();

		Data data1 = new Data();
		Data data2 = new Data();

		data1.dia = 1;
		data1.mes = 10;
		data1.ano = 2020;

		data2.dia = 30;
		data2.mes = 9;
		data2.ano = 2018;

		cliente1.nome = "Alberto";
		cliente1.sobrenome = "Alves";
		cliente1.cpf = "10";

		cliente2.nome = "Alexandro";
		cliente2.sobrenome = "Santos";
		cliente2.cpf = "20";

		conta1.titular = cliente1;
		conta1.dataDeAbertura = data1;
		conta1.agencia = "A";
		conta1.limite = 1000.5;

		conta2.titular = cliente2;
		conta2.dataDeAbertura = data2;
		conta2.agencia = "B";
		conta2.limite = 2000;

		conta1.deposita(1000);

		System.out.println(conta1.recuperarDadosParaImpressão());

		conta1.transferePara(conta2, 1001);

		System.out.println(conta1.recuperarDadosParaImpressão());
		System.out.println(conta2.recuperarDadosParaImpressão());

		conta1.transferePara(conta2, 500);

		System.out.println(conta1.recuperarDadosParaImpressão());
		System.out.println(conta2.recuperarDadosParaImpressão());

	}
}