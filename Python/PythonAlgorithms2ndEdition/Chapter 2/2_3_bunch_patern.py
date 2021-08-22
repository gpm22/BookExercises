#O padrão bunch

class Bunch(dict):
	def __init__(self, *args, **kwds):
		super(Bunch, self).__init__(*args, **kwds)
		self.__dict__ = self

#exemplo

x = Bunch(name="Jayne Cobb", position="Public Relations")
x.name

#exemplo para uma árvore

'''
	t
    0      0
 a    b  c    d

'''

T = Bunch
t = T(left=T(left="a", right="b"), right=T(left="c"))

t.left.right
t['left']['right']
