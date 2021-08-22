#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jan 12 06:10:02 2020

@author: gabrielpacmil
"""

#método de euler bolado

import numpy as np
import matplotlib.pyplot as plt
import scipy.integrate as sci

def func(x,t):
    derivs = [np.exp(t)*np.sin(t)]
    return derivs

f = lambda x:np.exp(x)*np.sin(x)

x = np.arange(-10,10,0.1)

dx = x[1]-x[0]

y = np.zeros(x.size)
y[0]=0

psoln = sci.odeint(func, y[0], x)

for i in range(1,x.size):
    y[i] = y[i-1]+dx*f(x[i-1])
    y[i] = y[i-1]+dx*(f(x[i-1])+f(x[i]))/2

plt.figure()

plt.plot(x,y,'Dk')
plt.plot(x,psoln,'r')