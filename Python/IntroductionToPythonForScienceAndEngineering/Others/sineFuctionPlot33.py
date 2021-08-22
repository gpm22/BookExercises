#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 27 17:36:36 2019

@author: gabrielpacmil
"""

#exemplo de como fazer um script básico para plot

#Import the NumPy and matplotlib modules (lines 1-2 below).

import numpy as np
import matplotlib.pyplot as plt

#Create the (x, y) data arrays (lines 3-4 below).

x = np.linspace(0, 4.*np.pi, 100)
y = np.sin(x)
z = np.cos(x)

#Have plot draw straight lines between the (x, y) data points (line 5 below).

plt.plot(y, z)
                                         
#Display the plot in a figure window using the show function (line 6 below).

plt.show()