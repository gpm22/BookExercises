#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 03:27:47 2020

@author: gabrielpacmil
"""

#implementaçãp do método chi²

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

tempo, velocidade, erro = np.loadtxt('/home/gabrielpacmil/estatistica e python/programas_muito_loucos/cientifico/primeiro livro que usei/exercise_7_3.txt', skiprows = 2, usecols = (0,1,2), unpack = True)

b_1,a_1 = lineFit(tempo, velocidade)
b_2,a_2, erro_a, erro_b = lineFitWt(tempo, velocidade, erro)
#b_3,a_3 = lineFitWt(tempo, velocidade)

chi_red_1 = chi_r(tempo, velocidade)
#((velocidade-a_1-b_1*tempo)**2).sum()/(tempo.size-2)
chi_red_2 = chi_r(tempo, velocidade, erro)

#g = chi_red_1 - ((velocidade-a_1-b_1*tempo)**2).sum()/(tempo.size-2)
    

v_1 = a_1 + b_1*tempo
v_2 = a_2 + b_2*tempo
#v_3 = a_3 + b_3*tempo

g = (velocidade[-1]-velocidade[0])/(tempo[-1]-tempo[0])
print("\nA aceleração média é {} m/s²".format(g))

v_3 = velocidade[0] + g*tempo-g*tempo[0]

print("\no" + r'$ {\chi} $' + "² sem correção é {}.".format(chi_red_1))
print("\no" + r'$ {\chi} $' + "² com correção é {}.".format(chi_red_2))
print("\nO erro associado ao ponto inicial é de {} e o associado a inclinação é {}.".format(erro_a,erro_b))

fig1 = plt.figure()
plt.plot(tempo, v_1, label = 'curva sem correção')
plt.plot(tempo, v_2,'g-.', label = 'curva com correção')
#plt.errorbar(tempo, v_2, fmt ='g-.', yerr = erro_a, xerr = erro_b, label = 'curva com correção e erros', ecolor = 'yellow')
plt.plot(tempo, v_3, 'r-', label = 'curva considerando a aceração média')
plt.errorbar(tempo, velocidade, fmt ='o', yerr = erro, label = 'data', ecolor = 'black')
plt.xlabel('Tempo (s)')
plt.ylabel('Velocidade (m/s)')
plt.legend(loc='upper right')

plt.show()
fig1.canvas.manager.window.raise_()

