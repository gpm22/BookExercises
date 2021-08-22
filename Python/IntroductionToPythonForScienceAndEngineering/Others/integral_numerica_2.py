#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 18:59:20 2020

@author: gabrielpacmil
"""

#tentar uma integral numérica mais funcional

import numpy as np
#import matplotlib.pyplot as plt
def integration_num(x,y):
    inte = ((y[:-1]+y[1:])*(-x[:-1]+x[1:])/2)
    return inte.sum()


#x = np.linspace(2,5,100000)
#y = x**2

#integral= integration_num(x,y)
#erro = (39-integral)/39
#print(integral)
#print(erro)    

#x = np.linspace(0,np.pi,100000)
#y = np.sin(x)

#integral= integration_num(x,y)
#erro = (2-integral)/2
#print(integral)
#print(erro)   

#x = np.linspace(0,3.5,100000)
#y = np.exp(-x**2)

#integral= integration_num(x,y)
#erro = (0.8862262668989721-integral)/0.8862262668989721
#print(integral)
#print(erro)   

#x = np.linspace(-1,1,100000)
#y = 1/(1+x**2)

#integral= integration_num(x,y)
#erro = (np.pi/2-integral)/(np.pi/2)
#print(integral)
#print(erro)


x = np.linspace(-10000,6,1000000)
y = 1/((np.exp(x)+x+1)**2 + np.pi**2)
#plt.plot(x,y)
integral= integration_num(x,y)
erro = (2/3-integral)/(2/3)
print(integral)
print(erro)