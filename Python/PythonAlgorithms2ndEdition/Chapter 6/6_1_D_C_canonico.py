#Listing 6-1. A General Implementation of the Divide-and-Conquer Scheme

def divide_and_conquer(S, divide, combine):
	if len(S) == 1: return S
	L, R = divide(S)
	A = divide_and_conquer(L, divide, combine)
	B = divide_and_conquer(R, divide, combine)
	return combine(A, B)
	
	
#Código da bissecção pela direita

def bisect_right(a, x, lo=0, hi=None):
	if hi is None:				#Searching to the end
		hi = len(a)
	while lo < hi:				#More than one possibility
		mid = (lo+hi)//2		#Bisect (find midpoint)
		if x < a[mid]: hi = mid	#Value < middle? Go left
		else: lo = mid+1		#Otherwise: go right
	return lo

'''
Sadly, the various functions of the bisect library don’t support the key argument, used in list.sort , for example. You can achieve similar functionality with the so-called decorate, sort, undecorate (or, in this case, decorate, search, undecorate) pattern, or DSU for short:


Exemplo do padrão DSU (Decorate, Sort, Undecorate)

>>> seq = "I aim to misbehave".split()
>>> dec = sorted((len(x), x) for x in seq)
>>> keys = [k for (k, v) in dec]
>>> vals = [v for (k, v) in dec]
>>> vals[bisect_left(keys, 3)]
'aim'
Or, you could do it more compactly:
>>> seq = "I aim to misbehave".split()
>>> dec = sorted((len(x), x) for x in seq)
>>> dec[bisect_left(dec, (3, ""))][1]
'aim'


'''

#Listing 6-2. Insertion into and Search in a Binary Search Tree

class Node:
	lft = None
	rgt = None
	lvl = 1   #alteração para simular AA-tree
	def __init__(self, key, val):
		self.key = key
		self.val = val

def insert(node, key, val):
	if node is None: return Node(key, val)	#Empty leaf: add node here
	if node.key == key: node.val = val		#Found key: replace val
	elif key < node.key:				#Less than the key?
		node.lft = insert(node.lft, key, val)	#Go left
	else:						#Otherwise...
		node.rgt = insert(node.rgt, key, val)	#Go right

	return node

def search(node, key):
	if node is None: raise KeyError	#Empty leaf: it's not here
	if node.key == key: return node.val	#Found key: return val
	elif key < node.key:			#Less than the key?
		return search(node.lft, key)	#Go left
	else:					#Otherwise...
		return search(node.rgt, key)	#Go right
		
#Listing 6-6. The Binary Search Tree, Now with AA-Tree Balancing	

def skew(node):	#Basically a right rotation
	if None in [node, node.lft]: return node	#No need for a skew
	if node.lft.lvl != node.lvl: return node	#Still no need
	lft = node.lft					#The 3 steps of the rotation
	node.lft = lft.rgt
	lft.rgt = node
	return lft					#Switch pointer from parent
	
def split(node):# Left rotation & level incr.
	if None in [node, node.rgt, node.rgt.rgt]: return node #no need for split
	if node.rgt.rgt.lvl != node.lvl: return node #Still no need for split
	rgt = node.rgt 	#The 3 steps of rotation
	node.rgt = rgt.lft
	rgt.lft = node
	rgt.lvl += 1		# This has moved up
	return rgt		# This should be pointed to

def insert(node, key, val):
	if node is None: return Node(key, val)
	if node.key == key: node.val = val
	elif key < node.key:
		node.lft = insert(node.lft, key, val)
	else:
		node.rgt = insert(node.rgt, key, val)
	node = skew(node)	#In case it's backward
	node = split(node)	#In case it's overfull
	return node

class Tree:					# Simple wrapper
	root = None
	def __setitem__(self, key, val):
		self.root = insert(self.root, key, val)
	def __getitem__(self, key):
		return search(self.root, key)
	def __contains__(self, key):
		try: search(self.root, key)
		except KeyError: return False
		return True
		
