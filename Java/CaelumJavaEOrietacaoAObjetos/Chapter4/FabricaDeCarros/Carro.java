class Carro{
	String cor;
	String modelo;
	double velocidadeAtual;
	double velocidadeMaxima;
	Motor motor;
	
	void ligar(){
		System.out.println("O carro está ligado");
	}
	
	void acelerar(double valor){
		this.velocidadeAtual += valor;
	}
	
	int pegarValorDaMarcha(){
		if(this.velocidadeAtual <0){
			return -1;
		}
		
		if(this.velocidadeAtual < 40){
			return 1;
		}
		
		if(this.velocidadeAtual< 80){
			return 2;
		}
		
		return 3;
	
	}
	
}
