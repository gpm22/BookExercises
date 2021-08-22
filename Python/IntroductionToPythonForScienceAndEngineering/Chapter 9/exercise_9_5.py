#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Jan 13 05:10:01 2020

@author: gabrielpacmil
"""

#usar polyval do np com special.eval_chebyt 
#para fazer as seguintes funções:

#a) os 4 primeiros polinômios de chebyshec 
#do primeiro tipo no intervalo -1 a 1

#b) os 4 primeiros polinômios de hermite 
#multiplicados por np.exp(-x*x/2)/np.sqrt((2**n)*np.math.factorial(n)*np.sqrt(np.pi))
#plotar tudo entre -5 a 5

import numpy as np
import matplotlib.pyplot as plt
import scipy.special as scs


esp_1 = lambda n,x:np.exp(-x*x/2)/np.sqrt((2**n)*np.math.factorial(n)*np.sqrt(np.pi))

#a)

c = ['a','b','c','d'] #polinômios de chebyshev
N = 4
for i in range(N):
    print(i)
    c[i] = scs.chebyt((i+1))

x = np.linspace(-1,1,512)

lc = ['a','b','c','d'] 

for i in range(N):
    lc[i] = np.polyval(c[i],x)

#b) 
    
h = ['a','b','c','d']  #polinômios de chebyshev

for i in range(N):
    h[i] = scs.hermite((i+1))

x_1 = np.linspace(-5,5,1024)

lh = ['a','b','c','d'] 

for i in range(N):
    lh[i] = np.polyval(h[i],x_1)*esp_1((i+1),x_1)
    
#plot
    
fig_1, (ax1, ax2) = plt.subplots(2, 1, figsize=(9, 6))

ax1.plot(x,lc[0],  color='black', label='Chebyshev de ordem 1')
ax1.plot(x,lc[1],  color='dodgerblue', label='Chebyshev de ordem 2')
ax1.plot(x,lc[2],  color='coral', label='Chebyshev de ordem 3')
ax1.plot(x,lc[3],  color='yellow', label='Chebyshev de ordem 4')
ax1.legend()
ax1.set_xlabel(r'$x$')
ax1.set_ylabel(r'$f(x)$')

ax2.plot(x_1,lh[0],  color='black', label='Hermite de ordem 1')
ax2.plot(x_1,lh[1],  color='dodgerblue', label='Hermite  de ordem 2')
ax2.plot(x_1,lh[2],  color='coral', label='Hermite  de ordem 3')
ax2.plot(x_1,lh[3],  color='yellow', label='Hermite de ordem 4')
ax2.legend()
ax2.set_xlabel(r'$x$')
ax2.set_ylabel(r'$f(x)$')

fig_1.tight_layout()
fig_1.show()
fig_1.canvas.manager.window.raise_()