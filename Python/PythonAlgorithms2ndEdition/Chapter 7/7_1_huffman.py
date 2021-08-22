#Listing 7-1. Huffman’s Algorithm

from heapq import heapify, heappush, heappop
from itertools import count

def huffman(seq, frq):
	num = count()
	trees = list(zip(frq, num, seq))		#num ensures valid ordering
	heapify(trees)					#A min-heap based on frq
	while len(trees) > 1:				#Until all are combined
		fa, _, a = heappop(trees)		#Get the two smallest trees
		fb, _, b = heappop(trees)
		n = next(num)
		heappush(trees, (fa+fb, n, [a, b])) 	#Combine and re-add them
	#print(trees)
	return trees[0][-1]
	
#Listing 7-2. Extracting Huffman Codes from a Huffman Tree

def codes(tree, prefix=""):
	if len(tree) == 1:
		yield (tree, prefix)		# A leaf with its code
		return
	for bit, child in zip("01", tree): 	# Left (0) and right (1)
		for pair in codes(child, prefix + bit): # Get codes recursively
			yield pair


if __name__ == '__main__':
	seq = "abcdefghi"
	frq = [4, 5, 6, 9, 11, 12, 15, 16, 20]
	print(huffman(seq, frq))
	
	#[['i', [['a', 'b'], 'e']], [['f', 'g'], [['c', 'd'], 'h']]]

