#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jan  9 04:55:21 2020

@author: gabrielpacmil
"""

import numpy as np

N = 10 #número de linhas e colunas

a = np.zeros([N,N])

a[0::(N-1)]=1;
#a[-1]=1;
a[:,0::(N-1)]=1;
#a[:,-1]=1;

#print(a)

b = np.zeros([N,N])

b[1::2,0::2] = 1
b[0::2,1::2] = 1

#for x in range(0,N,2):
#    b[x][0::2]=1

#for x in range(1,N,2):
 #   b[x][1::2]=1

#print(b)
    
c = np.arange(2, 50, 5) 
print(c)
c[c%3!=0] = -c[c%3!=0]
print(c)


