class Conta{
	int numero;
	Cliente titular;
	String agencia;
	Data dataDeAbertura;
	double saldo;
	double limite;
	
	
	boolean saca(double valor){
	
		if(this.saldo < valor){
			return false;
		}
			
		this.saldo -= valor;
		return true;
	}
	
	boolean transferePara(Conta destino, double valor){
	
		if(this.saca(valor)){
			destino.deposita(valor);
			return true;
		}
		
		return false;
	}
	
	void deposita(double valor){
		this.saldo += valor;
	}
	
	double calcularRendimento(){
		return this.saldo*0.1;
	}
	
	String recuperarDadosParaImpressão(){
		
		 return "\nNome completo do titular: " +
			this.titular.nome + " " + 
			this.titular.sobrenome + "\n" +
			"CPF do titular " + 
			this.titular.cpf +"\n" + 
			"Agencia: " + this.agencia + "\n" +
			"Saldo: " + this.saldo.toString() + "\n" +
			"Limite: " + this.limite.toString() + "\n" +
			"Data de Abertura " + this.dataDeAbertura.retornarData() + "\n";
				
	}
}

























