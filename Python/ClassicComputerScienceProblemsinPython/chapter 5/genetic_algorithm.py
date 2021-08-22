from __future__ import annotations
from typing import TypeVar, Generic, List, Tuple, Callable
from enum import Enum
from random import choices, random
from heapq import nlargest
from statistics import mean
from cromossomo import Chromosome


C = TypeVar('C', bound=Chromosome) # type of the chromosomes

class GeneticAlgorithm(Generic[C]):
	SelectionType = Enum("SelectionType", "ROULETTE TOURNAMENT")
	
	
	def __init__(self, initial_population: List[C], threshold: float, max_ generations: int = 100, mutation_chance: float = 0.01, crossover_chance: float = 0.7, selection_type: SelectionType = SelectionType.TOURNAMENT) -> None:
	
		self._population: List[C] = initial_population
		self._threshold: float = threshold
		self._max_generations: int = max_generations
		self._mutation_chance: float = mutation_chance
		self._crossover_chance: float = crossover_chance
		self._selection_type: GeneticAlgorithm.SelectionType = selection_type
		self._fitness_key: Callable = type(self._population[0]).fitness
		
	# Use the probability distribution wheel to pick 2 parents
	# Note: will not work with negative fitness results
	def _pick_roulette(self, wheel: List[float]) -> Tuple[C, C]:
		return tuple(choices(self._population, weights=wheel, k=2))
		
	
