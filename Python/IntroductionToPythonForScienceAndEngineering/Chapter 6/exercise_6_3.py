#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 31 06:20:14 2019

@author: gabrielpacmil
"""

#plot da funções sin(x) e cos(x), -pi < x < pi 

import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-np.pi,np.pi,300)

fig = plt.figure()

plt.plot(x,np.sin(x), color = 'orange', label = 'sin(x)')
plt.plot(x,np.cos(x), 'g', label = 'cos(x)')

plt.xlabel('x')
plt.ylabel('sin(x), cos(x)')

plt.axhline(color='gray', zorder=-1)
plt.axvline(color='gray', zorder=-1)

plt.legend(loc='upper left', title='legenda boladona lindíssima')