from __future__ import annotations
from typing import TypeVar, Iterable, Sequence, Generic, List, Callable, Set, Deque, Dict, Any, Optional
from typing_extensions import Protocol
from heapq import heappush, heappop



T = TypeVar('T')

#Linear search

def linear_contains(iterable: Iterable[T], key: T) -> bool:
	for item in iterable:
		if item == key:
			return True
	return False



C = TypeVar("C", bound="Comparable")



class Comparable(Protocol):
	def __eq__(self, other: Any) -> bool:
		...
	def __lt__(self: C, other: C) -> bool:
		...
	def __gt__(self: C, other: C) -> bool:
		return (not self < other) and self != other
	def __le__(self: C, other: C) -> bool:
		return self < other or self == other
	def __ge__(self: C, other: C) -> bool:
		return not self < other

#binary search

def binary_contains(sequence: Sequence[C], key: C) -> bool:
	low: int = 0
	high: int = len(sequence) - 1
	while low <= high: # while there is still a search space
		mid: int = (low + high) // 2
		if sequence[mid] < key:
			low = mid + 1
		elif sequence[mid] > key:
			high = mid - 1
		else:
			return True
	return False
	
#Depth-first search

#Definindo a classe Stack onde é basicamente uma lista com a primeira saida é a última entrada, igual a uma pilha de papéis;

class Stack(Generic[T]):
	def __init__(self) -> None:
		self._container: List[T] = []

	@property
	def empty(self) -> bool:
		return not self._container # not is true for empty container
		
	def push(self, item: T) -> None:
		self._container.append(item)

	def pop(self) -> T:
		return self._container.pop() #LIFO

	def __repr__(self) -> str:
		return repr(self._container)
		
#definição da classe Node, que se refere como se deu a saída de um estado para outro

class Node(Generic[T]):
	def __init__(self, state: T, parent: Optional[Node], cost: float = 0.0,
heuristic: float = 0.0) -> None:

		self.state: T = state
		self.parent: Optional[Node] = parent
		self.cost: float = cost
		self.heuristic: float = heuristic

	def __lt__(self, other: Node) -> bool:
		return (self.cost + self.heuristic) < (other.cost + other.heuristic)
		
		
def dfs(initial: T, goal_test: Callable[[T], bool], successors: Callable[[T], List[T]]) -> Optional[Node[T]]:
	
	# frontier is where we've yet to go
	frontier: Stack[Node[T]] = Stack()
	frontier.push(Node(initial, None))
	# explored is where we've been
	explored: Set[T] = {initial}
	# keep going while there is more to explore
	while not frontier.empty:
		
		current_node: Node[T] = frontier.pop()
		current_state: T = current_node.state
		# if we found the goal, we're done
		if goal_test(current_state):
			return current_node
		# check where we can go next and haven't explored
		for child in successors(current_state):
			if child in explored: # skip children we already explored
				continue
			explored.add(child)
			frontier.push(Node(child, current_node))
	return None # went through everything and never found goal	


#Breadth-first search

#Sempre encontra o caminho mais curto para um trajeto, porém é mais lento que o dfs, que normalmente não encontra o caminho mais curto; Ele acha o menor caminho por praticamente procurar por tudo!

#Implementação de Queues que é o primeiro que entra é o primeiro que sai, ao contrário dos Stacks; É como uma fila que se forma para um show;


class Queue(Generic[T]):
	def __init__(self) -> None:
		self._container: Deque[T] = Deque() # o uso do Deque se deve a fazer o pop do lado esquerdo de uma maneira mais eficiente do que uma lista consegue fazer

	@property
	def empty(self) -> bool:
		return not self._container # not is true for empty container
		
	def push(self, item: T) -> None:
		self._container.append(item)
		
	def pop(self) -> T:
		return self._container.popleft() # FIFO
		
	def __repr__(self) -> str:
		return repr(self._container)
		
# a diferença de um BFS para um DFS é que o primeiro usa queues e o segundo stacks, real que é só isso mesmo (QUE ROLÊ). Isso faz com que sempre as fronteira adjacentes devem ser buscadas no caso do BFS;


