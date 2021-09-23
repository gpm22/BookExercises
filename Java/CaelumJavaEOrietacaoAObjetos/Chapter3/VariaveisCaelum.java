


public class VariaveisCaelum{
	
	public static void main(String[] args){
		//problema1();
		//problema2();
		//problema3();
		//problema4();
		//problema5();
		//problema6();
		//problema7();
		problema8();
		
	}
	
	
	private static void problema1(){
		
		
		System.out.println(" 1 - Na empresa em que trabalhamos, há tabelas com o gasto de cada mês.\n Para fechar o balanço do primeiro trimestre, precisamos somar o gasto total.\n Sabendo que, em janeiro, foram gastos 15 mil reais, em fevereiro, 23 mil reais\n e, em março, 17 mil reais, faça um programa que calcule e imprima a despesa\n total no trimestre e a média mensal de gastos.\n");
		
		
		int gastosJaneiro 	= 15000;
		int gastosFevereiro = 23000;
		int gastosMarco 	= 17000;
		
		int gastosTotais= gastosJaneiro + gastosFevereiro + gastosMarco;
		double mediaDeGastos = gastosTotais/3.0;
		
		System.out.println(gastosTotais);
		System.out.println(mediaDeGastos);
		
	}
	
	private static void problema2(){
		
		System.out.println("2 - Imprima todos os números de 150 a 300.\n");
		
		
		for(int i=150; i<=300; i++){
			System.out.println(i);
		}
		
	}
	
	private static void problema3(){
		
		System.out.println("3 - Imprima a soma de 1 até 1000.\n");
		
		int soma =0 ;
		
		for(int i = 1; i<=1000; i++){
			soma += i;
		}
		
		System.out.println(soma);
	}
	
	private static void problema4(){
		
		System.out.println("4 - Imprima todos os múltiplos de 3, entre 1 e 100.");
		
		for(int i = 1; i<101; i++){
			if(i%3 == 0){
				System.out.println(i);
			}
		}
	}
	
	private static void problema5(){
		
		System.out.println("5 - Imprima os fatoriais de 0 a 20.");
		
		for(long i = 0; i<20; i++){
			
			System.out.print(String.format("Fatorial de %d: ", i));
			System.out.println(fatorial(i));
		}
				
	}
	
	private static long fatorial(long num){
		long fatorial = 1;
		
		for(long i = num; i>1; i--){
			fatorial *= i;
		}
		
		return fatorial;
	}
	
	private static void problema6(){
		
		System.out.println("6 - Imprima os primeiros números da série de Fibonacci até passar de 100.");
		
		long n_1 = 0;
		
		long n= 1;
		
		int posicao = 1;
		
		System.out.println(String.format("posicao %d: numero %d", posicao, n_1));
		System.out.println(String.format("posicao %d: numero %d", ++posicao, 1));
		
		while(true){
			posicao++;
			
			n += n_1;
			
			System.out.println(String.format("posicao %d: numero %d", posicao, n));
			
			n_1 = n - n_1;
			
			if(n> 100){ break;}
			
		}
	}
	
	private static void problema7(){
		
		System.out.println("7 - Escreva um programa em que, dada uma variável x com algum valor inteiro, temos um novo x de acordo com a seguinte regra:\n\n Se x é par, x = x / 2; \nSe x é impar, x = 3 * x + 1;\nImprime x;\nO programa deve parar quando x tiver o valor final de 1.\nPor exemplo, para x = 13, a saída será:\n40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1\n\n\nExecução:\n\n");
		
		int numero=13;
		
		while(numero != 1){
			
			if(numero % 2 == 0){
				numero /= 2;
			} else {
				numero = 3*numero + 1;
			}
			
			System.out.print(String.format(" %d ->", numero));
			
		}
		
	}
	
	private static void problema8(){
		
		System.out.println("8 - Imprima a seguinte tabela usando fors encadeados:\n1 \n2 4\n3 6 9\n4 8 12 16\nn n*2 n*3 .... n*n \n\n\nExecução:");
		
		int n = 5;
		
		for(int i = 1; i <= n; i++){
			
			for(int j = 1; j<=i; j++){
				
				System.out.print(i*j);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	
}