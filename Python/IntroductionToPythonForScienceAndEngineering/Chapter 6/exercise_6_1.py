#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 31 06:12:01 2019

@author: gabrielpacmil
"""

#plot da função y=3x² para -1<x<3

import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-1,3,300)

y = 3*(x**2)

plt.plot(x,y, 'C5')

plt.xlabel('x')
plt.ylabel('y')

plt.show()
