#Código para encontrar qualquer número do universo.

import sys

import math

numero = float(input('Entre com o número a ser verificado:\n'))


#verificar se o número é positivo

OLA = 10000000

if numero == 0:
	print("\nO número é zero!!!!!!!")
	
	
elif math.sqrt(numero**2) < 1:


	if numero> 0:

		minnum = 10**-100
		maxnum = 1
	
		a=2
		b = 0
		c = 0
	
	
		while math.sqrt(((maxnum/minnum))**2) > 1:
	
			if c == 0:
				metade = maxnum/a
			else:
				metade = minnum*1.5
	
			if numero > metade:
				minnum = metade
				c = 1
			else:
				maxnum = metade
				c = 0
			
			
			if b > OLA:
				break
			
		#print(metade)
			
		#print("\nValor max:", maxnum)
		#print("\nValor min:", minnum) 
		
		#a*=2
		b+=1
		
	else:
	
		minnum = -1
		maxnum = 10**-100
	
		a=2
		b = 0
		c = 0
	
	
		while math.sqrt(((minnum/maxnum))**2) > 1:
	
			if c == 0:
				metade = minnum/2
			else:
				metade = maxnum*1.5
	
			if numero < metade:
				maxnum = metade
				c = 0
			else:
				minnum = metade
				c = 1
			
			
			if b > OLA:
				break
			
		#print(metade)
			
		#print("\nValor max:", maxnum)
		#print("\nValor min:", minnum) 
		
		#a*=2
		b+=1
		
	

elif numero > 0:

	minnum = -1
	maxnum = sys.maxsize
	
	
	#verificando o limite superior
	while numero>maxnum:

		minnum = maxnum
		maxnum = maxnum**10
		
	
	a=2
	b = 0
	
	c = 0
	
	
	while math.sqrt(((maxnum/minnum))**2) > 1:
	
		if c == 0:
			metade = maxnum/a
		else:
			metade = minnum*1.5
	
		if numero > metade:
			minnum = metade
			c = 1
		else:
			maxnum = metade
			c = 0
			
			
		if b > OLA:
			break
			print('moio\n')
			
		b+=1
		
		
	
	
	
else:
	minnum = -sys.maxsize
	maxnum = 1
	
	#verificando o limite inferior
	while numero<minnum:
	
		maxnum = minnume
		minnum = -sys.maxsize**10
		
	a=2
	b = 0
	c = 0
	
	
	while math.sqrt(((minnum/maxnum))**2) > 1:
	
		if c == 0:
			metade = minnum/a
		else:
			metade = maxnum*1.5
	
		if numero < metade:
			maxnum = metade
			c = 0
		else:
			minnum = metade
			c = 1
			
			
		if b > OLA:
			print('moio\n')
			break
			
		b+=1
		
print("O seu número é:", metade, '\n')
print("Você entrou com:", numero)
print("\nNúmero de rodadas:", b)
print("\nValor do max/min:",maxnum/minnum)
print("\nValor max:", maxnum)
print("\nValor min:", minnum)  
		








