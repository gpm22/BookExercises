#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 31 06:57:46 2019

@author: gabrielpacmil
"""
#fazer um histograma de valores aleatórios

import numpy as np
import matplotlib.pyplot as plt

fig1 = plt.figure()
a = 'stepfilled'
plt.hist(np.random.rand(10000), bins=50, color = 'lightgrey', histtype = a)

fig2 = plt.figure()

plt.hist(np.random.randn(10000), bins = 40, color = 'gray', histtype = a)

#np.random.rand
#np.random.randn

#'bar', 'barstacked', 'step',  'stepfilled'}