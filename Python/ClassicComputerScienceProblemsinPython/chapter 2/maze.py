from enum import Enum
from typing import List, NamedTuple, Callable, Optional
import random
from math import sqrt
from generic_search import dfs, node_to_path, Node, bfs, astar



class Cell(str, Enum):
	EMPTY = " "
	BLOCKED = "X"
	START = "S"
	GOAL = "G"
	PATH = "*"
	
class MazeLocation(NamedTuple):
	row: int
	column: int
	
	
class Maze:
	def __init__(self, rows: int = 10, columns: int = 10, sparseness: float = 0.2, start: MazeLocation = MazeLocation(0, 0), goal: MazeLocation = MazeLocation(9, 9)) -> None:

		# initialize basic instance variables
		self._rows: int = rows
		self._columns: int = columns
		self.start: MazeLocation = start
		self.goal: MazeLocation = goal
		# fill the grid with empty cells
		self._grid: List[List[Cell]] = [[Cell.EMPTY for c in range(columns)] for r in range(rows)]
		# populate the grid with blocked cells
		self._randomly_fill(rows, columns, sparseness)
		# fill the start and goal locations in
		self._grid[start.row][start.column] = Cell.START
		self._grid[goal.row][goal.column] = Cell.GOAL

	def _randomly_fill(self, rows: int, columns: int, sparseness: float):
		for row in range(rows):
			for column in range(columns):
				if random.uniform(0, 1.0) < sparseness:
					self._grid[row][column] = Cell.BLOCKED
	
	def __str__(self) -> str:
		output: str = ''
		for row in self._grid:
			output += ''.join([c.value for c in row]) + '\n'
		return output
		
	def goal_test(self, ml: MazeLocation) -> bool:
		return ml == self.goal
	
	def successors(self, ml: MazeLocation) -> List[MazeLocation]: 
		locations: List[MazeLocation] = []
		if ml.row + 1 < self._rows and self._grid[ml.row + 1][ml.column] != Cell.BLOCKED:
			locations.append(MazeLocation(ml.row +1, ml.column))
		if ml.row - 1 >= 0 and self._grid[ml.row - 1][ml.column] != Cell.BLOCKED:
			locations.append(MazeLocation(ml.row - 1, ml.column))

		if ml.column + 1 < self._columns and self._grid[ml.row][ml.column + 1] != Cell.BLOCKED:

			locations.append(MazeLocation(ml.row, ml.column + 1))
			
		if ml.column - 1 >= 0 and self._grid[ml.row][ml.column - 1] != Cell.BLOCKED:

			locations.append(MazeLocation(ml.row, ml.column - 1))
			
		return locations
	
	def mark(self, path: List[MazeLocation]):
		for maze_location in path:
			self._grid[maze_location.row][maze_location.column] = Cell.PATH
		self._grid[self.start.row][self.start.column] = Cell.START
		self._grid[self.goal.row][self.goal.column] = Cell.GOAL
	
	def clear(self, path: List[MazeLocation]):
	
		for maze_location in path:
			self._grid[maze_location.row][maze_location.column] = Cell.EMPTY
		self._grid[self.start.row][self.start.column] = Cell.START
		self._grid[self.goal.row][self.goal.column] = Cell.GOAL
		
#Definir a linha reta entre a entrada e a saída

def euclidean_distance(goal: MazeLocation) -> Callable[[MazeLocation], float]:
	def distance(ml: MazeLocation) -> float:
		xdist: int = ml.column - goal.column
		ydist: int = ml.row - goal.row
		return sqrt((xdist * xdist) + (ydist * ydist))
	return distance

#Manhattan Distance

#é a distância para se locomover em um local separado por blocos, assim a distância é a soma das distâncias horizontal e vertical


def manhattan_distance(goal: MazeLocation) -> Callable[[MazeLocation], float]:
	def distance(ml: MazeLocation) -> float:
		xdist: int = abs(ml.column - goal.column)
		ydist: int = abs(ml.row - goal.row)
		return (xdist + ydist)
	return distance




