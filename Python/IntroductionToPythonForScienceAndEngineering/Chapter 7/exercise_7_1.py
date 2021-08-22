#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Jan  6 19:02:48 2020

@author: gabrielpacmil
"""

import numpy as np
import matplotlib.pyplot as plt


def bessel_esferico(x, N):
    J0 = np.sin(x)/x
    J0[x==0]=1
    J1 = J0/x - np.cos(x)/x
    J1[x==0]=0
    J2 = 3*J1/x-J0
    J2[x==0]=0
    if N == 0:
        y = J0
    elif N == 1:
        y = J1
    elif N == 2:
        y = J2
    else:
        print('se fuder, carai, só vai até 2')
    return y

        

x = np.linspace(0,20,200)

#J0 = np.sin(x)/x
#J0[x==0]=1

#J1 = np.sin(x)/(x**2)-np.cos(x)/x
#J1 = J0/x - np.cos(x)/x
#J1[x==0]=0

#J2 = (3/(x**2)-1)*np.sin(x)/x-3*np.cos(x)/(x**2)
#J2 = 3*J1/x-J0
#J2[x==0]=0

fig1 = plt.figure()

plt.plot(x,bessel_esferico(x,0), label = '$j_o(x)$')
plt.plot(x,bessel_esferico(x,1), '-.r', label = '$j_1(x)$')
plt.plot(x,bessel_esferico(x,2), '_g', label = '$j_2(x)$')
plt.axhline(color='gray', zorder=-1)
plt.ylim(-0.3,1.05)
plt.xlim(0,20)
plt.legend(loc='upper right')

plt.show()


