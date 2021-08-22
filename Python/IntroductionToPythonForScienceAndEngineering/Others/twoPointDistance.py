#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec 19 03:48:25 2019

@author: gabrielpacmil
"""

""" código boladão pra cálcular a distância entre 2 pontos cartesianos"""

import numpy as np

#ponto 1

x1 = 23.7
y1 = -9.2
z1 = -7.8

#ponto 2

x2 = -3.5
y2 = 4.8
z2 = 8.1

distancia1 = ((x2-x1)**2+(y2-y1)**2+(z2-z1)**2)**(1/2)
distancia2 = np.sqrt((x2-x1)**2+(y2-y1)**2+(z2-z1)**2)
print(distancia1)
print(distancia2)
