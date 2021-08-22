#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Jan 11 06:04:36 2020

@author: gabrielpacmil
"""

#resolver integrais utilizando scipy

import numpy as np
import scipy.integrate as sci

#integral de dx/(1+x²) de -1 até 1

print(sci.quad(lambda x:1/(1+x**2),-1,1))

#integral de dx/((exp(x)+x+1)² + pi²), -inf to inf

print(sci.quad(lambda x:1/((np.exp(x)+x+1)**2 + np.pi**2),-np.inf,np.inf))
