from csp import Constraint, CSP
from typing import List, NamedTuple, Dict, Optional, Tuple

Grid = List[List[int]] # type alias for grids

class GridLocation(NamedTuple):
	row: int
	column: int
	box: int

def generate_domain(grid:List[List[int]]) -> List[List[GridLocation]]:
	
	domain: Dict[GridLocation, List[int]] = {}
	z = 0
	for x in range(9):
		for y in range(9):
		
			if x<3 and y<3:
				z = 0
			elif (x > 2 and x < 6) and y <3:
				z = 1
			elif (x > 5 and x < 9) and y < 3:
				z = 2
			elif x < 3 and (y > 2 and y < 6):
				z = 3
			elif (x > 2 and x < 6) and (y > 2 and y < 6):
				z = 4
			elif (x > 5 and x < 9) and (y > 2 and y < 6):
				z = 5
			elif x < 3 and (y > 5 and y < 9):
				z = 6
			elif (x > 2 and x < 6) and (y > 5 and y < 9):
				z = 7
			elif (x > 5 and x < 9) and (y > 5 and y < 9):
				z = 8

		
			if grid[x][y] == 0:
				domain[GridLocation(x,y,z)] = list(range(1,10))
			else: 
				domain[GridLocation(x,y,z)] = [grid[x][y]]
				
			
	return domain
				



class SudokuConstraint(Constraint[GridLocation, int]):

	def __init__(self, locais: List[List[int]]) -> None:
		super().__init__(locais)
		self.locais: List[List[int]] = locais
		
	
	def satisfied(self, assignment: Dict[GridLocation, List[List[int]]]) -> bool:
		posicoes = assignment.keys()
		#print(assignment)
		#return False
		for posicao_1 in posicoes:
			for posicao_2 in posicoes:
				if posicao_1 != posicao_2:
					if posicao_1.row == posicao_2.row and assignment[posicao_1] == assignment[posicao_2]: 
						#print('roi 1\n')
						return False
					if posicao_1.column == posicao_2.column and assignment[posicao_1] == assignment[posicao_2]: 
						#print('roi 2\n')
						return False
					if posicao_1.box == posicao_2.box and assignment[posicao_1] == assignment[posicao_2]: 
						#print('roi 3\n')
						return False
						
				#print(posicao_1, posicao_2)
		#print('amem\n')
		return True		


def display_grid(grid: Grid) -> None:
	for row in grid:
		print(" ".join(row))
		
def limpador(assignment):
	posicoes = list(assignment.keys())
	posicoes_1 = []
	posicoes_2 = []
	dominio_1 = assignment
	for posicao in posicoes:
		if len(assignment[posicao]) == 1:
			posicoes_1.append(posicao)
		else:
			posicoes_2.append(posicao)
	
	
	#print(assignment)
	#return False
	for posicao_1 in posicoes_1:
		for posicao_2 in posicoes_2:
			if posicao_1 != posicao_2:
				if posicao_1.row == posicao_2.row:
					if dominio_1[posicao_1][0] in dominio_1[posicao_2]:
						dominio_1[posicao_2].remove(dominio_1[posicao_1][0])
				if posicao_1.column == posicao_2.column: 
					if dominio_1[posicao_1][0] in dominio_1[posicao_2]:
						dominio_1[posicao_2].remove(dominio_1[posicao_1][0])
				if posicao_1.box == posicao_2.box:
					if dominio_1[posicao_1][0] in dominio_1[posicao_2]:
						dominio_1[posicao_2].remove(dominio_1[posicao_1][0])
					
	return dominio_1
					

if __name__ == '__main__':

	sudoku_grid = []
	sudoku_grid.append([1,0,0,2,6,9,0,0,0])
	sudoku_grid.append([2,0,0,7,4,0,0,0,3])
	sudoku_grid.append([0,0,0,0,0,0,0,0,0])
	sudoku_grid.append([6,3,0,8,2,0,0,0,4])
	sudoku_grid.append([0,0,0,0,5,0,0,0,1])
	sudoku_grid.append([9,5,0,0,0,3,0,0,0])
	sudoku_grid.append([0,9,7,0,3,0,4,6,0])
	sudoku_grid.append([3,0,0,0,0,0,0,9,5])
	sudoku_grid.append([4,1,0,5,0,0,8,0,0])
	
	sudoku_grid_2 = map(str, sudoku_grid[:])
	display_grid(sudoku_grid_2)
	print("\n")
	
	
	dominio = generate_domain(sudoku_grid)
	dominio_2 = limpador(dominio)
	
	#print(dominio_2)
	
	#print(len(dominio.keys()))
	
	csp: CSP[GridLocation, List[List[int]]] = CSP(list(dominio_2.keys()), dominio_2)
	
	csp.add_constraint(SudokuConstraint(dominio_2.keys()))
	
	solution: Optional[Dict[GridLocation, List[List[int]]]] = csp.backtracking_search()
	
	#print(solution)
	
	if solution is None:
		print("No solution found!")
	else:
	
		grid = list(solution.keys())
		#numbers = solution.values()
		count = 0
		for x in range(9):
			for y in range(9):
			
				sudoku_grid[x][y] = str(solution[grid[count]])
				count+=1
				
		display_grid(sudoku_grid)
	
	
	
