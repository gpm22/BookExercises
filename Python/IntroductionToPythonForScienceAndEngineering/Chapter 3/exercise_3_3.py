#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 20 16:16:40 2019

@author: gabrielpacmil
"""

#exercício 3.3

import numpy as np

h0 = 10 #altura inicial em metros;
g = 9.8 #aceleração gravitacional em m/s^2

y = np.arange(10,0,-0.5)

t = np.sqrt(2*(h0-y)/g)

b = t[y==0.5]

#print(a)

v = (y[:-1]-y[1:])/(t[:-1]-t[1:])

t2 = (t[:-1]+t[1:])/2

a = (v[:-1]-v[1:])/(t2[:-1]-t2[1:])

print(a)