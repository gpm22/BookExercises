from typing import TypeVar, Generic, List, Optional
from edge import Edge
from generic_search import bfs, Node, node_to_path


V = TypeVar('V') # type of the vertices in the graph

class Graph(Generic[V]):
	def __init__(self, vertices: List[V] = []) -> None:
		self._vertices: List[V] = vertices
		self._edges: List[List[Edge]] = [[] for _ in vertices]
		
	@property
	def vertex_count(self) -> int:
		return len(self._vertices) # Number of vertices

	@property
	def edge_count(self) -> int:
		return sum(map(len, self._edges)) # Number of edges

	# Add a vertex to the graph and return its index
	def add_vertex(self, vertex: V) -> int:
		self._vertices.append(vertex)
		self._edges.append([]) # Add empty list for containing edges
		return self.vertex_count - 1 # Return index of added vertex
	
	# Remove a vertex from the graph and return its index
	
	def remove_vertex_by_index(self,  index: int) -> None:
	
		if index < len(self._vertices):
			if self._vertices[index] in self._vertices:
			
				vertex = self._vertices[index]
				self._vertices.pop(index)
				self._edges.pop(index)
			
				#remove todos os egdes que apontam para o vertex
			
				for lista in self._edges:
					for edges_1 in lista:
						if edges_1.v == index:
							lista.remove(edges_1)
				
					for edges_1 in lista:
						if edges_1.v > index:
							edges_1.v -=1
						if edges_1.u > index:
							edges_1.u -=1
		
				print('The vertex {1} at index "{0}" was successfully removed from the graph.\n'.format(index, vertex))
			
		else:
			print('The index "{0}" do not exist.\n'.format(index))
		
		
	def remove_vertex_by_name(self,  vertex: V) -> None:
		if vertex in self._vertices:	
			i = self._vertices.index(vertex)
			self.remove_vertex_by_index(i)
		else:
			print('The vertex "{0}" is not in the graph.\n'.format(vertex))
			

	# This is an undirected graph,
	# so we always add edges in both directions
	def add_edge(self, edge: Edge, dia = False) -> None:
		if not dia:
			self._edges[edge.u].append(edge)
			self._edges[edge.v].append(edge.reversed())
		else:
			self._edges[edge.u].append(edge)
		
	def remove_edge(self, edge: Edge) -> None:

		if edge in self._edges[edge.u]:
			self._edges[edge.u].remove(edge)
			
			if edge.reversed() in self._edges[edge.v]:
				self._edges[edge.v].remove(edge.reversed())
		else:
			print('Edge {0} inexistente.\n'.format(edge))

	# Add an edge using vertex indices (convenience method)
	def add_edge_by_indices(self, u: int, v: int) -> None:
		edge: Edge = Edge(u, v)
		self.add_edge(edge)
		
	def remove_edge_by_indices(self, u: int, v: int) -> None:
		edge: Edge = Edge(u, v)
		self.remove_edge(edge)

	# Add an edge by looking up vertex indices (convenience method)
	def add_edge_by_vertices(self, first: V, second: V) -> None:
		u: int = self._vertices.index(first)
		v: int = self._vertices.index(second)
		self.add_edge_by_indices(u, v)
		
	def remove_edge_by_vertices(self, first: V, second: V) -> None:
		u: int = self._vertices.index(first)
		v: int = self._vertices.index(second)
		self.remove_edge_by_indices(u, v)

	# Find the vertex at a specific index
	def vertex_at(self, index: int) -> V:
		return self._vertices[index]

	# Find the index of a vertex in the graph
	def index_of(self, vertex: V) -> int:
		return self._vertices.index(vertex)

	# Find the vertices that a vertex at some index is connected to
	def neighbors_for_index(self, index: int) -> List[V]:
		return list(map(self.vertex_at, [e.v for e in self._edges[index]]))

	# Look up a vertice's index and find its neighbors 	(convenience method)
	def neighbors_for_vertex(self, vertex: V) -> List[V]:
		return self.neighbors_for_index(self.index_of(vertex))

	# Return all of the edges associated with a vertex at some index
	def edges_for_index(self, index: int) -> List[Edge]:
		return self._edges[index]
	# Look up the index of a vertex and return its edges (convenience method)
	def edges_for_vertex(self, vertex: V) -> List[Edge]:
		return self.edges_for_index(self.index_of(vertex))

# Make it easy to pretty-print a Graph
	def __str__(self) -> str:
		desc: str = ""

		for i in range(self.vertex_count):
			desc += f"{self.vertex_at(i)} -> {self.neighbors_for_index(i)}\n"
		return desc
		
		
if __name__ == "__main__":
	# test basic Graph construction
	city_graph: Graph[str] = Graph(["Seattle", "San Francisco", "Los Angeles", "Riverside", "Phoenix", "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston", "Detroit", "Philadelphia", "Washington"])

	city_graph.add_edge_by_vertices("Seattle", "Chicago")
	city_graph.add_edge_by_vertices("Seattle", "San Francisco")
	city_graph.add_edge_by_vertices("San Francisco", "Riverside")
	city_graph.add_edge_by_vertices("San Francisco", "Los Angeles")
	city_graph.add_edge_by_vertices("Los Angeles", "Riverside")
	city_graph.add_edge_by_vertices("Los Angeles", "Phoenix")
	city_graph.add_edge_by_vertices("Riverside", "Phoenix")
	city_graph.add_edge_by_vertices("Riverside", "Chicago")
	city_graph.add_edge_by_vertices("Phoenix", "Dallas")
	city_graph.add_edge_by_vertices("Phoenix", "Houston")
	city_graph.add_edge_by_vertices("Dallas", "Chicago")
	city_graph.add_edge_by_vertices("Dallas", "Atlanta")
	city_graph.add_edge_by_vertices("Dallas", "Houston")
	city_graph.add_edge_by_vertices("Houston", "Atlanta")
	city_graph.add_edge_by_vertices("Houston", "Miami")
	city_graph.add_edge_by_vertices("Atlanta", "Chicago")
	city_graph.add_edge_by_vertices("Atlanta", "Washington")
	city_graph.add_edge_by_vertices("Atlanta", "Miami")
	city_graph.add_edge_by_vertices("Miami", "Washington")
	city_graph.add_edge_by_vertices("Chicago", "Detroit")
	city_graph.add_edge_by_vertices("Detroit", "Boston")
	city_graph.add_edge_by_vertices("Detroit", "Washington")
	city_graph.add_edge_by_vertices("Detroit", "New York")
	city_graph.add_edge_by_vertices("Boston", "New York")
	city_graph.add_edge_by_vertices("New York", "Philadelphia")
	city_graph.add_edge_by_vertices("Philadelphia", "Washington")
	print(city_graph)
	
	bfs_result: Optional[Node[V]] = bfs("Boston", lambda x: x == "Miami", city_graph.neighbors_for_vertex)

	if bfs_result is None:
		print("No solution found using breadth-first search!")
	else:
		path: List[V] = node_to_path(bfs_result)
		print("Path from Boston to Miami:")
		print(path)
		
	city_graph.remove_vertex_by_name("Bo")
	
	print(city_graph.vertex_at(10))
	
	city_graph.remove_vertex_by_index(19)
	
	
	print(city_graph)
	
	city_graph.remove_edge_by_indices(4,5)
	
	print(city_graph)
	

	city_graph.remove_edge_by_vertices('Detroit','Dallas')
	
	print(city_graph)
	

