#Listing 4-7. A Naïve Solution to the Celebrity Problem

def naive_celeb(G):
	n = len(G)
	for u in range(n):			#For every candidate...
		for v in range(n):		#For everyone else...
			if u == v: continue	#Same person? Skip.
			if G[u][v]: break	#Candidate knows other
			if not G[v][u]: break	#Other doesn't know candidate
		else:
			return u		#No breaks? Celebrity!
	return None				#Couldn't find anyone
	
	
#Listing 4-8. A Solution to the Celebrity Problem

def celeb(G):
	n = len(G)
	u, v = 0, 1			#The first two
	for c in range(2,n+1):		#Others to check
		if G[u][v]: 	u = c	#u knows v? Replace u
		else:		v = c	#Otherwise, replace v
	if u == n:		c = v	#u was replaced last; use v
	else:			c = u	#Otherwise, u is a candidate
	for v in range(n):		#For everyone else...
		if c == v: continue	#Same person? Skip.
		if G[c][v]: break	#Candidate knows other
		if not G[v][c]: break	#Other doesn't know candidate
	else:
		return c		#No breaks? Celebrity!
	return None			#Couldn't find anyone
	
def my_celeb(G):
	ver = list(map(sum, G)) #LENTOOOOOOOOOOOOOOOOO
	
	c = 0
	
	for i in ver:
		if i == 0: break
		c+=1
		
	return c
	"""
	for v in range(n):		#For everyone else...
		if c == v: continue	#Same person? Skip.
		if G[c][v]: break	#Candidate knows other
		if not G[v][c]: break	#Other doesn't know candidate
	else:
		return c		#No breaks? Celebrity!
	return None			#Couldn't find anyone		"""
		

'''import numpy as np

def my_celeb(G):
	new = np.array(G)
	new_2 = []
	for a in new:
		new_2.append(sum(a))
		
	new_2 = np.array(new_2)
	
	d = new_2[new_2==0]
	
	return d'''

if __name__ == '__main__':
	from random import randrange
	from timeit import timeit
	n = 1000
	G = [[randrange(2) for i in range(n)] for i in range(n)]
	c = randrange(n)
	for i in range(n):
		G[i][c] = 1#True
		G[c][i] = 0#False
	
	time1 = timeit('naive_celeb(G)',setup="from __main__ import naive_celeb", globals=globals(), number = 1)
	time2 = timeit('celeb(G)', setup="from __main__ import celeb", globals=globals(), number = 1)
	time3 = timeit('my_celeb(G)', setup="from __main__ import my_celeb", globals=globals(), number = 1)
	print(naive_celeb(G))
	print(celeb(G))
	print(my_celeb(G))
	print("tempo do naive: ", time1, "\ntempo do bolado: ", time2, "\ntempo do meu: ", time3)
	
	#"\nnaive/bolado: ", time1/time2)'

















