#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 02:45:07 2020

@author: gabrielpacmil
"""

#programa que simula o lançamento de n dados

import numpy as np
import matplotlib.pyplot as plt


lados_do_dado = 6
def dadinhos(N):
    lados_do_dado = 6
    dado = np.arange(N)
    for i in range(N):
        dado[i] = randint(1, lados_do_dado + 1) 
    #print(dado)
    return dado.sum()

roladas = 10000 #número de roladas
n_dados = 3 #número de dados
jogada = np.arange(roladas)


for i in range(roladas):
    jogada[i] = dadinhos(n_dados)

plt.hist(jogada,bins = (n_dados*lados_do_dado-n_dados), edgecolor = 'black', color = 'gray')
#n_dados*lados_do_dado-n_dados
#print(dadinhos(10))