#Listing 6-3. A Straightforward Implementation of Partition and Select

def partition(seq):
	pi, seq = seq[0], seq[1:]	#Pick and remove the pivot
	#pi, seq = seq[-1], seq[:-1]
	lo = [x for x in seq if x <= pi]	#All the small elements
	hi = [x for x in seq if x > pi]	#All the large ones
	return lo, pi, hi			#pi is "in the right place"
	
# in_partition da net

def partition_3(seq,l=0,h=None):
	
    if h == None: h = len(seq)-1
    i = ( l - 1 )
    x = seq[h]
  
    for j in range(l , h):
        if   seq[j] <= x:
  
            # increment index of smaller element
            i = i+1
            seq[i],seq[j] = seq[j],seq[i]
  
    seq[i+1],seq[h] = seq[h],seq[i+1]
    return (i+1)
	
def in_partition(seq):
	pi = seq.pop()
	#print(pi)
	
	i = 0
	
	j = len(seq)-1
	
	#saber quantos dígitos são menores ou iguais do que pi
	
	n = 0
	
	for dig in seq:
		if dig <= pi:
			n+=1
	
	while i < n:
	
		if seq[i] <= pi: i+=1; continue
		if seq[i] > pi and  seq[j] <= pi:
			#print(seq[i], seq[j], i, j)
			#print(seq)
			seq[i], seq[j] = seq[j], seq[i]
			#print(seq)
			#print('trocaaa')
			i += 1
			j -= 1
				
		else: j -=1

	seq.insert(n, pi)
	return n

	

def select(seq, k):
	lo, pi, hi = partition(seq)		# [<= pi], pi, [>pi]
	m = len(lo)
	if m == k: return pi			#We found the kth smallest
	elif m < k:				#Too far to the left
		return select(hi, k-m-1)	#Remember to adjust k
	else:					#Too far to the right
		return select(lo, k)		#Just use original k here


#função anterior alterada


def partition_and_select(seq, k):
	pi, seq = seq[0], seq[1:]	#Pick and remove the pivot
	lo = [x for x in seq if x <= pi]	#All the small elements
	hi = [x for x in seq if x > pi]	#All the large ones
	
	m = len(lo)
	if m == k: return pi			#We found the kth smallest
	elif m < k:				#Too far to the left
		return select(hi, k-m-1)	#Remember to adjust k
	else:					#Too far to the right
		return select(lo, k)		#Just use original


#Listing 6-4. Quicksort

def quicksort(seq):
	if len(seq) <= 1: return seq # Base case

	lo, pi, hi = partition(seq) # pi is in its place
	
	return quicksort(lo) + [pi] + quicksort(hi) # Sort lo and hi separately
	

#código anterior alterado

def in_quicksort(seq, key = None):
	if len(seq) <= 1: return seq # Base case
	
	if key:
		temp = [key(i) for i in seq]
		
		chave_inicial = {}
		a = 0
		for t in temp:
			chave_inicial[t] = a
		
			a += 1
		
		
		n = in_partition(temp) # pi is in its place
		temp[:n] =  quicksort(temp[:n]) 
		temp[n:] = quicksort(temp[n:])
		chave_final = {}
		
		a = 0
		for t in temp:
			chave_final[t] = a
		
			a += 1
		
		seq_2 = seq[:]
		
		
		for t in temp:
			seq[chave_final[t]] = seq_2[chave_inicial[t]]
			
		
		
	else:
		n = in_partition(seq) # pi is in its place
	
		seq[:n] =  quicksort(seq[:n]) 
		
		seq[n:] = quicksort(seq[n:]) # Sort lo and hi separately
	
def in_quicksort_2(seq):
	if len(seq) <= 1: return seq # Base case
	
	n = partition_3(seq)#in_partition(seq) # pi is in its place
	
	seq[:n] =  quicksort(seq[:n]) 
	
	seq[n:] = quicksort(seq[n:]) # Sort lo and hi separately

