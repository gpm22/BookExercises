#Listing 4-9. A Naïve Algorithm for Topological Sorting

def naive_topsort(G, S=None):
	if S is None: S = list(G)#set(G)		#Default: All nodes
	if len(S) == 1: return list(S)	#Base case, single node
	v = S.pop()				#Reduction: Remove a node
	seq = naive_topsort(G, S)		#Recursion (assumption), n-1
	min_i = 0
	for i, u in enumerate(seq):
		if v in G[u]: min_i = i+1	#After all dependencies
	seq.insert(min_i, v)
	return seq
	
#Listing 4-10. Topological Sorted of a Directed, Acyclic Graph

def topsort(G):
	#A = set(range(len(G)))
	count = dict((u, 0) for u in G)	#The in-degree for each node

	for u in G:
		for v in G[u]:
			count[v] += 1		#Count every in-edge
	#print(count)
	Q = [u for u in G if count[u] == 0]	#Valid initial nodes
	S = []					#The result
	while Q:				#While we have start nodes...
		u = Q.pop()			#Pick one
		#print(u)
		S.append(u)			#Use it as first of the rest
		for v in G[u]:
			count[v] -= 1		#"Uncount" its out-edges
			if count[v] == 0:	#New valid start nodes?
				Q.append(v)	#Deal with them next
	return S


def topsort_2(G_2): #invertido
	#A = set(range(len(G)))
	G = G_2.copy()
	count = dict((u, 0) for u in G)	#The out-degree for each node
	for u in G: 
		count[u] = len(G[u])		#Count every out-edge
	#print(count)
	Q = [u for u in G if count[u] == 0]	#Valid final nodes
	S = []					#The result
	a = 0					#Anti-loop infinito
	while Q:				#While we have final nodes...
		#print(Q)
		u = Q.pop()			#Pick one
		#print(u)
		S.append(u)			#Use it as first of the rest
				
		for n in G:
			if n != u:
				if u in G[n]:
					count[n] -= 1		#retira 1 edge dos vizinhos
					G[n].remove(u)		#remove nó do viziho
				if count[n] == 0:		#adiciona novos nós finais
					Q.append(n)
		G.pop(u)					#retira nó do grafo
		if a > 2*len(G_2):
			break
		a+=1
		#print(count)
		#print(Q)
				
	S.reverse()						#inverte sequência
	return S

#Listing 5-8. Topological Sorting Based on Depth-First Search

def dfs_topsort(G):
	S, res = set(), []		#History and result
	
	def recurse(u):		#Traversal subroutine
		if u in S: return	#Ignore visited nodes
		S.add(u)		#Otherwise: Add to history
		for v in G[u]:
			recurse(v)	#Recurse through neighbors
		res.append(u)		#Finished with u: Append it
		
	for u in G:
		recurse(u)		#Cover entire graph
	res.reverse()			#It's all backward so far
	return res

#Gerador de Directional acyclic graphs (DAGs)
from random import randrange
 
def gen_DAG(V):
		
	Nos = list(range(1,V))
	G = {}
	
	#criar os vizinhos 
	
	for no in Nos:
		G[no] = []
		for i in range(no+1, randrange(no+2,V+2)):
			G[no].append(i)
		G[no] = set(G[no][:])
		
	
	G[V] = set()
	
	return G

def test_top():
	#criar o DAG
	
	A = randrange(1000)
	
	G = gen_DAG(A)
	
	#G = {'a':set('bf'), 'b':set('cdf'), 'c':set('d'), 'd':set('ef'), 'e':set('f'), 'f':set('')}
	
	#print(G)
	
	S_1 = topsort(G)
	S_2 = topsort_2(G)
	
	
	#print(S_1)
	#print(S_2)
	
	print(S_1==S_2)


if __name__ == '__main__':
	#from timeit import timeit
	
	test_top()

	#M = [2, 2, 0, 5, 3, 5, 7, 4]
	#M = [randrange(1000) for i in range(1000)]
	
	#G = [[1, 5], [2,3,5], [3], [4, 5], [5], []]
	
	'''G = {'a':set('bf'), 'b':set('cdf'), 'c':set('df'), 'd':set('ef'), 'e':set('f'), 'f':set('')}
	
	time1 = timeit('naive_topsort(G)',setup="from __main__ import naive_topsort", globals=globals(), number = 1)
	print(naive_topsort(G))
	time2 = timeit('topsort(G)', setup="from __main__ import topsort", globals=globals(), number = 1)
	print(topsort(G))
	
	print("tempo do naive: ", time1, "\ntempo do bolado: ", time2)'''
