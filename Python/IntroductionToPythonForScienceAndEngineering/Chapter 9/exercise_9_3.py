#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Jan 11 06:08:34 2020

@author: gabrielpacmil
"""

#resolver EDOS utilizando scipy boladão

import numpy as np
import scipy.integrate as sci
import matplotlib.pyplot as plt

def func(x,t,parametros):
    a,b,c = parametros
    x,y,z = x
    derivs = [a*(y-x),(c-a)*x-x*z+c*y, x*y-b*z]
    return derivs


#condições iniciais

x0, y0, z0 = -10, 0, 35
y0 = np.array([x0,y0,z0])
#parâmetros iniciais

a, b, c = 40, 5, 35 #caóticas; 

a_1, b_1, c_1 = 40, 10, 35 #periódicas

parametros = [a, b, c]
parametros_1 = [a_1, b_1, c_1]

# dx/dt = a(y-x)

# dy/dt = (c-a)x-xz + cy

#dz/dt = xy-bz


t = np.arange(0.,10, 0.1)

psoln = sci.odeint(func, y0, t, args =(parametros,))

psoln_1 = sci.odeint(func, y0, t, args =(parametros_1,))

#plot dos resultados

fig, ax = plt.subplots(3,2)

ax[0,0].plot(t, psoln[:, 0], 'b-')
ax[0,0].set_xlabel('tempo')
ax[0,0].set_ylabel('x', fontsize=14)
ax[0,0].set_ylim(-30,30)

ax[1,0].plot(t, psoln[:, 1], 'g-')
ax[1,0].set_xlabel('tempo')
ax[1,0].set_ylabel('y', fontsize=14)
ax[1,0].set_ylim(-30,30)

ax[2,0].plot(t, psoln[:, 2], 'r-')
ax[2,0].set_xlabel('tempo')
ax[2,0].set_ylabel('z', fontsize=14)
ax[2,0].set_ylim(0,40)

ax[0,1].plot(psoln[:, 1], psoln[:, 0], dashes = (1,2), ms=1, color = 'gray')
ax[0,1].set_xlabel('y')
ax[0,1].set_ylabel('x', fontsize=14)
ax[0,1].set_ylim(-30,30)
ax[0,1].set_xlim(-30,30)

ax[1,1].plot(psoln[:, 2], psoln[:, 1], dashes = (1,2), ms=1, color = 'blue')
ax[1,1].set_xlabel('z')
ax[1,1].set_ylabel('y', fontsize=14)
ax[1,1].set_ylim(-30,30)
ax[1,1].set_xlim(0,40)

ax[2,1].plot(psoln[:, 0], psoln[:, 2], dashes = (1,2), ms=1, color = 'green')
ax[2,1].set_xlabel('x')
ax[2,1].set_ylabel('z', fontsize=14)
ax[2,1].set_ylim(0,40)
ax[2,1].set_xlim(-30,30)

fig.tight_layout()
fig.show()
fig.canvas.manager.window.raise_()


fig2, ax2 = plt.subplots(3,2)

ax2[0,0].plot(t, psoln_1[:, 0], 'b-')
ax2[0,0].set_xlabel('tempo')
ax2[0,0].set_ylabel('x', fontsize=14)
ax2[0,0].set_ylim(-30,30)

ax2[1,0].plot(t, psoln_1[:, 1], 'g-')
ax2[1,0].set_xlabel('tempo')
ax2[1,0].set_ylabel('y', fontsize=14)
ax2[1,0].set_ylim(-30,30)

ax2[2,0].plot(t, psoln_1[:, 2], 'r-')
ax2[2,0].set_xlabel('tempo')
ax2[2,0].set_ylabel('z', fontsize=14)
ax2[2,0].set_ylim(0,40)

ax2[0,1].plot(psoln_1[:, 1], psoln_1[:, 0], dashes = (1,2), ms=1, color = 'gray')
ax2[0,1].set_xlabel('y')
ax2[0,1].set_ylabel('x', fontsize=14)
ax2[0,1].set_ylim(-30,30)
ax2[0,1].set_xlim(-30,30)


ax2[1,1].plot(psoln_1[:, 2], psoln_1[:, 1], dashes = (1,2), ms=1, color = 'yellow')
ax2[1,1].set_xlabel('z')
ax2[1,1].set_ylabel('y', fontsize=14)
ax2[1,1].set_ylim(-30,30)
ax2[1,1].set_xlim(0,40)


ax2[2,1].plot(psoln_1[:, 0], psoln_1[:, 2], dashes = (1,2), ms=1, color = 'red')
ax2[2,1].set_xlabel('x')
ax2[2,1].set_ylabel('z', fontsize=14)
ax2[2,1].set_ylim(0,40)
ax2[2,1].set_xlim(-30,30)


for i in [0,1,2]:
    ax[i,0].set_xlim(0,10)
    ax2[i,0].set_xlim(0,10)

fig2.tight_layout()
fig2.show()
fig2.canvas.manager.window.raise_()