#Listing 5-1. Walking Through a Connected Component of a Graph Represented Using Adjacency Sets

def walk(G, s, S=set()):			 #Walk the graph from node s
						 #S são os nós a serem evitados
	P, Q = dict(), set()			 #Predecessors + "to do" queue
	P[s] = None				 #s has no predecessor
	Q.add(s)				 #We plan on starting with s
	while Q:				 #Still nodes to visit
		u = Q.pop()
		#print('nó visitado:', u, '\n')			 #Pick one, arbitrarily
		for v in G[u].difference(P, S): #New nodes?
			#print('nó a ser visitado: ', v, '\n')
			Q.add(v)		 #We plan to visit them!
			P[v] = u		 #Remember where we came from
	return P				 #The traversal tree
	
#Listing 5-2. Finding Connected Components

def components(G):			#The connected components
	comp = []
	seen = set()			#Nodes we've already seen
	for u in G:			#Try every starting point
		if u in seen: continue	#Seen? Ignore it
		C = walk(G, u)		#Traverse component
		print(C)
		seen.update(C)		#Add keys of C to seen
		comp.append(C)		#Collect the components
	return comp

#Listing 5-3. Recursive Tree-Traversal

def tree_walk(T, r):		 #Traverse T from root r
	for u in T[r]:		 # For each child. . .
		tree_walk(T, u) # ... traverse its subtree
		
#Listing 5-4. Recursive Depth-First Search

def rec_dfs(G, s, S=None):
	if S is None: S = set()	#Initialize the history
	S.add(s)			#We've visited s
	for u in G[s]:			#Explore neighbors
		if u in S: continue	#Already visited: Skip
		rec_dfs(G, u, S)	#New: Explore recursively

#Listing 5-7. Depth-First Search with Timestamps

'''timestamps: d-Discovered, 
f-finish(backtraking), t=time(iteration)
'''

def dfs(G, s, d, f, S=None, t=0):
	if S is None: S = set()		#Initialize the history
	d[s] = t; t += 1			#Set discover time
	S.add(s)				#We've visited s
	for u in G[s]:				#Explore neighbors
		if u in S: continue		#Already visited. Skip
		t = dfs(G, u, d, f, S, t)	#Recurse; update timestamp
	f[s] = t; t += 1			#Set finish time
	return t				#Return timestamp

#versão iterativa do código anterior

def dfs_2(G, s):
	S, Q, Res = set(), [], []			#Visited-set and queue
	d, f = {}, {}				#Lista com os tempos
	t = 0					#tempo inicial
	Q.append((1, s))				#We plan on visiting s
	
	while Q:			#Planned nodes left?
		u, v = Q.pop()		#Get one
		if v == None:		#backtracking
			f[u] = t; t+=1
			continue
		if v in S: continue	#Already visited? Skip it
		S.add(v)		#We've visited it now
		d[v]=t; t+=1		#Set discover time
		Q.append((v, None))	#adiconar bactracking
		for w in G[v]:
			Q.append((v,w)) #Schedule all neighbors
		#Q.extend(G[u])		
		Res.append(v)
	return Res, d, f		#Report u,d, and f as visited
	

#Versão anterior com função para pre e postorder

def dfs_3(G, s, prehook = None, posthook = None):
	S, Q, Res = set(), [], []			#Visited-set and queue
	d, f = {}, {}				#Lista com os tempos
	t = 0					#tempo inicial
	Q.append((1, s))				#We plan on visiting s
	
	while Q:			#Planned nodes left?
		u, v = Q.pop()		#Get one
		if v == None:		#backtracking
			f[u] = t; t+=1
			#posthook
			continue
		if v in S: continue	#Already visited? Skip it
		#prehook
		S.add(v)		#We've visited it now
		d[v]=t; t+=1		#Set discover time
		Q.append((v, None))	#adiconar bactracking
		for w in G[v]:
			Q.append((v,w)) #Schedule all neighbors
		#Q.extend(G[u])		
		Res.append(v)
	return Res, d, f		#Report u,d, and f as visited
	

#Listing 5-5. Iterative Depth-First Search

def iter_dfs(G, s):
	S, Q = set(), []		#Visited-set and queue
	Q.append(s)			#We plan on visiting s
	while Q:			#Planned nodes left?
		u = Q.pop()		#Get one
		if u in S: continue	#Already visited? Skip it
		S.add(u)		#We've visited it now
		Q.extend(G[u])		#Schedule all neighbors
		yield u		#Report u as visited

#Listing 5-6. A General Graph Traversal Function

def traverse(G, s, qtype=set):
	S, Q = set(), qtype()
	Q.add(s)
	while Q:
		u = Q.pop()
		if u in S: continue
		S.add(u)
		for v in G[u]:
			Q.add(v)
		yield u

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

#Listing 5-9. Iterative Deepening Depth-First Search

