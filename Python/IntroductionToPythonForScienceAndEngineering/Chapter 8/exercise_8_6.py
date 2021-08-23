#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jan  9 02:59:01 2020

@author: gabrielpacmil
"""

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

#solução do exercício 8.6 utilizando dados experimentais para fazer o fitting de uma curva mucho loca


import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as sco

# define fitting function
def oscDecay(t, A, B, C, omega, tau):
	return A*(1+B*np.cos(omega*t))*np.exp((-(t/tau)**2)/2) + C

#d(t) = A(1+B*cos(w*t))exp(-t²/2*tau²) + C

tempo, sinal, erro = np.loadtxt('../exercise_8_6.txt', skiprows = 4 , unpack = True)

y = sinal
x = tempo
erro_1 = erro


A0, B0, C0, omega0, tau0  = 14.5, 0.6221, 18.31, 0.781 , 16.15

nlfit, nlpcov = sco.curve_fit(oscDecay, x, y, p0=[A0, B0, C0, omega0, tau0], sigma=erro_1)

A_1, B_1, C_1, omega1, tau1 = nlfit
erro_A, erro_B, erro_C, erro_omega, erro_tau  = [np.sqrt(nlpcov[j, j]) for j in range(nlfit.size)]

#b_1,a_1, erro_a, erro_b = lineFitWt(, y, erro_1)
#chi_red_1 = chi_r(tempo, y, a_1, b_1, erro_1)

d = A_1*(1+B_1*np.cos(omega1*x))*np.exp((-(x/tau1)**2)/2) + C_1

x_2 = np.linspace(x[0],x[-1],300)
d_2 = A_1*(1+B_1*np.cos(omega1*x_2))*np.exp((-(x_2/tau1)**2)/2) + C_1

chi_2 = (((y-d)/erro_1)**2).sum()
chi_red_1 = chi_2/(x.size-2)

print("\no" + r'$ {\chi} $' + "² é {}.".format(chi_red_1))
print("\nO valor de A é {}, o de B é {}, o de C é {}, o de omega é {} e o de tau é {}.".format(A_1, B_1, C_1, omega1, tau1))
print("\nO valor do erro associado a A é {}, o de B é {}, o de C é {}, o de omega é {} e o de tau é {}.".format(erro_A, erro_B, erro_C, erro_omega, erro_tau))

fig1 = plt.figure()
plt.plot(x_2, d_2, label = 'Curva fitting gostosona')
plt.errorbar(x, y, fmt ='oy', yerr = erro_1, label = 'Dados Experimentais', ecolor = 'black')
#plt.plot(x, d,'r-.' ,label = 'Curva fitting gostosona 2')
plt.xlabel('tempo (ms)')
plt.ylabel('decaimento (unidade arbitrária)')
plt.legend(loc='upper right')

plt.show()
fig1.canvas.manager.window.raise_()

#chi 1 = 0.9671549455044228.
#chi 2 = 0.9671549455311336
