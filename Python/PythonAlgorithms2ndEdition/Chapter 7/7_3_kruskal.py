#Listing 7-3. A Naïve Implementation of Kruskal’s Algorithm

def naive_find(C, u):		#Find component rep.
	while C[u] != u:	#Rep. would point to itself
		u = C[u]
	return u

def naive_union(C, u, v):
	u = naive_find(C, u)	#Find both reps
	v = naive_find(C, v)	#Make one refer to the other
	C[u] = v

def naive_kruskal(G):
	E = [(G[u][v],u,v) for u in G for v in G[u]]
	T = set()						#Empty partial solution
	C = {u:u for u in G}					#Component reps
	for _, u, v in sorted(E):				#Edges, sorted by weight
		if naive_find(C, u) != naive_find(C, v):
			T.add((u, v))				#Different reps? Use it!
			naive_union(C, u, v)			#Combine components
	return T
	
#listing 7-4. Kruskal’s Algorithm

def find(C, u):
	if C[u] != u:
		C[u] = find(C, C[u])	# Path compression
	return C[u]

def union(C, R, u, v):#a
	u, v = find(C, u), find(C, v)
	if R[u] > R[v]:		# Union by rank
		C[v] = u
	else:
		C[u] = v
	if R[u] == R[v]:		# A tie: Move v up a level
		R[v] += 1
	
def kruskal(G):
	E = [(G[u][v],u,v) for u in G for v in G[u]]
	T = set()
	C, R = {u:u for u in G}, {u:0 for u in G} # Comp. reps and ranks
	for _, u, v in sorted(E):
		if find(C, u) != find(C, v):
			T.add((u, v))
			union(C, R, u, v)
	return T

#Listing 7-5. Prim’s Algorithm

from heapq import heappop, heappush

def prim(G, s):
	P, Q = {}, [(0, None, s)]
	while Q:
		_, p, u = heappop(Q)
		if u in P: continue
		P[u] = p
		for v, w in G[u].items():
			heappush(Q, (w, u, v))
	return P

#Implementação da solução gananciosa para o problema dos horários

def hora_gananciosa(horarios_1):

	ultima_dl = max(horarios_1, key = lambda x:x[1])
	
	programa = [(0,0) for _ in range(ultima_dl[1])]
	
	#a[0] = a[-len(a)]
	for u in reversed(sorted(horarios_1)):
		
		
		if u[1] < programa[0][1]: continue
		
		for i in range(-1,-len(programa) ,-1):
		
			if programa[i][1] == 0:
				programa[i]= u
				break
				
			elif u[1] > programa[i][1]:
				programa[i], u = u, programa[i]

	
	#retirar os zeros
	
	programa_2 = [b for b in programa if b != (0,0)]
	
		
	return programa_2
	
if __name__ == '__main__':

	horarios = [(1,10),(3,7),(2,9),(4,5),(6,11),(5,3)]
	
	certo = [(5,3), (4,5), (3,7), (2,9), (1,10), (6,11)]
	
	a = hora_gananciosa(horarios)
	print(a)
	print(a==certo)
	
	


