#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jan 19 17:10:15 2020

@author: gabrielpacmil
"""

import numpy as np
import pandas as pd

planetas = pd.read_table('../planetData.txt', sep='\s+', index_col = 'planet')

#calcular a densidade de cada planeta relativo a densidade da terra
#densidade = massa/volume

# volume dos planetas

volume = np.pi*planetas['diameter']**3/6

planetas['densidade'] = (planetas['mass']/volume)/(planetas['mass']['Earth']/volume['Earth'])

#print((planetas.sort_values(by = ['diameter'], ascending=False)))

grandes = planetas[planetas['mass'] > 1].sort_values(by = 'mass')

print(grandes)
