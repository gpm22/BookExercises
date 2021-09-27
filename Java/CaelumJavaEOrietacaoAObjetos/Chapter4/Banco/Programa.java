class Programa {
	public static void main(String[] args) {
		try {
			Data data1 = new Data(1, 10, 2020);
			Data data2 = new Data(28, 2, 2001);

			Cliente cliente1 = new Cliente("Alberto", "Alves", "10", 30);
			Cliente cliente2 = new Cliente("Alexandro", "Santos", "20", 18);

			Conta conta1 = new Conta(cliente1, "A", data1, 1000.5);
			Conta conta2 = new Conta(cliente2, "B", data2, 2000);

			conta1.depositar(1000);

			System.out.println(conta1.recuperarDadosParaImpressão());

			conta1.transferirPara(conta2, 1001);

			System.out.println(conta1.recuperarDadosParaImpressão());
			System.out.println(conta2.recuperarDadosParaImpressão());

			conta1.transferirPara(conta2, 500);

			System.out.println(conta1.recuperarDadosParaImpressão());
			System.out.println(conta2.recuperarDadosParaImpressão());
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}