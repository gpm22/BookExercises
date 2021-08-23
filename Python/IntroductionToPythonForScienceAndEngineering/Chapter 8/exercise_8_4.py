#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jan  9 02:35:09 2020

@author: gabrielpacmil
"""

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Jan  8 06:00:55 2020

@author: gabrielpacmil
"""

#Exercício 8.4 - fazer o fitting de uns certos dados para uma curva exponencial

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

#r = K*t^p
#ln(r) = ln(K) + p*ln(t)

#y = ln(r) , x = ln(t), a = ln(K) e b = p

tempo, tamanho, erro = np.loadtxt('../exercise_8_4.txt', skiprows = 5 , unpack = True)

y = np.log(tamanho)
x = np.log(tempo)
erro_1 = erro/tamanho

b_1,a_1, erro_a, erro_b = lineFitWt(x, y, erro_1)
chi_red_1 = chi_r(x, y, erro_1)


T_1 = a_1 + b_1*x

print("\no" + r'$ {\chi} $' + "² é {}.".format(chi_red_1))
print("\nO valor do ponto inicial é de {} e o valor da inclinação é {}.".format(a_1, b_1))

print("\nO erro associado ao ponto inicial é de {} e o associado a inclinação é {}.".format(erro_a,erro_b))

fig1 = plt.figure()
plt.plot(x, T_1, label = 'regressão linear')
plt.errorbar(x, y, fmt ='ok', yerr = erro_1, label = 'Dados Experimentais', ecolor = 'black')
plt.xlabel('ln(Tempo) (m)')
plt.ylabel('ln(Tamanho) (nm)')
plt.legend(loc='upper left')

plt.show()
fig1.canvas.manager.window.raise_()
