#problemas para sequência de fibonacci


#Tentativa recursiva

def fib1(n,a=0):
	if n < 2:
		return n
	else:
		return fib1(n-1) + fib1(n-2)
	

#Tentativa com memorização

memoria = {0:0,1:1}

def fib2(n):
	
	if n in memoria: return memoria[n]
	else:
		memoria[n] = fib2(n-1) + fib2(n-2)
	return memoria[n]
	
#memorização automática

from functools import lru_cache

@lru_cache(maxsize=None) #serve como memorizador
def fib3(n): # same definition as fib2()
	if n < 2: # base case
		return n
	return fib3(n - 2) + fib3(n - 1) # recursive case
	
#simples e brabo

def fib4(n):
	if n == 0: return n
	last: int = 0
	next: int = 1
	for _ in range(1, n):
		last, next = next, last + next
	return next
	
#simples, brabo e com memorização

memoria_2 = {0:0,1:1}

def fib5(n):
	if n in memoria_2: return memoria_2[n]
	last = memoria_2[list(memoria_2.keys())[-2]]
	next = memoria_2[list(memoria_2.keys())[-1]]
	for _ in range(list(memoria_2.keys())[-1], n):
		last, next = next, last + next
	memoria_2[n] = next
	return memoria_2[n]



#gerador de sequências de fibonacci
	
def fib6(n):
	yield 0
	if n > 0: yield 1
	last: int = 0
	next: int = 1
	for _ in range(1, n):
		last, next = next, last + next
		yield next


if __name__ == '__main__':
	from time import time
	c = 100
	start1 = time()
	a= fib5(c)
	end1 = time()
	b= fib4(c)
	end2 = time()
	print(end1-start1, end2-end1)
	print(a, b)
	
	'''for i in fib5(50):
		print(i)'''
