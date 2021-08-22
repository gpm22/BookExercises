#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Jan 11 05:54:27 2020

@author: gabrielpacmil
"""

#ercício 9.1 -> resolver numericamente o seguinte sistema

# x1 - 2*x2 + 9*x3 + 13*x4 = 1
# -5*x1 + x2 + 6*x3 - 7*x4 = -3
# 4*x1 + 8*x2 - 4*x3 - 2*x3 = -2
# 8*x1 + 5*x2 - 7*x3 + x4 = 5



import numpy as np
import scipy.linalg as scl

#considerando o sistema como A*x = b

A = np.array([[1,-2,9,13],[-5,1,6,-7],[4,8,-4,-2],[8,5,-7,1]])

b = np.array([1,-3,-2,5])

#método 1

print(scl.solve(A,b))

#método 2

Ainv = scl.inv(A)

print(np.dot(Ainv,b))
