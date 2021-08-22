#Listing 8-1. A Naïve Solution to the Longest Increasing Subsequence Problem

from itertools import combinations

def naive_lis(seq):
	for length in range(len(seq), 0, -1):
		for sub in combinations(seq, length):
			if list(sub) == sorted(sub):
				return sub

#funções para série de fibonacci

def fib(i):
	if i < 2: return 1
	return fib(i-1) + fib(i-2)
	
	# trava sinistro em i = 100


#Listing 8-2. A Memoizing Decorator MUITO EFICIENTE

from functools import wraps

def memo(func):
	cache = {}

	@wraps(func)	
	def wrap(*args):
		if args not in cache:
			cache[args] = func(*args)
		return cache[args]
	return wrap


#fibonacci usando memo como decorator

@memo
def fib_2(i):
	if i < 2: return 1
	return fib(i-1) + fib(i-2)
	
#Cálculo recurssivo das combinações C(n,k):

@memo
def C(n,k):
	if k == 0: return 1
	if n == 0: return 0
	return C(n-1, k-1) + C(n-1, k)

#Cálculo iterativo das combinações C(n,k):

from collections import defaultdict 

def C_2(n,k):
	C_1 = defaultdict(int)
	for row in range(n+1):
		C_1[row, 0] = 1
		for col in range(1, k+1):
			C_1[row, col] = C_1[row-1,col-1] + C_1[row-1,col]
	#print(C_1)
	return C_1[n,k]	

#Listing 8-3. Recursive, Memoized DAG Shortest Path

def rec_dag_sp(W, s, t):		#Shortest path from s to t
	@memo				#Memoize f
	def d(u):			#Distance from u to t
		if u == t: return 0	#We're there!
		return min(W[u][v]+d(v) for v in W[u]) #Best of every first step
	return d(s)			#Apply f to actual start node

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

#Listing 8-4. DAG Shortest Path

def dag_sp(W, s, t): 		  #Shortest path from s to t
	d = {u:float('inf') for u in W} #Distance estimates
	d[s] = 0 		  #Start node: Zero distance
	for u in topsort(W): 	  #In top-sorted order...
		if u == t: break #Have we arrived?
		for v in W[u]:   #For each out-edge ...
			d[v] = min(d[v], d[u] + W[u][v]) #Relax the edge
	return d[t] 		  #Distance to t (from s)


#Listing 8-5. A Memoized Recursive Solution to the Longest Increasing Subsequence Problem

def rec_lis(seq):		#Longest increasing subseq.
	@memo
	def L(cur):		#Longest ending at seq[cur]
		res = 1	#Length is at least 1
		for pre in range(cur):	#Potential predecessors
			if seq[pre] <= seq[cur]:	    #A valid (smaller) predec.
				res = max(res, 1 + L(pre)) #Can we improve the solution?
		return res
	return max(L(i) for i in range(len(seq)))	    #The longest of them all


#Listing 8-6. A Basic Iterative Solution to the Longest Increasing Subsequence Problem

def basic_lis(seq):
	L = [1] * len(seq)
	for cur, val in enumerate(seq):
		for pre in range(cur):
			if seq[pre] <= val:
				L[cur] = max(L[cur], 1 + L[pre])
	return max(L)


#Listing 8-7. Longest Increasing Subsequence
from bisect import bisect

def lis(seq):				#Longest increasing subseq.
	end = []			#End-values for all lengths
	for val in seq:		#Try every value, in order
		idx = bisect(end, val)	#Can we build on an end val?
		if idx == len(end): end.append(val)	#Longest seq. extended
		else: end[idx] = val	#Prev. endpoint reduced
	return len(end)	#The longest we found
	
#Listing 8-8. A Memoized Recursive Solution to the LCS Problem

def rec_lcs(a,b):	#Longest common subsequence
	@memo		#L is memoized
	def L(i,j):	#Prefixes a[:i] and b[:j]
		if min(i,j) < 0: return 0	#One prefix is empty
		if a[i] == b[j]: return 1 + L(i-1,j-1)	#Match! Move diagonally
		return max(L(i-1,j), L(i,j-1))	#Chop off either a[i] or b[j]
	return L(len(a)-1,len(b)-1)		#Run L on entire sequences

#Listing 8-9. An Iterative Solution to the Longest Common Subsequence (LCS)

