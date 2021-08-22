#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Jan  6 04:19:41 2020

@author: gabrielpacmil
"""

#código para plotar o seno cardinal

import numpy as np
import matplotlib.pyplot as plt

#def sinc(x):
#    y = np.where(x == 0.0, 1.0, np.sin(x)/x)
#    return y

sinc = lambda x:np.where(x == 0.0, 1.0, np.sin(x)/x)
    
x = np.arange(-100, 100, 0.1)    
y = sinc(x)  
  
fig, ax = plt.subplots(figsize=(8, 4))
ax.plot(x, y)
#ax.set_xlim(-10, 10)
ax.axhline(color="gray", zorder=-1)
ax.axvline(color="gray", zorder=-1)
#fig.savefig("sinc2.pdf")
fig.show()    