from typing import TypeVar, Generic, List

T = TypeVar('T')

class Stack(Generic[T]):
	def __init__(self) -> None:
		self._container: List[T] = []
	def push(self, item: T) -> None:
		self._container.append(item)
	def pop(self) -> T:
		return self._container.pop()
	def __repr__(self) -> str:
		return repr(self._container)
		
num_discs: int = 33
num_towers: int = 3

towers = []

for _ in range(num_towers):
	towers.append(Stack())
	

for i in range(1, num_discs+1):
	towers[0].push(i)
	

def hanoi1(begin: Stack[int], end: Stack[int], temp: Stack[int], n: int) -> None:
	if n == 1:
		end.push(begin.pop())
	else:
		hanoi1(begin, temp, end, n - 1)
		hanoi1(begin, end, temp, 1)
		hanoi1(temp, end, begin, n - 1)
		
def hanoi2(Towers: List[Stack[int]], n: int) -> None:

	if len(Towers)%2 > 0:
		for i in range(0,len(Towers)-2,2):
			hanoi1(Towers[i], Towers[i+2], Towers[i+1], n)
	else:
		for i in range(0,len(Towers)-2,2):
			hanoi1(Towers[i], Towers[i+2], Towers[i+1], n)
		hanoi1(Towers[-2], Towers[-1], Towers[-3], n)
		

if __name__ == "__main__":
	hanoi2(towers, num_discs)
	for i in range(len(towers)):
		print(towers[i])

