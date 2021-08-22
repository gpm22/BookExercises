#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jan 19 17:42:51 2020

@author: gabrielpacmil

Exercise 10.2 from the book Introduction to Python for Science
"""

import numpy as np
import pandas as pd

url1 = 'http://www.bankofcanada.ca/'
url2 = 'valet/observations/group/FX_RATES_DAILY/csv?start_date='
start_date = '2017-01-03'  # Earliest start date is 2017-01-03
#start_date = '2019-12-03'
url = url1 + url2 + start_date  # Complete url to download csv file

# Read in rates for different currencies for a range of dates
rates = pd.read_csv(url, skiprows=39, index_col='date')
rates.index = pd.to_datetime(rates.index)  # assures data type

# Get number of days & number of currences from shape of rates
days, currencies = rates.shape


#flutuações relativas ao dolar

a= np.zeros(len(rates.columns))
m= np.zeros(len(rates.columns))
s= np.zeros(len(rates.columns))

j=0
for i in rates.columns:
    flut = rates[i]/rates['FXUSDCAD']
    a[j] = flut.mean()
    m[j] = flut.max()
    s[j] = flut.std()    
    j = j +1

#Read in the currency codes & strip off extraneous part
codes = pd.read_csv(url, skiprows=10, usecols=[0, 2],nrows=currencies)
for i in range(currencies):
    codes.iloc[i, 1] = codes.iloc[i, 1].split(' to Canadian')[0]

#flutuacoes = pd.DataFrame([m/a,s/a, codes['description']],rates.columns, ['mx','sd','description'])

flutuacoes = pd.DataFrame({'mx':(m/a),'sd':(s/a),'description':codes['description']})

flutuacoes.index = rates.columns


print(flutuacoes.sort_values(by='mx', ascending = False))
