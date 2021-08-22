#My solution for The Seven Bridges of Königsberg

from typing import TypeVar, Generic, List, Optional
from grafo_pesado import  WeightedGraph

from graph import Graph
from edge_pesado import WeightedEdge
from generic_search import bfs, Node, node_to_path
from mst import *#mst, total_weight, print_weighted_path

from csp import *

V = TypeVar('V') # type of the vertices in the graph
WeightedPath = List[WeightedEdge] # type alias for paths


class PonteConstraint(Constraint[GridLocation, int]):

	def __init__(self, Pontes: List[List[int]]) -> None:
		super().__init__(Pontes)
		self.Pontes: List[List[int]] = Pontes
		
	
	def satisfied(self, assignment: Dict[GridLocation, List[List[int]]]) -> bool:
		combinacoes = assignment.keys()


if __name__ == '__main__':

	pontes:  WeightedGraph[str] =  WeightedGraph(['A', 'B', 'C', 'D', 'E', 'F', 'G'])
	
	peso = 1
	
	pontes.add_edge_by_vertices("A", "B", peso)
	pontes.add_edge_by_vertices("A", "C", peso)
	pontes.add_edge_by_vertices("A", "D", peso)
	pontes.add_edge_by_vertices("A", "F", peso)
	pontes.add_edge_by_vertices("A", "G", peso)
	
	pontes.add_edge_by_vertices("B", "C", peso)
	pontes.add_edge_by_vertices("B", "D", peso)
	pontes.add_edge_by_vertices("B", "F", peso)
	pontes.add_edge_by_vertices("B", "G", peso)
	
	pontes.add_edge_by_vertices("C", "D", peso)
	pontes.add_edge_by_vertices("C", "E", peso)
	
	pontes.add_edge_by_vertices("D", "E", peso)
	pontes.add_edge_by_vertices("D", "F", peso)
	pontes.add_edge_by_vertices("D", "G", peso)
	
	pontes.add_edge_by_vertices("E", "F", peso)
	pontes.add_edge_by_vertices("E", "G", peso)
	
	pontes.add_edge_by_vertices("F", "G", peso)
	

	print(pontes)
	
	
	'''Terras: WeightedGraph[str] = WeightedGraph(['A', 'B', 'C', 'D']) 
	
	Terras.add_edge_by_vertices("A", "B")
	Terras.add_edge_by_vertices("A", "B")
	Terras.add_edge_by_vertices("A", "C")
	Terras.add_edge_by_vertices("B", "D")
	Terras.add_edge_by_vertices("B", "D")
	Terras.add_edge_by_vertices("B", "C")
	Terras.add_edge_by_vertices("C", "D")
	
	print(Terras)
	
	result: Optional[WeightedPath] = mst(Terras)
	if result is None:
		print("No solution found!")
	else:
		print_weighted_path(Terras, result)
		
		
	bfs_result: Optional[Node[V]] = bfs("A", lambda x: x == 'A', pontes.neighbors_for_vertex)

	if bfs_result is None:
		print("No solution found using breadth-first search!")
	else:
		path: List[V] = node_to_path(bfs_result)
		print("Path from A to A:")
		print(path, '\n')'''
	
	'''#bfs_result: Optional[Node[V]] = bfs("A", lambda x: x == 'A', pontes.neighbors_for_vertex)
	bfs_result: Optional[Node[V]] = bfs("A", lambda x: x == 'A', pontes.neighbors_for_vertex)

	if bfs_result is None:
		print("No solution found using breadth-first search!")
	else:
		path: List[V] = node_to_path(bfs_result)
		print("Path from A to A:")
		print(path, '\n')
		
	result: Optional[WeightedPath] = mst(pontes)
	if result is None:
		print("No solution found!")
	else:
		print_weighted_path(pontes, result)'''
		
	
	
