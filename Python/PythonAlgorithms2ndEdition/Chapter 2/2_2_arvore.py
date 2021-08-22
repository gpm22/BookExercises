'''Exemplo da árvore:

            T
            0
               2
      0     0     0   
                    1   
  a      b  c  d       0
                          0 -> edge
                  e         f 
                   
'''

T = [["a", "b"], ["c"], ["d", ["e", "f"]]]


#para acessar e

T[2][1][0]


#Listing 2-7. A Binary Tree Class

class bi_Tree:
	def __init__(self, left, right):
		self.left = left
		self.right = right
		
#criando uma árvore usando a classe Tree

'''

	    t
	
    0		    0

a	b	c	d


'''

t_b = bi_Tree(bi_Tree("a", "b"), bi_Tree("c", "d"))


#para acessar o c

print(t_b.right.left)



#Listing 2-8. A Multiway Tree Class

class Tree:
	def __init__(self, kids, next=None):
		self.kids = self.val = kids
		self.next = next
		
#Árvore criada

'''
	    t
		
a  ->	b  ->	c  ->	d

'''


t = Tree(Tree("a", Tree("b", Tree("c", Tree("d")))))




#para acessar c

print(t.kids.next.next.val)