def bfs(initial: T, goal_test: Callable[[T], bool], successors: Callable[[T], List[T]]) -> Optional[Node[T]]:
	
	# frontier is where we've yet to go
	frontier: Queue[Node[T]] = Queue()
	frontier.push(Node(initial, None))
	# explored is where we've been
	explored: Set[T] = {initial}
	
	count = 0
	
	# keep going while there is more to explore
	while not frontier.empty:
		
		#count +=1
		#print(count)
		current_node: Node[T] = frontier.pop()
		current_state: T = current_node.state
		# if we found the goal, we're done
		if goal_test(current_state):
			return current_node
		# check where we can go next and haven't explored
		for child in successors(current_state):
			if child in explored: # skip children we already explored
				continue
			explored.add(child)
			frontier.push(Node(child, current_node))
	return None # went through everything and never found goal

#A* search

#tem o objetivo de encontrar o menor caminho de uma maneira mais rápido do que o BFS; Para isso se utilizado conceito das funções custo e heurística; A função custo g(n) verifica o custo para se alcançar um determinado estado, enquanto a função heurística h(n) é uma estimativa do custo para sair do estado atual é o ponto de chegada; O custo total é f(n) = g(n) + h(n);

#Nesse algorítimo cada passo a ser feito é o que possuir o menor f(n);

#estrutura de dado utilizada é a priority queue, que uma queue onde o topo estará ocupada por um dado prioritário, que no caso é o menor f(n) possível; Para isso usa-se um binary heap com push e pop igual theta(lg n);

#criação da classe PriorityQueue

class PriorityQueue(Generic[T]):
	def __init__(self) -> None:
		self._container: List[T] = []
	
	@property
	def empty(self) -> bool:
		return not self._container # not is true for empty container
	
	def push(self, item: T) -> None:
		heappush(self._container, item) # in by priority
	
	def pop(self) -> T:
		return heappop(self._container) # out by priority
	
	def __repr__(self) -> str:
		return repr(self._container)


#A* 




def astar(initial: T, goal_test: Callable[[T], bool], successors: Callable[[T], List[T]], heuristic: Callable[[T], float]) -> Optional[Node[T]]:
	
	
	# frontier is where we've yet to go
	frontier: PriorityQueue[Node[T]] = PriorityQueue()
	frontier.push(Node(initial, None, 0.0, heuristic(initial)))
	# explored is where we've been
	explored: Dict[T, float] = {initial: 0.0}

	# keep going while there is more to explore
	while not frontier.empty:
		
		current_node: Node[T] = frontier.pop()
		current_state: T = current_node.state
		# if we found the goal, we're done
		if goal_test(current_state):
			return current_node
		# check where we can go next and haven't explored
		for child in successors(current_state):
			new_cost: float = current_node.cost + 1 # 1 assumes a grid, need a cost function for more sophisticated apps

			if child not in explored or explored[child] > new_cost:
				explored[child] = new_cost
				frontier.push(Node(child, current_node, new_cost, heuristic(child)))
	return None # went through everything and never found goal





#função para reconstruir o caminho feito da entrada do labirinto até a saída do mesmo

def node_to_path(node: Node[T]) -> List[T]:
	path: List[T] = [node.state]
	# work backwards from end to front
	while node.parent is not None:
		node = node.parent
		path.append(node.state)
	path.reverse()
	return path


if __name__ == "__main__":
	from time import time
	
	a = [i*2 for i in range(500000)]+[i*3 for i in range (-500000,0)]
	
	start = time()
	print(linear_contains(a, -1))
	end_1 = time()
	a.sort()
	print(binary_contains(a, -1))
	end_2 = time()
	print(len(a))
	
	time1 = end_1-start
	time2 = end_2-end_1
	
	print((1-end_1/end_2)*100)
	print(time1, time2)
	
	


	'''print(linear_contains([1, 5, 15, 15, 15, 15, 20], 5)) # True
	print(binary_contains(["a", "d", "e", "f", "z"], "f")) # True
	print(binary_contains(["john", "mark", "ronald", "sarah"], "sheila")) # False'''
