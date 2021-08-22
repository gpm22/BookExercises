from __future__ import annotations
from dataclasses import dataclass

@dataclass #para criar automaticamente um __init__() com todas as varias com type annotations
class Edge:
	u: int # the "from" vertex
	v: int # the "to" vertex

	def reversed(self) -> Edge:
		return Edge(self.v, self.u)

	def __str__(self) -> str:
		return f"{self.u} -> {self.v}"
