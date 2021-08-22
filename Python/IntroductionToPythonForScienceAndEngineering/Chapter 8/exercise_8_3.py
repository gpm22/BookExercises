#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jan  9 02:14:36 2020

@author: gabrielpacmil
"""

#solução do exercício 8.2 utilizando uma função pronta


import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as sco

# define fitting function
def fitFunc(t, a, b):
	return a + b*t


def chi_r(x,y, slope, yint, uncert= np.array([0])):
    if uncert.sum() == 0:
        uncert = np.ones(x.size)
   
    #x_a = (x/(uncert**2)).sum()/(1/(uncert**2) ).sum() #x chápeu
    #y_a = (y/(uncert**2)).sum()/(1/(uncert**2) ).sum() #y chápeu
    #slope = ((y*(x-x_a))/(uncert**2)).sum()/((x*(x-x_a))/(uncert**2)).sum()
    #yint = y_a-slope*x_a
    
    chi_2 = (((y-yint-slope*x)/uncert)**2).sum()
    chi_red = chi_2/(x.size-2)

    return chi_red

#V(t) = Vo*expo(-gamma*t)
#ln(V(t)) = ln(Vo) - gamma*t

#y = ln(V(t)) , a = ln(Vo) e b = -gamma

tempo, tensao, erro = np.loadtxt('exercise_8_2.txt', skiprows = 6 , unpack = True)

y = np.log(tensao)
erro_1 = erro/tensao

a0, b0 = 1.609840818650069, -0.01213992580850636

nlfit, nlpcov = sco.curve_fit(fitFunc, tempo, y, p0=[a0, b0], sigma=erro_1)

a_1, b_1 = nlfit
erro_a, erro_b = [np.sqrt(nlpcov[j, j]) for j in range(nlfit.size)]

#b_1,a_1, erro_a, erro_b = lineFitWt(, y, erro_1)
#chi_red_1 = chi_r(tempo, y, a_1, b_1, erro_1)

v_1 = a_1 + b_1*tempo

chi_2 = (((y-v_1)/erro_1)**2).sum()
chi_red_1 = chi_2/(tempo.size-2)
    

print("\no" + r'$ {\chi} $' + "² é {}.".format(chi_red_1))
print("\nO erro associado ao ponto inicial é de {} e o associado a inclinação é {}.".format(erro_a,erro_b))

fig2 = plt.figure()
plt.plot(tempo, v_1, label = 'regressão linear')
plt.errorbar(tempo, y, fmt ='o', yerr = erro_1, label = 'Dados Experimentais', ecolor = 'black')
plt.xlabel('Tempo (ns)')
plt.ylabel('ln(Tensão) (V)')
plt.legend(loc='upper right')

plt.show()
fig2.canvas.manager.window.raise_()