def lcs(a,b):
	n, m = len(a), len(b)
	pre, cur = [0]*(n+1), [0]*(n+1)	#Previous/current row
	for j in range(1,m+1):			#Iterate over b
		pre, cur = cur, pre		#Keep prev., overwrite cur.
		for i in range(1,n+1):		#Iterate over a
			if a[i-1] == b[j-1]:	#Last elts. of pref. equal?
				cur[i] = pre[i-1] + 1	#L(i,j) = L(i-1,j-1) + 1
			else:			#Otherwise...
				cur[i] = max(pre[i], cur[i-1])	#max(L(i,j-1),L(i-1,j))
	return cur[n]	#L(n,m)

#Listing 8-10. A Memoized Recursive Solution to the Unbounded Integer Knapsack Problem

def rec_unbounded_knapsack(w, v, c):	#Weights, values and capacity
	@memo					#m is memoized
	def m(r):		#Max val. w/remaining cap. r
		if r == 0: return 0		#No capacity? No value
		val = m(r-1)			#Ignore the last cap. unit?
		for i, wi in enumerate(w):	#Try every object
			if wi > r: continue	#Too heavy? Ignore it
			val = max(val, v[i] + m(r-wi)) #Add value, remove weight
		return val		#Max over all last objects
	return m(c)			#Full capacity available


#Listing 8-11. An Iterative Solution to the Unbounded Integer Knapsack Problem

def unbounded_knapsack(w, v, c):
	m = [0]
	for r in range(1,c+1):
		val = m[r-1]
		for i, wi in enumerate(w):
			if wi > r: continue
			val = max(val, v[i] + m[r-wi])
		m.append(val)
	return m[c]

#Listing 8-12. A Memoized Recursive Solution to the 0-1 Knapsack Problem

def rec_knapsack(w, v, c):	#Weights, values and capacity
	@memo			#m is memoized
	def m(k, r):		#Max val., k objs and cap r
		if k == 0 or r == 0: return 0	#No objects/no capacity
		i = k-1			#Object under consideration
		drop = m(i, r)		#What if we drop the object?
		if w[i] > r: return drop	#Too heavy: Must drop it
		return max(drop, v[i] + m(i, r-w[i])) #Include it? Max of in/out
	return m(len(w), c)	#All objects, all capacity
	
	
#Listing 8-13. An Iterative Solution to the 0-1 Knapsack Problem

def knapsack(w, v, c):					#Returns solution matrices
	n = len(w)					#Number of available items
	m = [[0]*(c+1) for i in range(n+1)]		#Empty max-value matrix
	P = [[False]*(c+1) for i in range(n+1)]	#Empty keep/drop matrix
	for k in range(1,n+1):				#We can use k first objects
		i = k-1				#Object under consideration
		for r in range(1,c+1):			#Every positive capacity
			m[k][r] = drop = m[k-1][r]	#By default: drop the object
			if w[i] > r: continue		#Too heavy? Ignore it
			keep = v[i] + m[k-1][r-w[i]]	#Value of keeping it
			m[k][r] = max(drop, keep)	#Best of dropping and keeping
			P[k][r] = keep > drop	#Did we keep it?
	return m, P	#Return full results	

#Listing 8-14. Memoized Recursive Function for Expected Optimal Search Cost

def rec_opt_tree(p):
	@memo
	def s(i,j):
		if i == j: return 0
		return s(i,j-1) + p[j-1]
	
	@memo
	def e(i,j):
		if i == j: return 0
		sub = min(e(i,r) + e(r+1,j) for r in range(i,j))
		return sub + s(i,j)
	return e(0,len(p))

#Listing 8-15. An Iterative Solution to the Optimal Search Tree Problem

from collections import defaultdict

def opt_tree(p):
	n = len(p)
	s, e = defaultdict(int), defaultdict(int)
	for k in range(1,n+1):
		for i in range(n-k+1):
			j = i + k
			s[i,j] = s[i,j-1] + p[j-1]
			e[i,j] = min(e[i,r] + e[r+1,j] for r in range(i,j))
			e[i,j] += s[i,j]
	return e[0,n]



#Minha função fatorial

@memo
def my_fat(i):
	if i < 2: return 1
	return i*my_fat(i-1)
	
	
	

if __name__ == '__main__':

	fib = memo(fib)
	print('test')
	

