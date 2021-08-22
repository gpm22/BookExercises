#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jan  9 02:46:20 2020

@author: gabrielpacmil
"""

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jan  9 02:14:36 2020

@author: gabrielpacmil
"""

#solução do exercício 8.4 utilizando uma função pronta


import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as sco

# define fitting function
def fitFunc(t, a, b):
	return a + b*t

#r = K*t^p
#ln(r) = ln(K) + p*ln(t)

#y = ln(r) , x = ln(t), a = ln(K) e b = p

tempo, tamanho, erro = np.loadtxt('exercise_8_4.txt', skiprows = 5 , unpack = True)

y = np.log(tamanho)
x = np.log(tempo)
erro_1 = erro/tamanho


a0, b0 = 5.838175399356846 , 0.5444330235029855

nlfit, nlpcov = sco.curve_fit(fitFunc, x, y, p0=[a0, b0], sigma=erro_1)

a_1, b_1 = nlfit
erro_a, erro_b = [np.sqrt(nlpcov[j, j]) for j in range(nlfit.size)]

#b_1,a_1, erro_a, erro_b = lineFitWt(, y, erro_1)
#chi_red_1 = chi_r(tempo, y, a_1, b_1, erro_1)

T_1 = a_1 + b_1*x

chi_2 = (((y-T_1)/erro_1)**2).sum()
chi_red_1 = chi_2/(x.size-2)
    
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
