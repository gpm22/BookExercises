#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Dec 22 03:00:18 2019

@author: gabrielpacmil
"""
#exercício 3.5

import numpy as np

#a)

#a = np.zeros((8,8))
#a[0] = 1 
#a[-1] = 1

a1 = np.zeros(8)
a1[0], a1[-1] =1,1
a2 = np.ones(8)

a = np.array([[a2],
           [a1],
           [a1],
           [a1],
           [a1],
           [a1],
           [a1],
           [a2]])
#print(a)


#b)

b1 = np.zeros(8)
b2 = np.ones(8)

b = np.array([[b2],
           [b1],
           [b2],
           [b1],
           [b2],
           [b1],
           [b2],
           [b1]])

#print(b)

#c)

c = np.arange(2,50,5)

#print(c)

c[c%3 > 0] = c[c%3 > 0]*(-1)

#print(c)

#d - size, shape, mean and standard deviation of all posterior arrays

print(a.size)
print(a.shape)
print(a.mean())
print(a.std())

print(b.size)
print(b.shape)
print(b.mean())
print(b.std())


print(c.size)
print(c.shape)
print(c.mean())
print(c.std())



