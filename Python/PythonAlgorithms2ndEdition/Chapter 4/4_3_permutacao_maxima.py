#Funções para encontrar a permutação máxima


#Listing 4-5. A Naïve Implementation of the Recursive Algorithm
#Idea for Finding a Maximum Permutation

def naive_max_perm(M, A=None):
	if A is None: 				#The elt. set not supplied?
		A = set(range(len(M)))		#A = {0, 1, ... , n-1}
	if len(A) == 1: return A		#Base case -- single-elt. A
	B = set(M[i] for i in A)		#The "pointed to" elements
	C = A - B				#"Not pointed to" elements
	if C:					#Any useless elements?		
		A.remove(C.pop())		#Remove one of them
		return naive_max_perm(M, A)	#Solve remaining problem
	return A				#All useful -- return all

#Listing 4-6. Finding a Maximum Permutation

def max_perm(M):				
	n = len(M)				#How many elements?
	A = set(range(n))			#A = {0, 1, ... , n-1}
	count = [0]*n				#C[i] == 0 for i in A
	for i in M:				#All that are "pointed to"
		count[i] += 1			#Increment "point count"
	Q = [i for i in A if count[i] == 0]	#Useless elements
	while Q:				#While useless elts. left...
		i = Q.pop()			#Get one
		A.remove(i)			#Remove it
		j = M[i]			#Who's it pointing to?
		count[j] -= 1			#Not anymore...
		if count[j] == 0:		#Is j useless now?
			Q.append(j)		#Then deal w/it next
	return A				#Return useful elts.


if __name__ == '__main__':

	from timeit import timeit
	from random import randrange 
	#M = [2, 2, 0, 5, 3, 5, 7, 4]
	M = [randrange(1000) for i in range(1000)]
	
	time1 = timeit('naive_max_perm(M)',setup="from __main__ import naive_max_perm", globals=globals(), number = 1)
	print(naive_max_perm(M))
	time2 = timeit('max_perm(M)', setup="from __main__ import max_perm", globals=globals(), number = 1)
	print(max_perm(M))
	
	print("tempo do naive: ", time1, "\ntempo do bolado: ", time2)
