from enum import IntEnum
from typing import Tuple, List

#Type Hints

Nucleotide: IntEnum = IntEnum('Nucleotide', ('A', 'C', 'G', 'T'))

Codon = Tuple[Nucleotide, Nucleotide, Nucleotide] 

Gene = List[Codon]

#Função para transformar uma String em um Gene

def string_to_gene(s: str) -> Gene:
	gene: Gene = []
	for i in range(0, len(s), 3):
		if (i+2) >=len(s): #don't run off end!
			return gene
		codon: Codon = (Nucleotide[s[i]], Nucleotide[s[i+1]], Nucleotide[s[i+2]]) #Initialize codon out of three nucleotides
		gene.append(codon) #add codon to gene
	return gene


#Busca linear -> Theta(n)
#procura por um Codon em partir a partir do início do gene

def linear_contains(gene: Gene, key_codon: Codon) -> bool:

	for codon in gene:
		if codon == key_codon:
			return True
	return False


#Busca binária

'''

Nesse tipo de algorítimo a ideia é diminuir cada busca pela metade, assim se torna theta (log(n))


'''

def binary_contains(gene: Gene, key_codon: Codon) -> bool:
	low: int = 0
	high: int = len(gene) - 1
	sorted_gene = gene[:]
	sorted_gene.sort()
	while low <=high: #While there is still a search space
		mid: int = (low+high)//2
		
		if sorted_gene[mid] < key_codon:
			low = mid + 1
		if sorted_gene[mid] > key_codon:
			high = mid -1
		else:
			return True
			
	return False

if __name__ == '__main__':
	from time import time
	gene_str: str = "ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT"
	my_gene: Gene = string_to_gene(gene_str)
	
	acg: Codon = (Nucleotide.A, Nucleotide.C, Nucleotide.G)
	
	gat: Codon = (Nucleotide.G, Nucleotide.A, Nucleotide.T)
	
	start = time()
	linear_contains(my_gene, acg)
	end_1 = time()
	binary_contains(my_gene, acg)
	end_2 = time()
	acg in my_gene
	end_3 = time()
	linear_contains(my_gene, gat)
	end_4 = time()
	binary_contains(my_gene, gat)
	end_5 = time()
	gat in my_gene
	end_6 = time()
	
	
	print(end_1-start) 
	print(end_2-end_1)
	print(end_3-end_2) 
	print(end_4-end_3)
	print(end_5-end_4) 
	print(end_6-end_5)


