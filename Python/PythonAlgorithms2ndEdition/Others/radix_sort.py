'''from collections import defaultdict

def counting_sort(A, key=lambda x: x):
	B, C = [], defaultdict(list)
	for x in A:
		C[key(x)].append(x)
	for k in range(min(C), max(C)+1):
		B.extend(C[k])
	return B'''

# Python program for implementation of Radix Sort 
  
# A function to do counting sort of arr[] according to 
# the digit represented by exp. 
def countingSort(arr, exp1): 
  
    n = len(arr) 
  
    # The output array elements that will have sorted arr 
    output = [0] * (n) 
  
    # initialize count array as 0 
    count = [0] * (10) 
  
    # Store count of occurrences in count[] 
    for i in range(0, n): 
        index = (arr[i]/exp1) 
        count[ int((index)%10) ] += 1
  
    # Change count[i] so that count[i] now contains actual 
    #  position of this digit in output array 
    for i in range(1,10): 
        count[i] += count[i-1] 
  
    # Build the output array 
    i = n-1
    while i>=0: 
        index = (arr[i]/exp1) 
        output[ count[ int((index)%10) ] - 1] = arr[i] 
        count[ int((index)%10) ] -= 1
        i -= 1
  
    # Copying the output array to arr[], 
    # so that arr now contains sorted numbers 
    i = 0
    for i in range(0,len(arr)): 
        arr[i] = output[i] 
    return arr
  
# Method to do Radix Sort 
def radixSort(arr): 
  
    # Find the maximum number to know number of digits 
    max1 = max(arr) 
  
    # Do counting sort for every digit. Note that instead 
    # of passing digit number, exp is passed. exp is 10^i 
    # where i is current digit number 
    exp = 1
    while max1/exp > 10**-17: 
        countingSort(arr,exp) 
        exp *= 10
    return arr
 


if __name__ == '__main__':
    from time import time
    
    #a_1 = list(map(lambda x:x**3,range(0,100,1)))+list(map(lambda x:x**2,range(-100,-15,5)))
    
    a_1 = list(range(10000,0,-1))

    start_1 = time()
    a_2 = radixSort(a_1)
    end_1 = time()
    
    start_2 = time()
    a_3 = countingSort(a_1,1)
    end_2 = time()
    
    print("radix sort: ", end_1-start_1, "counting sort: ", end_2-start_2)
    


