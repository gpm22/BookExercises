#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 27 04:12:08 2019

@author: gabrielpacmil
"""

#criar list comprehension para extrair a última coluna
#e criar outra list comprehesion para criar uma vetor com o quadrado da coluna do meio

x = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

c3 = [a[2] for a in x]

print(c3)

x2 = [2*b[1]**2 for b in x]

print(x2)