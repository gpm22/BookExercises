#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Jan  8 05:49:52 2020

@author: gabrielpacmil
"""

#Exercício 8.1 - fazer o fitting de uns certos dados

import numpy as np
import matplotlib.pyplot as plt

def lineFit(x, y): #regressão linear boladaça
    ''' Returns slope and y-intercept of linear fit to (x,y) data set'''
    xavg = x.mean()
    slope = (y * (x-xavg)).sum()/(x * (x-xavg)).sum() #b
    yint = y.mean() - slope*xavg #a
    return slope, yint

def lineFitWt(x,y,uncert = np.array([0])): #regressão linear chi² boladaça
    if uncert.sum() == 0:
        uncert = np.ones(x.size)
    
    x_a = (x/(uncert**2)).sum()/(1/(uncert**2) ).sum() #x chápeu
    y_a = (y/(uncert**2)).sum()/(1/(uncert**2) ).sum() #y chápeu
    
    #print(x_a)
    #print(y_a)
    slope = ((y*(x-x_a))/(uncert**2)).sum()/((x*(x-x_a))/(uncert**2)).sum()
    yint = y_a-slope*x_a
    
    uncert_b = (1/((x*(x-x_a))/(uncert**2)).sum())**0.5
    uncert_a = uncert_b*(((x**2)/(uncert**2)).sum()/(1/(uncert**2)).sum())**0.5
    
    return slope, yint, uncert_a, uncert_b
    
def chi_r(x,y,uncert= np.array([0])):
    if uncert.sum() == 0:
        uncert = np.ones(x.size)
   
    x_a = (x/(uncert**2)).sum()/(1/(uncert**2) ).sum() #x chápeu
    y_a = (y/(uncert**2)).sum()/(1/(uncert**2) ).sum() #y chápeu
    slope = ((y*(x-x_a))/(uncert**2)).sum()/((x*(x-x_a))/(uncert**2)).sum()
    yint = y_a-slope*x_a
    
    chi_2 = (((y-yint-slope*x)/uncert)**2).sum()
    chi_red = chi_2/(x.size-2)

    return chi_red

distancia = np.array([0.38, 0.64, 0.91, 1.26, 1.41, 1.66, 1.90, 2.18])
forca = np.array([1.4, 1.65, 3.0, 3.95, 4.3, 5.20, 6.85, 7.4])
erro_f = np.array([0.4, 0.5, 0.4, 0.5, 0.6, 0.5, 0.5, 0.4])



b_1,a_1, erro_a, erro_b = lineFitWt(distancia, forca, erro_f)
chi_red_1 = chi_r(distancia, forca, erro_f)

    
f_1 = a_1 + b_1*distancia
f_2 = a_1-erro_a + (b_1+erro_b)*distancia
f_3 = a_1+erro_a + (b_1-erro_b)*distancia


print("\no" + r'$ {\chi} $' + "² sem correção é {}.".format(chi_red_1))
print("\nO erro associado ao ponto inicial é de {} e o associado a inclinação é {}.".format(erro_a,erro_b))

fig1 = plt.figure()
plt.plot(distancia, f_1, label = 'Fitting ideal')
plt.plot(distancia, f_2,'g-.', label = 'Fitting + erro')
plt.plot(distancia, f_3, 'r-.', label = 'Fittin - erro')
plt.errorbar(distancia, forca, fmt ='o', yerr = erro_f, label = 'data', ecolor = 'black')
plt.xlabel('Distância (cm)')
plt.ylabel('Força (N)')
plt.legend(loc='upper left')

plt.show()
fig1.canvas.manager.window.raise_()

