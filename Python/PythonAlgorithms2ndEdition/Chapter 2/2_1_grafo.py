#A Straightforward Adjacency Set Representation

a, b, c, d, e, f, g, h = range(8)

N = [{b, c, d, e, f}, {c, e}, {d}, {e}, {f}, {c, g, h}, {f,h}, {f, g}]

	
	
b in N[a]  #verificação se b está na vizinhança de a

len(N[f]) #irá dar o grau do nó f;


#No caso de grafos com pesos:


N_p = [{b:2, c:1, d:3, e:9, f:4}, {c:4, e:3},{d:8}, {e:7}, {f:5}, {c:2, g:2, h:2}, {f:1,h:6}, {f:9, g:8}]


B in N_p[a] # Neighborhood membership

len(N_p[f]) # Degree

N_p[a][b] # Edge weight for (a, b)

#Listing 2-4. A dict with Adjacency Sets

N_d = {'a':set('bcdef'), 'b':set('ce'), 'c':set('d'), 'd':set('e'), 'e':set('f'), 'f':set('cgh'), 'g':set('fh'), 'h':set('fg')}

# Listing 2-5. An Adjacency Matrix, Implemented with Nested Lists

N_m = [[0,1,1,1,1,1,0,0], [0,0,1,0,1,0,0,0], [0,0,0,1,0,0,0,0], [0,0,0,0,1,0,0,0], [0,0,0,0,0,1,0,0], [0,0,1,0,0,0,1,1], [0,0,0,0,0,1,0,1], [0,0,0,0,0,1,1,0]]

N_m[a][b] # Neighborhood membership

sum(N[f]) #grau

#Listing 2-6. A Weight Matrix with Infinite Weight for Missing Edges

inf = float('inf')


N_mp = [[0,2,1,3,9,4,inf,inf], [inf,0,4,inf,3,inf,inf,inf], [inf,inf,0,8,inf,inf,inf,inf], [inf,inf,inf,0,7,inf,inf,inf], [inf,inf,inf,inf,0,5,inf,inf], [inf,inf,2,inf,inf,0,2,2], [inf,inf,inf,inf,inf,1,0,6], [inf,inf,inf,inf,inf,9,9,0]]

W[a][b] < inf Neighborhood membership

W[c][e] < inf # Neighborhood membership

sum(1 for w in W[a] if w < inf) - 1 # Degree









