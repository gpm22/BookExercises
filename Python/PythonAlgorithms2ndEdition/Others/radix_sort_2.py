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
    
    
if __name__ == '__main__':
    from time import time
    
    #a_1 = list(map(lambda x:x**3,range(0,100,1)))+list(map(lambda x:x**2,range(-100,-15,5)))
    
    a_1 = list(range(100,0,-1))

    start_1 = time()
    a_2 = radixSort(a_1)
    end_1 = time()
    
    start_2 = time()
    a_3 = countingSort(a_1)
    end_2 = time()
    
    print("radix sort: ", end_1-start_1, "counting sort: ", end_2-start_2)
    
