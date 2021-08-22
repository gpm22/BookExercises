#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec 26 04:14:49 2019

@author: gabrielpacmil
"""

import numpy as np

x = int(input("entre com o número a ter o seu fatorial calculado "))

a = np.array(list(range(x))) #lista com os números inteiros até o x)
a = a + 1

b=1
for y in a:
    b = b*y
        
print(b)

c = x
d = 1

while c > 1:
    d = d*c
    c = c - 1

print(d)     