# in_quicsort da net
'''
0.9163968613384632
0.4094164547286149

'''
def quicksort_3(seq,l=0,h=None):
	
    if h == None: h = len(seq)-1
    # Create an auxiliary stack
    size = h - l + 1
    stack = [0] * (size)
  
    # initialize top of stack
    top = -1
  
    # push initial values of l and h to stack
    top = top + 1
    stack[top] = l
    top = top + 1
    stack[top] = h
  
    # Keep popping from stack while is not empty
    while top >= 0:
  
        # Pop h and l
        h = stack[top]
        top = top - 1
        l = stack[top]
        top = top - 1
  
        # Set pivot element at its correct position in
        # sorted seqay
        p = partition_3( seq, l, h )
  
        # If there are elements on left side of pivot,
        # then push left side to stack
        if p-1 > l:
            top = top + 1
            stack[top] = l
            top = top + 1
            stack[top] = p - 1
  
        # If there are elements on right side of pivot,
        # then push right side to stack
        if p+1 < h:
            top = top + 1
            stack[top] = p + 1
            top = top + 1
            stack[top] = h
	
	
		
	
#Listing 6-5. Merge Sort recursivo

def mergesort(seq):
	mid = len(seq)//2			#Midpoint for division
	lft, rgt = seq[:mid], seq[mid:]
	if len(lft) > 1: lft = mergesort(lft)	#Sort by halves
	if len(rgt) > 1: rgt = mergesort(rgt)
	res = []
	
	while lft and rgt:			#Neither half is empty
		if lft[-1] >=rgt[-1]:		#lft has greatest last value
			res.append(lft.pop())	#Append it
		else:				#rgt has greatest last value
			res.append(rgt.pop())	#Append it
	res.reverse()				#Result is backward
	return (lft or rgt) + res		#Also add the remainder

#Listing 3-2. Merge Sort igual kkkkkk

def mergesort_2(seq):
	mid = len(seq)//2
	lft, rgt = seq[:mid], seq[mid:]
	if len(lft) > 1: lft = mergesort(lft)
	if len(rgt) > 1: rgt = mergesort(rgt)
	res = []
	while lft and rgt:
		if lft[-1] >=rgt[-1]:
			res.append(lft.pop())
		else:
			res.append(rgt.pop())
	res.reverse()
	return (lft or rgt) + res
	
if __name__ == '__main__':
	from random import randrange
	from timeit import timeit
	
	seq_1 = "This is a test string from Andrew".split()#[ randrange(-100,100) for i in range(10)]
	seq_2 = seq_1[:]
	#seq_3 = seq_1[:]
	#seq_4 = seq_1[:]
	print(seq_1)
	#key_1 = lambda x: x**3
	
	in_quicksort(seq_1, key=str.lower)
	#quicksort_3(seq_3)
	seq_2.sort(key=str.lower)
	print(seq_1)
	print(seq_2)
	print(seq_1==seq_2)
	#print(seq_1==seq_3)
	
	
	'''print('\n')
	print(seq_2)
	print(seq_1)'''
	
	#print('\nO código do livro:')
	#print(select(seq_1, k))
	#print('O meu código:')
	#print(partition_and_select(seq_1, k))
	#seq_3 = in_quicksort(seq_1)
	
	#seq_4 = quicksort(seq_1)
	
	'''time1 = timeit('in_quicksort(seq_2)',setup="from __main__ import in_quicksort", globals=globals(), number = 10)

	time2 = timeit('in_quicksort_2(seq_2)', setup="from __main__ import in_quicksort_2", globals=globals(), number = 10)
	
	time3 = timeit('quicksort_3(seq_2)', setup="from __main__ import quicksort_3", globals=globals(), number = 10) 
	time4 = timeit('seq_2.sort()', globals=globals(), number = 10)'''
	
	#print(seq_2, '\n')
	#print(seq_3)
	#print(seq_4)
	#print(seq_4)
	#print("\nRazão Tempos:")
	#print(time1/time2)
	#print(time1/time3)
	#print(time1/time4)


