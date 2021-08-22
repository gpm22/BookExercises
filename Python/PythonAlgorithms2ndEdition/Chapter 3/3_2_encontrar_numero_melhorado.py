#Código melhorado para encontrar qualquer número do universo.

import sys
import math


def faixa_de_valores(valor_max, valor_min, numero_1):

	while numero_1>valor_max:

		valor_min = valor_max
		valor_max = valor_max**10
		print("\nGIGANTESCO, NÉ????????")
		
	return valor_max, valor_min
	

def procura_binaria(valor_max, valor_min, numero_1):


	a = 2
	b = 0
	c = 0
	OLA = 10000000
	
	while math.sqrt(((valor_max/valor_min))**2) > 1:
	
		if c == 0:
			metade = valor_max/a
		else:
			metade = valor_min*1.5
	
		if numero_1 > metade:
			valor_min = metade
			c = 1
		else:
			valor_max = metade
			c = 0
			
			
		if b > OLA:
			print('moio\n')
			break
			
		b+=1
		
		
	return (valor_max+valor_min)/2, valor_max, valor_min, b
	
	
if __name__ == '__main__':
	numero = float(input('Entre com o número a ser verificado:\n'))
	if numero == 0:
		print("\nO número é zero!!!!!!!")
		
		
	elif math.sqrt(numero**2) < 1:
		if numero> 0:
			minnum = 10**-100
			maxnum = 1
			
			metadinha, maxnum_1, minnum_1, n = procura_binaria(maxnum , minnum , numero)
			
		else:
			minnum = 10**-100
			maxnum = 1
			numero_2 = -1*numero
			metadinha_2, maxnum_2, minnum_2, n = procura_binaria(maxnum , minnum , numero_2)
			metadinha, maxnum_1, minnum_1 = -1*metadinha_2, -1*maxnum_2, -1*minnum_2
			
	elif numero > 0:
		minnum = -0.1
		maxnum = sys.maxsize
		
		maxnum_3, minnum_3 = faixa_de_valores(maxnum, minnum, numero)
		
		metadinha, maxnum_1, minnum_1, n = procura_binaria(maxnum_3 , minnum_3 , numero)
		
	else:
		minnum = -0.1
		maxnum = sys.maxsize	
		numero_2 = -1*numero
		
		maxnum_3, minnum_3 = faixa_de_valores(maxnum, minnum, numero)
		
		metadinha_2, maxnum_2, minnum_2, n = procura_binaria(maxnum_3 , minnum_3 , numero_2)
		metadinha, maxnum_1, minnum_1 = -1*metadinha_2, -1*maxnum_2, -1*minnum_2
			
	if numero != 0:
		print("O seu número é:", metadinha, '\n')
		print("Você entrou com:", numero)
		print("\nNúmero de rodadas:", n)
		print("\nValor do max/min:",maxnum_1/minnum_1)
		print("\nValor max:", maxnum_1)
		print("\nValor min:", minnum_1)
		print("\nPorcentagem de diferença do número real para o número encontrado:\n", 100*(metadinha-numero)/metadinha, '%') 
