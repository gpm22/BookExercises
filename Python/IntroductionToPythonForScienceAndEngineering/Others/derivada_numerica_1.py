#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Dec 29 03:48:39 2019

@author: gabrielpacmil
"""

#programa para testar minha hipótese sobre derivação numérica simples

import numpy as np
import matplotlib.pyplot as plt

x = np.array(np.linspace(-10, 10, 1000))

y = np.tan(x)

Dy1 = 1/(np.cos(x)**2)

#v = (y[:-1]-y[1:])/(t[:-1]-t[1:])
#t2 = (t[:-1]+t[1:])/2

Dy2 = (y[:-1]-y[1:])/(x[:-1]-x[1:]) #derivação numérica BOLAAAAAAAAAAAAAAAAAAA

x2 = (x[:-1]+x[1:])/2 

plt.figure()

#plt.plot(x,y,'-g')
plt.plot(x2, Dy2, 'Dm') #'Dm'
plt.plot(x, Dy1, '-', color = 'black')  #'-k'
plt.ylim(1,10)

plt.show() 

