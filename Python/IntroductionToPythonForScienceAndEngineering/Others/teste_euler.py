#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jan 12 05:24:07 2020

@author: gabrielpacmil
"""

#código para verificar método de euler

import numpy as np
import matplotlib.pyplot as plt
import scipy.integrate as sci
#import matplotlib.pyplot as plt

def func(x,t):
    y,z = x
    derivs = [t+z,y+5]
    return derivs


t = np.arange(1,2,0.01)
dt = t[1]-t[0]

#dy/dt = t+z, dz/dt = y+5
y = np.zeros(t.size)
z = np.zeros(t.size)

y[0]=2
z[0]=3
#print(y,z)

psoln = sci.odeint(func, [y[0],z[0]], t)

for i in range(1,t.size):
    #print(i)
    y[i]=y[i-1]+dt*(t[i-1]+z[i-1])
    z[i]=z[i-1]+dt*(y[i-1]+5)
    
    y[i]=y[i-1]+dt*((t[i-1]+z[i-1])+(t[i]+z[i]))/2
    z[i]=z[i-1]+dt*((y[i-1]+5+y[i]+5))/2
    


print((y-psoln[:,0]).mean())
print((z-psoln[:,1]).mean())

plt.figure()

#plt.plot(t,y,'dy')
plt.plot(t,z,'db')
#plt.plot(t,psoln[:,0],'-k')
plt.plot(t,psoln[:,1],'-r')