def iddfs(G, s):
	yielded = set()				#Visited for the first time

	def recurse(G, s, d, S=None):			#Depth-limited DFS
		if s not in yielded:
			yield s
			yielded.add(s)
		if d == 0: return			 #Max depth zero: Backtrack
		if S is None: S = set()
		S.add(s)
		for u in G[s]:
			if u in S: continue
			for v in recurse(G, u, d-1, S): #Recurse with depth-1
				yield v

	n = len(G)
	for d in range(n):				 #Try all depths 0..V-1
		if len(yielded) == n: break		 #All nodes seen?
		for u in recurse(G, s, d):
			yield u

#Listing 5-10. Breadth-First Search

from collections import deque

def bfs(G, s):
	P, Q = {s: None}, deque([s])		# Parents and FIFO queue
	while Q:
		u = Q.popleft()		# Constant-time for deque
		for v in G[u]:
			if v in P: continue	# Already has parent
			P[v] = u		# Reached from u: u is parent
			Q.append(v)
	return P
	
#Mostra a distância que os nós se encontram do nó de início

def bfs_2(G, s):
	P, Q , D= {s: None}, deque([s]), {s: 0}		# Parents, FIFO queue, and distancias do s	
	k = 0 					#Distância do s
	while Q:
		u = Q.popleft()		# Constant-time for deque
		#print(k)
		for v in G[u]:
			if v in P: continue	# Already has parent
			if v in D.keys(): continue
			D[v] = D[u] + 1		# Reached from u: u is parent
			Q.append(v)
	return D

#Listing 5-11. Kosaraju’s Algorithm for Finding Strongly Connected Components

def tr(G):				#Transpose (rev. edges of) G
	GT = {}
	for u in G: GT[u] = set()	#Get all the nodes in there
	for u in G:
		for v in G[u]:
			GT[v].add(u)	#Add all reverse edges
	return GT

def scc(G):
	GT = tr(G)			#Get the transposed graph
	sccs, seen = [], set()
	for u in dfs_topsort(G):	#DFS starting points
		if u in seen: continue	#Ignore covered nodes
		C = walk(GT, u, seen)	#Don't go "backward" (seen)
		seen.update(C)		#We've now seen C
		sccs.append(C)		#Another SCC found
	return sccs

#versão sem usar grafo transposto e com lista de tempo na ordem crescente

def scc_2(G):
	
	sccs, seen = [], set()
	
	a = list(dfs_topsort(G))
	a.reverse()
	for u in a:	#DFS starting points
		if u in seen: continue	#Ignore covered nodes
		C = walk(G, u, seen)	#Don't go "backward" (seen)
		seen.update(C)		#We've now seen C
		sccs.append(C)		#Another SCC found
		
	#sccs.reverse()
	return sccs


#Gerador de graphs
from random import randrange
 
def gen_G(V):
		
	Nos = list(range(1,V+1))
	G = {}
	
	#criar os vizinhos 
	
	for no in Nos[:-1]:
		G[no] = []
		#for i in range(no+1, randrange(no+1,V+2)):
		#for i in range(no+1, randrange(no+1,V+2)):
		
		
		f1 = no+1#randrange(1,V+1)
		f2 = randrange(no+2,V+2)#randrange(1,V+1)

		#while f1 >= f2:		
		#	f1 = randrange(1,V+1)
		#	f2 = randrange(1,V+1)
		
		k = 0
		for i in range(f1, f2):
		
			G[no].append(i)
			k +=1
			if k > 1: break
		G[no] = set(G[no][:])
		
	
	G[Nos[-1]] = set()
	
	return G


if __name__ == '__main__':

	#G = {'a':set('bcd'), 'b':set('ad'), 'c':set('ad'), 'd':set('abc'), 'e':set('gf'), 'f':set('eg'), 'g':set('ef'), 'h':set('i'), 'i':set('h')} #Conected componentes
	
	#G = {'a':set('b'), 'b':set('ac'), 'c':set('bd'), 'd':set('c')}
	
	#G = {'a':set('bcde'), 'b':set('acde'), 'c':set('abde'), 'd':set('abce'), 'e':set('abcd')}
	#print(walk(G, 'c'))
	
	G = {'a':set('bc'), 'b':set('dei'), 'c':set('d'), 'd':set('ah'), 'e':set('f'), 'f':set('g'), 'g':set('eh'), 'h':set('i'), 'i':set('h')} #SCCs
	
	#print(components(G))
	
	#V = 35#randrange(1,15)
	
	#n = 1#randrange(1,V)
	
	#G = gen_G(V)
	
	#print(G,'\n'*2, n, '\n')
	
	
	'''print(list(iter_dfs(G, n)))
	#print(list(iddfs(G, 'a')))'''
	
	print(G,'\n')
	'''a, b, c = dfs_2(G, 'a')

	for a_1 in a:
		print('nó:', a_1, ' TD:', b[a_1], ' TF:', c[a_1])
	'''
	
	#print(bfs_2(G, n))	

	print(scc(G),'\n')
	print(scc_2(G))
	
	
	
	#Caminho para um nó u
	'''
	path = [u]
	while P[u] is not None:
		path.append(P[u])
		u = P[u]
	
	path.reverse()
	
	print(path)'''











