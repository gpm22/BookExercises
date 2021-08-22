#TOPOLOGICAL SORTING

def naive_topsort(G, S=None):
	#Default: All nodes
	if S is None: S = set(G)
	#Base case, single node
	if len(S) == 1: return list(S)
	#Reduction: Remove a node
	v = S.pop()
	#Recursion (assumption), n-1
	seq = naive_topsort(G, S)
	min_i = 0
	for i, u in enumerate(seq):
		#After all dependencies
		if v in G[u]: min_i = i+1
	seq.insert(min_i, v)
	return seq
	
def topsort(G):
	#The in-degree for each node
	count = dict((u, 0) for u in G)
	for u in G:
		for v in G[u]:
			#Count every in-edge
			count[v] += 1
	#Valid initial nodes
	Q = [u for u in G if count[u] == 0]
	#The result
	S = []
	#While we have start nodes...
	while Q:
		#Pick one
		u = Q.pop()
		#Use it as first of the rest
		S.append(u)
		for v in G[u]:
			#"Uncount" its out-edes		
			count[v] -= 1
			#New valid start nodes?
			if count[v] == 0:
			#Deal with them next
				Q.append(v)
	return S
	
def topsort_2(G):
	#The in-degree for each node
	count = dict((u, 0) for u in G)
	for u in G:
		for v in G[u]:
			#Count every in-edge
			count[v] += 1
	#Valid initial nodes
	Q = [u for u in G if count[u] == 0]
	#The result
	S = []
	#While we have start nodes...
	while Q:
		#Pick one
		u = Q.pop()
		#Use it as last of the rest
		S.append(u)
		S = S[::-1]
		for v in G[u]:
			#"Uncount" its out-edes		
			count[v] -= 1
			#New valid start nodes?
			if count[v] == 0:
			#Deal with them next
				Q.append(v)
	return S
	
if __name__ == '__main__':
	from time import time
	import networkx as nx
	from random import randint
	
	G=nx.gnp_random_graph(30,0.5,directed=True) # 10, 0.5

	DAG = nx.DiGraph([(u,v,{'weight':randint(-10,10)}) for (u,v) in G.edges() if u<v])

	print("is a DAG?: ", nx.is_directed_acyclic_graph(DAG))
	DAG_1 = list(DAG)
	#start_1 = time()
	#G_1 = naive_topsort(DAG)
	#end_1 = time()
	start_2 = time()
	G_2 = topsort(DAG)
	end_2 = time()
	start_3 = time()
	G_3 = list(nx.topological_sort(DAG))
	end_3 = time()
	start_4 = time()
	G_4 = topsort(DAG)
	end_4 = time()

	#print("Naive == Top? ",G_1 == G_2)
	#print("Naive == Native Top? ", G_1 == G_3)
	print("Top == Native Top? ", G_3 == G_2)
	print("Top2 == Native Top? ", G_4 == G_2)
	#print('naive topsort: ',end_1-start_1,' topsort: ', end_2-start_2, "topsort native: ", end_3- start_3)
	print(' topsort: ', end_2-start_2, "topsort native: ", end_3- start_3,'topsort2 :',end_4- start_4)



	
	
