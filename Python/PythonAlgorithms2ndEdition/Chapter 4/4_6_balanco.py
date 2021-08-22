'''código para encontrar função para calcular 
fator de balanço em uma árvore binária 
'''

import math

class bi_Tree:
	def __init__(self, left, right):
		self.left = left
		self.right = right
		
def altura(arvore):

	#verificar se o valor é próximo de 2^i, ai fica top pra calcular altura
	count = dict((u, 0) for u in arvore)
	for u in arvore: 
		count[u] = len(arvore[u])		#Count every out-edge
	folhas = [u for u in arvore if count[u] == 0]
	
	altura = math.log(len(folhas), 2)
	
	return altura

def sub_arvore(arvore_mae, no_filho):

	count = dict((u, 0) for u in arvore_mae)
	for u in arvore_mae: 
		count[u] = len(arvore_mae[u])		#Count every out-edge

	nos_sub_arvores = [no_filho]
	nos_inverificados = [no_filho]
	
	sub_arvore = {no_filho:arvore_mae[no_filho]}
	for no in arvore_mae:
		#print(no)
		if no in nos_inverificados:
			if count[no] !=0:
				#nos_sub_arvores.append(arvore_mae[no][0])
				#nos_sub_arvores.append(arvore_mae[no][1])
				sub_arvore[no] = arvore_mae[no]
				nos_inverificados.remove(no)
				nos_inverificados.append(arvore_mae[no][0])
				if len(arvore_mae[no])>1:
					nos_inverificados.append(arvore_mae[no][1])
			else:
				#nos_sub_arvores.append(no)
				sub_arvore[no] = arvore_mae[no]

		#print(nos_inverificados)
		#print(sub_arvore)
	#print(nos_sub_arvores)
	return sub_arvore

	
def balanco(arvore, i = None):

	chaves = list(arvore.keys())
	
	if len(arvore[chaves[0]])>1:
		altura_esquerda = altura(sub_arvore(arvore,chaves[1]))
		altura_direita  = altura(sub_arvore(arvore,chaves[2]))
		return altura_esquerda - altura_direita
	else:
		return altura(sub_arvore(arvore,chaves[1]))+1
		


if __name__ == '__main__':

	Tb = {'a':list('bc'), 'b':list('de'), 'c':list('fg'), 'd':list('hi'), 'e':list('jk'), 'f':list('lm'), 'g':list('n'), 'h':list(), 'i':list(), 'j':list(), 'k':list(), 'l':list(), 'm':list(), 'n':list()}#, 'o':list()}
	
	print(altura(Tb))
	print(balanco(Tb))
	
	
	print(balanco(sub_arvore(Tb, 'g')))
