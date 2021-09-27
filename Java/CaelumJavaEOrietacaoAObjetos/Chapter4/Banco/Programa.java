class Programa{
	public static void main(String[] args){
		/*Conta minhaConta = new Conta();
		
		minhaConta.titular = "Duke";
		minhaConta.saldo = 1000.0;
		
		System.out.println("Saldo atual:" + minhaConta.saldo);
		
		boolean consegui = minhaConta.saca(1001);
		
		if (consegui) {
			System.out.println("Consegui sacar");
		} else {
			System.out.println("Não consegui sacar");
		}
		
		System.out.println("Saldo atual:" + minhaConta.saldo);
		
		consegui = minhaConta.saca(200);
		
		if (consegui) {
			System.out.println("Consegui sacar");
		} else {
			System.out.println("Não consegui sacar");
		}
		
		System.out.println("Saldo atual:" + minhaConta.saldo);
		
		minhaConta.deposita(500);
		System.out.println("Saldo atual:" + minhaConta.saldo);
		
		
		
		Conta conta1 = new Conta();
		conta1.deposita(100);

		Conta conta2 = conta1;  // linha importante!
		conta2.deposita(200);

		System.out.println(conta1.saldo);
		System.out.println(conta2.saldo);*/
		
		Conta conta1 = new Conta();
		Conta conta2 = new Conta();
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		
		cliente1.nome = "Alberto";
		cliente1.sobrenome = "Alves";
		cliente1.cpf = "10";
		
		cliente2.nome = "Alexandro";
		cliente2.sobrenome = "Santos";
		cliente2.cpf = "20";
		
		conta1.titular = cliente1;
		conta2.titular = cliente2;
		
		conta1.deposita(1000);
		
		System.out.println(conta1.titular.nome);
		System.out.println(conta1.saldo);
		
		conta1.transferePara(conta2, 1001);
		
		System.out.println(conta1.titular.nome);
		System.out.println(conta1.saldo);
		System.out.println(conta2.titular.nome);
		System.out.println(conta2.saldo);
		
		conta1.transferePara(conta2, 500);
		
		System.out.println(conta1.titular.nome);
		System.out.println(conta1.saldo);
		System.out.println(conta2.titular.nome);
		System.out.println(conta2.saldo);
		
	}
}
