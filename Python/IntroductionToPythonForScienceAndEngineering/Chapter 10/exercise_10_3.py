#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jan 19 18:41:40 2020

@author: gabrielpacmil
"""

import pandas as pd
import matplotlib.pyplot as plt


url1 = 'http://www.bankofcanada.ca/'
url2 = 'valet/observations/group/FX_RATES_DAILY/csv?start_date='
start_date = '2016-01-03'  # Earliest start date is 2017-01-03
url = url1 + url2 + start_date  # Complete url to download csv file
# Read in rates for different currencies for a range of dates


rates = pd.read_csv(url, skiprows=39, index_col='date')
rates.index = pd.to_datetime(rates.index)  # assures data type

# Get number of days & number of currences from shape of rates
days, currencies = rates.shape

# Read in the currency codes & strip off extraneous part
codes = pd.read_csv(url, skiprows=10, usecols=[0, 2], nrows=currencies)

for i in range(currencies):
    codes.iloc[i, 1] = codes.iloc[i, 1].split(' to Canadian')[0]
    
codes.index = codes['id']

i = 'FXUSDCAD'

#cálculo das outras 2 curvas

d10 = rates[i].rolling(10).mean()
d30 = rates[i].rolling(30).mean()

#gráfico boladinho

fig_1, ax1 = plt.subplots()

rates[i].plot(ax = ax1, color = 'C0') #, label = codes['description']['FXUSDCAD'])
d10.plot(ax = ax1, color = 'red')
d30.plot(ax = ax1, color = 'green')

#ax1.legend(codes['description']['FXUSDCAD'])
ax1.set_title(codes['description'][i])
ax1.set_ylabel('Canadian dollar')

fig_1.show()
fig_1.canvas.manager.window.raise_() 

