from csp import CSP, Constraint
from string import ascii_uppercase

from typing import NamedTuple, List, Dict, Optional, Tuple

Grid = List[List[Tuple]] # type alias for grids

class GridLocation(NamedTuple):
	row: int
	column: int
	
def generate_grid(rows: int, columns: int) -> Grid:
	return [['*' for c in range(columns)] for r in range(rows)]

def display_grid(grid: Grid) -> None:
	for row in grid:
		print("".join(row))
		

def generate_domain(chip: Tuple, grid: Grid) -> List[List[GridLocation]]:
	domain: List[List[GridLocation]] = []
	height: int = len(grid)
	width: int = len(grid[0])
	
	altura: int = chip[0]
	largura: int = chip[1]

	for row in range(height):
		for col in range(width):
		
			columns: range = range(col, col + largura)
			rows: range = range(row, row + altura)
	
			if row + altura <= height and col + largura <= width:
				domain.append([GridLocation(r, c) for r in rows for c in columns])
				domain.append([GridLocation(c, r) for r in rows for c in columns])

	return domain
	
	

class ChipSearchConstraint(Constraint[Tuple, List[GridLocation]]):

	def __init__(self, chips: List[Tuple]) -> None:
		super().__init__(chips)
		self.chips: List[Tuple] = chips

	def satisfied(self, assignment: Dict[Tuple, List[GridLocation]]) -> bool:
		# if there are any duplicates grid locations, then there is an overlap
		all_locations = [locs for values in assignment.values() for locs in values]
		
		return len(set(all_locations)) == len(all_locations)
		


if __name__ == "__main__":
	grid: Grid = generate_grid(9, 9)
	chips: List[Tuple] = [(6,1), (1,6), (3,4), (4,2)]#[(6,2), (2,2), (1,2), (4,4)]

	locations: Dict[Tuple, List[List[GridLocation]]] = {}

	for chip in chips:
		locations[chip] = generate_domain(chip, grid)

	csp: CSP[Tuple, List[GridLocation]] = CSP(chips, locations)
	csp.add_constraint(ChipSearchConstraint(chips))
	solution: Optional[Dict[Tuple, List[GridLocation]]] = csp.backtracking_search()
	
	
	if solution is None:
		print("No solution found!")
	else:
		count = 0
		for chip, grid_locations in solution.items():
			for locais in grid_locations:
				(row, col) = (locais.row, locais.column)
				grid[row][col] = str(count)
			
			count+=1
				
		display_grid(grid)
	
