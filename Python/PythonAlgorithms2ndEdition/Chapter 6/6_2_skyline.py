'''
problema skyline:

Recebendo uma série de coordenadas (L, H, R), onde:

L -> coordenada x esquerda do prédio
H -> altura do prédio
R -> coordenada x direita do prédio

gerar o contorno de todos os prédios juntos

o resultado será uma lista com os pontos (x, y) do contorno 

'''

def coordenadas(L, H , R): 
	return [(L, H), (R, 0)]
	
def skyline(predios):

	if predios == []: return [] #se lista estiver vazia
	if len(predios) == 1: return coordenadas(predios[0][0],predios[0][1],predios[0][2]) #caso base
	m = len(predios)//2
	
	esq  = predios[:m]
	dire = predios[m:]
	
	sky_esq = skyline(esq)
	sky_dir = skyline(dire)
	
	#return merge(sky_esq, sky_dir)
	
	h1, h2, pontos = 0, 0, []
	
	#combinação
	
	while sky_esq and sky_dir:
		if sky_esq[0][0] < sky_dir[0][0]:
			pos, h1 = sky_esq[0]
			sky_esq = sky_esq[1:]
		elif sky_esq[0][0] > sky_dir [0][0]:
			pos, h2 = sky_dir[0]
			sky_dir = sky_dir[1:]
		else:
			pos, h1 = sky_esq[0]
			h2 = sky_dir[0][1]
			sky_esq = sky_esq[1:]
			sky_dir = sky_dir[1:]
		
		H = max(h1, h2)
		
		if not pontos or H != pontos[-1][1]:
			pontos.append((pos, H))
			
	if sky_esq:
		pontos += sky_esq
	if sky_dir:
		pontos += sky_dir
	
	return pontos
	
'''def merge(esq, dire):
	h1, h2, res = 0, 0, []
	while esq and dire:
		if esq[0][0] < dire[0][0]:
			pos, h1 = esq[0]
			esq = esq[1:]
		elif esq[0][0] > dire [0][0]:
			pos, h2 = dire[0]
			dire = dire[1:]
		else:
			pos, h1 = esq[0]
			h2 = dire[0][1]
			esq = esq[1:]
			dire = dire[1:]
		
		H = max(h1, h2)
		
		if not res or H != res[-1][1]:
			res.append((pos, H))
			
	if esq:
		res += esq
	if dire:
		res += dire
	
	return res	'''	

	
def skyline_1(buildings):

	edges = []
	edges.extend([building[0],building[2]] for building in buildings)
	#print(edges)
	edges = sorted(sum(edges,[])) #sorting and flatening the list of building edges
	#print(edges)
 
	current = 0
	points = []
  
	for i in edges:
		active = []
		active.extend(building for building in buildings if (building[0] <= i and building[2] > i)) 
	  #current observed point is within borders of these buildings (active buildings)
		#print(i,active)
		if not active: 
	    #if there is no active buildings, highest point is 0
	    		current = 0
	    		points.append((i,0))
	    		continue
	  
		max_h = max(building[1] for building in active)
		if max_h != current:
		#if current highest point is lower then highest point of current active buildings change current highest point
			current = max_h
			points.append((i,max_h))
     
	return points
	
	
	
if __name__ == '__main__':

	predios = [(1,11,5), (2,6,7), (3,13,9), (12,7,16), (14,3,25), (19,18,22), (23,13,29), (24,4,28)]
	
	#predios = [[2,10,9],[3,15,7],[5,12,12],[15,10,20],[19,8,24]]
	
	print(predios)
	
	print(skyline_1(predios))
	
	print(skyline(predios))
  
