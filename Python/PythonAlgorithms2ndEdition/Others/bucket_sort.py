from collections import defaultdict

def countingSort(A, key=lambda x: x):
	B, C = [], defaultdict(list)
	for x in A:
		C[key(x)].append(x)
	for k in range(min(C), max(C)+1):
		B.extend(C[k])
	return B


def radixSort(arr): 
  
    # Find the maximum number to know number of digits 
    number_1 = len(str(max(arr))) #number of digits of the gratest number
    D = arr
    for num in range(number_1, -1, -1):
        D = countingSort(D, lambda x: x//(10**num))
    	
    return D
    
def bucketSort(arr):
	digi = 1/((max(arr)-min(arr))/(len(arr)-1))
	digi = int(digi)
	a_2 = list(map(lambda x:int(x*digi), arr))
	a_3 = list(map(lambda x:x/digi, radixSort(a_2)))
	
	return a_3


if __name__ == '__main__':
	import numpy as np
	
	a = list(np.arange(150000,0,-0.001))
	
	b = a[:]
	
	b.sort()
	
	c = bucketSort(a)

	print(b[-1], '      ', c[-1])
	
	
