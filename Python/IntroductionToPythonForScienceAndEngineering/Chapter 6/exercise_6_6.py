#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 31 17:27:48 2019

@author: gabrielpacmil
"""

#plot de dados lidos de um txt, calcular velocidade, aceleração e suas incertezas

import numpy as np
import matplotlib.pyplot as plt

tempo, posicao, erro = np.loadtxt('exercise_6_4.txt', skiprows = 1, usecols = (0,1,2), unpack = True)


velocidade = (posicao[:-1]-posicao[1:])/(tempo[:-1]-tempo[1:]) #velocidade

tempo_v = (tempo[:-1]+tempo[1:])/2 #tempo para a velocidade

aceleracao = (velocidade[:-1]-velocidade[1:])/(tempo_v[:-1]-tempo_v[1:]) #aceleração

tempo_a = (tempo_v[:-1]+tempo_v[1:])/2 #tempo para a aceleração

erro_velocidade =  (erro[:-1]-erro[1:])/(tempo[:-1]-tempo[1:]) #erro associdado a velocidade

erro_aceleracao =  (erro_velocidade[:-1]-erro_velocidade[1:])/(tempo_v[:-1]-tempo_v[1:]) #erro associdado a velocidade


fig, ax = plt.subplots(1,3, sharex = True)


#gráfico da posição x tempo
ax[0].errorbar(tempo, posicao, fmt ='-o', yerr = erro, label = 'data', ecolor = 'black')
ax[0].set_xlabel('tempo(s)')
ax[0].set_ylabel('posição(m)')

#gráfico da velocidade x tempo
ax[1].errorbar(tempo_v, velocidade, fmt ='-o', yerr = erro_velocidade, label = 'data', ecolor = 'black')
ax[1].set_xlabel('tempo(s)')
ax[1].set_ylabel('velocidade(m/s)')

#gráfico da aceleração x tempo
ax[2].errorbar(tempo_a, aceleracao, fmt ='-o', yerr = erro_aceleracao, label = 'data', ecolor = 'black')
ax[2].set_xlabel('tempo(s)')
ax[2].set_ylabel('aceleração(m/s²)')

fig.tight_layout()
fig.show()
fig.canvas.manager.window.raise_()