if __name__ == '__main__':
	from time import time
	dfs_count = []
	bfs_count = []
	A_eu_count = []
	A_mah_count = []
	time1 = []
	time2 = []
	time3 = []
	time4 = []

	for _ in range(1000):
		m: Maze = Maze()
		start = time()
		solution1, count1 = dfs(m.start, m.goal_test, m.successors)
		end_1 = time()
		solution2, count2 = bfs(m.start, m.goal_test, m.successors)
		end_2 = time()
		distance: Callable[[MazeLocation], float] = manhattan_distance(m.goal)
		
		solution3, count3 = astar(m.start, m.goal_test, m.successors, distance)
		end_3 = time()
	
		distance_2: Callable[[MazeLocation], float] = euclidean_distance(m.goal)
	
		solution4, count4 = astar(m.start, m.goal_test, m.successors, distance_2)
		end_4 = time()
		
		dfs_count.append(count1)
		bfs_count.append(count2)
		A_eu_count.append(count4)
		A_mah_count.append(count3)
		time1.append(end_1-start)
		time2.append(end_2-end_1)
		time3.append(end_3-end_2)
		time4.append(end_4-end_3)
	
	print("média dos passos para:\nDFS: {0}, BFS: {1}, A* euleriano: {2}, A* manhattan: {3}".format(sum(dfs_count)/len(dfs_count), sum(bfs_count)/len(bfs_count),sum(A_eu_count)/len(A_eu_count), sum(A_mah_count)/len(A_mah_count)))
	
	print("média dos tempos de execução para:\nDFS: {0}, BFS: {1}, A* euleriano: {2}, A* manhattan: {3}".format(sum(time1)/len(time1), sum(time2)/len(time2),sum(time4)/len(time4), sum(time3)/len(time3)))


	'''from time import time
	# Test DFS
	m: Maze = Maze()
	print(m)
	
	start1 = time()
	solution1, count1 = dfs(m.start, m.goal_test, m.successors)
	end1 = time()
	if solution1 is None:
		print("No solution found using depth-first search!")
	else:
		path1 = node_to_path(solution1)
		total1 = len(path1)
		m.mark(path1)
		print(m)
		m.clear(path1)
	
	# Test BFS 
	start2 = time()
	solution2, count2 = bfs(m.start, m.goal_test, m.successors)
	end2 = time()
	if solution2 is None:
		print("No solution found using breadth-first search!")
	else:
		path2 = node_to_path(solution2)
		total2 = len(path2)
		m.mark(path2)
		print(m)
		m.clear(path2)
	
	#Test A* with manhattan
	
	distance: Callable[[MazeLocation], float] = manhattan_distance(m.goal)
	start3 = time()
	solution3, count3 = astar(m.start, m.goal_test, m.successors, distance)
	end3 = time()
	
	if solution3 is None:
		print("No solution found using A*!")
	else:
		path3 = node_to_path(solution3)
		total3 = len(path3)
		m.mark(path3)
		print(m)
		m.clear(path3)
	
	#Test A* with euclidian
	
	distance: Callable[[MazeLocation], float] = euclidean_distance(m.goal)
	
	start4 = time()
	solution4, count4 = astar(m.start, m.goal_test, m.successors, distance)
	end4 = time()
	
	if solution4 is None:
		print("No solution found using A*!")
	else:
		path4 = node_to_path(solution4)
		total4 = len(path4)
		m.mark(path4)
		print(m)
		m.clear(path4)
	
	
	time1 = end1-start1
	time2 = end2-start2
	time3 = end3-start3
	time4 = end4-start4
		
	print('Tempos de execução para:\nDFS: {0}, BFS: {1}, A* Manhattan: {2}, A* euclidian: {3}'.format(time1, time2, time3, time4))
	
	print('Distância percorridas para:\nDFS: {0}, BFS: {1}, A* Manhattan: {2}, A* euclidian: {3}'.format(total1, total2, total3, total4))
	
	print('Varreduras para:\nDFS: {0}, BFS: {1}, A* Manhattan: {2}, A* euclidian: {3}'.format(count1, count2, count3, count4))
	'''
