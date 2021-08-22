#Listing 3-1. Gnome Sort, An Example Sorting Algorithm

def gnomesort(seq_1):
	
	seq = seq_1[:]	
	i = 0
	while i < len(seq):
		if i == 0 or seq[i-1] <= seq[i]:
			i += 1
		else:
			seq[i], seq[i-1] = seq[i-1], seq[i]
			i -= 1
			
	return seq
	
#Listing 3-2. Merge Sort, Another Example Sorting Algorithm

def mergesort(seq):
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

#Listing 4-1. Recursive Insertion Sort

def ins_sort_rec(seq_1, i = None):

	seq = seq_1[:] 
	if i is None:
		i = len(seq)-1
		a = True
	else:
		a = False
		
	if i==0: return 				#Base case -- do nothing
	ins_sort_rec(seq, i-1) 			#Sort 0..i-1
	j = i 						#Start "walking" down
	while j > 0 and seq[j-1] > seq[j]: 		#Look for OK spot
		seq[j-1], seq[j] = seq[j], seq[j-1] 	#Keep moving seq[j] down
		j -= 1 				#Decrement j
	
	if a: return seq
		
#Listing 4-2. Insertion Sort

def ins_sort(seq_1):
	seq = seq_1[:]

	for i in range(1,len(seq)): 				#0..i-1 sorted so far
		j = i 						#Start "walking" down
		while j > 0 and seq[j-1] > seq[j]: 		#Look for OK spot
			seq[j-1], seq[j] = seq[j], seq[j-1] 	#Keep moving seq[j] down
			j -= 1  				#Decrement j

	return seq
			
#Listing 4-3. Recursive Selection Sort

def sel_sort_rec(seq_1, i=None):
	seq = seq_1[:] 
	if i is None:
		i = len(seq)-1
		a = True
	else:
		a = False
		
	if i==0: return 				#Base case -- do nothing
	max_j = i 					#Idx. of largest value so far
	for j in range(i): 				#Look for a larger value
		if seq[j] > seq[max_j]: max_j = j 	#Found one? Update max_j
	seq[i], seq[max_j] = seq[max_j], seq[i] 	#Switch largest into place
	sel_sort_rec(seq, i-1) 			#Sort 0..i-1
	
	if a: 
		return seq


#Listing 4-4. Selection Sort

def sel_sort(seq_1):

	seq = seq_1[:]
	for i in range(len(seq)-1,0,-1): 			#n..i+1 sorted so far
		max_j = i 					#Idx. of largest value so far
		for j in range(i):				#Look for a larger value
			if seq[j] > seq[max_j]: max_j = j 	#Found one? Update max_j
		seq[i], seq[max_j] = seq[max_j], seq[i] 	#Switch largest into place
	
	return seq
		
#Counting Sort		
		
from collections import defaultdict

def counting_sort(A, key=lambda x: x):
	B, C = [], defaultdict(list)		# Output and "counts"
	for x in A:
		C[key(x)].append(x)		# "Count" key(x)
		
	#print('\n',C)
	#a = 0
	for k in range(min(C), max(C)+1):	# For every key in the range
		B.extend(C[k])			# Add values in sorted order
	#	a += 1
	#print(C[391])
	#print(a)
	#print(B)
	return B
	
#sorting do python

def python_sorting(seq_1):
	seq = seq_1[:]
	seq.sort()
	return seq

#4-11. Implement radix sort.

def my_radix_sort(seq_1):
	seq = seq_1[:]
	numero_digitos = len(str(max(seq)))
	#print('meu radix durao\n')
	
	# a dividsão tem que ir de 10 até 10**numero_digitos
	#%1 -> retorna a lista
	#%10 -> usa o primeiro dígito
	#%100 -> usa o segundo dígito
	#%1000 -> usa o terceiro dígito
	for i in range(1, numero_digitos+1):
		seq = counting_sort(seq,  lambda x: (x%(10**i))//(10**(i-1)))
		#print(seq)
	
	
	return seq

# Python program for implementation of Radix Sort 
# A function to do counting sort of arr[] according to 
# the digit represented by exp. 
  
def countingSort(seq, exp1):

    arr = seq[:] 
  
    n = len(arr) 
  
    # The output array elements that will have sorted arr 
    output = [0] * (n) 
  
    # initialize count array as 0 
    count = [0] * (10) 
  
    # Store count of occurrences in count[] 
    for i in range(0, n): 
        index = (arr[i] / exp1) 
        count[int(index % 10)] += 1
  
    # Change count[i] so that count[i] now contains actual 
    # position of this digit in output array 
    for i in range(1, 10): 
        count[i] += count[i - 1] 
  
    # Build the output array 
    i = n - 1
    while i >= 0: 
        index = (arr[i] / exp1) 
        output[count[int(index % 10)] - 1] = arr[i] 
        count[int(index % 10)] -= 1
        i -= 1
  
    # Copying the output array to arr[], 
    # so that arr now contains sorted numbers 
    i = 0
    for i in range(0, len(arr)): 
        arr[i] = output[i] 
    
    return arr
  
# Method to do Radix Sort 
def radixSort(seq): 
    arr = seq[:]
    # Find the maximum number to know number of digits 
    max1 = max(arr) 
    #print('radix da net\n')
  
    # Do counting sort for every digit. Note that instead 
    # of passing digit number, exp is passed. exp is 10^i 
    # where i is current digit number 
    exp = 1
    #a = 0
    while max1 / exp > 0: 
        arr = countingSort(arr, exp)
        #if arr1 and arr1 == arr:
        
        #if arr_1 and arr_1 != arr:
        #		print('\n',arr_1)
        
        #arr = arr_1[:] 
        	#arr = arr1[:] 
        exp *= 10
        #a +=1
     	
    #print(a)
    return arr
    
#4-12. Implement bucket sort.

def my_bucket_sort(seq_1):

	seq = seq_1[:]
	
	passo = 1/((max(seq)-min(seq))/len(seq))
	
	#print(passo)
	seq = counting_sort(seq, lambda x: int(x*passo))
	
	'''for num in seq:
		print(int(num*(10**(len(str(num))-2))))'''
	
	return seq

if __name__ == '__main__':
	from timeit import timeit
	#from random import randrange
	import numpy as np
	
	dt = 0.03
	#print(1/dt)
	
	seqs = list(np.arange(0,100+dt,dt))
	seqs.reverse()
	
	#print(round(1/((max(seqs)-min(seqs))/len(seqs))-1))
	#print(seqs)
	
	#list(range(500000, 1, -1))+list(range(1, 500000, 1))
	
	#seqs.sort()
	
	#print(my_bucket_sort(seqs))
	
	j = seqs[:]
	j.sort()
	#print(j)
	print(my_bucket_sort(seqs)==j)
	#sorts = [gnomesort, mergesort, ins_sort, sel_sort, counting_sort, python_sorting, my_radix_sort] #, radixSort]
	#nome_sort = ['gnomesort', 'mergesort', 'ins_sort', 'sel_sort', 'counting_sort', 'python_sorting', 'my_radix_sort'] #, 'radixSort']
	
	'''sorts = [my_radix_sort, counting_sort]
	nome_sort = ['my_radix_sort', 'counting_sort']
	

	for i in range(len(sorts)):
		#print(nome_sort[i], ': ', sorts[i](seqs)==j)
		print("tempo do", nome_sort[i], ": ", timeit(nome_sort[i] + '(seqs)',setup= "from __main__ import "+nome_sort[i], globals=globals(), number = 1))'''
	















