#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jan 19 19:09:34 2020

@author: gabrielpacmil
"""

import pandas as pd
import matplotlib.pyplot as plt

clima = pd.read_csv('../exercise_10_4.csv', index_col = 'DATE')

clima.index = pd.to_datetime(clima.index)

clima.dropna()

#print(clima.loc['1924-07','TMAX':'TMIN']) #clima em uma certo mês

#gerar as médias para certos periodos

media_max = clima['TMAX'].rolling(30, center = True).mean()
media_min = clima['TMIN'].rolling(30, center = True).mean()
#d30 = rates[i].rolling(30).mean()

#plots boladinhos

fig_1, ax1 = plt.subplots()

media_max.plot(ax=ax1, color = 'red', label = 'TMAX')
media_min.plot(ax=ax1, color = 'blue', label = 'TMIN')

ax1.set_ylabel('Temperatura do ar (ºC)')

fig_1.show()
fig_1.canvas.manager.window.raise_()
