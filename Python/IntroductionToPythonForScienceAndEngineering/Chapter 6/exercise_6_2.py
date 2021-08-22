#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 31 06:17:12 2019

@author: gabrielpacmil
"""

#plot da função y=cos(x)/(1+x²/5) para -15<x<15

import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-15,15,300)

y = np.cos(x)/(1+(x**2)/5)

plt.plot(x,y, 'C5')

plt.xlabel('x')
plt.ylabel('y')

plt.axhline(color='black', zorder=-1)
plt.axvline(color='black', zorder=-1)