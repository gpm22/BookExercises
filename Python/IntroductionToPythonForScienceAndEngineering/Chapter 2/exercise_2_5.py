#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec 19 04:30:10 2019

@author: gabrielpacmil
"""
#perímetro de um n-gon dentro de uma esfera de diâmetro 1

import numpy as np

d = 1 #diâmetro

n = 1000000000#número de lados

ang_lado = (n-2)*np.pi/n

ang_utilizado = ang_lado/2

L = d*np.cos(ang_utilizado)

P = n*L

print(P)