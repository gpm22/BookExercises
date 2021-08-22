#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec 19 04:12:55 2019

@author: gabrielpacmil
"""

#exercicio 1 do capítulo 2

h0 = 1.6#altura inicial em m
v0 = 14.2#velocidade inicial em m/s
g = 9.8 #gravidade e m/s²
t = 2 #tempo em s

h = h0 + v0*t-(g*(t**2))/2
v = v0 - g*t

print(h,v)