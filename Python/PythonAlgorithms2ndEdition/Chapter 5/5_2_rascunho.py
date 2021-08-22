#Listing 5-4. Recursive Depth-First Search

def rec_dfs(G, s, S=None):
	if S is None: S = set()	#Initialize the history
	S.add(s)			#We've visited s
	for u in G[s]:			#Explore neighbors
		if u in S: continue	#Already visited: Skip
		rec_dfs(G, u, S)	#New: Explore recursively
		
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
		
		
def dfs(G, s, d, f, S=None, t=0):
	if S is None: S = set()		#Initialize the history
	d[s] = t; t += 1			#Set discover time
	S.add(s)				#We've visited s
	for u in G[s]:				#Explore neighbors
		if u in S: continue		#Already visited. Skip
		t = dfs(G, u, d, f, S, t)	#Recurse; update timestamp
	f[s] = t; t += 1			#Set finish time
	return t				#Return timestamp

