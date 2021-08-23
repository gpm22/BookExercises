#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 31 06:27:51 2019

@author: gabrielpacmil
"""

#plot de dados lidos de um txt

import numpy as np
import matplotlib.pyplot as plt

tempo, altura, erro = np.loadtxt('../exercise_6_4.txt', skiprows = 1, usecols = (0,1,2), unpack = True)

y = (3+np.sin(np.pi*tempo/5)/2)*tempo*np.exp(-tempo/10)

fig, ax = plt.subplots()

#ax.plot(tempo, altura, 'o')
ax.errorbar(tempo, altura, fmt ='o', yerr = erro, label = 'data', ecolor = 'black')
ax.plot(tempo, y, zorder=-1, color='orange')
ax.set_xlabel('tempo(s)')
ax.set_ylabel('altura(m)')
